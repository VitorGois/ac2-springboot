package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.dto.EscolaDTO;
import com.example.demo.model.Curso;
import com.example.demo.model.Escola;
import com.example.demo.service.CursoService;
import com.example.demo.service.EscolaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//Anotação para dizer ao Spring que essa classe se trata de um controlador
@RestController 
//Anotação que define o mapeamento padrão deste controlador
@RequestMapping("/escolas")
public class EscolaController {
    
    //Anotação que tem por função injetar uma dependência externa
    @Autowired
    private EscolaService escolaService;

    @Autowired
    private CursoService cursoService;

    //Faz a leitura do método HTTP GET (receber)
    @GetMapping
    /*  Método para buscar através do nosso service todos as escolas já cadastradas 
        retornando uma lista de escolas
        */
    public List<Escola> getAllEscolas() {
        return escolaService.getAllEscolas();
    }

    //Faz a leitura do método HTTP GET (receber), porém terá um caminho a mais a ser passado na URI
    @GetMapping("/{id}")
    /*  Método para buscar através do nosso service uma determinada escola através do seu ID
        retornado além da escola, qual o status http da requisição
        */
    public ResponseEntity<Escola> getEscolaByID(@PathVariable /*Anotação que faz a busca de um valor na URI*/ int id) {
        Escola aux = escolaService.getEscolaByID(id);

        return ResponseEntity.ok(aux);  //status 200 (ok)
    }

    //Faz a leitura do método HTTP POST (Mudança)
    @PostMapping
    /*  Método para fazer um novo cadastro (salvar) em nosso sistema uma nova escola
        retornando o status de criação junto com a nova URI
        */
    public ResponseEntity<Void> postEscola(@RequestBody /*Faz a requisição dos dados através de um Body (Postman)*/Escola novoCadastro, 
                                            HttpServletRequest request, /*Contém todas as informações da requisição*/
                                            UriComponentsBuilder builder) /*Utilizada para fazer a construção de uma nova URI*/ {
        //Variável auxiliar para armazenar a nova escola cadastrada, através do nosso service com o axuílio do DTO
        Escola aux = escolaService.save(novoCadastro);

        //Criação de uma URI dinâmica, através dos atributos que buscamos através do request e builder
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + aux.getId()).build();

        return ResponseEntity.created(uriComponents.toUri()).build();
    } 

    //Faz a leitura do método HTTP DELETE (Apagar)
    @DeleteMapping("/{id}")
    /*  Método responsável por fazer a remoção de uma escola dos nossos cadastrados
        retornando o status de sem conteúdo a mostrar
        */
    public ResponseEntity<Void> deleteEscola(@PathVariable int id) {
        escolaService.removeByID(id);

        return ResponseEntity.noContent().build();  //status 204 (noContent)
    }

    //Faz a leitura do método HTTP PUT (Atualizar)
    @PutMapping("/{id}")
    /*  Método responsável por fazer uma atualização em determinado cadastro
        retornando o status de criação junto com os novos dados
        */
    public ResponseEntity<Escola> putEscola(@PathVariable int id, @RequestBody EscolaDTO novosDados) {
        Escola aux = escolaService.fromDTO(novosDados);
        aux.setId(id);
        aux = escolaService.update(aux);

        return ResponseEntity.ok(aux);  //status 200 (ok)
    }

    @GetMapping("/{id}/cursos")
    
    //Método responsável por buscar todos os cursos referente a Escola de ID   
    public List<Curso> getPedidosCliente(@PathVariable int id) {
        Escola aux = escolaService.getEscolaByID(id);
        return aux.getCursos();	
    }

    @PostMapping("/{id}/cursos")
    /*  
        Método responsável por fazer o cadastro de um novo curso em determinada Escola,
        retornando o status de criação (200) e a nova URI criada
        */ 
    public ResponseEntity<Void> postCursoEscola(@PathVariable int id, @RequestBody Curso curso, HttpServletRequest request, UriComponentsBuilder builder) {
        curso = cursoService.save(id, curso);

        //Criação de uma URI dinâmica, através dos atributos que buscamos através do request e builder
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + curso.getId()).build();

        return ResponseEntity.created(uriComponents.toUri()).build(); //status 200 (created)
    }

}
