package com.example.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ContextSingleton {
    private static ContextSingleton instance;
    private ApplicationContext context;
    
    private ContextSingleton() {
        this.context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    
    public static synchronized ContextSingleton getInstance() {
        if (instance == null) {
            instance = new ContextSingleton();
        }
        return instance;
    }
    
    public ApplicationContext getContext() {
        return context;
    }
    
    public void closeContext() {
        if (context instanceof ClassPathXmlApplicationContext) {
            ((ClassPathXmlApplicationContext) context).close();
        }
    }
}
