package com.example.repasitogabo;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class HelloController {
    private final List<String> nombres = new ArrayList<>();

    @GetMapping("/hola")
    public String saludando() {
        return "Hola.";
    }

    @PostMapping("/nombre")
    public String guardando(@RequestParam String name) {
        nombres.add(name);
        return "El nombre guardado es: " + name;
    }

    @PostMapping("/nombre/cuerpo")
    public String guardandoCuerpo(@RequestBody RequestData requestData) {
        nombres.add(requestData.getNombre());
        return "El nombre guardado es: " + requestData.getNombre();
    }

    @GetMapping("/nombres")
    public List<String> obtenerNombre() {
        return nombres;
    }
}
