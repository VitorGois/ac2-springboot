package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.EscolaDTO;
import com.example.demo.model.Escola;
import com.example.demo.repository.EscolaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//Anotação para dizer ao Spring que essa classe se trata de um serviço
@Service
public class EscolaService {

	//Anotação que tem por função injetar uma dependência
	@Autowired
	private EscolaRepository repository;

	/*	Método responsável por fazer a busca em nosso repositório de todsa as escolas
		retornando uma lista
		*/
	public List<Escola> getAllEscolas() {
		return repository.getAllEscolas();
	}

	/*	Método responsável por fazer a busca, utilizando o Repositório, de uma determinada escola através do seu ID
		Sempre que chamarmos essa função, irá retornar um objeto com os seus dados ou diretamente uma 
		mensagem de erro 404
		*/
	public Escola getEscolaByID(int id) {
		//A principal proposta do Optional é encapsular o retorno de métodos e informar se um valor do tipo <T> está presente ou ausente.
		Optional<Escola> op = repository.getEscolaByID(id); 

		/*	Após guardar um objeto do tipo Optional vazio ou não da busca pelo ID, haverá um retorno no qual se for encontrado será retornado 
			o objeto Escola, 
			caso contrário ele lançara o STATUS 404 NOT FOUND através do ResponseStatusException, através de uma "arrow function" (programação funcional)
			com uma mensagem de erro definida.
			*/
		return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Escola nao cadastrada: " + id));
	}

	/*	Método responsável por coletar os dados que nosso usuário digitou atráves do DTO, cujo é passado por parâmetro
		retornando esse novo cadastro
		*/
	public Escola fromDTO(EscolaDTO dto) {
		Escola aux = new Escola();
		aux.setTel(dto.getTel());
		aux.setEmail(dto.getEmail());

		return aux;
	}

	/*	Método responsável por salvar os dados que nosso usuário digitou em nosso repositório
		*/
    public Escola save(Escola escola) {
		return repository.save(escola);
	}

	/*	Método responsável por deletar os dados de determinada escola em nosso repositório
		*/
	public void removeByID(int id) {
		repository.remove(getEscolaByID(id));
	}

	/*	Método resposável por atualizar o cadastro de uma escola
		*/
	public Escola update(Escola escola) {
		getEscolaByID(escola.getId());
		return repository.update(escola);
	}
    
}