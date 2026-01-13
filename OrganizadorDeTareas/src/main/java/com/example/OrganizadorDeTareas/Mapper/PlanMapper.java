package com.example.OrganizadorDeTareas.Mapper;

import com.example.OrganizadorDeTareas.Domain.Plan;
import com.example.OrganizadorDeTareas.Dto.PlanRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlanMapper {
    default PlanRes toRes(Plan plan){
        PlanRes planRes = new PlanRes();
        planRes.setNombre(plan.getNombre());
        planRes.setLugar(plan.getLugar());
        planRes.setFecha(plan.getFecha());
        planRes.setHora(plan.getHora());
        planRes.setCompleto(plan.completo());
        return planRes;
    }
}
