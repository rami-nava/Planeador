package com.example.OrganizadorDeTareas.Dto;

import com.example.OrganizadorDeTareas.Domain.Encargado;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TareaReq {
    private String nombre;
    private Integer cantidad;

    public TareaReq(){}
}
