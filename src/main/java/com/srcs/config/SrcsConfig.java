package com.srcs.config;

import freemarker.cache.ClassTemplateLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

import static freemarker.template.Configuration.VERSION_2_3_0;

/**
 * Created by santhosh on 23/07/16.
 */
@Configuration
public class SrcsConfig {

    @Value("${gmail.authentication.username}")
    private String username;
    @Value("${gmail.authentication.password}")
    private String password;

    @Bean
    public freemarker.template.Configuration freemarkerConfiguration() {
        ClassTemplateLoader classPathLoader = new ClassTemplateLoader(this.getClass().getClassLoader(), "ftl");

        freemarker.template.Configuration configuration = new freemarker.template.Configuration(VERSION_2_3_0);
        configuration.setTemplateLoader(classPathLoader);

        return configuration;
    }

    @Bean
    public Session mailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        return session;
    }
}
