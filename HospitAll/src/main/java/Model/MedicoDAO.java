/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.Medico;
import Model.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {


    public void save(Medico medico) {
        String sql = "INSERT INTO hospital.pessoa (nome, cpf, idade, tipo_pessoa, crm, espec, salario) " +
                     "VALUES (?, ?, ?, 'MEDICO', ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getNome());
            stmt.setInt(2, medico.getCpf());
            stmt.setInt(3, medico.getIdade());
            stmt.setInt(4, medico.getCrm());
            stmt.setString(5, medico.getEspec());
            stmt.setFloat(6, medico.getSalario());
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar médico: " + e.getMessage(), e);
        }
    }

    public Medico findByCrm(int crm) {
        String sql = "SELECT * FROM hospital.pessoa WHERE crm = ? AND tipo_pessoa = 'MEDICO'";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, crm);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Medico medico = new Medico();
                    medico.setNome(rs.getString("nome"));
                    medico.setCpf(rs.getInt("cpf"));
                    medico.setIdade(rs.getInt("idade"));
                    medico.setCrm(rs.getInt("crm"));
                    medico.setEspec(rs.getString("espec"));
                    medico.setSalario(rs.getFloat("salario"));
                    return medico;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar médico por CRM: " + e.getMessage(), e);
        }
        return null; 
    }

    
    public boolean updateSalario(int crm, float novoSalario) {
        String sql = "UPDATE hospital.pessoa SET salario = ? WHERE crm = ? AND tipo_pessoa = 'MEDICO'";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, novoSalario);
            stmt.setInt(2, crm);
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar salário: " + e.getMessage(), e);
        }
    }

  
    public boolean deleteByCrm(int crm) {
        String sql = "DELETE FROM hospital.pessoa WHERE crm = ? AND tipo_pessoa = 'MEDICO'";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, crm);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar médico: " + e.getMessage(), e);
        }
    }

 
    public List<Medico> findAll() {
        String sql = "SELECT * FROM hospital.pessoa WHERE tipo_pessoa = 'MEDICO'";
        List<Medico> medicos = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setNome(rs.getString("nome"));
                medico.setCpf(rs.getInt("cpf"));
                medico.setIdade(rs.getInt("idade"));
                medico.setCrm(rs.getInt("crm"));
                medico.setEspec(rs.getString("espec"));
                medico.setSalario(rs.getFloat("salario"));
                medicos.add(medico);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar médicos: " + e.getMessage(), e);
        }
        return medicos;
    }
}