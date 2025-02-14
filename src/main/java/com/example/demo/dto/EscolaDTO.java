package com.example.demo.dto;

/*  O DTO (Data Transfer Objeto), funciona como um limitador das informações que o usuário
    pode ter acesso. "Regra de negócios"
    É muito semelhante a classe Escola porém sem conter o ID
    */
public class EscolaDTO {

	private String nome;
	private String endereco;
	private String tel;
	private String email;

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
