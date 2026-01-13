package com.example.OrganizadorDeTareas.Domain;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Tarea {

    private Integer id;
    private String nombre;
    private Integer cantidad;
    private List<Encargado> encargados;


    public Tarea(Integer id, String nombre, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.encargados = new ArrayList<>();
    }

    public Boolean completa(){
        return encargados.size() == cantidad;
    }
}
