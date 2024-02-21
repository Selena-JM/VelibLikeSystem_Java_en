package main;

/**
 * This class describes a trip on a bike.
 */
public class Trip {
	
	/**
	 * The coordinates of the starting point of the trip
	 */
	private Coordinates start_coordinates;
	/**
	 * The coordinates of the ending point of the trip
	 */
	private Coordinates end_coordinates;
	/**
	 * The user of the bicycle during the trip
	 */
	private Users user;
	/**
	 * The bicycle used during the trip
	 */
	private Bicycles bicycle;
	/**
	 * The duration of the trip
	 */
	private int time;
	
	
	/**
	 * Gets start coordinates
	 * 
	 * @return start coordinates
	 */
	public Coordinates getStart_coordinates() {
		return start_coordinates;
	}
	
	/**
	 * Sets start coordinates
	 * 
	 * @param start_coordinates start coordinates
	 */
	public void setStart_coordinates(Coordinates start_coordinates) {
		this.start_coordinates = start_coordinates;
	}
	
	/**
	 * Gets end coordinates
	 * 
	 * @return end coordinates
	 */
	public Coordinates getEnd_coordinates() {
		return end_coordinates;
	}
	
	/**
	 * Sets end coordinates
	 * 
	 * @param end_coordinates end coordinates
	 */
	public void setEnd_coordinates(Coordinates end_coordinates) {
		this.end_coordinates = end_coordinates;
	}
	
	/**
	 * Gets user
	 * 
	 * @return user
	 */
	public Users getUser() {
		return user;
	}
	
	/**
	 * Sets user
	 * 
	 * @param user user
	 */
	public void setUser(Users user) {
		this.user = user;
	}
	
	/**
	 * Gets bicycle
	 * 
	 * @return bicycle
	 */
	public Bicycles getBicycle() {
		return bicycle;
	}
	
	/**
	 * Sets bicycle
	 * 
	 * @param bicycle the bicycle
	 */
	public void setBicycle(Bicycles bicycle) {
		this.bicycle = bicycle;
	}
	
	/**
	 * Gets the duration of the trip
	 * 
	 * @return the duration of the trip
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Sets the duration of the trip
	 * 
	 * @param time the duration of the trip
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	
	/**
     * Instantiates a new Trip
     *
     * @param start_coordinates the starting coordinates of the trip
     * @param end_coordinates the ending coordinates of the trip
     * @param user the user of the trip
     * @param bicycle the bicycle used during the trip
     * @param time the duration of the trip
     */
	public Trip(Coordinates start_coordinates, Coordinates end_coordinates, Users user, Bicycles bicycle, int time) {
		super();
		this.start_coordinates = start_coordinates;
		this.end_coordinates = end_coordinates;
		this.user = user;
		this.bicycle = bicycle;
		this.time = time;
	}
	
	/**
	 * Gets the starting coordinates, the ending coordinates, the user, the bicycle with its type and the duration of the trip
	 * 
	 * @return String with these information
	 */
	@Override
	public String toString() {
		return "Trip : (start coordinates = " + start_coordinates + ", end coordinates = " + end_coordinates + ", user id = "
				+ user.getID() + ", bicycle id = " + bicycle.getID() + ", type of bicycle = " + bicycle.getType() + ", duration = " + time + ")";
	}
	
	
}
