package org.example.util;

import org.example.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class ContactSaverToFileUtil {
    @Autowired
    private Environment environment;
    private String pathToSave;

    @PostConstruct
    public void postConstruct() {
        pathToSave = environment.getProperty("contacts.path.download");
    }

    public void saveDataToFile(List<Contact> contacts) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToSave));
            for (int i = 0; i < contacts.size(); i++) {
                if (i == contacts.size() - 1) {
                    bufferedWriter.write(contacts.get(i).getFullName() + ";" + contacts.get(i).getPhoneNumber() + ";" + contacts.get(i).getEmail());
                } else {
                    bufferedWriter.write(contacts.get(i).getFullName() + ";" + contacts.get(i).getPhoneNumber() + ";" + contacts.get(i).getEmail() + "\n");
                }
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Не удалось найти файл, проверьте правильность пути !");
        }
    }
}
