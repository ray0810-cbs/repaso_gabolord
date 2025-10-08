package com.example.repasitogabo.service;

import com.example.repasitogabo.clases.Estudiante;
import com.example.repasitogabo.clases.Salon;
import com.example.repasitogabo.clases.Tutor;
import com.example.repasitogabo.dto.response.EstudianteResponseDTO;
import com.example.repasitogabo.dto.response.MessageResponseDTO;
import com.example.repasitogabo.dto.response.SalonResponseDTO;
import com.example.repasitogabo.exception.SalonNotFoundException;
import com.example.repasitogabo.exception.UnauthorizedAssignmentException;
import com.example.repasitogabo.exception.UnauthorizedException;
import com.example.repasitogabo.exception.UserNotFoundException;
import com.example.repasitogabo.repositorios.EstudianteRepository;
import com.example.repasitogabo.repositorios.SalonRepository;
import com.example.repasitogabo.repositorios.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    @Transactional(readOnly=true)
    public List<EstudianteResponseDTO> listarEstudiantes(Long id) {
        if (!salonRepository.existsById(id)) {
            throw new SalonNotFoundException("El salon no existe, id: "+id);
        }
        return estudianteRepository.findBySalonId(id).stream()
                .map(this::mapToEstudianteDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public MessageResponseDTO asignarSalon(Long estudianteId, Long salonId, Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(()-> new UserNotFoundException("Tutor no existe con id: " + tutorId));
        Salon salon = salonRepository.findById(salonId)
                .orElseThrow(()-> new SalonNotFoundException("Salon no existe con id: " + salonId));
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(()-> new UserNotFoundException("Estudiante no existe con id: " + estudianteId));

        //Si tutor no pertenece a salon error 403, forbidden
        if (salon.getTutor()==null || !salon.getTutor().getId().equals(tutor.getId())) {
                throw new UnauthorizedAssignmentException("Tutor no asignado al salón con id "+ salonId);
        }

        estudiante.setSalon(salon);
        //Guardar cambios
        estudianteRepository.save(estudiante);
        String msg = String.format(
                "Estudiante %s asignado al salón %s por el tutor %s",
                estudiante.getNombre(),
                salon.getCodigo(),
                tutor.getNombre()
        );
        return new MessageResponseDTO(msg);
    }

    @Transactional(readOnly = true)
    public SalonResponseDTO salonEstudiante(Long estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId).
                orElseThrow(()-> new UserNotFoundException("No existe estudiante con id: "+estudianteId));

        Salon salon = estudiante.getSalon();

        if (salon==null){
            throw new SalonNotFoundException("Salon no existe");
        }
        return mapToSalonDTO(salon);
    }


    private SalonResponseDTO mapToSalonDTO(Salon salon) {
        //mapper es del donde quiero sacar informacion -> al que quiero darle informacion
        SalonResponseDTO dto = modelMapper.map(salon, SalonResponseDTO.class);
        if (salon.getTutor() != null) {
            dto.setTutorNombre(salon.getTutor().getNombre());
        }
        return dto;
    }

    private EstudianteResponseDTO mapToEstudianteDTO(Estudiante estudiante) {
        EstudianteResponseDTO dto = modelMapper.map(estudiante, EstudianteResponseDTO.class);
        dto.setRol(estudiante.getRol().name().replace("ROLE_", ""));
        if (estudiante.getSalon() != null) {
            dto.setSalonId(estudiante.getSalon().getId());
        }
        return dto;
    }



}
