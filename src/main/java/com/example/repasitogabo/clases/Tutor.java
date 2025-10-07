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

    @Column(unique = true)
    private String nombre;

    @Column
    private String especialidad;

    @Column(unique = true,  nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    //Tutor tiene salon a su cargo. Un tutor solo puede tener un salon a su cargo.
    @OneToOne(mappedBy = "tutor")
    private Salon salon;

    //Nos aseguramos de que si rol no fue asignado antes de guardar el objeto, se le asigna ROLE_TUTOR
    @PrePersist
    public void prePersist() {
        if (this.rol == null) {
            this.rol = Rol.ROLE_TUTOR;
        }
    }
}
