package org.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
@Component
public class ProfilingBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
       Class<?> beanClass = bean.getClass();
       if(beanClass.isAnnotationPresent(Profiling.class)){
           map.put(beanName,beanClass);
       }
       return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);
        if(beanClass!=null){
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("START PROFILING");
                    long before = System.nanoTime();
                    Object invoke = method.invoke(bean,args);
                    long after = System.nanoTime();
                    System.out.println(after-before);
                    System.out.println("END PROFILING");
                    return invoke;
                }
            });
        }
        return bean;
    }
}
