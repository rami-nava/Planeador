package com.example.OrganizadorDeTareas.Domain;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Plan {

    private Integer id;
    private String nombre;
    private Integer participantes;
    private String lugar;
    private LocalDate fecha;
    private LocalTime hora;
    private List<Tarea> tareas;

    public Plan(Integer id,String nombre, Integer participantes, String lugar, LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.nombre = nombre;
        this.participantes = participantes;
        this.lugar = lugar;
        this.fecha = fecha;
        this.hora = hora;
        this.tareas = new ArrayList<>();
    }

    public Boolean completo(){
        return tareas.stream().allMatch(t -> t.completa());
    }


}
