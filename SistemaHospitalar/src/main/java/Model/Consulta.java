/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model; 

import java.time.LocalDate;
import java.util.Objects;

public class Consulta {

    private int idConsulta; // NOVO CAMPO: para o ID do banco de dados
    private LocalDate data;
    private String descricao;
    private Paciente paciente;
    private Medico medico;

    // Construtor atual (usado para criar novas consultas ANTES de salvar no BD)
    public Consulta(LocalDate data, String descricao, Paciente paciente, Medico medico) {
        if (data == null || paciente == null || medico == null) {
            throw new IllegalArgumentException("Data, Paciente e Médico são obrigatórios para criar uma consulta.");
        }
        this.data = data;
        this.descricao = (descricao != null && !descricao.trim().isEmpty()) ? descricao : "Consulta de rotina";
        this.paciente = paciente;
        this.medico = medico;
        // idConsulta não é definido aqui, será definido pelo DAO após inserção ou ao carregar do BD
    }
    
    // Getter e Setter para idConsulta
    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public void registrarConsulta() {
        System.out.println("\n--- DETALHES DA CONSULTA ---"); // Mudamos a mensagem, pois o DAO fará o registro no BD
        System.out.println("ID (se já salva): " + (this.idConsulta > 0 ? this.idConsulta : "Ainda não salva no BD"));
        System.out.println("Data: " + this.data);
        System.out.println("Paciente: " + this.paciente.getNome() + " (" + this.paciente.getTipoPaciente() + ")");
        System.out.println("Médico: " + this.medico.getNome() + " (" + this.medico.getEspecialidade() + ")");
        System.out.println("Descrição: " + this.descricao);
        
        // A lógica de adicionar aos históricos do médico/paciente pode permanecer
        // se você quiser manter essa informação em memória nos objetos carregados
        if (this.paciente != null) this.paciente.adicionarConsultaAoHistorico(this);
        if (this.medico != null) this.medico.adicionarConsultaRealizada(this);
        
        System.out.println("--- FIM DETALHES CONSULTA ---\n");
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
        return "Consulta[ID: "+ idConsulta +", Data: " + data + 
               ", Paciente: " + (paciente != null ? paciente.getNome() : "N/A") + 
               ", Médico: " + (medico != null ? medico.getNome() : "N/A") + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        // Se a consulta já tem ID do BD, compara por ID. Senão, pelos outros campos.
        if (idConsulta != 0 && consulta.idConsulta != 0) {
            return idConsulta == consulta.idConsulta;
        }
        return Objects.equals(data, consulta.data) &&
               Objects.equals(paciente, consulta.paciente) &&
               Objects.equals(medico, consulta.medico) &&
               Objects.equals(descricao, consulta.descricao);
    }

    @Override
    public int hashCode() {
         // Se a consulta já tem ID do BD, usa ID. Senão, os outros campos.
        if (idConsulta != 0) {
            return Objects.hash(idConsulta);
        }
        return Objects.hash(data, paciente, medico, descricao);
    }
}