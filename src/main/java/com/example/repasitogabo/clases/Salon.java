package com.example.repasitogabo.clases;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "salones")
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Codigo del salon, A1 por ejemplo
    @Column(nullable = false, unique = true)
    private String codigo;

    @Column
    private String grado;

    @OneToOne
    @JoinColumn(name="tutor_id")
    private Tutor tutor;

    @OneToMany(mappedBy = "salon", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default //Mantiene el valor por defecto al usar builder
    private List<Estudiante> estudiantes = new ArrayList<>();

}
