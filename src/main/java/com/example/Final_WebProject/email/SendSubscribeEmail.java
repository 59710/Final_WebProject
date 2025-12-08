package com.example.Final_WebProject.email;

import com.example.Final_WebProject.entity.FoodData;
import com.example.Final_WebProject.entity.UserData;
import com.example.Final_WebProject.service.FoodService;
import com.example.Final_WebProject.service.UserService;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;



@Service
public class SendSubscribeEmail {
    @Autowired
    private JavaMailSender mailSender;

    private HttpServletRequest request;
    @Resource
    private FoodService foodService;
    @Resource
    private UserService userService;
    @Value("${spring.mail.username}")
    private String from;
    static final Logger logger = LogManager.getLogger(SendSubscribeEmail.class);

    public void sendEmail(UserData userData, FoodData foodData) {
        try {
            logger.info("开始发送邮件...");
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(from);
            messageHelper.setTo(userData.getEmail());
            messageHelper.setSubject("预定成功!!!");
            messageHelper.setText(userData.getAddress()+"的"+userData.getUsername()+"成功预定了"+foodData.getName());
            this.mailSender.send(mimeMessage);
            logger.info("{}预定成功!!!", userData.getUsername());
        }
        catch (Exception ex) {
            logger.error("{}预定失败!qwq!", userData.getUsername());
        }
    }


}
