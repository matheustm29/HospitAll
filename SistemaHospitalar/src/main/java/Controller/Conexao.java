/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/HospitAll";
    private static final String USER = "postgres";
    private static final String SENHA = "1234"; // Coloque sua senha aqui!
    private static final String DRIVER = "org.postgresql.Driver";

    // Bloco estático para carregar o driver uma vez quando a classe é carregada
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Falha ao carregar o driver JDBC: " + e.getMessage());
            // Em uma aplicação real, você poderia lançar uma RuntimeException aqui
            // throw new RuntimeException("Falha ao carregar o driver JDBC", e);
        }
    }

    /**
     * Obtém uma conexão com o banco de dados.
     * @return Um objeto Connection.
     * @throws SQLException se a conexão falhar.
     */
    public static Connection getConexao() throws SQLException {
        System.out.println("Conectando ao BD...");
        Connection conn = DriverManager.getConnection(URL, USER, SENHA);
        System.out.println("Banco conectado com sucesso!");
        return conn;
    }

    /**
     * Fecha a conexão com o banco de dados.
     * @param conn A conexão a ser fechada.
     */
    public static void fecharConexao(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    /**
     * Fecha a conexão e o PreparedStatement.
     * @param conn A conexão a ser fechada.
     * @param stmt O PreparedStatement a ser fechado.
     */
    public static void fecharConexao(Connection conn, PreparedStatement stmt) {
        fecharConexao(stmt); // Primeiro fecha o PreparedStatement
        fecharConexao(conn); // Depois fecha a Connection
    }

    /**
     * Fecha a conexão, o PreparedStatement e o ResultSet.
     * @param conn A conexão a ser fechada.
     * @param stmt O PreparedStatement a ser fechado.
     * @param rs O ResultSet a ser fechado.
     */
    public static void fecharConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {
        fecharConexao(rs);   // Primeiro fecha o ResultSet
        fecharConexao(stmt); // Depois o PreparedStatement
        fecharConexao(conn); // Por último a Connection
    }

    // Métodos auxiliares para fechar Statement e ResultSet
    private static void fecharConexao(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                 System.err.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
            }
        }
    }

    private static void fecharConexao(ResultSet rs) {
         if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                 System.err.println("Erro ao fechar o ResultSet: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Connection testeConn = null;
        try {
            testeConn = Conexao.getConexao();
            if (testeConn != null) {
                System.out.println("Teste de conexão bem-sucedido!");
            }
        } catch (SQLException e) {
            System.err.println("Teste de conexão falhou: " + e.getMessage());
        } finally {
            Conexao.fecharConexao(testeConn);
        }
    }
}