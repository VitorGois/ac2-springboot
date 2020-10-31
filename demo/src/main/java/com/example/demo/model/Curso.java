package com.example.demo.model;

import java.util.Date;

public class Curso {
    
    private int id;
    private String nome;
    private String professor;
    private String sala;
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public String getTeacher() {
        return professor;
    }

    public void setTeacher(String teacher) {
        this.professor = teacher;
    }

    public String getRoom() {
        return sala;
    }

    public void setRoom(String room) {
        this.sala = room;
    }

    public Date getDate() {
        return data;
    }

    public void setDate(Date date) {
        this.data = date;
    }

    

}
