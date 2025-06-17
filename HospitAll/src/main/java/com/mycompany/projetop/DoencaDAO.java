/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DoencaDAO {

    /**
     * Salva um objeto Doenca no banco de dados e retorna o objeto com o ID preenchido.
     * @param doenca O objeto Doenca a ser salvo (ainda sem ID).
     * @return O mesmo objeto Doenca, agora com o ID gerado pelo banco.
     */
    public Doenca save(Doenca doenca) {
        // SQL para inserir uma doença na tabela.
        String sql = "INSERT INTO hospital.doenca (tipo, sintomas, restricoes) VALUES (?, ?, ?)";

        // Usamos try-with-resources para garantir que a conexão será fechada.
        try (Connection conn = ConnectionFactory.getConnection();
             // Pedimos ao PreparedStatement para retornar as chaves geradas (o ID).
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, doenca.getTipo());
            stmt.setString(2, doenca.getSint());
            stmt.setString(3, doenca.getRestr());

            // Executa a inserção.
            stmt.executeUpdate();

            // Após executar, recuperamos o conjunto de chaves geradas.
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                // Se houver uma chave (e haverá), pegamos o ID...
                if (rs.next()) {
                    // ...e o atribuímos de volta ao nosso objeto doenca.
                    doenca.setId(rs.getInt(1)); // Supondo que sua classe Doenca tenha um setId(int id)
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar doença no banco de dados.", e);
        }
        
        // Retornamos o objeto completo.
        return doenca;
    }
}