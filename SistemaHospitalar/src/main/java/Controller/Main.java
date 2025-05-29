/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controller;

import Model.HospitAll;
import Model.Medico;
import Model.Paciente;
import Model.PacienteCronico;
import Model.PacienteInfeccioso;
import Model.PacienteTraumatico;
import Model.Consulta;
import Model.GeradorRelatorios;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      INICIANDO SIMULAÇÃO - SISTEMA HOSPITALAR    ");
        System.out.println("         (TODAS AS ENTIDADES NO BD)       ");
        System.out.println("==================================================");

        HospitAll meuHospital = new HospitAll("Hospital Cura Total");
        GeradorRelatorios relatorios = new GeradorRelatorios();

        System.out.println("\n----- 1. Cadastrando Médicos (Banco de Dados) -----");
        Medico drHouse = new Medico("Gregory House", 12345, "Diagnóstico", 38);
        Medico drWilson = new Medico("James Wilson", 54321, "Oncologia", 42);
        meuHospital.adicionarMedico(drHouse);
        meuHospital.adicionarMedico(drWilson);
        
        System.out.println("\n----- 2. Cadastrando Pacientes (Banco de Dados) -----");
        // Para garantir que temos os objetos corretos do BD (com médico associado corretamente)
        // Buscamos os médicos recém-criados ou já existentes
        Medico medicoParaJoao = meuHospital.buscaPorCRM(12345).orElse(null);
        Medico medicoParaMaria = meuHospital.buscaPorCRM(54321).orElse(null);

        if (medicoParaJoao == null || medicoParaMaria == null) {
            System.err.println("ERRO CRÍTICO: Médicos base para pacientes não encontrados no BD. Abortando.");
            return;
        }

        Paciente joao = new PacienteCronico("João da Silva", "111.222.333-00", 60, medicoParaJoao, "Hipertensão Severa", "Controle de Pressão");
        Paciente maria = new PacienteInfeccioso("Maria Oliveira", "222.333.444-00", 35, medicoParaMaria, "Pneumonia", LocalDate.of(2025, 5, 20));
        ((PacienteInfeccioso)maria).setEmIsolamento(true);
        
        meuHospital.adicionarPaciente(joao);
        meuHospital.adicionarPaciente(maria);

        System.out.println("\n----- Listando Pacientes do Banco de Dados -----");
        meuHospital.getPacientes().forEach(p -> 
            System.out.println("BD: " + p.getNome() + ", CPF: " + p.getCpf() + ", Tipo: " + p.getTipoPaciente() + 
            (p.getMedico() != null ? ", Médico: Dr(a). " + p.getMedico().getNome() : ", Médico: N/A"))
        );

        System.out.println("\n----- 4. Agendando Consultas (Agora no BD) -----");
        LocalDate hoje = LocalDate.now();
        
        // Buscamos os pacientes do BD para garantir que temos as instâncias corretas
        Paciente joaoDoBd = meuHospital.buscaPorCPF("111.222.333-00").orElse(null);
        Paciente mariaDoBd = meuHospital.buscaPorCPF("222.333.444-00").orElse(null);
        Medico drHouseDoBd = meuHospital.buscaPorCRM(12345).orElse(null); // drHouse já está no BD

        if (joaoDoBd != null && drHouseDoBd != null) {
            Consulta c1 = new Consulta(hoje.plusDays(15), "Revisão Hipertensão", joaoDoBd, drHouseDoBd);
            meuHospital.agendarEregistrarConsulta(c1);
            System.out.println("Consulta c1 agendada com ID do BD: " + c1.getIdConsulta());
        } else {
            System.out.println("Não foi possível agendar c1: João ou Dr. House não encontrados no BD como esperado.");
        }

        if (mariaDoBd != null && medicoParaMaria != null) { // usando medicoParaMaria que é o mesmo que drWilson
             Consulta c2 = new Consulta(hoje.plusDays(20), "Acompanhamento Pneumonia", mariaDoBd, medicoParaMaria);
             meuHospital.agendarEregistrarConsulta(c2);
             System.out.println("Consulta c2 agendada com ID do BD: " + c2.getIdConsulta());
        } else {
            System.out.println("Não foi possível agendar c2: Maria ou Dr. Wilson não encontrados no BD como esperado.");
        }
        
        System.out.println("\n----- Listando Todas as Consultas do BD -----");
        List<Consulta> consultasDoSistema = meuHospital.getTodasConsultas();
        if (consultasDoSistema.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada no banco de dados.");
        } else {
            consultasDoSistema.forEach(c -> 
                System.out.println("BD Consulta ID: " + c.getIdConsulta() + " Data: " + c.getData() +
                                   " Pac: " + c.getPaciente().getNome() + " Med: " + c.getMedico().getNome())
            );
        }

        System.out.println("\n----- 8. Gerando Relatório Final (Com todas as entidades do BD) -----");
        relatorios.gerarRelatorioGeral(meuHospital);

        System.out.println("==================================================");
        System.out.println("                 SIMULAÇÃO FINALIZADA             ");
        System.out.println("==================================================");
    }
}