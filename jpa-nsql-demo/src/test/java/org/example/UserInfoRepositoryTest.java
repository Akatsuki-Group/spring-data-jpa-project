package org.example;

import org.example.entity.Address;
import org.example.entity.UserInfo;
import org.example.repository.AddressRepository;
import org.example.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserInfoRepositoryTest {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private AddressRepository addressRepository;


    @BeforeAll
    @Rollback(false)
    @Transactional
    public void init() {

        UserInfo u1 = UserInfo.builder().name("smise1").ages(10).telephone("13232424").build();
        UserInfo u2 = UserInfo.builder().name("smise2").ages(10).telephone("13232424").build();
        UserInfo u3 = UserInfo.builder().name("smise3").ages(10).telephone("13232424").build();
        UserInfo save1 = userInfoRepository.save(u1);
        UserInfo save2 = userInfoRepository.save(u2);
        UserInfo save3 = userInfoRepository.save(u3);

        Address address1 = Address.builder().city("chengdu").userInfo(save1).build();
        addressRepository.save(address1);
        Address address2 = Address.builder().city("chongqin").userInfo(save1).build();
        addressRepository.save(address2);
        Address address3 = Address.builder().city("chengdu").userInfo(save2).build();
        addressRepository.save(address3);
        Address address4 = Address.builder().city("chongqin").userInfo(save2).build();
        addressRepository.save(address4);
        Address address5 = Address.builder().city("chengdu").userInfo(save3).build();
        addressRepository.save(address5);
        Address address6 = Address.builder().city("chongqin").userInfo(save3).build();
        addressRepository.save(address6);
    }

    @Test
    public void testFindAll(){
        List<UserInfo> all = userInfoRepository.findAll();
        System.out.println("###########################");
    }

    @Test
    public void testFindAllAddress(){
        List<Address> all = addressRepository.findAll();
        System.out.println("###########################");
    }


}
