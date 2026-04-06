package com.Sameer.DdosAttack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class signUpRqst {
    private String username;
    private String password;
    private String email;
    private String mobile;
}