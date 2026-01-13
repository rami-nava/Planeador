package com.example.OrganizadorDeTareas.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EncargadoReq {
    public String nombre;
    public String apellido;
    public String contacto;

    public EncargadoReq(){}
}
