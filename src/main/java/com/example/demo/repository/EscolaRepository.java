package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.model.Escola;

import org.springframework.stereotype.Component;

//Anotação responsável por indicar ao Spring que se trata de uma classe Componente
@Component
public class EscolaRepository {
    
    private ArrayList<Escola> escolas = new ArrayList<>();
    private int nextID=1;

    

    /*  Método responsável por fazer a busca de todas as escolas,
        retornando uma lista
        */
	public ArrayList<Escola> getAllEscolas() {
		return escolas;
    }
    
    /*  Método responsável por fazer a busca da escola através do seu ID,
        através de um ForEach é realizado a busca dentre todas as escolas buscando
        a que contenha o mesmo ID, sendo assim, returnando um Optional de Escola (dados),
        caso contrário retornando um Objeto Optional vazio
        */
    public Optional<Escola> getEscolaByID(int id) {
        for (Escola aux : escolas) {
            if (aux.getId() == id) {
                return Optional.of(aux);
            }
        }

        return Optional.empty();
    }

    /*  Método responsável por salvar uma nova escola em nossa Lista, incrementando automaticamente um novo ID
        */
    public Escola save(Escola escola) {
        escola.setId(nextID++);
        escolas.add(escola);

        return escola;
    }

    /*  Método responsável por remover uma nova escola em nossa Lista
        */
    public void remove(Escola escola) {
        escolas.remove(escola);
    }

    /*  Método responsável por atualizar o cadastro de uma escola
        retornando os novos dados
        */
    public Escola update(Escola escola) {
        Escola aux = getEscolaByID(escola.getId()).get(); //Armazena os dados da escola com o ID correto de um Optional, para isso se dever usar o get

        if (aux != null) {
            aux.setTel(escola.getTel());
            aux.setEmail(escola.getEmail());    
        }

        return aux;
    }
    
}
