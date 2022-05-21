package org.example.repository;


import org.example.dto.UserInfoDto;
import org.example.entity.UserInfo;
import org.example.service.UserInfoService;
import org.example.service.UserInfoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(UserInfoServiceImpl.class)
public class UserInfoServiceTest {
    @Autowired
    private UserInfoService userInfoService;
    @MockBean
    private UserInfoRepository userInfoRepository;

    // 利用单元测试的思想，mock userInfoService里面的UserInfoRepository，这样Service层就不用连接数据库，就可以测试自己的业务逻辑了
    @Test
    public void testGetUserInfoDto() {
        Mockito.when(userInfoRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(UserInfo.builder().name("jack").id(1L).build()));

        UserInfoDto userInfoDto = userInfoService.findByUserId(1L);
        //经过一些service里面的逻辑计算，我们验证一下返回结果是否正确
        Assertions.assertEquals("jack_HELLO",userInfoDto.getName());
    }
}
