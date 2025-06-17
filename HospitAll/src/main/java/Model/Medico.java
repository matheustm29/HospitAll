

package Model;

import Model.Humano;

public class Medico extends Humano {
    private float salario;
    private String espec;
    private int crm;

    public Medico() {
        this.salario = 0;
        this.espec = "";
        this.crm = 0;
    }

    public int calcular(){  //sobrescrita
	return 2023 - getIdade();
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void setEspec(String espec) {
        this.espec = espec;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }

    public float getSalario() {
        return this.salario;
    }

    public String getEspec() {
        return this.espec;
    }

    public int getCrm() {
        return this.crm;
    }


}