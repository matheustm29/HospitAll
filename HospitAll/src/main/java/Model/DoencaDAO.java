/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Model.Doenca;
import Model.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DoencaDAO {

    public Doenca save(Doenca doenca) {
        
        String sql = "INSERT INTO hospital.doenca (tipo, sintomas, restricoes) VALUES (?, ?, ?)";

       
        try (Connection conn = ConnectionFactory.getConnection();
            
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, doenca.getTipo());
            stmt.setString(2, doenca.getSint());
            stmt.setString(3, doenca.getRestr());

            
            stmt.executeUpdate();

            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
               
                if (rs.next()) {
                    
                    doenca.setId(rs.getInt(1)); 
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar doença no banco de dados.", e);
        }
        
        
        return doenca;
    }
}