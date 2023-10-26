package org.example;


import org.example.configuration.AnotherConfigurationClass;
import org.example.configuration.InitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

@Component
public class Main {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("application.properties");
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            System.out.println("Ошибка чтения проперти файла");
        }
        String activeProfile = properties.getProperty("spring.profiles.active");
        System.setProperty("spring.profiles.active", activeProfile);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InitConfiguration.class, AnotherConfigurationClass.class);
        applicationContext.getBean("application", Application.class).start();
    }
}