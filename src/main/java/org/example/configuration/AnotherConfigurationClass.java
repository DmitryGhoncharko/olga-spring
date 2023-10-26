package org.example.configuration;

import org.example.Application;
import org.example.util.ContactSaverToFileUtil;
import org.example.util.ContactsUploaderFromFileUtil;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("application.properties")
@ComponentScan()
@Profile(value = "another")
public class AnotherConfigurationClass {
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
