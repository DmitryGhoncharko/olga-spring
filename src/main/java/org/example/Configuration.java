package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.context.annotation.Configuration
@ComponentScan
public class Configuration {

    @Bean
    public InjectRandomIntBeanPostProcessor getInjectRandomInt(){
        return new InjectRandomIntBeanPostProcessor();
    }
    @Bean
    public ProfilingBeanPostProcessor getProfilingBeanPostProcessor(){
        return new ProfilingBeanPostProcessor();
    }
    @Bean
    public Quoter getQuoter(){
        return new TerminatorQuoter();
    }
}
