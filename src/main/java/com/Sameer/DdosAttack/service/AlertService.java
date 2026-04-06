package com.Sameer.DdosAttack.service;

import com.Sameer.DdosAttack.model.User;
import com.Sameer.DdosAttack.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private userRepo repo;
    public void sendAlert(String message) {
        List<User> users = repo.findAll();
        if (users.isEmpty()) {
            System.out.println("⚠️ No users found to send alert");
            return;
        }
        for (User user : users) {
            String email = user.getEmail();
            if (email == null || email.isEmpty()) {
                continue;
            }

            try {
                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setFrom("sameermd5090@gmail.com");
                mail.setTo(email);
                mail.setSubject("🚨 DDoS Attack Detected");

                mail.setText("Attack Details:\n" + message + "\n\nTime: " + java.time.LocalDateTime.now());
                mailSender.send(mail);
                System.out.println("✅ Email alert sent!");

            } catch (Exception e) {
                System.err.println("❌ Failed to send email: " + email);
            }
        }


    }
}
