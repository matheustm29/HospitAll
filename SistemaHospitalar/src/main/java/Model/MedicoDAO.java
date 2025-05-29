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
import java.util.Optional;

public class MedicoDAO {

    public void adicionarMedico(Medico medico) {
        String sql = "INSERT INTO medico (crm, nome, especialidade, carga_horaria) VALUES (?, ?, ?, ?)"; // Tabela "medico"
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
            System.out.println("Médico " + medico.getNome() + " adicionado com sucesso ao BD (tabela medico)!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar médico ao BD (tabela medico): " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, pstmt);
        }
    }

    public Optional<Medico> buscarMedicoPorCRM(int crm) {
        String sql = "SELECT nome, especialidade, carga_horaria FROM medico WHERE crm = ?"; // Tabela "medico"
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Medico medicoEncontrado = null;

        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, crm);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String especialidade = rs.getString("especialidade");
                int cargaHoraria = rs.getInt("carga_horaria");
                medicoEncontrado = new Medico(nome, crm, especialidade, cargaHoraria);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar médico por CRM (tabela medico): " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, pstmt, rs);
        }
        return Optional.ofNullable(medicoEncontrado);
    }

    public List<Medico> listarTodosMedicos() {
        String sql = "SELECT crm, nome, especialidade, carga_horaria FROM medico ORDER BY nome"; // Tabela "medico"
        List<Medico> listaMedicos = new ArrayList<>();
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
                listaMedicos.add(new Medico(nome, crm, especialidade, cargaHoraria));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar médicos (tabela medico): " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn, pstmt, rs);
        }
        return listaMedicos;
    }
    
    // Se você tiver métodos de ATUALIZAR e REMOVER, lembre-se de alterar o nome da tabela neles também:
    // Exemplo:
    // public void atualizarMedico(Medico medico) {
    //     String sql = "UPDATE medico SET nome = ?, especialidade = ?, carga_horaria = ? WHERE crm = ?";
    //     // ... restante do código ...
    // }

    // public void removerMedico(int crm) {
    //     String sql = "DELETE FROM medico WHERE crm = ?";
    //     // ... restante do código ...
    // }
}