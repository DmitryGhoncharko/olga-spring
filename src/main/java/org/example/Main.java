package org.example;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class Main {
    @Autowired
    ApplicationContext applicationContext;
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Configuration.class);
//        Quoter quoter = (Quoter) applicationContext.getBean("getQuoter");
//        quoter.sayQuote();
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("context.xml");
//        Quoter quoter = (Quoter) classPathXmlApplicationContext.getBean("terminatorQuoter");
//        quoter.sayQuote();
        Main main = new Main();
        Quoter quoter = (Quoter) main.applicationContext.getBean("terminatorQuoter");
        quoter.sayQuote();
    }
}