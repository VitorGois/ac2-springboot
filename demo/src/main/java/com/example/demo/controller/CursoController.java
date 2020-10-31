package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Curso;
import com.example.demo.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> getCursos() {
        return cursoService.getAllCursos();
    }
    

}
