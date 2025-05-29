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
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("      INICIANDO SIMULAÇÃO - SISTEMA HOSPITALAR    ");
        System.out.println("         (Médicos no BD, outros em memória)       ");
        System.out.println("==================================================");

        HospitAll meuHospital = new HospitAll("Hospital Vida & Saúde Tech");
        GeradorRelatorios relatorios = new GeradorRelatorios();

        System.out.println("\n----- 1. Cadastrando Médicos (Banco de Dados) -----");
        Medico drHouse = new Medico("Gregory House", 12345, "Diagnóstico", 38);
        Medico drWilson = new Medico("James Wilson", 54321, "Oncologia", 42);
        Medico drChase = new Medico("Robert Chase", 67890, "Cirurgia Intensiva", 35);

        meuHospital.adicionarMedico(drHouse);
        meuHospital.adicionarMedico(drWilson);
        meuHospital.adicionarMedico(drChase);

        System.out.println("\nTentando adicionar Dr. House (CRM 12345) novamente...");
        meuHospital.adicionarMedico(new Medico("G. House Duplicado", 12345, "Nefrologia", 40)); 
        
        System.out.println("\n----- Listando Médicos do Banco de Dados -----");
        List<Medico> medicosDoSistema = meuHospital.getMedicos();
        if (medicosDoSistema.isEmpty()) {
            System.out.println("Nenhum médico cadastrado no banco de dados.");
        } else {
            medicosDoSistema.forEach(m -> System.out.println("BD: Dr(a). " + m.getNome() + ", CRM: " + m.getCrm()));
        }

        System.out.println("\n----- Buscando Médicos Específicos no BD -----");
        meuHospital.buscaPorCRM(54321).ifPresentOrElse(
            m -> System.out.println("Busca CRM 54321: Encontrado Dr(a). " + m.getNome()),
            () -> System.out.println("Busca CRM 54321: Médico não encontrado.")
        );
        meuHospital.buscaPorCRM(11111).ifPresentOrElse(
            m -> System.out.println("Busca CRM 11111: Encontrado Dr(a). " + m.getNome()),
            () -> System.out.println("Busca CRM 11111: Médico não encontrado (esperado).")
        );


        System.out.println("\n----- 2. Cadastrando Pacientes (Em Memória) -----");
        Paciente joao = new PacienteCronico("João das Neves", "101.202.303-44", 58, drHouse, "Lúpus", "Imunossupressores");
        Paciente maria = new PacienteInfeccioso("Maria Clara", "505.606.707-88", 30, drChase, "Dengue Hemorrágica", LocalDate.of(2025, 5, 20));
        Paciente pedro = new PacienteTraumatico("Pedro Álvares", "404.505.606-77", 25, drChase, "Politrauma (acidente)", LocalDate.of(2025, 5, 10));
        
        meuHospital.adicionarPaciente(joao);
        meuHospital.adicionarPaciente(maria);
        meuHospital.adicionarPaciente(pedro);

        System.out.println("\n----- 3. Atividades Médicas (Dr. Chase) -----");
        drChase.associarEstagiario("Dr. Foreman");
        drChase.listarEstagiarios();


        System.out.println("\n----- 4. Agendando Consultas (Médico do BD, Paciente da Memória) -----");
        LocalDate hoje = LocalDate.now();
        Consulta c1 = new Consulta(hoje.plusDays(1), "Acompanhamento Lúpus", joao, drHouse);
        Consulta c2 = new Consulta(hoje.plusDays(2), "Monitoramento Dengue", maria, drChase);
        Consulta c3 = new Consulta(hoje.plusDays(7), "Reavaliação Pós-trauma", pedro, drChase);
        // Consulta com médico que não existe no BD para teste
        Medico drFantasma = new Medico("Dr. Fantasma", 77777, "Inexistente", 0);
        Consulta cFantasma = new Consulta(hoje.plusDays(3), "Consulta Teste", joao, drFantasma);


        meuHospital.agendarEregistrarConsulta(c1);
        meuHospital.agendarEregistrarConsulta(c2);
        meuHospital.agendarEregistrarConsulta(c3);
        System.out.println("\nTentando agendar consulta com médico inexistente no BD...");
        meuHospital.agendarEregistrarConsulta(cFantasma);


        System.out.println("\n----- 5. Atividades de Pacientes -----");
        joao.verHistorico();
        maria.pedirReceita("Hidratação Venosa e Repouso", "Contínuo");
        if (maria instanceof PacienteInfeccioso) {
             ((PacienteInfeccioso) maria).setEmIsolamento(false); 
        }


        System.out.println("\n----- 6. Verificando Agendas dos Médicos -----");
        drHouse.verAgenda();
        drChase.verAgenda();

        System.out.println("\n----- 7. Demonstração de Buscas Combinadas -----");
        meuHospital.demonstrarBusca(joao.getCpf(), drHouse.getCrm()); // Paciente em memória, Médico no BD
        meuHospital.demonstrarBusca("000.111.222-33", 999000); // Ambos inexistentes


        System.out.println("\n----- 8. Gerando Relatório Final -----");
        relatorios.gerarRelatorioGeral(meuHospital);


        System.out.println("==================================================");
        System.out.println("                 SIMULAÇÃO FINALIZADA             ");
        System.out.println("==================================================");
    }
}

