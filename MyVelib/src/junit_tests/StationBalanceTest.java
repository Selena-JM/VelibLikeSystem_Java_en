package junit_tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;
import balance_update_from_log.*;

class StationBalanceTest {
	
	private StationBalance stationBalance;
    private DockingStations dockingStation;

    @BeforeEach
    public void setUp() {
        dockingStation = new DockingStations(10, new Coordinates(0.0, 0.0), "service", "standard");
        stationBalance = new StationBalance(dockingStation, 0, 0);
    }
    
    @Test
    public void testGetDockingstation() {
        assertEquals(dockingStation, stationBalance.getDockingstation());
    }

    @Test
    public void testSetDockingstation() {
        DockingStations newDockingStation = new DockingStations(5, new Coordinates(1.0, 1.0), "service", "standard");
        stationBalance.setDockingstation(newDockingStation);
        assertEquals(newDockingStation, stationBalance.getDockingstation());
    }

    @Test
    public void testGetTotal_rents() {
        assertEquals(0, stationBalance.getTotal_rents());
    }

    @Test
    public void testSetTotal_rents() {
        int totalRents = 10;
        stationBalance.setTotal_rents(totalRents);
        assertEquals(totalRents, stationBalance.getTotal_rents());
    }

    @Test
    public void testGetTotal_returns() {
        assertEquals(0, stationBalance.getTotal_returns());
    }

    @Test
    public void testSetTotal_returns() {
        int totalReturns = 5;
        stationBalance.setTotal_returns(totalReturns);
        assertEquals(totalReturns, stationBalance.getTotal_returns());
    }

    @Test
    public void testUpdate_RentTrip() {
        Coordinates startCoordinates = new Coordinates(0.0, 0.0);
        Coordinates endCoordinates = new Coordinates(1.0, 1.0);
        Users user = new Users("John", startCoordinates, new CreditCard(), "Vlibre");
        Bicycles bike = new Bicycles("mechanical", startCoordinates, "available");
        Trip rentTrip = new Trip(startCoordinates, endCoordinates, user, bike, 60);
        stationBalance.update(rentTrip);
        assertEquals(1, stationBalance.getTotal_rents());
        assertEquals(0, stationBalance.getTotal_returns());
    }

    @Test
    public void testUpdate_ReturnTrip() {
        Coordinates startCoordinates = new Coordinates(2.0, 2.0);
        Coordinates endCoordinates = new Coordinates(0.0, 0.0);
        Users user = new Users("Lucie", startCoordinates, new CreditCard(), "Vlibre");
        Bicycles bike = new Bicycles("mechanical", startCoordinates, "available");
        Trip returnTrip = new Trip(startCoordinates, endCoordinates, user, bike, 60);
        stationBalance.update(returnTrip);
        assertEquals(0, stationBalance.getTotal_rents());
        assertEquals(1, stationBalance.getTotal_returns());
    }

    @Test
    public void testUpdate_NoChange() {
        Coordinates startCoordinates = new Coordinates(1.0, 1.0);
        Coordinates endCoordinates = new Coordinates(2.0, 2.0);
        Users user = new Users("Alice", startCoordinates, new CreditCard(), "Vlibre");
        Bicycles bike = new Bicycles("mechanical", startCoordinates, "available");
        Trip trip = new Trip(startCoordinates, endCoordinates, user, bike, 60);
        stationBalance.update(trip);
        assertEquals(0, stationBalance.getTotal_rents());
        assertEquals(0, stationBalance.getTotal_returns());
    }

	@Test
	void testTripTotalRentsAndReturns() {
		Coordinates coord1 = new Coordinates(1.0,1.0);
		Coordinates coord2 = new Coordinates(2.0,2.0);
		CreditCard credit_card = new CreditCard(200.0);
		Users user1 = new Users("Alice", coord1, credit_card, "Vmax");		
		Bicycles bicycle1 = new Bicycles("electrical", coord1, "available");		
		Trip trip = new Trip(coord1, coord2, user1, bicycle1, 10);
		
		DockingStations dock1 = new DockingStations(100, coord1, "online", "plus");
		DockingStations dock2 = new DockingStations(100, coord2, "online", "plus");
		
		StationBalance station1 = new StationBalance(dock1,100,200);
		StationBalance station2 = new StationBalance(dock2,300,400);
		
		int total_rents_before1 = station1.getTotal_rents();
		int total_returns_before1 = station1.getTotal_returns();
		int total_rents_before2 = station2.getTotal_rents();
		int total_returns_before2 = station2.getTotal_returns();
		
		station1.update(trip);
		station2.update(trip);
		
		assertAll("Assert that total rents and total returns increase for the right stations",
				() -> assertTrue(station1.getTotal_rents() == (total_rents_before1 + 1)),
				() -> assertTrue(station1.getTotal_returns() == total_returns_before1),
				() -> assertTrue(station2.getTotal_rents() == total_rents_before2),
				() -> assertTrue(station2.getTotal_returns() == (total_returns_before2 + 1))
		);
		
	}

}
