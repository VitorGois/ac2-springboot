package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.CursoDTO;
import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repository;

	public List<Curso> getAllCursos() {
		return repository.getAllCursos();
	}

	public Curso getCursoByID(int id) {
		Optional<Curso> op = repository.getCursoByID(id);

		return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso nao cadastrado: " + id));
	}

	public Curso fromDTO(CursoDTO dto) {
		Curso aux = new Curso();
		aux.setNome(dto.getNome());
		aux.setProfessor(dto.getProfessor());
		aux.setSala(dto.getSala());
		aux.setData(dto.getData());

		return aux;
	}

	public Curso save(Curso curso) {
		return repository.save(curso);
	}

	public void removeByID(int id) {
		repository.remove(getCursoByID(id));
	}

	public Curso update(Curso curso) {
		getCursoByID(curso.getId());
		return repository.update(curso);
	}

	
    
}
