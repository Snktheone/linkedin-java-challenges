package parking;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestParking {
    private Parking parking;
    private static Logger logger = LoggerFactory.getLogger(TestParking.class);

    @BeforeAll
    public void setup(){
        parking = new Parking(10);
        logger.info(parking.toString());
    }

    @Test
    @DisplayName("Should be able to book a parking slot.")
    public void test1(){
        String licensePlate = "LO62 YOT";
        assertTrue(parking.bookParking(licensePlate));
        licensePlate = "CB12 EJV";
        assertTrue(parking.bookParking(licensePlate));
        licensePlate = "CB12 EJV";
        assertTrue(parking.bookParking(licensePlate));
        licensePlate = "CB12 EJV";
        assertTrue(parking.bookParking(licensePlate));
        licensePlate = "CB12 EJV";
        assertTrue(parking.bookParking(licensePlate));
        parking.displayParking();
    }

}
