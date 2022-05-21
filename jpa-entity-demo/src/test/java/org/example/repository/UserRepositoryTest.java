package org.example.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.RedBook;
import org.example.entity.User;
import org.example.entity.UserInfo;
import org.example.reposiyory.RedBookRepository;
import org.example.reposiyory.UserInfoRepositoy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserInfoRepositoy userInfoRepository;

    @Test
    public void testIdClass() {
/*        userInfoRepository.save(UserInfo.builder().ages(1).name("jack").telephone("123456789").build());
        Optional<UserInfo> userInfo = userInfoRepository.findById(UserInfoID.builder().name("jack").telephone("123456789").build());
        System.out.println(userInfo.get());*/
    }

    @Test
    public void testIdClass1() {
//        userInfoRepository.save(UserInfo.builder().ages(1).userInfoID(UserInfoID.builder().name("jack").telephone("123456789").build()).build());
//        Optional<UserInfo> userInfo = userInfoRepository.findById(UserInfoID.builder().name("jack").telephone("123456789").build());
//        System.out.println(userInfo.get());
    }

    @Autowired
    private RedBookRepository redBookRepository;

    @Test
    public void testRedBook() {
        RedBook redBook = new RedBook();
        redBook.setTitle("redbook");
        redBook.setRedMark("redmark");
        redBook.setId(1L);
        redBookRepository.saveAndFlush(redBook);
        RedBook r = redBookRepository.findById(1L).get();
        System.out.println(r.getId() + ":" + r.getTitle() + ":" + r.getRedMark());
    }

    @Test
    @Rollback(false)
    public void testUserRelationships() throws JsonProcessingException {
        User user = User.builder().name("jackxx").email("123456@126.com").build();
        UserInfo userInfo = UserInfo.builder().ages(12).user(user).telephone("12345678").build();
        //保存userInfo的同上也会保存User信息
        userInfoRepository.saveAndFlush(userInfo);
        //删除userInfo，同时也会级联的删除user记录
        userInfoRepository.delete(userInfo);
    }

    @Test
    public void testUserRelationships1() throws JsonProcessingException {
        User user = User.builder().name("jackxx").email("123456@126.com").build();
        UserInfo userInfo = UserInfo.builder().ages(12).user(user).telephone("12345678").build();
        userInfoRepository.saveAndFlush(userInfo);
        userInfo.setAges(13);
        userInfo.setUser(null);//还是通过这个设置user数据为空
        userInfoRepository.delete(userInfo);
    }

    /**
     * 测试用User关联关系操作
     *
     * @throws JsonProcessingException
     */
    @Test
    @Rollback(false)
    public void testUserRelationships2() throws JsonProcessingException {
        UserInfo userInfo1 = userInfoRepository.getOne(1L);
        System.out.println(userInfo1);
        System.out.println(userInfo1.getUser().getId());
    }
}