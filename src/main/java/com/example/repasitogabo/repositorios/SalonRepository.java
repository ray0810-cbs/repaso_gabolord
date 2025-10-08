package com.example.repasitogabo.repositorios;

import com.example.repasitogabo.clases.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalonRepository extends JpaRepository<Salon,Long> {
}
