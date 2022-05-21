package org.example;

import org.example.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @author yct
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserInfoRepository userRepository;

    private Long id1;
    private Long id2;

//    @BeforeAll
//    public void init() {
//        UserInfo userInfo = UserInfo.builder()
//                .ages(10)
//                .lastName("zhanf")
//                .emailAddress("sasd")
//                .telephone("234325243")
//                .name("dsd")
//                .build();
//
//        UserInfo userInfo1 = UserInfo.builder()
//                .ages(10)
//                .lastName("zhanf")
//                .emailAddress("sasd")
//                .telephone("234325243")
//                .name("dsd")
//                .build();
//        id1 = userRepository.save(userInfo).getId();
//        id2 = userRepository.save(userInfo1).getId();
//    }

    @Test
    public void test() {
        userRepository.findById(3L);

        System.out.println("select");
        userRepository.findById(3L);

    }


}
