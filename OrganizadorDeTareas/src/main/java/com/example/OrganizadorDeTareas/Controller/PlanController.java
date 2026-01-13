package com.example.OrganizadorDeTareas.Controller;

import com.example.OrganizadorDeTareas.Domain.Plan;
import com.example.OrganizadorDeTareas.Dto.EncargadoReq;
import com.example.OrganizadorDeTareas.Dto.PlanReq;
import com.example.OrganizadorDeTareas.Dto.PlanRes;
import com.example.OrganizadorDeTareas.Dto.TareaReq;
import com.example.OrganizadorDeTareas.Services.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;


    @PostMapping()
    public ResponseEntity<Plan> nuevoPlan(@RequestBody PlanReq planReq){
        Plan plan = planService.create(planReq);
        return ResponseEntity.ok(plan);
    }

    @GetMapping
    public ResponseEntity<List<PlanRes>> planes(){
        List<PlanRes> planesExistentes = planService.getPlanes();
        return ResponseEntity.ok(planesExistentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> planBuscado(@PathVariable Integer id){
        Plan plan = planService.find(id);
        return ResponseEntity.ok(plan);
    }

    @PostMapping("/{id}/tareas")
    public ResponseEntity<Void> nuevaTarea(@PathVariable Integer id, @RequestBody TareaReq tareaReq){
        planService.nuevaTarea(id,tareaReq);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idP}/tareas/{idT}")
    public ResponseEntity<Void> nuevoEncargado(@PathVariable Integer idP, @PathVariable Integer idT, @RequestBody EncargadoReq encargadoReq){
        planService.nuevoEncargado(idP,idT, encargadoReq);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idP}/tareas/{idT}/encargados/{ind}")
        public ResponseEntity<Void> eliminarEncargado(@PathVariable Integer idP, @PathVariable Integer idT, @PathVariable Integer ind){
        planService.quitarEncargado(idP, idT, ind);
        return ResponseEntity.ok().build();
    }
}
