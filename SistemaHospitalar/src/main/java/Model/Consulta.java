/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Objects;

public class Consulta {

    private LocalDate data;
    private String descricao;
    private Paciente paciente;
    private Medico medico;

    public Consulta(LocalDate data, String descricao, Paciente paciente, Medico medico) {
        if (data == null || paciente == null || medico == null) {
            throw new IllegalArgumentException("Data, Paciente e Médico são obrigatórios para criar uma consulta.");
        }
        this.data = data;
        this.descricao = (descricao != null && !descricao.trim().isEmpty()) ? descricao : "Consulta de rotina";
        this.paciente = paciente;
        this.medico = medico;
    }

    public void registrarConsulta() {
        System.out.println("\n--- REGISTRANDO CONSULTA ---");
        System.out.println("Data: " + this.data);
        System.out.println("Paciente: " + this.paciente.getNome() + " (" + this.paciente.getTipoPaciente() + ")");
        System.out.println("Médico: " + this.medico.getNome() + " (" + this.medico.getEspecialidade() + ")");
        System.out.println("Descrição: " + this.descricao);
        
        this.paciente.adicionarConsultaAoHistorico(this);
        this.medico.adicionarConsultaRealizada(this);
        
        System.out.println("--- CONSULTA REGISTRADA ---\n");
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if (data != null) {
            this.data = data;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
         if (paciente != null) {
            this.paciente = paciente;
        }
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        if (medico != null) {
            this.medico = medico;
        }
    }

    @Override
    public String toString() {
        return "Consulta[Data: " + data + 
               ", Paciente: " + paciente.getNome() + 
               ", Médico: " + medico.getNome() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(data, consulta.data) &&
               Objects.equals(paciente, consulta.paciente) &&
               Objects.equals(medico, consulta.medico) &&
               Objects.equals(descricao, consulta.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, paciente, medico, descricao);
    }
}
