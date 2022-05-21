package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.example.entity.UserInfo;
import org.example.help.TransactionHelper;
import org.example.repository.UserInfoRepository;
import org.example.service.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;


@Slf4j
@RestController
public class UserInfoController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Autowired
    private TransactionHelper transactionHelper;


    //异步操作必须要建立线程池，这个不多说了，因为不是本讲的重点，有兴趣的话你可以了解一下线程池的原理，我的Demo采用的是Spring异步框架字段的异步线程池
    @Autowired
    private Executor executor;

    /**
     * 模拟一个业务service方法，里面有一些异步操作，一些业务方法里面可能修改了两次用户信息
     *
     * @param name
     * @return
     */
    @PostMapping("test/async/user1")
    @Transactional // 模拟一个service方法，期待是一个事务
    public String testSaveUser1(String name) {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            UserInfo user = userInfoRepository.findById(1L).get();
            //..... 此处模拟一些业务操作，第一次改变UserInfo里面的值；
            try {
                Thread.sleep(200L);// 加上复杂业务耗时200毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            user.setLastName(RandomUtils.nextInt(1, 100000) + "_first" + name); //模拟一些业务操作，改变了UserInfo里面的值
            userInfoRepository.save(user);
            //..... 此处模拟一些业务操作，第二次改变UserInfo里面的值；
            try {
                Thread.sleep(300L);// 加上复杂业务耗时300毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            user.setLastName(RandomUtils.nextInt(1, 100000) + "_second" + name);//模拟一些业务操作，改变了UserInfo里面的值
            userInfoRepository.save(user);
        }, executor).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
        //... 实际业务中，可能还有会其他异步方法，我们举这一个例子已经可以说明问题了
        cf.isDone();
        return "Success";
    }

    /**
     * 模拟一个业务service方法，里面有一些异步操作，一些业务方法里面可能修改了两次用户信息
     *
     * @param name
     * @return
     */
    @PostMapping("test/async/user")
//	@Transactional // 模拟一个service方法期待是一个事务
    public String testSaveUser(String name) {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            userInfoService.businessUserMethod(name);
        }, executor).exceptionally(e -> {
            log.error(e.getMessage(), e);//把异常信息打印出来
            return null;
        });

        //... 实际业务中，可能还有会其他异步方法，我们举例一个已经可以说明问题了
        cf.isDone();
        return "Success";
    }


    @PostMapping("test/async/user2")
    public String testSaveUser2(String name) {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            transactionHelper.transactional((param)->{ // 通过lambda实现事务管理
                UserInfo user = userInfoRepository.findById(1L).get();
                //..... 此处模拟一些业务操作，第一次改变UserInfo里面的值；
                try {
                    Thread.sleep(200L);// 加上复杂业务耗时200毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                user.setLastName(RandomUtils.nextInt(1,100000)+ "_first"+name); //模拟一些业务操作，改变了UserInfo里面的值
                userInfoRepository.save(user);
                //..... 此处模拟一些业务操作，第二次改变UserInfo里面的值；
                try {
                    Thread.sleep(300L);// 加上复杂业务耗时300毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                user.setLastName(RandomUtils.nextInt(1,100000)+ "_second"+name);//模拟一些业务操作，改变了UserInfo里面的值
                userInfoRepository.save(user);
            },name);
        }, executor).exceptionally(e -> {
            log.error(e.getMessage(),e);//把异常信息打印出来
            return null;
        });
        //... 实际业务中，可能还有会其他异步方法，我们举一个例子已经可以说明问题了
        cf.isDone();
        return "Success";
    }
}