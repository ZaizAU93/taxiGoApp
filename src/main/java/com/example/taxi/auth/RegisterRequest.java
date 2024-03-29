package com.example.taxi.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String password;
    private String username;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private String imgUrl;
}
