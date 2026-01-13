package com.example.OrganizadorDeTareas.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class PlanReq {
    private String nombre;
    private String lugar;
    private LocalDate fecha;   // después puede ser LocalDate
    private LocalTime hora;    // después LocalTime
    private Integer participantes;

    public PlanReq(){}
}
