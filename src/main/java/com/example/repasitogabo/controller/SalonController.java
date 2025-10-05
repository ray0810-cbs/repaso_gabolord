package com.example.repasitogabo.controller;

import com.example.repasitogabo.clases.Salon;
import com.example.repasitogabo.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Salon> create(@RequestBody Salon salon) {
        Salon newSalon= salonService.crearSalon(salon);
        return new ResponseEntity<>(newSalon, HttpStatus.CREATED);
    }
}
