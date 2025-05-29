/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model; // Ou Model.dao

import Controller.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Para o método de busca

public class MedicoDAO {

    /**
     * Adiciona um novo médico ao banco de dados.
     * @param medico O objeto Medico a ser adicionado.
     */
    public void adicionarMedico(Medico medico) {
        String sql = "INSERT INTO medicos (crm, nome, especialidade, carga_horaria) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, medico.getCrm());
            pstmt.setString(2, medico.getNome());
            pstmt.setString(3, medico.getEspecialidade());
            pstmt.setInt(4, medico.getCargaHoraria());
            pstmt.executeUpdate();
            System.out.println("Médico " + medico.getNome() + " adicionado com sucesso ao BD!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar médico ao BD: " + e.getMessage());
            // Você pode querer lançar uma exceção customizada aqui
        } finally {
            Conexao.fecharConexao(conn, pstmt);
        }
    }

    /**
     * Busca um médico pelo CRM.
     * @param crm O CRM do médico a ser buscado.
     * @return Um Optional contendo o Medico se encontrado, ou Optional.empty() caso contrário.
     */
    public Optional<Medico> buscarMedicoPorCRM(int crm) {
        String sql = "SELECT nome, especialidade, carga_horaria FROM medicos WHERE crm = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Medico medico = null;

        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, crm);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String especialidade = rs.getString("especialidade");
                int cargaHoraria = rs.getInt("carga_horaria");
                medico = new Medico(nome, crm, especialidade, cargaHoraria);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar médico por CRM: " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, pstmt, rs);
        }
        return Optional.ofNullable(medico);
    }

    /**
     * Lista todos os médicos cadastrados.
     * @return Uma lista de objetos Medico.
     */
    public List<Medico> listarTodosMedicos() {
        String sql = "SELECT crm, nome, especialidade, carga_horaria FROM medicos ORDER BY nome";
        List<Medico> medicos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int crm = rs.getInt("crm");
                String nome = rs.getString("nome");
                String especialidade = rs.getString("especialidade");
                int cargaHoraria = rs.getInt("carga_horaria");
                medicos.add(new Medico(nome, crm, especialidade, cargaHoraria));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar médicos: " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, pstmt, rs);
        }
        return medicos;
    }

    // TODO: Implementar métodos para atualizarMedico e removerMedico
    // public void atualizarMedico(Medico medico) { ... }
    // public void removerMedico(int crm) { ... }
}