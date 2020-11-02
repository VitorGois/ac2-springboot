package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.demo.model.Curso;

import org.springframework.stereotype.Component;

@Component
public class CursoRepository {
    
	private List<Curso> cursos;
	private int nextID;

	@PostConstruct
	public void init() {
		Curso c1 = new Curso();
		c1.setId(1);
		c1.setNome("Algoritmos");
		c1.setProfessor("Glauco");
		c1.setSala("A21");
		c1.setData("Terça");

		cursos = new ArrayList<Curso>();
		cursos.add(c1);

		nextID = 2;
	}

    public List<Curso> getAllCursos() {
        return cursos;
    }

	public Optional<Curso> getCursoByID(int id) {
		for (Curso aux : cursos) {
			if (aux.getId() == id) {
				return Optional.of(aux);
			}
		}

		return Optional.empty();
	}

	public Curso save(Curso curso) {
		curso.setId(nextID++);
		cursos.add(curso);

		return curso;
	}

	public void remove(Curso curso) {
		cursos.remove(curso);
	}

	public Curso update(Curso curso) {
		Curso aux = getCursoByID(curso.getId()).get();

		if (aux != null) {
			aux.setNome(curso.getNome());
			aux.setProfessor(curso.getProfessor());
			aux.setSala(curso.getSala());
			aux.setData(curso.getData());
		}

		return aux;
	}

}
