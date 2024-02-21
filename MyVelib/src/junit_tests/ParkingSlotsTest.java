package junit_tests;

import static org.junit.jupiter.api.Assertions.*;

import main.*;
import log_update_from_parking_slots.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import renting.LogOngoingTrips;
import renting.OngoingTrip;

import java.time.LocalTime;

class ParkingSlotsTest {

    private ParkingSlots parkingSlot;
    private DockingStations dockingStation;

    @BeforeEach
    void setUp() {
        Coordinates coordinates = new Coordinates(0.0, 0.0);
        dockingStation = new DockingStations(10, coordinates, "service", "standard");
        parkingSlot = new ParkingSlots("free", dockingStation);
    }

    @Test
    void testGetID() {
        int id = parkingSlot.getID();
        assertEquals(id, parkingSlot.ID);
    }

    @Test
    void testGetStatus() {
        String status = parkingSlot.getStatus();
        assertEquals(status, parkingSlot.status);
    }

    @Test
    void testSetStatus() {
        parkingSlot.setStatus("occupied");
        assertEquals("occupied", parkingSlot.getStatus());
    }

    @Test
    void testIsChanged() {
        boolean changed = parkingSlot.isChanged();
        assertFalse(changed);
    }

    @Test
    void testSetBeing_taken() {
        parkingSlot.setBeing_taken(true);
        assertTrue(parkingSlot.isChanged());
    }

    @Test
    void testGetDockingstation() {
        DockingStations dockingStations = parkingSlot.getDockingstation();
        assertEquals(dockingStations, parkingSlot.dockingstation);
    }

    @Test
    void testSetDockingstation() {
        Coordinates newCoordinates = new Coordinates(1.0, 1.0);
        DockingStations newDockingStation = new DockingStations(20, newCoordinates, "service", "standard");
        parkingSlot.setDockingstation(newDockingStation);
        assertEquals(newDockingStation, parkingSlot.getDockingstation());
    }

    @Test
    void testGetBike_id() {
        int bikeId = parkingSlot.getBike_id();
        assertEquals(bikeId, parkingSlot.bike_id);
    }

    @Test
    void testSetBike_id() {
        parkingSlot.setBike_id(123);
        assertEquals(123, parkingSlot.getBike_id());
    }

    @Test
    void testBikeParked() {
        OngoingTrip ongoingTrip = new OngoingTrip(new Coordinates(0.0, 0.0), LocalTime.now().minusMinutes(10), null, null);
        Bicycles bike = new Bicycles("mechanical", new Coordinates(0.0, 0.0), "available");
        ongoingTrip.setBike(bike);
        LogOngoingTrips.addOngoingTrip(ongoingTrip);

        parkingSlot.setBike_id(123);
        parkingSlot.bikeParked(30);

        assertFalse(parkingSlot.isChanged());
    }

    @Test
    void testRegisterObserverLog() {
        TestLogObserver observer = new TestLogObserver();
        parkingSlot.registerObserverLog(observer);
        assertTrue(parkingSlot.observers.contains(observer));
    }

    @Test
    void testRemoveObserverLog() {
        TestLogObserver observer = new TestLogObserver();
        parkingSlot.registerObserverLog(observer);
        parkingSlot.removeObserverLog(observer);
        assertFalse(parkingSlot.observers.contains(observer));
    }

    private static class TestLogObserver implements LogObserver {

        @Override
        public void updateLog(Trip trip) {
        }
    }
}

