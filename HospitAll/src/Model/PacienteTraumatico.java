/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

public class PacienteTraumatico extends Paciente {

    private String tipoTrauma;
    private LocalDate dataAcidente;

    public PacienteTraumatico(String nome, String cpf, int idade, Medico medico, String tipoTrauma, LocalDate dataAcidente) {
        super(nome, cpf, idade, medico);
        this.tipoTrauma = tipoTrauma;
        this.dataAcidente = dataAcidente;
    }

    @Override
    public String getTipoPaciente() {
        return "Traumático";
    }

    @Override
    public String getDescricaoCondicao() {
        return "Trauma: " + this.tipoTrauma + " (Acidente em: " + dataAcidente + ")";
    }

    @Override
    public void verHistorico() {
        super.verHistorico();
        System.out.println("Detalhes do Trauma:");
        System.out.println("  - Tipo de Trauma: " + this.tipoTrauma);
        System.out.println("  - Data do Acidente: " + this.dataAcidente);
        System.out.println("--------------------------------------\n");
    }

    public String getTipoTrauma() {
        return tipoTrauma;
    }

    public void setTipoTrauma(String tipoTrauma) {
        this.tipoTrauma = tipoTrauma;
    }

    public LocalDate getDataAcidente() {
        return dataAcidente;
    }

    public void setDataAcidente(LocalDate dataAcidente) {
        this.dataAcidente = dataAcidente;
    }
}
