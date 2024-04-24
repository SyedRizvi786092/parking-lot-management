/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mojiz
 */

public class Parking {
    public String parkVehicle(String vin, String type) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            ConnectDatabase cb = new ConnectDatabase();
            connection = cb.connect();
            
            String sql = "SELECT * FROM appdata.slot WHERE stype = ? AND vin IS NULL LIMIT 1";
            statement = connection.prepareStatement(sql);
            statement.setString(1, type);
            
            resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                int fno = resultSet.getInt(1);
                int sno = resultSet.getInt(2);
                sql = "UPDATE appdata.slot SET vin = ? WHERE floorno = ? AND slotno = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, vin);
                statement.setInt(2, fno);
                statement.setInt(3, sno);
                statement.execute();
                return fno+"_"+sno;
            } else return "";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "Exception";
    }
    
    public void registerVehicle(String vin, String type, String color, String ticketId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            ConnectDatabase cb = new ConnectDatabase();
            connection = cb.connect();
            
            String sql = "SELECT * FROM appdata.vehicle WHERE vin = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, vin);
            
            resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                sql = "UPDATE appdata.vehicle SET status = 1, ticket_id = ? WHERE vin = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, ticketId);
                statement.setString(2, vin);
            } else {
                sql = "INSERT INTO appdata.vehicle VALUES (?,?,?,1,?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, vin);
                statement.setString(2, type);
                statement.setString(3, color);
                statement.setString(4, ticketId);
            }
            statement.execute();
    } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
