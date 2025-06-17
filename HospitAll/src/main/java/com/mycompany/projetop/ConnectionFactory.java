/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Coloque este arquivo no pacote: com.mycompany.projetop
package com.mycompany.projetop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta classe é responsável por criar e gerenciar a conexão
 * com o banco de dados PostgreSQL.
 */
public class ConnectionFactory {
    
    // --- DADOS DA SUA CONEXÃO COM O POSTGRESQL ---

    // Endereço do seu banco. "localhost" é a sua máquina, "5432" é a porta padrão do Postgre.
    // "sistema_hospitalar_db" é o nome do banco que criamos.
    // "currentSchema=hospital" seleciona o schema onde estão nossas tabelas.
    private static final String URL = "jdbc:postgresql://localhost:5432/hospital?currentSchema=hospital";
    
    // O usuário que você usa para acessar o PostgreSQL (o padrão costuma ser "postgres").
    private static final String USER = "postgres"; // <-- MUITO IMPORTANTE: ALTERE AQUI!
    
    // A senha para esse usuário.
    private static final String PASSWORD = "1234"; // <-- MUITO IMPORTANTE: ALTERE AQUI!

    /**
     * Obtém uma conexão ativa com o banco de dados.
     * @return um objeto Connection pronto para uso.
     */
    public static Connection getConnection() {
        try {
            // Carrega o driver do PostgreSQL que o Maven baixou.
            Class.forName("org.postgresql.Driver");
            // Tenta estabelecer a conexão usando os dados que definimos acima.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Este erro ocorre se o driver não foi encontrado (problema no Maven).
            throw new RuntimeException("Driver JDBC do PostgreSQL não encontrado! Verifique o pom.xml.", e);
        } catch (SQLException e) {
            // Este erro ocorre se a URL, usuário ou senha estiverem incorretos.
            throw new RuntimeException("Falha ao conectar com o banco de dados! Verifique usuário e senha.", e);
        }
    }
}