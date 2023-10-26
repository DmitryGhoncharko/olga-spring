package org.example.configuration;

import org.example.Application;
import org.example.util.ContactSaverToFileUtil;
import org.example.util.ContactsUploaderFromFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("application.properties")
@ComponentScan()
@Profile(value = "init")
public class InitConfiguration {
    @Bean
    Application application(){
        return new Application(contactSaverToFileUtil(),contactsUploaderFromFileUtil());
    }

    @Bean
    ContactSaverToFileUtil contactSaverToFileUtil(){
        return new ContactSaverToFileUtil();
    }
    @Bean
    ContactsUploaderFromFileUtil contactsUploaderFromFileUtil(){
        return new ContactsUploaderFromFileUtil();
    }
}
