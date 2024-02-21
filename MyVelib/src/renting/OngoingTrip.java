package renting;

import java.time.LocalTime;

import main.Coordinates;
import main.Users;
import main.Bicycles;

/**
 * This class describes an ongoing trip.
 * Hence it has no attribute duration but only a start_time since we can deduce its duration at the end with the current time and its starting time.
 */
public class OngoingTrip {
	/**
	 * The coordinates of the start of the trip
	 */
	private Coordinates start_coordinates;
	/**
	 * The time when the trip started
	 */
	private LocalTime start_time;
	/**
	 * The user who is doing the trip
	 */
	private Users user;
	/**
	 * The bike used for the trip
	 */
	private Bicycles bike;
	
	/**
	 * Gets the coordinates of the starting point of the trip
	 * 
	 * @return the coordinates of the starting point of the trip
	 */
	public Coordinates getStart_coordinates() {
		return this.start_coordinates;
	}
	
	/**
	 * Sets the coordinates of the starting point of the trip
	 * 
	 * @param start_coordinates the coordinates of the starting point of the trip
	 */
	public void setStart_coordinates(Coordinates start_coordinates) {
		this.start_coordinates = start_coordinates;
	}
	
	/**
	 * Gets the time when starting the trip
	 * 
	 * @return the time when starting the trip
	 */
	public LocalTime getStart_time() {
		return start_time;
	}
	
	/**
	 * Sets the time when starting the trip
	 * 
	 * @param start_time the time when starting the trip
	 */
	public void setStart_time(LocalTime start_time) {
		this.start_time = start_time;
	}
	
	/**
	 * Gets the user
	 * 
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}
	
	/**
	 * Sets the user
	 * 
	 * @param user the user
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	
	/**
	 * Gets the bike used
	 * 
	 * @return the bike used
	 */
	public Bicycles getBike() {
		return bike;
	}
	
	/**
	 * Sets the bike used
	 * 
	 * @param bike the bike used
	 */
	public void setBike(Bicycles bike) {
		this.bike = bike;
	}
	
		/**
		 * Instantiates an ongoing trip
		 * 
		 * @param start_coordinates the coordinates of the starting point of the trip
		 * @param start_time the time when starting the trip
		 * @param user the user
		 * @param bike the bike used
		 */
	public OngoingTrip(Coordinates start_coordinates, LocalTime start_time, Users user, Bicycles bike) {
		super();
		this.start_coordinates = start_coordinates;
		this.start_time = start_time;
		this.user = user;
		this.bike = bike;
	}
	
	/**
	 * Gets the starting coordinates, the time at the beginning, the user and the bike for this ongoing trip
	 * 
	 * @return String with these information
	 */
	@Override
	public String toString() {
		return "OngoingTrip : (start coordinates = " + start_coordinates + ", start time=" + start_time + ", user id = " + user.getID()
				+ ", bike id = " + bike.getID() + ", type of bike = " + bike.getType() + ")";
	}
	
	
}
