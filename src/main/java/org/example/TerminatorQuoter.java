package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Profiling
@Component
@Primary
public class TerminatorQuoter implements Quoter{
    @InjectRandomInt(min=2, max=7)
    private int repeat;
    @Value(value = "Terminator value")
    private String message;

    public TerminatorQuoter() {
        System.out.println(repeat);
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @PostConstruct
    public void postConstruct(){
        System.out.println(repeat);
    }
    @Override
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println(message);
        }
    }

}
