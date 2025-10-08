package com.example.repasitogabo.controller;

import com.example.repasitogabo.dto.request.EstudianteRequestDTO;
import com.example.repasitogabo.dto.response.EstudianteResponseDTO;
import com.example.repasitogabo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService  authService;

    @PostMapping("/register")
    public ResponseEntity<EstudianteResponseDTO> register(@Valid @RequestBody EstudianteRequestDTO requestDTO) {
        EstudianteResponseDTO responseDTO = authService.registrar(requestDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
