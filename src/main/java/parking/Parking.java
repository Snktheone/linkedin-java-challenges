package parking;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;


public class Parking {
    private static Logger logger = LoggerFactory.getLogger(Parking.class);

    private int parking_slots;
    private JSONArray parkingRegister;
    private String vehicleParkedLicensePlate;
    private int runningCountOfParkedVehiles = 1;

    public Parking(int parking_slots) {
        this.parking_slots = parking_slots;
        initializeParkingLot();
    }

    public boolean bookParking(String licensePlate) {
        boolean parkingBooked = false;
        int availableParkingSlots = 0;
        JSONObject parkingData = new JSONObject();
        if (parkingSlotsAvailable()) {
            availableParkingSlots = parking_slots--;
            parkingData.put(String.valueOf(runningCountOfParkedVehiles), licensePlate);
            runningCountOfParkedVehiles++;
            parkingRegister.put(runningCountOfParkedVehiles, parkingData);
            logger.info("Parking spots left - {}", availableParkingSlots);
            parkingBooked = true;
        } else{
            logger.info("Parking is full");
        }
        return parkingBooked;
    }

    private boolean parkingSlotsAvailable() {
        return parking_slots > 0 ? true : false;
    }

    public void displayParking(){
        logger.info("Parking lot looks like - {}",getParkingRegister());
    }


    public String getParkingRegister() {
        return parkingRegister.toString(4);
    }

    public void setParkingRegister(JSONArray parkingRegister) {
        this.parkingRegister = parkingRegister;
    }

    public void initializeParkingLot(){
        parkingRegister = new JSONArray();
        for (int i = 0; i <= parking_slots; i++) {
            parkingRegister.put(i, new JSONObject().put(String.valueOf(i), "--------Available-----------"));
        }
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parking_slots=" + parking_slots +
                '}';
    }

}
