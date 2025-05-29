/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HospitAll {

    private final PacienteDAO pacienteDAO;
    private final MedicoDAO medicoDAO; 
    private final ConsultaDAO consultaDAO; // ADICIONE ESTA LINHA
    // private final List<Consulta> todasConsultas; // REMOVA ESTA LINHA
    private final String nomeHospital;

    public HospitAll(String nomeHospital) {
        this.nomeHospital = (nomeHospital != null && !nomeHospital.trim().isEmpty()) ? nomeHospital.trim() : "Hospital Central Padrão";
        this.medicoDAO = new MedicoDAO();
        this.pacienteDAO = new PacienteDAO();
        this.consultaDAO = new ConsultaDAO(); // ADICIONE: Instancia o ConsultaDAO
        // this.todasConsultas = new ArrayList<>(); // REMOVA ESTA LINHA
    }

    // Métodos de Medico e Paciente (como antes, usando seus DAOs)
    public boolean adicionarMedico(Medico medico) { //...código como antes... 
        if (medico != null) {
            Optional<Medico> medicoExistente = medicoDAO.buscarMedicoPorCRM(medico.getCrm());
            if (medicoExistente.isEmpty()) {
                medicoDAO.adicionarMedico(medico);
                return true;
            } else {
                 System.out.println("Não foi possível adicionar o médico: CRM " + medico.getCrm() + " já cadastrado no BD.");
                 return false;
            }
        }
        System.out.println("Não foi possível adicionar o médico (objeto nulo).");
        return false;
    }
    public Optional<Medico> buscaPorCRM(int crm) { return medicoDAO.buscarMedicoPorCRM(crm); }
    public List<Medico> getMedicos() { return medicoDAO.listarTodosMedicos(); }
    public boolean adicionarPaciente(Paciente paciente) { //...código como antes... 
        if (paciente != null) {
            Optional<Paciente> pacienteExistente = pacienteDAO.buscarPacientePorCPF(paciente.getCpf());
            if (pacienteExistente.isEmpty()) {
                pacienteDAO.adicionarPaciente(paciente); 
                return true;
            } else {
                System.out.println("Não foi possível adicionar o paciente: CPF " + paciente.getCpf() + " já cadastrado no BD.");
                return false;
            }
        }
        System.out.println("Não foi possível adicionar o paciente (objeto nulo).");
        return false;
    }
    public Optional<Paciente> buscaPorCPF(String cpf) { return pacienteDAO.buscarPacientePorCPF(cpf); }
    public List<Paciente> getPacientes() { return pacienteDAO.listarTodosPacientes(); }
     public List<Paciente> buscaPacientePorMedico(Medico medico) {
        if (medico == null) return new ArrayList<>();
        System.out.println("Aviso: buscaPacientePorMedico em HospitAll ainda filtra em memória após buscar todos do BD.");
        List<Paciente> todosPacientes = getPacientes();
        List<Paciente> pacientesDoMedico = new ArrayList<>();
        for(Paciente p : todosPacientes) {
            if(p.getMedico() != null && p.getMedico().equals(medico)) {
                pacientesDoMedico.add(p);
            }
        }
        return pacientesDoMedico;
    }


    // Métodos de Consulta (AGORA USANDO consultaDAO)
    public boolean agendarEregistrarConsulta(Consulta consulta) {
        if (consulta == null || consulta.getPaciente() == null || consulta.getMedico() == null) {
             System.out.println("Falha ao agendar: Consulta, paciente ou médico nulos.");
            return false;
        }

        // Verifica se o médico e o paciente da consulta existem no BD
        boolean medicoValido = this.buscaPorCRM(consulta.getMedico().getCrm())
                                   .map(m -> m.equals(consulta.getMedico()))
                                   .orElse(false);

        boolean pacienteValido = this.buscaPorCPF(consulta.getPaciente().getCpf())
                                     .map(p -> p.equals(consulta.getPaciente()))
                                     .orElse(false);

        if (medicoValido && pacienteValido) {
            Consulta consultaSalva = consultaDAO.adicionarConsulta(consulta);
            if (consultaSalva != null && consultaSalva.getIdConsulta() > 0) {
                // A consulta no objeto original já teve seu ID atualizado pelo DAO.
                // O método registrarConsulta pode ser chamado para atualizar listas em memória de Medico/Paciente, se essa lógica for mantida.
                consulta.registrarConsulta(); // Para manter a lógica de adicionar aos históricos em memória dos objetos
                return true;
            } else {
                System.out.println("Falha ao salvar consulta no banco de dados.");
                return false;
            }
        } else {
             if (!medicoValido) System.out.println("Falha ao agendar: Médico da consulta (CRM "+consulta.getMedico().getCrm()+") não encontrado ou dados não correspondem ao BD.");
             if (!pacienteValido) System.out.println("Falha ao agendar: Paciente da consulta (CPF "+consulta.getPaciente().getCpf()+") não encontrado ou dados não correspondem ao BD.");
            return false;
        }
    }

    public List<Consulta> getTodasConsultas() {
        return consultaDAO.listarTodasConsultas();
    }
    
    // Métodos restantes (demonstrarBusca, gerenciar, getNomeHospital)
    public void demonstrarBusca(String cpf, int crm) { //...código como antes...
        System.out.println("\n--- DEMONSTRAÇÃO DE BUSCAS ---");
        buscaPorCPF(cpf).ifPresentOrElse(
            p -> System.out.println("Busca Paciente CPF '" + cpf + "': Encontrado (no BD) -> " + p.getNome() + " ("+p.getTipoPaciente()+")"),
            () -> System.out.println("Busca Paciente CPF '" + cpf + "': Não encontrado (no BD).")
        );

        buscaPorCRM(crm).ifPresentOrElse(
            m -> System.out.println("Busca Médico CRM '" + crm + "': Encontrado (no BD) -> " + m.getNome()),
            () -> System.out.println("Busca Médico CRM '" + crm + "': Não encontrado (no BD).")
        );
         System.out.println("------------------------------\n");
    }
    public void gerenciar() { System.out.println("Sistema de Gerenciamento " + this.nomeHospital + " - Acesso Restrito."); }
    public String getNomeHospital() { return nomeHospital; }
}