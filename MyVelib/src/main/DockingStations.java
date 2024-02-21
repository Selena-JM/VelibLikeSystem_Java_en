package main;

import java.util.ArrayList;

import balance_update_from_log.StationBalance;
import log_update_from_parking_slots.ParkingSlots;

/**
 * This class describes a docking station.
 */
public class DockingStations {
	/**
	 * The unique identifier of the docking station
	 */
	public int ID;
	/**
	 * The list of parking slots in the docking station
	 */
	public ArrayList<ParkingSlots> parkingslots = new ArrayList<ParkingSlots>();
	/**
	 * The number of parking slots
	 */
	public int nbparkingslots;
	/**
	 * The coordinates of the docking station
	 */
	public Coordinates coordinates;
	/**
	 * The state of the docking station: service or offline
	 */
	public String state; // service or offline
	/**
	 * The type of the docking station: standard or plus
	 */
	public String type; //standard or plus
	/**
	 * The balance of the station
	 */
	public StationBalance station_balance;
	
	/**
	 * The first unique ID
	 */
	protected static int uniqid = 0;
	/**
	 * Generating a unique ID for the Parking slots
	 * @return the unique ID
	 */
	protected static int getuniqID() {
		return ++uniqid;
	}
	
	/**
	 * Gets the ID
	 * 
	 * @return the ID
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Resets the ID (only for JUnit tests)
	 */
	public static void resetuniqID() {
		DockingStations.uniqid = 0;
	}
	
	/**
	 * Gets the list of parking slots
	 * 
	 * @return the list of parking slots
	 */
	public ArrayList<ParkingSlots> getParkingslots() {
		return parkingslots;
	}
	
	/**
	 * Sets the list of parking slots
	 * 
	 * @param parkingslots the list of parking slots
	 */
	public void setParkingslots(ArrayList<ParkingSlots> parkingslots) {
		this.parkingslots = parkingslots;
	}
	
	/**
	 * Gets the number of parking slots in the station
	 * 
	 * @return the number of parking slots in the station
	 */
	public int getNbparkingslots() {
		return nbparkingslots;
	}
	
	/**
	 * Sets the number of parking slots in the station
	 * 
	 * @param nbparkingslots the number of parking slots in the station
	 */
	public void setNbparkingslots(int nbparkingslots) {
		this.nbparkingslots = nbparkingslots;
	}
	
	/**
	 * Gets the coordinates of the station
	 * 
	 * @return the coordinates of the station
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	/**
	 * Sets the coordinates of the station
	 * 
	 * @param coordinates the coordinates of the station
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	/**
	 * Gets the state of the station
	 * 
	 * @return the state of the station
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Sets the state of the station
	 * 
	 * @param state the state of the station
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Gets the type of the station
	 * 
	 * @return the type of the station
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type of the station
	 * 
	 * @param type the type of the station
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the balance of the station
	 * 
	 * @return the balance of the station
	 */
	public StationBalance getStation_balance() {
		return station_balance;
	}

	/**
	 * Sets the balance of the station
	 * 
	 * @param station_balance the balance of the station
	 */
	public void setStation_balance(StationBalance station_balance) {
		this.station_balance = station_balance;
	}

	/**
     * Instantiates new docking station
     *
     * @param nbparkingslots the number of parking slots of the docking station
     * @param coordinates the coordinates of the docking station
     * @param state the state of the docking station
     * @param type the type of the docking station
     */
	public DockingStations(int nbparkingslots, Coordinates coordinates,
			String state, String type) {
		super();
		this.ID = DockingStations.getuniqID();
		this.nbparkingslots = nbparkingslots;
		this.coordinates = coordinates;
		this.state = state;
		this.type = type;
	}
	
	
	/**
	 * Gets the ID, number of parking slots, coordinates, state and type of the docking station
	 * 
	 * @return String with these information
	 */
	@Override
	public String toString() {
		return "Docking station : (ID = " + ID + ", number of parking slots =" + parkingslots.size()
				+ ", coordinates = " + coordinates + ", state = " + state + ", type = " + type + ")";
	}

	/**
	 * Method to add a parking slot
	 * 
	 * @param parking_slot the new parking slot
	 */
	public void addParkingSlots(ParkingSlots parking_slot) {
		// TODO Auto-generated method stub
		getParkingslots().add(parking_slot);
	}
	
	
	
}
