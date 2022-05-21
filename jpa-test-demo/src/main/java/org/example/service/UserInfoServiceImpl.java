package org.example.service;


import org.example.dto.UserInfoDto;
import org.example.entity.UserInfo;
import org.example.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoRepository userInfoRepository;

	//假设有个 findByUserId的方法经过一些业务逻辑计算返回了一个业务对象UserInfoDto
	@Override
	public UserInfoDto findByUserId(Long userId) {
		UserInfo userInfo = userInfoRepository.findById(userId).orElse(new UserInfo());
		//模拟一些业务计算改变一下name的值返回
		UserInfoDto userInfoDto = UserInfoDto.builder().name(userInfo.getName()+"_HELLO").id(userInfo.getId()).build();
		return userInfoDto;
	}
}