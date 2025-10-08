package com.example.repasitogabo.service;

import com.example.repasitogabo.clases.Estudiante;
import com.example.repasitogabo.clases.Tutor;
import com.example.repasitogabo.dto.request.EstudianteRequestDTO;
import com.example.repasitogabo.dto.request.LoginRequestDTO;
import com.example.repasitogabo.dto.response.EstudianteResponseDTO;
import com.example.repasitogabo.dto.response.LoginResponseDTO;
import com.example.repasitogabo.repositorios.EstudianteRepository;

import com.example.repasitogabo.repositorios.SalonRepository;
import com.example.repasitogabo.repositorios.TutorRepository;
import com.example.repasitogabo.seguridad.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final EstudianteRepository estudianteRepository;
    private final TutorRepository tutorRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtService  jwtService;

    @Transactional
    public EstudianteResponseDTO registrar(EstudianteRequestDTO estudianteRequestDTO) {

        //Inicializar valores de estudiante con valores en DTO
        Estudiante estudiante = Estudiante.builder()
                .nombre(estudianteRequestDTO.getNombre())
                .edad(estudianteRequestDTO.getEdad())
                .email(estudianteRequestDTO.getEmail())
                .password(passwordEncoder.encode(estudianteRequestDTO.getPassword()))
                .build();

        //Guardar estudiante en BD
        Estudiante saved = estudianteRepository.save(estudiante);

        EstudianteResponseDTO respuesta =  modelMapper.map(saved, EstudianteResponseDTO.class);

        respuesta.setRol(saved.getRol().name().replace("ROLE_", ""));
        return  respuesta;
    }

    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        String password = null;
        String rol = null;
        Estudiante estudiante= estudianteRepository.findByEmail(loginRequestDTO.getEmail()).orElse(null);
        Tutor tutor = tutorRepository.findByEmail(loginRequestDTO.getEmail()).orElse(null);
        if (estudiante != null){
            password = estudiante.getPassword();
            rol= estudiante.getRol().name();
        } else if (tutor != null){
            password = tutor.getPassword();
            rol= tutor.getRol().name();
        } else{
            //Luego cambiamos los errores para todos, primero le ponemos esto si no encuentra ni a profesor
            //Ni a alumnos
            throw new RuntimeException("Lol q mal");
        }
        //Chequea si la contraseña que ingresamos es valida para el usuario
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), password)){
            //Cambiar luego error
            throw new RuntimeException("Lol q mal");
        }
        // Generar token
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getEmail());
        String token = jwtService.generateToken(userDetails, rol);
        return new LoginResponseDTO(token, jwtService.getExpirationTime());
    }

}
