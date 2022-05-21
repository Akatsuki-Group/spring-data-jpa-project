package org.example.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.util.Lists;
import org.example.entity.UserAddressEntity;
import org.example.entity.UserEntity;
import org.example.reposiyory.UserAddressRepository;
import org.example.reposiyory.UserEntityRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserAddressRepositoryTest {
   @Autowired
   private UserAddressRepository userAddressRepository;
   @Autowired
   private UserEntityRepository userRepository;
   /**
    * 负责添加数据
    */
   @BeforeAll
   @Rollback(false)
   @Transactional
   void init() {
      UserEntity user = UserEntity.builder().name("jackxx").email("123456@126.com").build();
      UserAddressEntity userAddress = UserAddressEntity.builder().address("shanghai1").user(user).build();
      UserAddressEntity userAddress2 = UserAddressEntity.builder().address("shanghai2").user(user).build();
      userAddressRepository.saveAll(Lists.newArrayList(userAddress,userAddress2));
   }
   /**
    * 测试用User关联关系操作
    * @throws JsonProcessingException
    */
   @Test
   @Rollback(false)
   public void testUserRelationships() throws JsonProcessingException {
      UserEntity user = userRepository.getOne(2L);
      System.out.println(user.getName());
      System.out.println(user.getAddress());
   }
}