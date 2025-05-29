/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model; // Ou Model.dao

import Controller.Conexao; // Ajuste se necessário
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Para Statement.RETURN_GENERATED_KEYS
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultaDAO {

    public Consulta adicionarConsulta(Consulta consulta) {
        String sql = "INSERT INTO consulta (data_consulta, descricao, paciente_cpf, medico_crm) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setDate(1, java.sql.Date.valueOf(consulta.getData()));
            pstmt.setString(2, consulta.getDescricao());
            pstmt.setString(3, consulta.getPaciente().getCpf());
            pstmt.setInt(4, consulta.getMedico().getCrm());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    consulta.setIdConsulta(generatedKeys.getInt(1)); // Define o ID gerado no objeto
                    System.out.println("Consulta (ID: " + consulta.getIdConsulta() + ") para " + consulta.getPaciente().getNome() + " adicionada com sucesso ao BD!");
                }
            }
            return consulta; // Retorna a consulta com o ID preenchido
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar consulta ao BD: " + e.getMessage());
            e.printStackTrace();
            return null; // Ou lançar uma exceção
        } finally {
            Conexao.fecharConexao(conn, pstmt, generatedKeys);
        }
    }

    public Optional<Consulta> buscarConsultaPorId(int idConsulta) {
        String sql = "SELECT * FROM consulta WHERE id_consulta = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Consulta consulta = null;

        // DAOs para buscar os objetos Paciente e Medico associados
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();

        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idConsulta);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                LocalDate data = rs.getDate("data_consulta").toLocalDate();
                String descricao = rs.getString("descricao");
                String pacienteCpf = rs.getString("paciente_cpf");
                int medicoCrm = rs.getInt("medico_crm");

                Paciente paciente = pacienteDAO.buscarPacientePorCPF(pacienteCpf).orElse(null);
                Medico medico = medicoDAO.buscarMedicoPorCRM(medicoCrm).orElse(null);

                if (paciente != null && medico != null) {
                    consulta = new Consulta(data, descricao, paciente, medico);
                    consulta.setIdConsulta(rs.getInt("id_consulta")); // Define o ID do BD
                } else {
                    System.err.println("Erro ao buscar consulta ID " + idConsulta + ": Paciente ou Médico associado não encontrado no BD.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consulta por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conn, pstmt, rs);
        }
        return Optional.ofNullable(consulta);
    }

    public List<Consulta> listarTodasConsultas() {
        String sql = "SELECT id_consulta FROM consulta ORDER BY data_consulta DESC, id_consulta DESC"; // Pega só os IDs
        List<Consulta> consultas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int idConsulta = rs.getInt("id_consulta");
                // Reutiliza buscarConsultaPorId para construir o objeto completo
                buscarConsultaPorId(idConsulta).ifPresent(consultas::add);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todas as consultas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conn, pstmt, rs);
        }
        return consultas;
    }
    
    // TODO: Implementar métodos para atualizarConsulta e removerConsulta(int idConsulta)
}
