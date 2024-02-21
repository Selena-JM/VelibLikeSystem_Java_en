package junit_tests;

import static org.junit.jupiter.api.Assertions.*;
import main.*;

import org.junit.Test;

public class BicyclesTest {

    @Test
    public void testGetID() {
    	Coordinates coordinates = new Coordinates(1.0,2.0);
		Bicycles bicycle1 = new Bicycles("electrical", coordinates, "available");
		Bicycles bicycle2 = new Bicycles("electrical", coordinates, "available");
		assertTrue(bicycle1.getID()!=bicycle2.getID());
    }

    @Test
    public void testGetType() {
        Bicycles bicycle = new Bicycles("mechanical", new Coordinates(0.0, 0.0), "available");
        String expectedType = "mechanical";
        String actualType = bicycle.getType();
        assertEquals(expectedType, actualType);
    }

    @Test
    public void testSetType() {
        Bicycles bicycle = new Bicycles("mechanical", new Coordinates(0.0, 0.0), "available");
        String newType = "electrical";
        bicycle.setType(newType);
        String actualType = bicycle.getType();
        assertEquals(newType, actualType);
    }

    @Test
    public void testGetCoordinates() {
        Coordinates coordinates = new Coordinates(0.0, 0.0);
        Bicycles bicycle = new Bicycles("mechanical", coordinates, "available");
        Coordinates expectedCoordinates = coordinates;
        Coordinates actualCoordinates = bicycle.getCoordinates();
        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    public void testSetCoordinates() {
        Coordinates coordinates = new Coordinates(0.0, 0.0);
        Bicycles bicycle = new Bicycles("mechanical", new Coordinates(1.0, 1.0), "available");
        bicycle.setCoordinates(coordinates);
        Coordinates actualCoordinates = bicycle.getCoordinates();
        assertEquals(coordinates, actualCoordinates);
    }

    @Test
    public void testGetStatus() {
        Bicycles bicycle = new Bicycles("mechanical", new Coordinates(0.0, 0.0), "not_available");
        String expectedStatus = "not_available";
        String actualStatus = bicycle.getStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void testSetStatus() {
        Bicycles bicycle = new Bicycles("mechanical", new Coordinates(0.0, 0.0), "available");
        String newStatus = "not_available";
        bicycle.setStatus(newStatus);
        String actualStatus = bicycle.getStatus();
        assertEquals(newStatus, actualStatus);
    }

}

