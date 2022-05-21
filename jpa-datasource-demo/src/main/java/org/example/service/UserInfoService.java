package org.example.service;


import org.example.entity.UserInfo;

public interface UserInfoService {
	/**
	 * 根据UserId产生的一些业务计算逻辑
	 *
	 * @return
	 */
	UserInfo calculate(Long userId);
}