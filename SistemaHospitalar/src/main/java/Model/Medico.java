/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medico {

    private String nome;
    private int crm;
    private String especialidade;
    private int cargaHoraria;
    private List<String> estagiariosAssociados;
    private List<Consulta> consultasRealizadas;

    public Medico(String nome, int crm, String especialidade, int cargaHoraria) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do médico não pode ser vazio.");
        }
        if (crm <= 0) {
            throw new IllegalArgumentException("CRM deve ser um número positivo.");
        }
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.cargaHoraria = cargaHoraria;
        this.estagiariosAssociados = new ArrayList<>();
        this.consultasRealizadas = new ArrayList<>();
    }

    public void receitar(Paciente paciente, String medicamento, String dosagem) {
        if (paciente == null || medicamento == null || dosagem == null) {
            System.out.println("Erro: Dados insuficientes para gerar receita.");
            return;
        }

        System.out.println("\n--- PRESCRIÇÃO MÉDICA ---");
        System.out.println("Data: " + LocalDate.now());
        System.out.println("Médico: Dr(a). " + this.nome + " | CRM: " + this.crm + " | Esp.: " + this.especialidade);
        System.out.println("Paciente: " + paciente.getNome() + " | CPF: " + paciente.getCpf());
        System.out.println("-------------------------");
        System.out.println("Medicamento: " + medicamento);
        System.out.println("Dosagem/Uso: " + dosagem);
        System.out.println("-------------------------");
        System.out.println("Assinatura: Dr(a). " + this.nome);
        System.out.println("--- FIM DA PRESCRIÇÃO ---\n");
    }

    public void associarEstagiario(String nomeEstagiario) {
        if (nomeEstagiario != null && !nomeEstagiario.trim().isEmpty()) {
            if (!this.estagiariosAssociados.contains(nomeEstagiario.trim())) {
                this.estagiariosAssociados.add(nomeEstagiario.trim());
                System.out.println("Estagiário '" + nomeEstagiario.trim() + "' associado ao Dr(a). " + this.nome + ".");
            } else {
                System.out.println("Estagiário '" + nomeEstagiario.trim() + "' já está associado a este médico.");
            }
        } else {
            System.out.println("Erro: Nome do estagiário inválido.");
        }
    }

    public void adicionarConsultaRealizada(Consulta consulta) {
        if (consulta != null && this.equals(consulta.getMedico())) {
            if (!this.consultasRealizadas.contains(consulta)) {
                 this.consultasRealizadas.add(consulta);
                 System.out.println("Consulta de " + consulta.getData() + " adicionada ao histórico do Dr(a). " + this.nome);
            } else {
                 System.out.println("Consulta já registrada para o Dr(a). " + this.nome);
            }
        } else {
            System.out.println("Erro: Não foi possível adicionar a consulta (Inválida ou não pertence a este médico).");
        }
    }

    public void listarEstagiarios() {
        System.out.println("\n--- Estagiários do Dr(a). " + this.nome + " ---");
        if (this.estagiariosAssociados.isEmpty()) {
            System.out.println("Nenhum estagiário associado no momento.");
        } else {
            for (int i = 0; i < this.estagiariosAssociados.size(); i++) {
                System.out.println((i + 1) + ". " + this.estagiariosAssociados.get(i));
            }
        }
        System.out.println("---------------------------------------\n");
    }

     public void verAgenda() {
         System.out.println("\n--- Agenda/Histórico do Dr(a). " + this.nome + " ---");
         if (this.consultasRealizadas.isEmpty()) {
             System.out.println("Nenhuma consulta registrada.");
         } else {
             this.consultasRealizadas.sort((c1, c2) -> c1.getData().compareTo(c2.getData()));

             for (Consulta c : this.consultasRealizadas) {
                 System.out.println("- " + c.getData() + ": " + c.getPaciente().getNome() + " (" + c.getDescricao().substring(0, Math.min(c.getDescricao().length(), 30)) + "...)");
             }
         }
         System.out.println("----------------------------------------\n");
     }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
         if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }

    public int getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public List<String> getEstagiariosAssociados() {
        return new ArrayList<>(estagiariosAssociados);
    }

    public List<Consulta> getConsultasRealizadas() {
        return new ArrayList<>(consultasRealizadas);
    }

    @Override
    public String toString() {
        return "Medico [Nome: " + nome + ", CRM: " + crm + ", Especialidade: " + especialidade + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return crm == medico.crm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(crm);
    }
}
