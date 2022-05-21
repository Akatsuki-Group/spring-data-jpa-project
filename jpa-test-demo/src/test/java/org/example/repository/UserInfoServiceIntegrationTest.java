package org.example.repository;


import org.example.dto.UserInfoDto;
import org.example.entity.UserInfo;
import org.example.service.UserInfoService;
import org.example.service.UserInfoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

//@ExtendWith(SpringExtension.class)
@DataJpaTest
@ComponentScan(basePackageClasses= UserInfoServiceImpl.class)
public class UserInfoServiceIntegrationTest {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Test
    @Rollback(false)//如果我们事务回滚设置成false的话，数据库可以真实看到这条数据
    public void testIntegtation() {
        UserInfo u1 = UserInfo.builder().name("jack-db").ages(20).id(1L).telephone("1233456").build();
        //数据库真实加一条数据
        userInfoRepository.save(u1);//数据库里面真实保存一条数据

        UserInfoDto userInfoDto =  userInfoService.findByUserId(1L);
        userInfoDto.getName();
        Assertions.assertEquals(userInfoDto.getName(),u1.getName()+"_HELLO");
    }
}
