package com.example.repasitogabo.controller;

import com.example.repasitogabo.clases.Salon;
import com.example.repasitogabo.dto.SalonCreateDTO;
import com.example.repasitogabo.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SalonController {
    private final SalonService salonService;

    @PostMapping("/salon")
    public ResponseEntity<Salon> create(@Validated @RequestBody SalonCreateDTO salonDTO) {
        Salon savedsalon= salonService.crearSalon(salonDTO);
        return new ResponseEntity<>(savedsalon, HttpStatus.CREATED);
    }
}
