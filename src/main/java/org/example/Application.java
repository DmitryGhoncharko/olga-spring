package org.example;

import org.example.entity.Contact;
import org.example.util.ContactSaverToFileUtil;
import org.example.util.ContactsUploaderFromFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Component
public class Application {

    private final ContactSaverToFileUtil contactSaverToFileUtil;

    private final ContactsUploaderFromFileUtil contactsUploaderFromFileUtil;
    @Autowired
    public Application(ContactSaverToFileUtil contactSaverToFileUtil, ContactsUploaderFromFileUtil contactsUploaderFromFileUtil) {
        this.contactSaverToFileUtil = contactSaverToFileUtil;
        this.contactsUploaderFromFileUtil = contactsUploaderFromFileUtil;
    }

    private boolean appIsEnabled = true;
    public void start(){
        while (appIsEnabled){
            System.out.println("Выберите операцию для работы с контактами: " + "\n" + "1 - Получить все контакты" + "\n" + "2- Добавить контакт" + "\n" + "3- Удалить контакт" + "\n" + "4 - Выйти");
            int inputData = readUserInputFromConsole();
            switch (inputData){
                case 1 : {
                    showAllContacts();
                    break;
                }
                case 2 : {
                    addContact();
                    break;
                }
                case 3: {
                    deleteContact();
                    break;
                }
                case 4 : {
                    return;
                }
                default:{
                    System.out.println("Введены неверные данные попробуйте еще раз!");
                }
            }
        }
    }

    private void showAllContacts() {
        System.out.println("Имеющиеся конаткты: ");
        contactsUploaderFromFileUtil.uploadContactsFromFile().forEach(contact-> {
            System.out.println("ФИО - " + contact.getFullName());
            System.out.println("Номер телефона -" + contact.getPhoneNumber());
            System.out.println("Почта - " + contact.getEmail());
        });
    }

    private int readUserInputFromConsole(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int userInput = Integer.parseInt(bufferedReader.readLine());
            return userInput;
        } catch (IOException e) {
            System.out.println("Ошибка чтения с консоли !");
        }catch (Throwable e){
            System.out.println("Ошибка чтения с консоли !");
        }
        return -1;
    }
    private void addContact(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите ФИО ");
            String fullName = bufferedReader.readLine();
            System.out.println("Введите номер телефона ");
            String phoneNumber = bufferedReader.readLine();
            System.out.println("Введите почту ");
            String email = bufferedReader.readLine();
            List<Contact> contactList = contactsUploaderFromFileUtil.uploadContactsFromFile();
            boolean contactIsPresent  = contactList.stream().anyMatch(contact -> contact.getFullName().equals(fullName) && contact.getPhoneNumber().equals(phoneNumber) && contact.getEmail().equals(email));
            if(contactIsPresent){
                System.out.println("Такой контакт уже сохранен");
            }else {
                contactList.add(new Contact(fullName,phoneNumber,email));
                contactSaverToFileUtil.saveDataToFile(contactList);
                System.out.println("Контакт успешно добавлен");
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения с консоли !");
        }
    }
    private void deleteContact(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите почту ");
            String email = bufferedReader.readLine();
            List<Contact> contactList = contactsUploaderFromFileUtil.uploadContactsFromFile();
            contactList.removeIf(contact -> contact.getEmail().equals(email));
            contactSaverToFileUtil.saveDataToFile(contactList);
            System.out.println("Контакт успешно удален !");
        } catch (IOException e) {
            System.out.println("Ошибка чтения с консоли !");
        }
    }
}
