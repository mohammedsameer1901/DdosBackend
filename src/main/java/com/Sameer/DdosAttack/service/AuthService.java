package com.Sameer.DdosAttack.service;

import com.Sameer.DdosAttack.dto.loginRqst;
import com.Sameer.DdosAttack.dto.signUpRqst;
import com.Sameer.DdosAttack.model.User;
import com.Sameer.DdosAttack.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService{
    @Autowired
    private userRepo repo;
    public Map<String, Object> signup(signUpRqst rqst) {
        Map<String,Object> response=new HashMap<>();
        if(repo.existsByUsername(rqst.getUsername())){
            response.put("success",false);
            response.put("message","Username already exists");
            return response;
        }
        if(repo.existsByEmail(rqst.getEmail())) {
            response.put("success", false);
            response.put("message", "Email already registered");
            return response;
        }
        User user=new User();
        user.setUsername(rqst.getUsername());
        user.setPassword(rqst.getPassword());
        user.setEmail(rqst.getEmail());
        user.setMobile(rqst.getMobile());
        user.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        repo.save(user);
        response.put("success", true);
        response.put("message", "Signup successful");
        response.put("user", user);
        return response;
    }

    public Map<String, Object> login(loginRqst rqst) {
        User user= repo.findByUsername(rqst.getUsername());
        Map<String,Object> response=new HashMap<>();
        if(user==null){
            response.put("success",false);
            response.put("message","User Not Found");
            return response;
        }
        if(user.getPassword().equals(rqst.getPassword())){
            response.put("success",true);
            response.put("message","Login Successful");
            response.put("user",user);
        }else {
            response.put("success", false);
            response.put("message", "Invalid password");
        }
        return response;
    }
}
