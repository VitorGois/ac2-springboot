package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.CursoDTO;
import com.example.demo.model.Curso;
import com.example.demo.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoByID(@PathVariable int id) {
        Curso aux = cursoService.getCursoByID(id);

        return ResponseEntity.ok(aux);
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteCurso(@PathVariable int id) {
    //     cursoService.removeByID(id);

    //     return ResponseEntity.noContent().build();
    // }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> putCurso(@PathVariable int id, @RequestBody CursoDTO novosDados) {
        Curso aux = cursoService.fromDTO(novosDados);
        aux.setId(id);
        aux = cursoService.update(aux);

        return ResponseEntity.ok(aux);
    }

}
