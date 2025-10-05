package com.example.repasitogabo.service;

import com.example.repasitogabo.clases.Tutor;
import com.example.repasitogabo.repositorios.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;

    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }



}
