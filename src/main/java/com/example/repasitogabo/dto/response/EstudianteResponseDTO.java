package com.example.repasitogabo.dto.response;

import com.example.repasitogabo.clases.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteResponseDTO {
    private Long id;
    private String nombre;
    private int edad;
    private String email;
    private String rol;
    private Long salonId;
}
