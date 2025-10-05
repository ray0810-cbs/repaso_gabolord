package com.example.repasitogabo.clases;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tutores")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private String especialidad;

    @OneToOne(mappedBy = "tutor")
    private Salon salon;
}
