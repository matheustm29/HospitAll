/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import Controller.Conexao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HospitAll {

    private final List<Paciente> pacientes;
    private final MedicoDAO medicoDAO; 
    private final List<Consulta> todasConsultas;
    private final String nomeHospital;

    public HospitAll(String nomeHospital) {
        this.nomeHospital = (nomeHospital != null && !nomeHospital.trim().isEmpty()) ? nomeHospital.trim() : "Hospital Central Padrão";
        this.medicoDAO = new MedicoDAO();
        this.pacientes = new ArrayList<>();
        this.todasConsultas = new ArrayList<>();
    }

    public boolean adicionarMedico(Medico medico) {
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

    public Optional<Medico> buscaPorCRM(int crm) {
        return medicoDAO.buscarMedicoPorCRM(crm);
    }

    public List<Medico> getMedicos() {
        return medicoDAO.listarTodosMedicos();
    }

    public boolean adicionarPaciente(Paciente paciente) {
        if (paciente != null && !this.pacientes.contains(paciente)) {
            this.pacientes.add(paciente);
            System.out.println("Paciente " + paciente.getNome() + " (CPF: " + paciente.getCpf() + ") adicionado (em memória) ao sistema " + this.nomeHospital + ".");
            return true;
        }
        System.out.println("Não foi possível adicionar o paciente (Nulo ou CPF já cadastrado em memória).");
        return false;
    }

    public Optional<Paciente> buscaPorCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return Optional.empty();
        }
        return this.pacientes.stream()
                   .filter(p -> cpf.equals(p.getCpf()))
                   .findFirst();
    }
    
    public List<Paciente> buscaPacientePorMedico(Medico medico) {
        if (medico == null) {
            return new ArrayList<>();
        }
        return this.pacientes.stream()
                   .filter(p -> medico.equals(p.getMedico()))
                   .collect(Collectors.toList());
    }

    public boolean agendarEregistrarConsulta(Consulta consulta) {
        if (consulta != null && !this.todasConsultas.contains(consulta)) {
            
            boolean medicoValido = false;
            Optional<Medico> medicoDaConsultaOpt = this.buscaPorCRM(consulta.getMedico().getCrm());
            if (medicoDaConsultaOpt.isPresent() && medicoDaConsultaOpt.get().equals(consulta.getMedico())) {
                medicoValido = true;
            }

            boolean pacienteValido = false;
            Optional<Paciente> pacienteDaConsultaOpt = this.buscaPorCPF(consulta.getPaciente().getCpf());
             if (pacienteDaConsultaOpt.isPresent() && pacienteDaConsultaOpt.get().equals(consulta.getPaciente())) {
                pacienteValido = true;
            }

            if (medicoValido && pacienteValido) {
                this.todasConsultas.add(consulta);
                consulta.registrarConsulta(); 
                return true;
            } else {
                 if (!medicoValido) System.out.println("Falha ao agendar: Médico da consulta não encontrado ou não corresponde ao cadastrado no sistema.");
                 if (!pacienteValido) System.out.println("Falha ao agendar: Paciente da consulta não encontrado ou não corresponde ao cadastrado no sistema.");
            }
        } else {
            if (consulta == null) System.out.println("Falha ao agendar: objeto consulta nulo.");
            else System.out.println("Falha ao agendar: Consulta já registrada ou dados inválidos.");
        }
        return false;
    }

    public void demonstrarBusca(String cpf, int crm) {
        System.out.println("\n--- DEMONSTRAÇÃO DE BUSCAS ---");
        buscaPorCPF(cpf).ifPresentOrElse(
            p -> System.out.println("Busca Paciente CPF '" + cpf + "': Encontrado (em memória) -> " + p.getNome()),
            () -> System.out.println("Busca Paciente CPF '" + cpf + "': Não encontrado (em memória).")
        );

        buscaPorCRM(crm).ifPresentOrElse(
            m -> System.out.println("Busca Médico CRM '" + crm + "': Encontrado (no BD) -> " + m.getNome()),
            () -> System.out.println("Busca Médico CRM '" + crm + "': Não encontrado (no BD).")
        );
         System.out.println("------------------------------\n");
    }

    public void gerenciar() {
        System.out.println("Sistema de Gerenciamento " + this.nomeHospital + " - Acesso Restrito.");
    }

    public List<Paciente> getPacientes() {
        return new ArrayList<>(pacientes);
    }

    public List<Consulta> getTodasConsultas() {
        return new ArrayList<>(todasConsultas);
    }

    public String getNomeHospital() {
        return nomeHospital;
    }
}