package com.example.repasitogabo.repositorios;

import com.example.repasitogabo.clases.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
    Optional<Estudiante> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Estudiante> findBySalonId(Long salonId);
}
