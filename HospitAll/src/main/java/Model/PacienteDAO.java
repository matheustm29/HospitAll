/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Model.Paciente;
import Model.DoencaDAO;
import Model.Doenca;
import Model.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void save(Paciente paciente) {

        DoencaDAO doencaDAO = new DoencaDAO();
        
        doencaDAO.save(paciente.getDoenca());

        
        String sql = "INSERT INTO hospital.pessoa (nome, cpf, idade, tipo_pessoa, doenca_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
          
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getCpf());
            stmt.setInt(3, paciente.getIdade());
            
            
            stmt.setString(4, "PACIENTE"); 
            stmt.setInt(5, paciente.getDoenca().getId()); 

            stmt.executeUpdate();

            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    paciente.setId(rs.getInt(1)); 
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar paciente.", e);
        }
    }
    public List<Paciente> listarTodos() {
    
    String sql = "SELECT p.*, d.tipo as doenca_tipo, d.sintomas, d.restricoes " +
                 "FROM hospital.pessoa p " +
                 "LEFT JOIN hospital.doenca d ON p.doenca_id = d.id " +
                 "WHERE p.tipo_pessoa = 'PACIENTE'";

    List<Paciente> pacientes = new ArrayList<>();

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

       
        while (rs.next()) {
            
            Paciente paciente = new Paciente();
            paciente.setId(rs.getInt("id"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getInt("cpf"));
            paciente.setIdade(rs.getInt("idade"));

            
            int doencaId = rs.getInt("doenca_id");
            if (!rs.wasNull()) { 
                Doenca doenca = new Doenca();
                
                doenca.setId(doencaId);
                
                doenca.setTipo(rs.getString("doenca_tipo")); 
                doenca.setSint(rs.getString("sintomas"));
                doenca.setRestr(rs.getString("restricoes"));
                paciente.setDoenca(doenca);
            }
            
            
            pacientes.add(paciente);
        }
    } catch (Exception e) { 
        throw new RuntimeException("Erro ao listar pacientes.", e);
    }

    return pacientes; 
    }
    public Paciente buscarPorId(int id) {
        String sql = "SELECT p.*, d.tipo as doenca_tipo, d.sintomas, d.restricoes " +
                     "FROM hospital.pessoa p " +
                     "LEFT JOIN hospital.doenca d ON p.doenca_id = d.id " +
                     "WHERE p.id = ? AND p.tipo_pessoa = 'PACIENTE'";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { 
                    Paciente paciente = new Paciente();
                    paciente.setId(rs.getInt("id"));
                    paciente.setNome(rs.getString("nome"));
                    paciente.setCpf(rs.getInt("cpf"));
                    paciente.setIdade(rs.getInt("idade"));

                    int doencaId = rs.getInt("doenca_id");
                    if (!rs.wasNull()) {
                        Doenca doenca = new Doenca();
                        doenca.setId(doencaId);
                        doenca.setTipo(rs.getString("doenca_tipo"));
                        doenca.setSint(rs.getString("sintomas"));
                        doenca.setRestr(rs.getString("restricoes"));
                        paciente.setDoenca(doenca);
                    }
                    return paciente;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar paciente por ID.", e);
        }

        return null; 
    }


    public boolean deletar(int id) {
        String sql = "DELETE FROM hospital.pessoa WHERE id = ? AND tipo_pessoa = 'PACIENTE'";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar paciente.", e);
        }
    }
    public boolean atualizarRestricoes(int pacienteId, String novasRestricoes) {
    
        String sql = "UPDATE hospital.doenca SET restricoes = ? WHERE id = " +
                     "(SELECT doenca_id FROM hospital.pessoa WHERE id = ? AND tipo_pessoa = 'PACIENTE')";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novasRestricoes);
            stmt.setInt(2, pacienteId);

            int linhasAfetadas = stmt.executeUpdate();

            
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar restrições do paciente.", e);
        }
    }
}