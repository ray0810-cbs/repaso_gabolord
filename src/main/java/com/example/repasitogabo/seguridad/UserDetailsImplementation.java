package com.example.repasitogabo.seguridad;

import com.example.repasitogabo.clases.Estudiante;
import com.example.repasitogabo.clases.Tutor;
import com.example.repasitogabo.repositorios.EstudianteRepository;
import com.example.repasitogabo.repositorios.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsImplementation implements UserDetailsService {
    private final EstudianteRepository estudianteRepository;
    private final TutorRepository tutorRepository;

    //Sobrescribir funcion, UserDetailsService ya tiene esta misma función definida, nosotros personalizamos
    //Su funcionamiento
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Estudiante> estudiante = estudianteRepository.findByEmail(email);
        Optional<Tutor> tutor = tutorRepository.findByEmail(email);
        //Primero buscamos en estudiante
        if (estudiante.isPresent()) {
            Estudiante e = estudiante.get();
            return new User(
                    e.getEmail(),
                    e.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(e.getRol().name()))
            );
        }
        //Luego buscamos los tutores
        if (tutor.isPresent()) {
            Tutor t = tutor.get();
            return new User(
                    t.getEmail(),
                    t.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(t.getRol().name()))
            );
        }
        throw new UsernameNotFoundException("Usuario con email: "+email+" no encontrado ");}
}
