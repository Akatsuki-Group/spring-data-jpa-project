package org.example.service;

import org.example.dto.UserInfoDto;

public interface UserInfoService {
    UserInfoDto findByUserId(Long userId);
}