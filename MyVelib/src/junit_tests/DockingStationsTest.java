package junit_tests;


import log_update_from_parking_slots.*;
import main.*;
import balance_update_from_log.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class DockingStationsTest {

    private DockingStations dockingStations;
    private ParkingSlots parkingSlot1;
    private ParkingSlots parkingSlot2;

    @BeforeEach
    void setUp() {
        // Create a new DockingStations object before each test
        dockingStations = new DockingStations(2, new Coordinates(0.0, 0.0), "service", "standard");

        // Create two parking slots
        parkingSlot1 = new ParkingSlots("free", dockingStations);
        parkingSlot2 = new ParkingSlots("out of order", dockingStations);
    }

    @Test
    void testGetParkingslots() {
        // Verify that the parkingslots list is initially empty
        Assertions.assertTrue(dockingStations.getParkingslots().isEmpty());
    }

    @Test
    void testSetParkingslots() {
        // Create a new list of parking slots
        ArrayList<ParkingSlots> parkingSlotsList = new ArrayList<>();
        parkingSlotsList.add(parkingSlot1);
        parkingSlotsList.add(parkingSlot2);

        // Set the parking slots list
        dockingStations.setParkingslots(parkingSlotsList);

        // Verify that the parkingslots list is correctly set
        Assertions.assertEquals(parkingSlotsList, dockingStations.getParkingslots());
    }

    @Test
    void testGetNbparkingslots() {
        // Verify that the number of parking slots is correctly set
        Assertions.assertEquals(2, dockingStations.getNbparkingslots());
    }

    @Test
    void testSetNbparkingslots() {
        // Set a new number of parking slots
        dockingStations.setNbparkingslots(3);

        // Verify that the number of parking slots is correctly set
        Assertions.assertEquals(3, dockingStations.getNbparkingslots());
    }

    @Test
    void testGetCoordinates() {
        // Verify that the coordinates are correctly set
        Assertions.assertEquals(new Coordinates(0.0, 0.0), dockingStations.getCoordinates());
    }

    @Test
    void testSetCoordinates() {
        // Set new coordinates
        Coordinates newCoordinates = new Coordinates(1.0, 1.0);
        dockingStations.setCoordinates(newCoordinates);

        // Verify that the coordinates are correctly set
        Assertions.assertEquals(newCoordinates, dockingStations.getCoordinates());
    }

    @Test
    void testGetState() {
        // Verify that the state is correctly set
        Assertions.assertEquals("service", dockingStations.getState());
    }

    @Test
    void testSetState() {
        // Set a new state
        dockingStations.setState("offline");

        // Verify that the state is correctly set
        Assertions.assertEquals("offline", dockingStations.getState());
    }

    @Test
    void testGetType() {
        // Verify that the type is correctly set
        Assertions.assertEquals("standard", dockingStations.getType());
    }

    @Test
    void testSetType() {
        // Set a new type
        dockingStations.setType("plus");

        // Verify that the type is correctly set
        Assertions.assertEquals("plus", dockingStations.getType());
    }

    @Test
    void testGetStation_balance() {
        // Verify that the station balance is initially null
        Assertions.assertNull(dockingStations.getStation_balance());
    }

    @Test
    void testSetStation_balance() {
        // Create a new StationBalance object
        StationBalance stationBalance = new StationBalance(dockingStations, 10, 5);

        // Set the station balance
        dockingStations.setStation_balance(stationBalance);

        // Verify that the station balance is correctly set
        Assertions.assertEquals(stationBalance, dockingStations.getStation_balance());
    }

    @Test
    void testAddParkingSlots() {
        // Add a parking slot
        dockingStations.addParkingSlots(parkingSlot1);

        // Verify that the parking slot is added to the parkingslots list
        Assertions.assertTrue(dockingStations.getParkingslots().contains(parkingSlot1));
    }
}
