package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repository;

	public List<Curso> getAllCursos() {
		return repository.getAllCursos();
	}

	
    
}
