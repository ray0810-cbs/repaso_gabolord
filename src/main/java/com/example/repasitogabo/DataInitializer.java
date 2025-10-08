package com.example.repasitogabo;

import com.example.repasitogabo.clases.Rol;
import com.example.repasitogabo.clases.Salon;
import com.example.repasitogabo.clases.Tutor;
import com.example.repasitogabo.repositorios.SalonRepository;
import com.example.repasitogabo.repositorios.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final SalonRepository salonRepository;
    private final TutorRepository tutorRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (salonRepository.count() == 0) { // solo si está vacío
                // === Tutor 1 ===
                Tutor garcia = new Tutor();
                garcia.setNombre("Prof. García");
                garcia.setEspecialidad("Matemáticas");
                garcia.setEmail("garcia@colegio.edu");
                garcia.setPassword(passwordEncoder.encode("123456")); // 🔐 contraseña cifrada
                garcia.setRol(Rol.ROLE_TUTOR);
                tutorRepository.save(garcia);

                // === Tutor 2 ===
                Tutor rojas = new Tutor();
                rojas.setNombre("Ing. Rojas");
                rojas.setEspecialidad("Física");
                rojas.setEmail("rojas@colegio.edu");
                rojas.setPassword(passwordEncoder.encode("123456")); // 🔐 contraseña cifrada
                rojas.setRol(Rol.ROLE_TUTOR);
                tutorRepository.save(rojas);

                // === Crear Salones ===
                Salon salon1 = new Salon();
                salon1.setCodigo("A1");
                salon1.setGrado("Tercer ciclo");
                salon1.setTutor(garcia);
                salon1.setEstudiantes(null); // evita cascada innecesaria

                Salon salon2 = new Salon();
                salon2.setCodigo("B1");
                salon2.setGrado("Cuarto ciclo");
                salon2.setTutor(rojas);
                salon2.setEstudiantes(null); // evita cascada innecesaria

                salonRepository.save(salon1);
                salonRepository.save(salon2);

                System.out.println("Salones inicializados correctamente");
            } else {
                System.out.println("Salones ya existentes, no se insertaron datos iniciales");
            }
        };
    }
}
