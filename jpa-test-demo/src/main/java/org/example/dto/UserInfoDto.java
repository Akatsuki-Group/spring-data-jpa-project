package org.example.dto;

import lombok.Builder;
import lombok.Data;
import org.example.entity.Address;

import java.util.List;

@Data
@Builder
public class UserInfoDto {
    private String name;
    private Long id;
    private List<Address> addressList;
}