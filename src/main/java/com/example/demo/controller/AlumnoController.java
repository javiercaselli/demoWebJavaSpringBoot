package com.example.demo.controller;

import com.example.demo.model.Alumno;
import com.example.demo.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    // Mapea la ruta raíz y envía la lista de alumnos a la vista
    @GetMapping("/")
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", alumnoRepository.findAll());
        return "index";
    }

    // Procesa el formulario para agregar un nuevo alumno
    @PostMapping("/agregar")
    public String agregarAlumno(@RequestParam String nombre, @RequestParam String email) {
        Alumno alumno = new Alumno(nombre, email);
        alumnoRepository.save(alumno);
        return "redirect:/";
    }

    @GetMapping("/borrar")
    public String borrarAlumno(@RequestParam String id) {
        Alumno alumno = new Alumno();
        alumno.setId(Long.parseLong(id));
        alumnoRepository.delete(alumno);
        return "redirect:/";
    }
}
