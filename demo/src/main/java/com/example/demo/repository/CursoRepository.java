package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Curso;

import org.springframework.stereotype.Component;

@Component
public class CursoRepository {
    
    private List<Curso> cursos;

    public List<Curso> getAllCursos() {
        return cursos;
    }

}
