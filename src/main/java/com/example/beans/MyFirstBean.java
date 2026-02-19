package com.example.beans;

import org.springframework.stereotype.Component;

@Component
public class MyFirstBean {
    
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}