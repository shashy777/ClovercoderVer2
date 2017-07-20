package com.clove.clovewerable.mail;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author @Unascribed
 */
@Configuration
@ComponentScan(basePackages = "com.clove")
public class MailConfig {

    //Put Other Application configuration here.
    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        //Using gmail
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("<your-email-id>");
        mailSender.setPassword("<your-password>");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "false");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "false");

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}
