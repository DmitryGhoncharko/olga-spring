package org.example.util;

import org.example.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContactsUploaderFromFileUtil {
    @Autowired
    private Environment environment;
    private String pathToUpload;

    @PostConstruct
    public void postConstruct() {
        pathToUpload = environment.getProperty("contacts.path.download");
    }

    public List<Contact> uploadContactsFromFile() {
        List<Contact> contacts = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToUpload));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] data = line.split(";");
                contacts.add(new Contact(data[0], data[1], data[2]));
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл, проверьте правильность пути !");
        } catch (IOException e) {
            System.out.println("Не удалось считать файл ошибка во время чтения файла !");
        }
        return contacts;
    }
}
