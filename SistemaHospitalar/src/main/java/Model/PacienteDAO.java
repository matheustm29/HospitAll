/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model; // Ou Model.dao, conforme sua estrutura

import Controller.Conexao; // Ajuste se o pacote da Conexao for diferente
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types; // Para usar Types.INTEGER, Types.VARCHAR etc. ao setar nulos
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteDAO {

    public void adicionarPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (cpf, nome, idade, medico_crm, tipo_paciente, " +
                     "doenca_cronica, tratamento_continuo, " + 
                     "tipo_infeccao, is_isolado, data_inicio_infeccao, " +
                     "tipo_trauma, data_acidente) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, paciente.getCpf());
            pstmt.setString(2, paciente.getNome());
            pstmt.setInt(3, paciente.getIdade());

            if (paciente.getMedico() != null) {
                pstmt.setInt(4, paciente.getMedico().getCrm());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            if (paciente instanceof PacienteCronico pc) {
                pstmt.setString(5, "CRONICO");
                pstmt.setString(6, pc.getDoencaCronica());
                pstmt.setString(7, pc.getTratamentoContinuo());
                pstmt.setNull(8, Types.VARCHAR);   // tipo_infeccao
                pstmt.setNull(9, Types.BOOLEAN);   // is_isolado
                pstmt.setNull(10, Types.DATE);     // data_inicio_infeccao
                pstmt.setNull(11, Types.VARCHAR);  // tipo_trauma
                pstmt.setNull(12, Types.DATE);     // data_acidente
            } else if (paciente instanceof PacienteInfeccioso pi) {
                pstmt.setString(5, "INFECCIOSO");
                pstmt.setNull(6, Types.VARCHAR);   // doenca_cronica
                pstmt.setNull(7, Types.VARCHAR);   // tratamento_continuo
                pstmt.setString(8, pi.getTipoInfeccao());
                pstmt.setBoolean(9, pi.isIsolado());
                pstmt.setDate(10, pi.getDataInicioInfeccao() != null ? java.sql.Date.valueOf(pi.getDataInicioInfeccao()) : null);
                pstmt.setNull(11, Types.VARCHAR);  // tipo_trauma
                pstmt.setNull(12, Types.DATE);     // data_acidente
            } else if (paciente instanceof PacienteTraumatico pt) {
                pstmt.setString(5, "TRAUMATICO");
                pstmt.setNull(6, Types.VARCHAR);   // doenca_cronica
                pstmt.setNull(7, Types.VARCHAR);   // tratamento_continuo
                pstmt.setNull(8, Types.VARCHAR);   // tipo_infeccao
                pstmt.setNull(9, Types.BOOLEAN);   // is_isolado
                pstmt.setNull(10, Types.DATE);     // data_inicio_infeccao
                pstmt.setString(11, pt.getTipoTrauma());
                pstmt.setDate(12, pt.getDataAcidente() != null ? java.sql.Date.valueOf(pt.getDataAcidente()) : null);
            } else {
                System.err.println("Tipo de Paciente desconhecido para inserção no BD.");
                return; 
            }
            
            pstmt.executeUpdate();
            System.out.println("Paciente " + paciente.getNome() + " ("+ paciente.getTipoPaciente() +") adicionado com sucesso ao BD!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar paciente ao BD: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conn, pstmt);
        }
    }

    public Optional<Paciente> buscarPacientePorCPF(String cpf) {
        String sql = "SELECT * FROM paciente WHERE cpf = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Paciente paciente = null;

        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String nomeRet = rs.getString("nome");
                int idadeRet = rs.getInt("idade");
                int medicoCrmRet = rs.getInt("medico_crm");
                boolean medicoCrmIsNull = rs.wasNull(); // Verifica se o medico_crm era NULL no BD
                String tipoPacienteRet = rs.getString("tipo_paciente");

                Medico medicoAssociado = null;
                if (!medicoCrmIsNull && medicoCrmRet > 0) {
                    MedicoDAO medicoDAO = new MedicoDAO(); // Para buscar o médico associado
                    medicoAssociado = medicoDAO.buscarMedicoPorCRM(medicoCrmRet).orElse(null);
                     if (medicoAssociado == null) {
                        System.out.println("Aviso: Médico com CRM " + medicoCrmRet + " associado ao paciente " + cpf + " não encontrado no BD. Paciente será carregado sem médico.");
                    }
                }
                
                switch (tipoPacienteRet) {
                    case "CRONICO":
                        paciente = new PacienteCronico(nomeRet, cpf, idadeRet, medicoAssociado,
                                rs.getString("doenca_cronica"), rs.getString("tratamento_continuo"));
                        break;
                    case "INFECCIOSO":
                        java.sql.Date dataInicioSQL = rs.getDate("data_inicio_infeccao");
                        LocalDate dataInicioJava = (dataInicioSQL != null) ? dataInicioSQL.toLocalDate() : null;
                        PacienteInfeccioso pi = new PacienteInfeccioso(nomeRet, cpf, idadeRet, medicoAssociado,
                                rs.getString("tipo_infeccao"), dataInicioJava);
                        pi.setEmIsolamento(rs.getBoolean("is_isolado"));
                        paciente = pi;
                        break;
                    case "TRAUMATICO":
                        java.sql.Date dataAcidenteSQL = rs.getDate("data_acidente");
                        LocalDate dataAcidenteJava = (dataAcidenteSQL != null) ? dataAcidenteSQL.toLocalDate() : null;
                        paciente = new PacienteTraumatico(nomeRet, cpf, idadeRet, medicoAssociado,
                                rs.getString("tipo_trauma"), dataAcidenteJava);
                        break;
                    default:
                        System.err.println("Tipo de paciente desconhecido ("+ tipoPacienteRet +") encontrado no BD para CPF: " + cpf);
                        break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar paciente por CPF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conn, pstmt, rs);
        }
        return Optional.ofNullable(paciente);
    }

    public List<Paciente> listarTodosPacientes() {
        String sql = "SELECT * FROM paciente ORDER BY nome";
        List<Paciente> pacientes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String cpfRet = rs.getString("cpf");
                // Chamamos buscarPacientePorCPF para reutilizar a lógica de construção do objeto
                // Isso pode não ser o mais eficiente para muitos registros, mas é mais simples de implementar agora
                Optional<Paciente> optPaciente = buscarPacientePorCPF(cpfRet);
                optPaciente.ifPresent(pacientes::add);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conn, pstmt, rs);
        }
        return pacientes;
    }
    
    // TODO: Implementar métodos para atualizarPaciente e removerPaciente
}
