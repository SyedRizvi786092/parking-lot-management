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
public class ParkingLotStatus {
    public ResultSet getAvailableSlots(String type) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            ConnectDatabase cb = new ConnectDatabase();
            connection = cb.connect();
            
            String sql = "SELECT * FROM appdata.slot WHERE stype = ? AND vin IS NULL";
            statement = connection.prepareStatement(sql);
            statement.setString(1, type);
            
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return resultSet;
    }
    
    public ResultSet getOccupiedSlots(String type) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            ConnectDatabase cb = new ConnectDatabase();
            connection = cb.connect();
            
            String sql = "SELECT * FROM appdata.slot WHERE stype = ? AND vin IS NOT NULL";
            statement = connection.prepareStatement(sql);
            statement.setString(1, type);
            
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return resultSet;
    }
}
