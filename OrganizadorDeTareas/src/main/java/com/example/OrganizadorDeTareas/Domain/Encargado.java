package com.example.OrganizadorDeTareas.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Encargado {

    private String nombre;
    private String apellido;
    private String contacto;

    public Encargado(String nombre, String apellido, String contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contacto = contacto;
    }
}
