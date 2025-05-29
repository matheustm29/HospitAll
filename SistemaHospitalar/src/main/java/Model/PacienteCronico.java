/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
public class PacienteCronico extends Paciente {

    private String doencaCronica;
    private String tratamentoContinuo;

    public PacienteCronico(String nome, String cpf, int idade, Medico medico, String doencaCronica, String tratamentoContinuo) {
        super(nome, cpf, idade, medico);
        this.doencaCronica = doencaCronica;
        this.tratamentoContinuo = tratamentoContinuo;
    }

    @Override
    public String getTipoPaciente() {
        return "Crônico";
    }
    
    @Override
    public String getDescricaoCondicao() {
        return "Doença Crônica: " + this.doencaCronica;
    }

    @Override
    public void verHistorico() {
        super.verHistorico(); 
        System.out.println("Detalhes Crônicos:");
        System.out.println("  - Tratamento Contínuo: " + this.tratamentoContinuo);
        System.out.println("--------------------------------------\n");
    }

    public String getDoencaCronica() {
        return doencaCronica;
    }

    public void setDoencaCronica(String doencaCronica) {
        this.doencaCronica = doencaCronica;
    }

    public String getTratamentoContinuo() {
        return tratamentoContinuo;
    }

    public void setTratamentoContinuo(String tratamentoContinuo) {
        this.tratamentoContinuo = tratamentoContinuo;
    }
}
