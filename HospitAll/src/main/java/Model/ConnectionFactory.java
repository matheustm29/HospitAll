/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Coloque este arquivo no pacote: com.mycompany.projetop
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    

    private static final String URL = "jdbc:postgresql://localhost:5432/hospital?currentSchema=hospital";
    
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "1234"; 

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC do PostgreSQL não encontrado! Verifique o pom.xml.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao conectar com o banco de dados! Verifique usuário e senha.", e);
        }
    }
}