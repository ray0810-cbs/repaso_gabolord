package com.example.repasitogabo.repositorios;

import com.example.repasitogabo.clases.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Long> {
}
