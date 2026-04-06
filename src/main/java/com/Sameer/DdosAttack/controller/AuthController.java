package com.Sameer.DdosAttack.controller;

import com.Sameer.DdosAttack.dto.loginRqst;
import com.Sameer.DdosAttack.dto.signUpRqst;
import com.Sameer.DdosAttack.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public Map<String,Object> signup(@RequestBody signUpRqst sign){
        return authService.signup(sign);
    }
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody loginRqst rqst){
        return authService.login(rqst);
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Backend is running");
    }
}
