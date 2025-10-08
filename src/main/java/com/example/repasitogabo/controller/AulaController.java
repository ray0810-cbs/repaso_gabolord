package com.example.repasitogabo.controller;

import com.example.repasitogabo.dto.response.SalonResponseDTO;
import com.example.repasitogabo.service.AulaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
@RequiredArgsConstructor
public class AulaController {
    private final AulaService aulaService;

    @GetMapping("/salones")
    public ResponseEntity<List<SalonResponseDTO>> listar(){
        List<SalonResponseDTO> listaSalones = aulaService.listarSalones();
        return  ResponseEntity.ok(listaSalones);
    }

}
