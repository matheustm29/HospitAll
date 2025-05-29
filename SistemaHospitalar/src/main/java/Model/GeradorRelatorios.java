/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GeradorRelatorios {

    public void gerarRelatorioGeral(HospitAll hospital) {
        if (hospital == null) {
            System.out.println("Não é possível gerar relatório: hospital nulo.");
            return;
        }

        List<Medico> medicos = hospital.getMedicos();
        List<Paciente> pacientes = hospital.getPacientes();
        List<Consulta> consultas = hospital.getTodasConsultas();

        System.out.println("\n=================================");
        System.out.println("   RELATÓRIO GERAL - " + hospital.getNomeHospital().toUpperCase());
        System.out.println("=================================");
        System.out.println("Data: " + LocalDate.now() + " Hora: " + LocalTime.now().withNano(0));
        System.out.println("---------------------------------");

        System.out.println("MÉDICOS (" + medicos.size() + "):");
        if (medicos.isEmpty()) {
            System.out.println("  Nenhum médico cadastrado.");
        } else {
            for (Medico m : medicos) {
                System.out.println("  -> Dr(a). " + m.getNome() + " (CRM: " + m.getCrm() + ", Esp: " + m.getEspecialidade() + ")");
            }
        }

        System.out.println("\nPACIENTES (" + pacientes.size() + "):");
        if (pacientes.isEmpty()) {
            System.out.println("  Nenhum paciente cadastrado.");
        } else {
            for (Paciente p : pacientes) {
                String nomeMedico = (p.getMedico() != null) ? "Dr(a). " + p.getMedico().getNome() : "N/A";
                System.out.println("  -> " + p.getNome() + " (CPF: " + p.getCpf() + ", Tipo: " + p.getTipoPaciente() + ", Médico: " + nomeMedico + ")");
            }
        }
        
        System.out.println("\nCONSULTAS REGISTRADAS (" + consultas.size() + "):");
         if (consultas.isEmpty()) {
            System.out.println("  Nenhuma consulta registrada.");
        } else {
            consultas.sort((c1, c2) -> c1.getData().compareTo(c2.getData()));
            for (Consulta c : consultas) {
                System.out.println("  -> " + c.getData() + " | " + c.getPaciente().getNome() + " com Dr(a). " + c.getMedico().getNome());
            }
        }
        
        System.out.println("=================================\n");
    }

}
