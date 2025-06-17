

// Nome: Victor Ehiti Itimura Tamay RA: 2475561

package com.mycompany.projetop;

public class Humano{
    private int cpf;
    private String nome;    
    private int idade;

    public Humano(){
        this.nome = "";
        this.cpf = 1;
	this.idade = 1;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }


    public String getNome() {
        return this.nome;
    }

    public int getCpf() {
        return this.cpf;
    }

    public int getIdade() {
        return this.idade;
    }


}