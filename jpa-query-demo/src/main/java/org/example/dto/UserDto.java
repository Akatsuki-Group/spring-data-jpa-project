package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
/**
 * @author MSI-NB
 */
@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private String name,email,idCard;
}