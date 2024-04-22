/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.*;

/**
 *
 * @author Mojiz
 */
public class Releasing {
    public ResultSet searchSlot(int fno, int sno) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            ConnectDatabase cb = new ConnectDatabase();
            connection = cb.connect();
            
            String sql = "SELECT * FROM appdata.slot WHERE floorno = ? AND slotno = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, fno);
            statement.setInt(2, sno);
            
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        return resultSet;
    }
    
    public String releaseVehicle(int fno, int sno) {
        String msg="";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            ResultSet rSet = searchSlot(fno, sno);
            String vin="";
            if(rSet.next())
                vin = rSet.getString("vin");
            if(vin == null)
                msg = "The vehicle has already been released!";
            else {
                ConnectDatabase cb = new ConnectDatabase();
                connection = cb.connect();
                
                String sql = "UPDATE appdata.slot SET vin = NULL WHERE vin = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, vin);
                statement.execute();
                
                sql = "UPDATE appdata.vehicle SET status = 0, ticket_id = NULL WHERE vin = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, vin);
                statement.execute();
                
                msg = "The vehicle has successfully been released!\nThank You for using our Service :)";
            }
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
        return msg;
    }
}
