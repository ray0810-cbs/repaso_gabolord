package com.example.repasitogabo.service;


import com.example.repasitogabo.clases.Salon;
import com.example.repasitogabo.repositorios.SalonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalonService {
    private final SalonRepository salonRepository;

    public Salon crearSalon(Salon salon) {
        return salonRepository.save(salon);
    }
}
