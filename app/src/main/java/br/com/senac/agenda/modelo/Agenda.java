package br.com.senac.agenda.modelo;

import java.io.Serializable;

public class Agenda implements Serializable {

    private Long id;
    private String nome;
    private String horario;
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return getNome() +" - " + getData() + " - " + getHorario() ;
    }

}
