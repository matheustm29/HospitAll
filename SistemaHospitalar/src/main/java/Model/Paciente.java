/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Paciente {

    private String nome;
    private String cpf;
    private int idade;
    private Medico medico;
    private List<Consulta> historicoConsultas;

    protected Paciente(String nome, String cpf, int idade, Medico medico) {
        if (nome == null || nome.trim().isEmpty() || cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome e CPF do paciente são obrigatórios.");
        }
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.medico = medico;
        this.historicoConsultas = new ArrayList<>();
    }

    public abstract String getTipoPaciente();
    
    public abstract String getDescricaoCondicao();

    public void verHistorico() {
        System.out.println("\n--- Histórico do Paciente: " + this.nome + " (" + getTipoPaciente() + ") ---");
        System.out.println("CPF: " + this.cpf + " | Idade: " + this.idade);
        System.out.println("Médico Responsável: " + (this.medico != null ? this.medico.getNome() : "Nenhum"));
        System.out.println("Condição Principal: " + this.getDescricaoCondicao());

        System.out.println("\nConsultas Realizadas (" + this.historicoConsultas.size() + "):");
        if (historicoConsultas.isEmpty()) {
            System.out.println("Nenhuma consulta registrada.");
        } else {
            historicoConsultas.sort((c1, c2) -> c1.getData().compareTo(c2.getData()));
            for (Consulta c : historicoConsultas) {
                System.out.println("  - " + c.getData() + " | Dr(a). " + c.getMedico().getNome() + " | Motivo: " + c.getDescricao());
            }
        }
        System.out.println("--------------------------------------\n");
    }

    public void pedirReceita(String medicamento, String dosagem) {
        System.out.println("Paciente " + this.nome + " está a solicitar uma receita.");
        if (this.medico != null) {
            this.medico.receitar(this, medicamento, dosagem);
        } else {
            System.out.println("Nenhum médico associado para gerar receita para " + this.nome + ".");
        }
    }

    public void agendar() {
        System.out.println("Paciente " + this.nome + " deseja agendar uma nova consulta.");
        System.out.println("Por favor, contacte a recepção para marcar com " + (this.medico != null ? "Dr(a). " + this.medico.getNome() : "um médico disponível") + ".");
    }

    public void adicionarConsultaAoHistorico(Consulta consulta) {
        if (consulta != null && this.equals(consulta.getPaciente())) {
            if (!this.historicoConsultas.contains(consulta)) {
                this.historicoConsultas.add(consulta);
                System.out.println("Consulta de " + consulta.getData() + " adicionada ao histórico de " + this.nome + ".");
            }
        } else {
             System.out.println("Não foi possível adicionar a consulta ao histórico de " + this.nome + " (Inválida ou outro paciente).");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
        System.out.println("Médico " + (medico != null ? medico.getNome() : "Nenhum") + " agora é responsável por " + this.nome + ".");
    }

    public List<Consulta> getHistoricoConsultas() {
        return new ArrayList<>(historicoConsultas);
    }

    @Override
    public String toString() {
        return "Paciente [Tipo: " + getTipoPaciente() + ", Nome: " + nome + ", CPF: " + cpf + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(cpf, paciente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
