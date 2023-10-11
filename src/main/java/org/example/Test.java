package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Test {
    @Autowired
    ApplicationContext applicationContext;
    public void start(){
        Quoter quoter = (Quoter) applicationContext.getBean("terminatorQuoter");
        quoter.sayQuote();
    }
}
