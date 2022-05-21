package org.example;

import org.example.dto.UserDto;
import org.example.dto.UserOnlyName;
import org.example.dto.UserSimpleDto;
import org.example.entity.User;
import org.example.entity.UserExtend;
import org.example.repository.UserDtoRepository;
import org.example.repository.UserExtendRespoitory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryQueryTest {
    @Autowired
    private UserDtoRepository userDtoRepository;

    @Autowired
    private UserExtendRespoitory userExtendRepository;

    @Test
    public void testQueryAnnotation() {
        //新增一条数据方便测试
        userDtoRepository.save(User.builder().name("jack").email("123456@126.com").sex("man").address("shanghai").build());
        //调用上面的方法查看结果
        User user2 = userDtoRepository.findByQuery("jack");
        System.out.println(user2);
    }

    @Test
    public void testQueryAnnotationDto() {
        userDtoRepository.save(User.builder().name("jack").email("123456@126.com").sex("man").address("shanghai").build());
        userExtendRepository.save(UserExtend.builder().userId(1L).idCard("shengfengzhenghao").ages(18).studentNumber("xuehao001").build());
        UserDto userDto = userDtoRepository.findByUserDtoId(1L);
        System.out.println(userDto);
    }

    @Test
    public void testQueryAnnotationDto1() {
        userDtoRepository.save(User.builder().name("jack").email("123456@126.com").sex("man").address("shanghai").build());
        userExtendRepository.save(UserExtend.builder().userId(1L).idCard("shengfengzhenghao").ages(18).studentNumber("xuehao001").build());
        UserSimpleDto userDto = userDtoRepository.findByUserSimpleDtoId(1L);
        System.out.println(userDto);
        System.out.println(userDto.getName() + ":" + userDto.getEmail() + ":" + userDto.getIdCard());
    }

    @Test
    public void testQueryDinamicDto() {
        userDtoRepository.save(User.builder().name("jack").email("123456@126.com").sex("man").address("shanghai").build());
        UserOnlyName userDto = userDtoRepository.findByUser("jack", null);
        System.out.println(userDto.getName() + ":" + userDto.getEmail());
        UserOnlyName userDto2 = userDtoRepository.findByUser(User.builder().email("123456@126.com").build());
        System.out.println(userDto2.getName() + ":" + userDto2.getEmail());
    }
}