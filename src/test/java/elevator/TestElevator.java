package elevator;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestElevator {
    private static Logger logger = LoggerFactory.getLogger(TestElevator.class);
    private Elevator elevator;


    @BeforeAll
    @DisplayName("Setup the building and initial conditions for elevator")
    public void setup() {
        // set up the number of floor in the building
        int numberOfFloors = 10;
        elevator = new Elevator(numberOfFloors);
        elevator.setTOP_FLOOR(numberOfFloors);
        assertEquals(elevator.getNumberOfFloors(), numberOfFloors);
        logger.info(elevator.toString());
        elevator.resetElevator();
    }

    @Test
    @DisplayName("Calling Elevator on 4th floor should show the elevator at 5th floor.")
    public void test1() {
        try {
            assertTrue(elevator.callElevatortoFloor(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Show the current floor of the elevator")
    public void test2(){
        int currentFloor_of_elevator = 3;
        try {
        assertTrue(elevator.callElevatortoFloor(currentFloor_of_elevator));
        assertEquals(elevator.checkWhichFloorElevatorIs(currentFloor_of_elevator), 3);
        currentFloor_of_elevator = 6;
        assertTrue(elevator.callElevatortoFloor(currentFloor_of_elevator));
        assertEquals(elevator.checkWhichFloorElevatorIs(currentFloor_of_elevator), currentFloor_of_elevator);
        currentFloor_of_elevator = 4;
        assertTrue(elevator.callElevatortoFloor(currentFloor_of_elevator));
        assertEquals(elevator.checkWhichFloorElevatorIs(currentFloor_of_elevator), currentFloor_of_elevator);
        currentFloor_of_elevator = 10;
        assertTrue(elevator.callElevatortoFloor(currentFloor_of_elevator));
        assertEquals(elevator.checkWhichFloorElevatorIs(currentFloor_of_elevator), currentFloor_of_elevator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
