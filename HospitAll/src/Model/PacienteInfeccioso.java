/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

public class PacienteInfeccioso extends Paciente {

    private String tipoInfeccao;
    private boolean isIsolado;
    private LocalDate dataInicioInfeccao;

    public PacienteInfeccioso(String nome, String cpf, int idade, Medico medico, String tipoInfeccao, LocalDate dataInicio) {
        super(nome, cpf, idade, medico);
        this.tipoInfeccao = tipoInfeccao;
        this.isIsolado = true; 
        this.dataInicioInfeccao = dataInicio;
    }

    @Override
    public String getTipoPaciente() {
        return "Infeccioso";
    }

    @Override
    public String getDescricaoCondicao() {
        return "Infecção: " + this.tipoInfeccao + " (Desde: " + dataInicioInfeccao + ")";
    }

    @Override
    public void verHistorico() {
        super.verHistorico();
        System.out.println("Detalhes Infecciosos:");
        System.out.println("  - Tipo de Infecção: " + this.tipoInfeccao);
        System.out.println("  - Data de Início: " + this.dataInicioInfeccao);
        System.out.println("  - Em Isolamento: " + (this.isIsolado ? "Sim" : "Não"));
        System.out.println("--------------------------------------\n");
    }

    public void setEmIsolamento(boolean isolado) {
        this.isIsolado = isolado;
        System.out.println("Paciente " + getNome() + (isolado ? " foi colocado" : " foi retirado") + " em isolamento.");
    }
    
    public String getTipoInfeccao() {
        return tipoInfeccao;
    }

    public boolean isIsolado() {
        return isIsolado;
    }

    public LocalDate getDataInicioInfeccao() {
        return dataInicioInfeccao;
    }
}
