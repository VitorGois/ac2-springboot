package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.CursoDTO;
import com.example.demo.model.Curso;
import com.example.demo.model.Escola;
import com.example.demo.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repository;

	@Autowired
	private EscolaService escolaService;

	public CursoDTO toDTO(Curso curso) {
		CursoDTO dto = new CursoDTO();

		dto.setProfessor(curso.getProfessor());

		return dto;
	}

	public List<CursoDTO> toListDTO(List<Curso> cursos) {
		ArrayList <CursoDTO> dtoList = new ArrayList<>();

		for(Curso curso : cursos) {
			dtoList.add(toDTO(curso));
		}

		return dtoList;
	}

	public List<Curso> getAllCursos() {
		return repository.getAllCursos();
	}

	public Curso getCursoByID(int id) {
		Optional<Curso> op = repository.getCursoByID(id);

		return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso nao cadastrado: " + id));
	}

	public Curso fromDTO(CursoDTO dto) {
		Curso aux = new Curso();

		aux.setProfessor(dto.getProfessor());

		return aux;
	}

	public Curso save(int idEscola, Curso curso) {
		//Se não existir lança 404 e finaliza
		Escola escola = escolaService.getEscolaByID(idEscola);
		
		curso.setEscola(escola);
		escola.addCurso(curso);

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
