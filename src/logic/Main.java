/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

/**
 *
 * @author Mojiz
 */

import backend.*;
import java.sql.*;

public class Main {
    static String parkingLotId = "PL92";
    
    public String generateTicketId(String vin, String vType, String vColor) {
        Parking pr = new Parking();
        String fnoSno = pr.parkVehicle(vin,vType);
        if(!fnoSno.equals("")) {
            String ticketId = Integer.toString(pr.countVehicles())+"_"+parkingLotId+"_"+fnoSno;
            pr.registerVehicle(vin, vType, vColor, ticketId);
            return ticketId;
        }
        else {
            return "Full";
        }
    }
    
    public String unPark(String ticketId){
        String msg;
        
        String[] extract = ticketId.split("_");
        String plid = extract[1];
        int fno = Integer.parseInt(extract[2]);
        int sno = Integer.parseInt(extract[3]);
        
        Releasing r = new Releasing();
        
        if(validateTicketId(plid,fno,sno))
            msg = r.releaseVehicle(fno, sno);
        else
            msg = "Invalid Ticket ID !!!";
        
        return msg;
    }
    
    private boolean validateTicketId(String id, int fn, int sn) {
        boolean checkSlot = false;
        try {
            Releasing r = new Releasing();
            ResultSet rSet = r.searchSlot(fn, sn);
            checkSlot = rSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(checkSlot);
        return id.equals(parkingLotId) && checkSlot;
    }
}
