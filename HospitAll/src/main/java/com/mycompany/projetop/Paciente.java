// Nome: Victor Ehiti Itimura Tamay RA: 2475561

package com.mycompany.projetop;

public class Paciente extends Humano {
    private int id;
    private Doenca doenca;

    public Paciente() {
        this.id = 0;
        this.doenca = new Doenca();
    }

    public int calcular(){  //sobrescrita
	return 2023 - getIdade();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
   }


    public int getId() {
        return this.id;
    }

    public Doenca getDoenca() {
        return this.doenca;
    }



}