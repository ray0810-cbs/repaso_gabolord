package com.example.repasitogabo.controller;

import com.example.repasitogabo.clases.Estudiante;
import com.example.repasitogabo.dto.response.EstudianteResponseDTO;
import com.example.repasitogabo.dto.response.MessageResponseDTO;
import com.example.repasitogabo.dto.response.SalonResponseDTO;
import com.example.repasitogabo.service.AulaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
@RequiredArgsConstructor
public class AulaController {
    private final AulaService aulaService;

    @GetMapping("/salones")
    public ResponseEntity<List<SalonResponseDTO>> listar(){
        List<SalonResponseDTO> listaSalones = aulaService.listarSalones();
        return  ResponseEntity.status(HttpStatus.OK).body(listaSalones);
    }

    @GetMapping("/salones/{id}/estudiantes")
    public ResponseEntity<List<EstudianteResponseDTO>> listarEstudiantes( @PathVariable Long id){
        List<EstudianteResponseDTO> listaAlumnos = aulaService.listarEstudiantes(id);
        return ResponseEntity.status(HttpStatus.OK).body(listaAlumnos);
    }

    @PostMapping("/tutores/{tutorId}/asignar/{estudianteId}/salon/{salonId}")
    @PreAuthorize("hasRole('TUTOR')")
    public ResponseEntity<MessageResponseDTO> asignar(@PathVariable Long estudianteId, @PathVariable Long tutorId, @PathVariable Long salonId){
        MessageResponseDTO msg = aulaService.asignarSalon(estudianteId, tutorId, salonId);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @GetMapping("/estudiantes/{id}/salon")
    public ResponseEntity<SalonResponseDTO> salonEstudiante(@PathVariable Long id){
        SalonResponseDTO salon = aulaService.salonEstudiante(id);
        return ResponseEntity.status(HttpStatus.OK).body(salon);
    }

}
