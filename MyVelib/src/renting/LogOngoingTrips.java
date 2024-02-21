package renting;

import java.util.ArrayList;

/**
 * This class describes the log of all ongoing trips. Each trip is removed and stored to LogFinishedTrips when it ends.
 */
public class LogOngoingTrips {
	/**
	 * The list of all ongoing trips
	 */
	private static ArrayList<OngoingTrip> ongoing_trips = new ArrayList<OngoingTrip>();

	/**
	 * Gets the list of all ongoing trips
	 * 
	 * @return the list of all ongoing trips
	 */
	public static ArrayList<OngoingTrip> getOngoing_trips() {
		return ongoing_trips;
	}

	/**
	 * Sets the list of all ongoing trips
	 * 
	 * @param ongoing_trips the list of all ongoing trips
	 */
	public static void setOngoing_trips(ArrayList<OngoingTrip> ongoing_trips) {
		LogOngoingTrips.ongoing_trips = ongoing_trips;
	}

	/**
	 * Instantiates a log of all ongoing trips using an existing log
	 * 
	 * @param ongoing_trips the list of all ongoing trips
	 */
	public LogOngoingTrips(ArrayList<OngoingTrip> ongoing_trips) {
		super();
		LogOngoingTrips.ongoing_trips = ongoing_trips;
	}
	
	/**
	 * Instantiates a new log of all ongoing trips with no trips
	 */
	public LogOngoingTrips() {
		super();
		LogOngoingTrips.ongoing_trips = new ArrayList<OngoingTrip>();
	}
	
	/**
	 * Method to add an ongoing trip
	 * 
	 * @param ongoing_trip the ongoing trip that is added
	 */
	public static void addOngoingTrip(OngoingTrip ongoing_trip) {
		getOngoing_trips().add(ongoing_trip);
	}
	
	/**
	 * Method to remove Ongoing trips
	 * 
	 * @param ongoing_trip the ongoing trip that is deleted
	 */
	public static void removeOngoingTrip(OngoingTrip ongoing_trip) {
		getOngoing_trips().remove(ongoing_trip);
	}
	
	
	
}
