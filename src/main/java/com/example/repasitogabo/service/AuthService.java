package com.example.repasitogabo.service;

import com.example.repasitogabo.clases.Estudiante;
import com.example.repasitogabo.dto.request.EstudianteRequestDTO;
import com.example.repasitogabo.dto.response.EstudianteResponseDTO;
import com.example.repasitogabo.repositorios.EstudianteRepository;

import com.example.repasitogabo.repositorios.SalonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final EstudianteRepository estudianteRepository;
    private final SalonRepository salonRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public EstudianteResponseDTO registrar(EstudianteRequestDTO estudianteRequestDTO) {

        //Inicializar valores de estudiante con valores en DTO
        Estudiante estudiante = Estudiante.builder()
                .nombre(estudianteRequestDTO.getNombre())
                .edad(estudianteRequestDTO.getEdad())
                .email(estudianteRequestDTO.getEmail())
                .password(estudianteRequestDTO.getPassword())
                .build();

        //Guardar estudiante en BD
        Estudiante saved = estudianteRepository.save(estudiante);

        EstudianteResponseDTO respuesta =  modelMapper.map(saved, EstudianteResponseDTO.class);

        respuesta.setRol(saved.getRol().name().replace("ROLE_", ""));
        if (saved.getSalon() != null) {
            respuesta.setSalonId(saved.getSalon().getId());
        }
        return  respuesta;
    }

}
