package com.example.OrganizadorDeTareas.Services;


import com.example.OrganizadorDeTareas.Domain.Encargado;
import com.example.OrganizadorDeTareas.Domain.Plan;
import com.example.OrganizadorDeTareas.Domain.Tarea;
import com.example.OrganizadorDeTareas.Dto.EncargadoReq;
import com.example.OrganizadorDeTareas.Dto.PlanReq;
import com.example.OrganizadorDeTareas.Dto.PlanRes;
import com.example.OrganizadorDeTareas.Dto.TareaReq;
import com.example.OrganizadorDeTareas.Mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Ssl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanMapper planMapper;
    private List<Plan> planes = new ArrayList<>();
    private Integer id = 1;


    public Plan create(PlanReq planReq){
        Plan plan = new Plan(
                id,
                planReq.getNombre(),
                planReq.getParticipantes(),
                planReq.getLugar(),
                planReq.getFecha(),
                planReq.getHora()
        );
        id++;

        planes.add(plan);

        return plan;
    }

    public Plan find(Integer id){
        return planes.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList()).get(0);
    }
    public List<PlanRes> getPlanes(){
        List<PlanRes> planesRes = planes.stream().map(p -> planMapper.toRes(p)).toList();
        return planesRes;
    }

    public void nuevaTarea(Integer id, TareaReq tareaReq){
        Plan plan = find(id);

        Integer idd = plan.getTareas().size() + 1;

        Tarea tarea = new Tarea(idd, tareaReq.getNombre(), tareaReq.getCantidad());

        plan.getTareas().add(tarea);
    }

    public void nuevoEncargado(Integer idP,Integer idT, EncargadoReq encargadoReq) {
        Plan plan = find(idP);
        Tarea tarea = plan.getTareas().stream().filter(t -> t.getId().equals(idT)).collect(Collectors.toList()).get(0);

        Encargado encargado = new Encargado(encargadoReq.getNombre(), encargadoReq.getApellido(), encargadoReq.getContacto());
        tarea.getEncargados().add(encargado);
    }

    public void quitarEncargado(Integer idP, Integer idT, Integer ind){
        Plan plan = find(idP);
        Tarea tarea = plan.getTareas().stream().filter(t -> t.getId().equals(idT)).collect(Collectors.toList()).get(0);


        Encargado encargado = tarea.getEncargados().get(ind);
        tarea.getEncargados().remove(encargado);
    }
}
