package com.Sameer.DdosAttack.controller;

import com.Sameer.DdosAttack.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/alert")
@CrossOrigin
public class AlertController {
    @Autowired
    private AlertService alertService;

    @PostMapping("/ddos")
    public String sendAlert(@RequestBody Map<String,String> body){
        String message=body.get("message");
        alertService.sendAlert(message);
        return "email Sent successfully";
    }
}
