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
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void save(Paciente paciente) {
        // 1. Salvar a doença primeiro para obter o seu ID.
        DoencaDAO doencaDAO = new DoencaDAO();
        // A mágica acontece aqui: salvamos a doença e o objeto 'doenca' é atualizado com o ID.
        doencaDAO.save(paciente.getDoenca());

        // Agora, o paciente.getDoenca().getId() tem o valor correto do banco.
        
        // 2. SQL para inserir na tabela pessoa.
        String sql = "INSERT INTO hospital.pessoa (nome, cpf, idade, tipo_pessoa, doenca_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            // Preenchendo os dados do Humano
            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getCpf());
            stmt.setInt(3, paciente.getIdade());
            
            // Preenchendo os dados do Paciente
            stmt.setString(4, "PACIENTE"); // Coluna discriminadora
            stmt.setInt(5, paciente.getDoenca().getId()); // Usando o ID que acabamos de obter

            stmt.executeUpdate();

            // 3. Recuperar o ID gerado para o paciente e atualizar o objeto.
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    paciente.setId(rs.getInt(1)); // Atribui o ID gerado ao objeto paciente.
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar paciente.", e);
        }
    }
    public List<Paciente> listarTodos() {
    // A consulta SQL com o filtro MÁGICO!
    // Usamos 'LEFT JOIN' para que, se um paciente não tiver uma doença associada, ele ainda apareça na lista.
    String sql = "SELECT p.*, d.tipo as doenca_tipo, d.sintomas, d.restricoes " +
                 "FROM hospital.pessoa p " +
                 "LEFT JOIN hospital.doenca d ON p.doenca_id = d.id " +
                 "WHERE p.tipo_pessoa = 'PACIENTE'";

    List<Paciente> pacientes = new ArrayList<>();

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        // Itera sobre cada linha que o banco de dados retornou
        while (rs.next()) {
            // Cria o objeto Paciente e preenche com os dados de 'pessoa'
            Paciente paciente = new Paciente();
            paciente.setId(rs.getInt("id"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getInt("cpf"));
            paciente.setIdade(rs.getInt("idade"));

            // Verifica se este paciente tem uma doença associada
            int doencaId = rs.getInt("doenca_id");
            if (!rs.wasNull()) { // Se a coluna doenca_id não for nula...
                Doenca doenca = new Doenca();
                // Assumindo que você adicionou o setId na classe Doenca
                doenca.setId(doencaId);
                // Usando os apelidos que demos no SQL ('doenca_tipo', etc)
                doenca.setTipo(rs.getString("doenca_tipo")); 
                doenca.setSint(rs.getString("sintomas"));
                doenca.setRestr(rs.getString("restricoes"));
                paciente.setDoenca(doenca);
            }
            
            // Adiciona o paciente totalmente montado à lista
            pacientes.add(paciente);
        }
    } catch (Exception e) { // Capturando Exception mais genérico por causa da sua TipoDoencaException
        throw new RuntimeException("Erro ao listar pacientes.", e);
    }

    return pacientes; // Retorna a lista completa
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
                if (rs.next()) { // Se encontrou um resultado...
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
                    return paciente; // Retorna o objeto montado
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar paciente por ID.", e);
        }

        return null; // Retorna null se não encontrou ninguém com esse ID
    }


    /**
     * Deleta um paciente do banco de dados pelo seu ID.
     * @param id O ID do paciente a ser deletado.
     * @return true se o paciente foi deletado, false caso contrário.
     */
    public boolean deletar(int id) {
        String sql = "DELETE FROM hospital.pessoa WHERE id = ? AND tipo_pessoa = 'PACIENTE'";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            // Se alguma linha foi afetada (deletada), retorna true.
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar paciente.", e);
        }
    }
    public boolean atualizarRestricoes(int pacienteId, String novasRestricoes) {
    // Este SQL atualiza a tabela 'doenca', mas encontra a linha certa
    // usando uma sub-consulta na tabela 'pessoa'.
        String sql = "UPDATE hospital.doenca SET restricoes = ? WHERE id = " +
                     "(SELECT doenca_id FROM hospital.pessoa WHERE id = ? AND tipo_pessoa = 'PACIENTE')";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novasRestricoes);
            stmt.setInt(2, pacienteId);

            int linhasAfetadas = stmt.executeUpdate();

            // Se 1 linha foi afetada, significa que a atualização funcionou.
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar restrições do paciente.", e);
        }
    }
}