package elevator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Elevator {

    private static Logger logger = LoggerFactory.getLogger(Elevator.class);

    private int numberOfFloors;
    private int capacity_in_kg = 1000; // min weight of 100 kg
    private int capacity_in_people = 10; // min number of people
    private int number_to_call_floor = numberOfFloors;
    private final int GROUND_FLOOR = 0;
    private int current_floor_of_elevator;

    public int getTOP_FLOOR() {
        return TOP_FLOOR;
    }

    private int TOP_FLOOR;

    public Elevator(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }


    public boolean callElevatortoFloor(int floorCalled) throws Exception {

        // current floor on which elevator is
        // indicate the direction the elevator is moving up/down
        // ring a bell when it reaches the floor

        int currentFloorForElevator = checkWhichFloorElevatorIs(floorCalled);
        String directionOfElevator = floorCalled > currentFloorForElevator ? "\u2191" : "\u2193";
        String floorName = currentFloorForElevator == 0 ? "ground" : String.valueOf(currentFloorForElevator);
        logger.info("Elevator is coming {} to {} floor from {} floor", directionOfElevator, floorCalled, floorName);
        setCurrent_floor_of_elevator(floorCalled);
        return true;
    }

    public int checkWhichFloorElevatorIs(int floorCalled) throws Exception {
        // maintain a counter which gets reset when the elevator is reset on calling resetElevator
        if(floorCalled > getTOP_FLOOR()){
            setTOP_FLOOR(numberOfFloors);
            throw new ElevatorException("Floor called is greater than the total number of floors in the building.. try again");
        }

        logger.info("Elevator is currently on {} floor", getCurrent_floor_of_elevator());
        return getCurrent_floor_of_elevator();
    }

    public int resetElevator() {
        // bring the elevator to ground floor in case of any faults of exceptions
        logger.info("resetting elevator to start from ground floor");
        setCurrent_floor_of_elevator(GROUND_FLOOR);
        return GROUND_FLOOR;
    }

    public void setTOP_FLOOR(int numberOfFloors) {
        logger.info("setting top floor to be {} floor", numberOfFloors);
        this.TOP_FLOOR = numberOfFloors;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "numberOfFloors=" + numberOfFloors +
                ", capacity_in_kg=" + capacity_in_kg +
                ", capacity_in_people=" + capacity_in_people +
                ", number_to_call_floor=" + number_to_call_floor +
                ", GROUND_FLOOR=" + GROUND_FLOOR +
                ", TOP_FLOOR=" + TOP_FLOOR +
                '}';
    }

    public int getCurrent_floor_of_elevator() {
        return current_floor_of_elevator;
    }

    public void setCurrent_floor_of_elevator(int current_floor_of_elevator) {
        this.current_floor_of_elevator = current_floor_of_elevator;
    }
}
