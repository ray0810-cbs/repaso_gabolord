package com.example.repasitogabo.repositorios;

import com.example.repasitogabo.clases.Estudiante;
import com.example.repasitogabo.clases.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Long> {
    Optional<Tutor> findByEmail(String email);
    boolean existsByEmail(String email);
}
