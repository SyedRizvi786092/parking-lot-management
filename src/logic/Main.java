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

public class Main {
    static String parkingLotId = "PL92";
    
    public String generateTicketId(String vin, String vType, String vColor) {
        Parking pr = new Parking();
        String fnoSno = pr.parkVehicle(vin,vType);
        if(!fnoSno.equals("")) {
            pr.registerVehicle(vin, vType, vColor);
            return Integer.toString(pr.countVehicles())+"_"+parkingLotId+"_"+fnoSno;
        }
        else {
            return "Full";
        }
    }
}
