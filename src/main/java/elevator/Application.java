package elevator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {

        int numberOfFloors = 10;
        Elevator elevator = new Elevator(numberOfFloors);
        elevator.resetElevator();
        elevator.setTOP_FLOOR(numberOfFloors);
        logger.info(elevator.toString());
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Calling elevator to a floor");
            int floorCalled = scanner.nextInt();
            try{
            elevator.callElevatortoFloor(floorCalled);
            elevator.checkWhichFloorElevatorIs(floorCalled);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
