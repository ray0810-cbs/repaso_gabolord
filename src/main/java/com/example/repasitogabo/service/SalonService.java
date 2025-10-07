package com.example.repasitogabo.service;


import com.example.repasitogabo.clases.Salon;
import com.example.repasitogabo.dto.SalonCreateDTO;
import com.example.repasitogabo.repositorios.SalonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalonService {
    private final SalonRepository salonRepository;

    public Salon crearSalon(SalonCreateDTO salonDTO) {
        Salon salon =Salon.builder()
                .codigo(salonDTO.getCodigo())
                .grado(salonDTO.getGrado()).build();
        return salonRepository.save(salon);
    }
}
