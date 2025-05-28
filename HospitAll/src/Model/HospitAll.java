/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HospitAll {

    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Consulta> todasConsultas;
    private String nomeHospital;

    public HospitAll(String nomeHospital) {
        this.nomeHospital = (nomeHospital != null) ? nomeHospital : "Hospital Central";
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.todasConsultas = new ArrayList<>();
    }

    public boolean adicionarMedico(Medico medico) {
        if (medico != null && !this.medicos.contains(medico)) {
            this.medicos.add(medico);
            System.out.println("Médico " + medico.getNome() + " (CRM: " + medico.getCrm() + ") adicionado ao sistema " + this.nomeHospital + ".");
            return true;
        }
        System.out.println("Não foi possível adicionar o médico (Nulo ou CRM já cadastrado).");
        return false;
    }

    public boolean adicionarPaciente(Paciente paciente) {
        if (paciente != null && !this.pacientes.contains(paciente)) {
            this.pacientes.add(paciente);
            System.out.println("Paciente " + paciente.getNome() + " (CPF: " + paciente.getCpf() + ") adicionado ao sistema " + this.nomeHospital + ".");
            return true;
        }
        System.out.println("Não foi possível adicionar o paciente (Nulo ou CPF já cadastrado).");
        return false;
    }

    public boolean agendarEregistrarConsulta(Consulta consulta) {
        if (consulta != null && !this.todasConsultas.contains(consulta) && 
            this.medicos.contains(consulta.getMedico()) && 
            this.pacientes.contains(consulta.getPaciente())) 
        {
            this.todasConsultas.add(consulta);
            consulta.registrarConsulta();
            return true;
        }
        System.out.println("Não foi possível agendar/registrar a consulta (Inválida, duplicada ou médico/paciente não cadastrado).");
        return false;
    }

    public Optional<Paciente> buscaPorCPF(String cpf) {
        return this.pacientes.stream()
                   .filter(p -> p.getCpf().equals(cpf))
                   .findFirst();
    }

    public Optional<Medico> buscaPorCRM(int crm) {
        return this.medicos.stream()
                   .filter(m -> m.getCrm() == crm)
                   .findFirst();
    }
    
    public List<Paciente> buscaPacientePorMedico(Medico medico) {
        return this.pacientes.stream()
                   .filter(p -> medico.equals(p.getMedico()))
                   .collect(Collectors.toList());
    }

    public void demonstrarBusca(String cpf, int crm) {
        System.out.println("\n--- DEMONSTRAÇÃO DE BUSCAS ---");
        buscaPorCPF(cpf).ifPresentOrElse(
            p -> System.out.println("Busca CPF '" + cpf + "': Encontrado -> " + p.getNome()),
            () -> System.out.println("Busca CPF '" + cpf + "': Não encontrado.")
        );

        buscaPorCRM(crm).ifPresentOrElse(
            m -> System.out.println("Busca CRM '" + crm + "': Encontrado -> " + m.getNome()),
            () -> System.out.println("Busca CRM '" + crm + "': Não encontrado.")
        );
         System.out.println("------------------------------\n");
    }

    public void gerenciar() {
        System.out.println("Sistema de Gerenciamento " + this.nomeHospital + " - Acesso Restrito.");
    }

    public List<Paciente> getPacientes() {
        return new ArrayList<>(pacientes);
    }

    public List<Medico> getMedicos() {
        return new ArrayList<>(medicos);
    }

    public List<Consulta> getTodasConsultas() {
        return new ArrayList<>(todasConsultas);
    }

    public String getNomeHospital() {
        return nomeHospital;
    }
}
