package com.example.demo.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Escola {

	private int id;
	private String nome;
	private String endereco;
	private String tel;
	private String email;

	//Esta anotação faz com o que na exibição dos dados através do JSON, não aconteça repetição
	@JsonIgnore
	private ArrayList<Curso> cursos = new ArrayList<>();

	public int getId() {
		return id;
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	public void addCurso(Curso curso) {
        cursos.add(curso);
    }

    public void removeCurso(Curso curso) {
        cursos.remove(curso);
    }

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}    

}
