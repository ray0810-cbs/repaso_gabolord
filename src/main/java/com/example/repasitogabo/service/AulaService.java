package com.example.repasitogabo.service;

import com.example.repasitogabo.clases.Salon;
import com.example.repasitogabo.dto.response.SalonResponseDTO;
import com.example.repasitogabo.repositorios.EstudianteRepository;
import com.example.repasitogabo.repositorios.SalonRepository;
import com.example.repasitogabo.repositorios.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AulaService {
    private final TutorRepository tutorRepository;
    private final SalonRepository salonRepository;
    private final EstudianteRepository estudianteRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly=true)
    public List<SalonResponseDTO> listarSalones() {
        return salonRepository.findAll().stream()
                .map(this::mapToSalonDTO)
                .collect(Collectors.toList());
    }


    private SalonResponseDTO mapToSalonDTO(Salon salon) {
        //mapper es del donde quiero sacar informacion -> al que quiero darle informacion
        SalonResponseDTO dto = modelMapper.map(salon, SalonResponseDTO.class);
        if (salon.getTutor() != null) {
            dto.setTutorNombre(salon.getTutor().getNombre());
        }
        return dto;
    }
}
