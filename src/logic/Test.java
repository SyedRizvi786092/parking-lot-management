package logic;

class Test{
    public static void main(String[] args) {
        int nFloors=4;
        int nSlotsPerFloor=6;
        ParkingLot parkingLot = new ParkingLot("PR1234", nFloors, nSlotsPerFloor);

        System.out.println("No of open slots for car: " + parkingLot.getNoOfOpenSlots("car"));

        String ticket1 = parkingLot.parkVehicle("car", "MH-03", "red");
        String ticket2 = parkingLot.parkVehicle("car", "MH-04", "purple");
        System.out.println("After parking:");
        parkingLot.displayOccupiedSlots("car");

        parkingLot.unPark(ticket2);
        parkingLot.displayOccupiedSlots("car");

        parkingLot.displayOpenSlots("truck");
        parkingLot.parkVehicle("truck", "MH-01", "black");
        
        System.out.println();
        parkingLot.parkVehicle("truck", "MH-01-4532", "black");
        parkingLot.parkVehicle("truck", "MH-01-912", "black");
        parkingLot.parkVehicle("truck", "MH-01-0911", "black");

        System.out.println("After parking three more trucks:");
        parkingLot.parkVehicle("truck", "MH-01-723", "black");
    }
}

//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Properties;

//public class PropertiesExample {
//    private static final String PROPERTIES_FILE = "myIntValue.properties";
//
//    public static void main(String[] args) {
//        Properties props = new Properties();
//
//        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
//            props.load(input);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        int myIntValue = Integer.parseInt(props.getProperty("myIntValue"));
//        System.out.println("Initial value: " + myIntValue);
//
//        myIntValue++;
//        props.setProperty("myIntValue", String.valueOf(myIntValue));
//
//        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE)) {
//            props.store(output, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Updated value: " + myIntValue);
//    }
//}