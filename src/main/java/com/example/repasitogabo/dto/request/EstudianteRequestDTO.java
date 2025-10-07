package com.example.repasitogabo.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteRequestDTO {
    @NotBlank(message = "Nombre obligatorio")
    private String nombre;

    @NotBlank(message = "Edad obligatoria")
    @Max(value = 60,message = "Eres muy viejo retirate")
    @Min(value = 17,message = "Tas bien chiquito, vuelve en unos años")
    private int edad;

    @NotBlank(message = "Email no puede ser nulo")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    //No dice el tamaño en el texto, pero así lo puso gabodios
    private String password;
}
