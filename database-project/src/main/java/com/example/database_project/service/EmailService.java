package com.example.database_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {


        @Autowired
        private JavaMailSender mailSender;

        public void sendSimpleMessage(MailBody mailBody) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setTo(mailBody.getTo());
                helper.setSubject(mailBody.getSubject());
                helper.setText(mailBody.getText(), true); // true for HTML content
                //helper.addAttachment();
                mailSender.send(message);
            } catch (MessagingException e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }
        }


}
