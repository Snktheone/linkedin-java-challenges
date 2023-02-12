package parking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Parking parking = new Parking(5);
        parking.displayParking();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Book parking for vehicle ");
            String vehicleLicense = scanner.next();
            parking.bookParking(vehicleLicense);
            parking.displayParking();
        }
    }

}
