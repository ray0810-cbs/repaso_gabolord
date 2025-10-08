package com.example.repasitogabo.clases;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false,  unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="salon_id")
    private Salon salon;

    @PrePersist
    public void prePersist() {
        if (this.rol == null) {
            this.rol = Rol.ROLE_ESTUDIANTE;
        }
    }


}
