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
import java.io.*;
import java.util.Properties;

public class Main {
    private static final String PARKING_LOT_ID = "PL92";
    private static final String PROPERTIES_FILE = "M:/Coding/Java/Minor Project/ParkingLotManagementSystem/src/logic/vehicle_count.properties";
    
    public String generateTicketId(String vin, String vType, String vColor) {
        Parking pr = new Parking();
        String fnoSno = pr.parkVehicle(vin,vType);
        if(!fnoSno.equals("")) {
            String ticketId = Integer.toString(countVehicles()+1)+"_"+PARKING_LOT_ID+"_"+fnoSno;
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
        return id.equals(PARKING_LOT_ID) && checkSlot;
    }
    
    private int countVehicles() {
        Properties props = new Properties();
        
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int vehicleCount = Integer.parseInt(props.getProperty("vehicleCount"));
        props.setProperty("vehicleCount", String.valueOf(vehicleCount+1));
        
        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE)) {
            props.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return vehicleCount;
    }
    
    public int getOpenSlots(String vType) {
        int openSlots = 0;
        try {
            ParkingLotStatus pls = new ParkingLotStatus();
            ResultSet rSet = pls.getAvailableSlots(vType);
            while(rSet.next()) {
                openSlots++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return openSlots;
    }
    
    public String getSlotsInfo(String vType, int flag) {
        String slotsInfo = "";
        try {
            ParkingLotStatus pls = new ParkingLotStatus();
            ResultSet rSet;
            if(flag==1)
                rSet = pls.getAvailableSlots(vType);
            else
                rSet = pls.getOccupiedSlots(vType);
            while(rSet.next()) {
                int fno = rSet.getInt("floorno");
                int sno = rSet.getInt("slotno");
                slotsInfo+="\n<li>Floor "+String.valueOf(fno)+", Slot "+String.valueOf(sno)+"</li>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slotsInfo;
    }
}
