package org.example.service;

import org.apache.commons.lang3.RandomUtils;
import org.example.entity.UserInfo;
import org.example.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 根据UserId产生的一些业务计算逻辑
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, backoff = @Backoff(multiplier = 1.5, random = true))
    public UserInfo calculate(Long userId) {
//		//不带悲观锁的方法
//		UserInfo userInfo = userInfoRepository.getOne(userId);
        UserInfo userInfo = userInfoRepository.findById(userId).get();
        try {
            //模拟复杂的业务计算逻辑耗时操作；
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userInfo.setAges(userInfo.getAges() + 1);
        userInfo.setTelephone(Instant.now().toString());
        return userInfoRepository.saveAndFlush(userInfo);
    }


    /**
     * 加上事务，这样可以做到原子性，解决事务加到异常方法之外没有任何作用的问题
     * 加上重试机制，这样当我们发生乐观锁异常的时候，重新尝试下面的逻辑，减少请求的失败次数
     *
     * @param name
     */
    @Transactional(rollbackFor = Exception.class)
    @Retryable(value = ObjectOptimisticLockingFailureException.class, backoff = @Backoff(multiplier = 1.5, random = true))
    public void businessUserMethod(String name) {
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
    }
}