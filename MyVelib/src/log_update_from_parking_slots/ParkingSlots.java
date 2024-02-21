package log_update_from_parking_slots;

import java.time.Duration;

import java.time.LocalTime;
import java.util.ArrayList;

import main.DockingStations;
import main.Trip;
import renting.LogOngoingTrips;
import renting.OngoingTrip;

/**
 * This class describes a parking slot of a docking station.
 * It implements the interface LogObservable by notifying LogObserver corresponding to LogFinishedtrips when a bike is parked
 * in the parking slot.
 */
public class ParkingSlots implements LogObservable {
	/**
	 * The list of observers
	 */
	public ArrayList<LogObserver> observers = new ArrayList<LogObserver>();
	/**
	 * The unique ID of the slot
	 */
	public int ID;
	/**
	 * The status of the slot: free, out of order, with electrical bike or with mechanical bike
	 */
	public String status; //free, out of order, electrical, mechanical
	/**
	 * The docking station containing the slot
	 */
	public DockingStations dockingstation;
	/**
	 * boolean indicating when the slot is being taken and changes to false once the log is updated
	 */
	private boolean being_taken; // true if the parking slot is being taken and changes to false once the log is updated
	/**
	 * The unique ID of the slot
	 */
	public int bike_id; //bike_id if the slot is taken and 0 if free (0 corresponds to no bike id)
	
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
	 * Gets the ID of the slot
	 * 
	 * @return the ID of the slot
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Gets the status of the slot (free, out of order,electrical or mechanical)
	 * 
	 * @return the status of the slot
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status of the slot (free, out of order,electrical or mechanical)
	 * 
	 * @param status the status of the slot
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the boolean indicating if the slot has been taken
	 * 
	 * @return the boolean indicating if the slot has been taken
	 */
	public boolean isChanged() {
		return being_taken;
	}

	/**
	 * Sets the boolean indicating if the slot has been taken
	 * 
	 * @param changed the boolean indicating if the slot has been taken
	 */
	public void setBeing_taken(boolean changed) {
		this.being_taken = changed;
	}
	
	/**
	 * Gets the docking station containing the slot
	 * 
	 * @return the docking station containing the slot
	 */
	public DockingStations getDockingstation() {
		return dockingstation;
	}

	/**
	 * Sets the docking station containing the slot
	 * 
	 * @param dockingstations the docking station containing the slot
	 */
	public void setDockingstation(DockingStations dockingstations) {
		this.dockingstation = dockingstations;
	}

	/**
	 * Gets the ID the bike on the slot (0 if there is no bike)
	 * 
	 * @return the ID the bike on the slot (0 if there is no bike)
	 */
	public int getBike_id() {
		return bike_id;
	}

	/**
	 * Sets the ID the bike on the slot (0 if there is no bike)
	 * 
	 * @param bike_id the ID the bike on the slot (0 if there is no bike)
	 */
	public void setBike_id(int bike_id) {
		this.bike_id = bike_id;
	}


	/**
	 * Instantiates a parking slot
	 * 
	 * @param status the status of the slot (free, out of order,electrical or mechanical)
	 * @param dockingstations the docking station containing the slot
	 */
	public ParkingSlots(String status, DockingStations dockingstations) {
		super();
		this.ID = ParkingSlots.getuniqID();
		this.status = status;
		this.dockingstation = dockingstations;
	}

	/**
	 * Gets the ID, status, station and bike ID of the slot
	 * 
	 * @return String with these information
	 */
	@Override
	public String toString() {
		return "ParkingSlots : (ID = " + ID + ", status = " + status + ", station = " + dockingstation.getID() + ", bike id = "
				+ bike_id + ")";
	}


	/**
	 * Method to create a trip and update the log with a given log of ongoing trips, a given slot and a given duration (if time = 0, the time is set to the machine time when returning)
	 */
	public void bikeParked(int time) {
		// TODO Auto-generated method stub
		Trip trip = null;
		for(OngoingTrip on_trip : LogOngoingTrips.getOngoing_trips()) { // we look at the bike in ongoing trips and if one 
			if(on_trip.getBike().getID() == this.getBike_id()) {
				int elapsedMinutes = time;
				
				if(time == 0) {
					LocalTime end_time = LocalTime.now();
					elapsedMinutes = (int) Duration.between(on_trip.getStart_time(), end_time).toMinutes();
				}
				trip = new Trip(on_trip.getStart_coordinates(), this.getDockingstation().getCoordinates(), on_trip.getUser(), on_trip.getBike(), elapsedMinutes);
			}
		}
	
		this.being_taken = true;
		notifyObserversLog(trip);
	}
	
	/**
	 * Notifies the observers when a trip finishes on the slot
	 * 
	 * @param trip a trip
	 */
	@Override
	public void notifyObserversLog(Trip trip) {
		// TODO Auto-generated method stub
		if (this.being_taken) {
			for (LogObserver ob : observers) {
				ob.updateLog(trip);
			}
			being_taken = false;
		}
	}
	
	/**
	 * Adds an observer for the slot
	 * 
	 * @param observer the new observer
	 */
	@Override
	public void registerObserverLog(LogObserver observer) {
		observers.add(observer);
	}
	
	/**
	 * Removes an observer for the slot
	 * 
	 * @param observer the new observer
	 */
	@Override
	public void removeObserverLog(LogObserver observer) {
		observers.remove(observer);
	}



}
