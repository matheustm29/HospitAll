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

public class Main {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("      INICIANDO SIMULAÇÃO - SISTEMA HOSPITALAR    ");
        System.out.println("==================================================");

        HospitAll meuHospital = new HospitAll("Hospital Central Cornélio Procópio");
        GeradorRelatorios relatorios = new GeradorRelatorios();

        System.out.println("\n----- 1. Cadastrando Médicos -----");
        Medico drHouse = new Medico("Gregory House", 12345, "Nefrologia", 40);
        Medico drWilson = new Medico("James Wilson", 54321, "Oncologia", 45);
        Medico drCuddy = new Medico("Lisa Cuddy", 10001, "Endocrinologia", 50);

        meuHospital.adicionarMedico(drHouse);
        meuHospital.adicionarMedico(drWilson);
        meuHospital.adicionarMedico(drCuddy);
        meuHospital.adicionarMedico(drHouse); 

        System.out.println("\n----- 2. Cadastrando Pacientes -----");
        Paciente joao = new PacienteCronico("João Silva", "111.222.333-44", 55, drHouse, "Hipertensão", "Losartana 50mg");
        Paciente maria = new PacienteInfeccioso("Maria Souza", "555.666.777-88", 32, drHouse, "COVID-19", LocalDate.of(2025, 5, 22));
        Paciente pedro = new PacienteTraumatico("Pedro Rocha", "444.555.666-77", 28, drCuddy, "Fratura Exposta", LocalDate.of(2025, 5, 15));
        Paciente ana = new PacienteCronico("Ana Costa", "888.999.000-11", 68, drWilson, "Diabetes", "Metformina");

        meuHospital.adicionarPaciente(joao);
        meuHospital.adicionarPaciente(maria);
        meuHospital.adicionarPaciente(pedro);
        meuHospital.adicionarPaciente(ana);

        System.out.println("\n----- 3. Atividades Médicas -----");
        drHouse.associarEstagiario("Dr. Chase");
        drHouse.associarEstagiario("Dra. Cameron");
        drHouse.listarEstagiarios();

        System.out.println("\n----- 4. Agendando Consultas -----");
        LocalDate hoje = LocalDate.now();
        Consulta c1 = new Consulta(hoje, "Acompanhamento Hipertensão", joao, drHouse);
        Consulta c2 = new Consulta(hoje.plusDays(1), "Avaliação COVID", maria, drHouse);
        Consulta c3 = new Consulta(hoje, "Acompanhamento Diabetes", ana, drWilson);
        Consulta c4 = new Consulta(hoje.plusDays(5), "Revisão Fratura", pedro, drCuddy);
        Consulta c5 = new Consulta(hoje.plusDays(2), "Check-up Geral", joao, drCuddy);

        meuHospital.agendarEregistrarConsulta(c1);
        meuHospital.agendarEregistrarConsulta(c2);
        meuHospital.agendarEregistrarConsulta(c3);
        meuHospital.agendarEregistrarConsulta(c4);
        meuHospital.agendarEregistrarConsulta(c5);

        System.out.println("\n----- 5. Atividades de Pacientes -----");
        joao.verHistorico();
        ana.pedirReceita("Insulina", "10UI antes do café");
        
        if (maria instanceof PacienteInfeccioso) {
             ((PacienteInfeccioso) maria).setEmIsolamento(false);
        }
        maria.verHistorico();

        System.out.println("\n----- 6. Verificando Agendas -----");
        drHouse.verAgenda();
        drCuddy.verAgenda();

        System.out.println("\n----- 7. Testando Buscas -----");
        meuHospital.demonstrarBusca("111.222.333-44", 54321);
        meuHospital.demonstrarBusca("000.000.000-00", 11111);

        System.out.println("\n----- 8. Gerando Relatório Final -----");
        relatorios.gerarRelatorioGeral(meuHospital);

        System.out.println("==================================================");
        System.out.println("                 SIMULAÇÃO FINALIZADA             ");
        System.out.println("==================================================");
    }
}
