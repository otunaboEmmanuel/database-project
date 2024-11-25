package com.example.database_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {
    private String email;
    private String password;
    private String userName;
    private Integer prouctId;
    private String initiatorUserId;
    private String userId;
    private String storeName;
    private String cartId;

}
