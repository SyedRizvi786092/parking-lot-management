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
 * @author mudas
 */

public class ParkRelease {
    public String parkVehicle(String type) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            ConnectDatabase cb = new ConnectDatabase();
            connection = cb.connect();
            
            String sql = "SELECT * FROM appdata.slot WHERE stype = ? LIMIT 1";
            statement = connection.prepareStatement(sql);
            statement.setString(1, type);
            
            resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                String fno = resultSet.getString(1);
                String sno = resultSet.getString(2);
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
    
    public int countVehicles() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count=0;
        
        try {
            ConnectDatabase cb = new ConnectDatabase();
            connection = cb.connect();
            
            String sql = "SELECT * FROM appdata.vehicle";
            statement = connection.prepareStatement(sql);
            
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                count++;
            }
            return count;
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
        return -1;
    }
}
