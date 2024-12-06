package com.tunombre.demo.controladores;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class ControladorPeliculas {

    private static HashMap<String, String> listaPeliculas = new HashMap<>();

    public ControladorPeliculas() {
        listaPeliculas.put("Winnie the Pooh", "Don Hall");
        listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
        listaPeliculas.put("Tarzán", "Kevin Lima");
        listaPeliculas.put("Mulán", "Barry Cook");
        listaPeliculas.put("Oliver", "Kevin Lima");
        listaPeliculas.put("Big Hero 6", "Don Hall");
    }

    @GetMapping
    public String obtenerTodasLasPeliculas() {
        StringBuilder peliculas = new StringBuilder("Lista de películas disponibles:\n");
        listaPeliculas.forEach((pelicula, director) ->
                peliculas.append("- ").append(pelicula).append("\n"));
        return peliculas.toString();
    }

    @GetMapping("/{nombre}")
    public String obtenerPeliculaPorNombre(@PathVariable String nombre) {
        if (listaPeliculas.containsKey(nombre)) {
            return "Película: " + nombre + "\nDirector: " + listaPeliculas.get(nombre);
        } else {
            return "La película no se encuentra en nuestra lista.";
        }
    }

    @GetMapping("/director/{nombre}")
    public String obtenerPeliculasPorDirector(@PathVariable String nombre) {
        List<String> peliculas = new ArrayList<>();
        listaPeliculas.forEach((pelicula, director) -> {
            if (director.equalsIgnoreCase(nombre)) {
                peliculas.add(pelicula);
            }
        });

        if (peliculas.isEmpty()) {
            return "No contamos con películas con ese director en nuestra lista.";
        } else {
            return "Películas dirigidas por " + nombre + ":\n" + String.join("\n", peliculas);
        }
    }
}