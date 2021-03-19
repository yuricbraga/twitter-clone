package com.cancela.twitterclone.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;

    @Async
    void sendMail(String recipient, String subject, String body){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("twitter-clone@no-reply.com");
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(body);
        };
        try{
            mailSender.send(messagePreparator);
            log.info("Activation mail sent!!");
        } catch(MailException e){
            log.error("Exception occurred when sending mail", e);
            //throw new SpringRedditException("Exception occurred when sending mail to " + recipient, e);
        }
    }
}
