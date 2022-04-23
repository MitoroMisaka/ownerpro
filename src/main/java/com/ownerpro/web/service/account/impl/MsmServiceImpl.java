package com.ownerpro.web.service.account.impl;

import com.ownerpro.web.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MsmServiceImpl implements MsmService {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Override
    public void send(String email, int code) {
        // TODO Auto-generated method stub
        String s = String.valueOf(code);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Thesis论文管理系统验证码");
        message.setText("您的验证码为：" + s);
        message.setFrom("Liaojinchuan@126.com");
        message.setTo(email);
        mailSender.send(message);
    }


}
