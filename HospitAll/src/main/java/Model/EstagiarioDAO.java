/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.Estagiario;
import Model.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstagiarioDAO {

    public void save(Estagiario estagiario) {

        String sql = "INSERT INTO hospital.pessoa (nome, cpf, idade, tipo_pessoa, instituicao_ensino, periodo, curso) " +
                     "VALUES (?, ?, ?, 'ESTAGIARIO', ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estagiario.getNome());
            stmt.setInt(2, estagiario.getCpf());
            stmt.setInt(3, estagiario.getIdade());
            stmt.setString(4, estagiario.getInstituicaoEnsino());
            stmt.setInt(5, estagiario.getPeriodo());
            stmt.setString(6, estagiario.getCurso()); 
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar estagiário: " + e.getMessage(), e);
        }
    }

    public Estagiario findByCpf(int cpf) {
        String sql = "SELECT * FROM hospital.pessoa WHERE cpf = ? AND tipo_pessoa = 'ESTAGIARIO'";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Estagiario est = new Estagiario();
                    est.setNome(rs.getString("nome"));
                    est.setCpf(rs.getInt("cpf"));
                    est.setIdade(rs.getInt("idade"));
                    est.setInstituicaoEnsino(rs.getString("instituicao_ensino"));
                    try{
                        est.setPeriodo(rs.getInt("periodo"));
                    } catch(PerEstException e){
                        System.err.println("Aviso: Dado de período inválido no banco para o estagiário:" + est.getNome());
                    }
                    est.setCurso(rs.getString("curso")); 
                    return est;
                }
            }
        } catch (SQLException e) { 
            throw new RuntimeException("Erro ao buscar estagiário por CPF: " + e.getMessage(), e);
        }
        return null;
    }

    public boolean updatePeriodo(int cpf, int novoPeriodo) {
        String sql = "UPDATE hospital.pessoa SET periodo = ? WHERE cpf = ? AND tipo_pessoa = 'ESTAGIARIO'";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novoPeriodo);
            stmt.setInt(2, cpf);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar período: " + e.getMessage(), e);
        }
    }

    public boolean deleteByCpf(int cpf) {
        String sql = "DELETE FROM hospital.pessoa WHERE cpf = ? AND tipo_pessoa = 'ESTAGIARIO'";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cpf);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar estagiário: " + e.getMessage(), e);
        }
    }
    
    public List<Estagiario> findAll() {
        String sql = "SELECT * FROM hospital.pessoa WHERE tipo_pessoa = 'ESTAGIARIO'";
        List<Estagiario> estagiarios = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Estagiario est = new Estagiario();
                est.setNome(rs.getString("nome"));
                est.setCpf(rs.getInt("cpf"));
                est.setIdade(rs.getInt("idade"));
                est.setInstituicaoEnsino(rs.getString("instituicao_ensino"));
                try{
                    est.setPeriodo(rs.getInt("periodo"));
                } catch(PerEstException e){
                    System.err.println("Aviso: Dado de período inválido no banco para o estagiário:" + est.getNome());
                }
                est.setCurso(rs.getString("curso")); 
                
                estagiarios.add(est);
            }
        } catch (SQLException e) { 
            throw new RuntimeException("Erro ao listar estagiários: " + e.getMessage(), e);
        }
        return estagiarios;
    }
}