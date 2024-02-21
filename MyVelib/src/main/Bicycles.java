/**
 * 
 */
package main;

/**
 * This class describes a bicycle.
 */
public class Bicycles {
	/**
     * The unique identifier of the bicycle
     */
	public int ID;
	/**
     * The type of the bicycle: electrical or mechanical
     */
	public String type; // electrical or mechanical
	/**
     * The coordinates of the bicycle
     */
	public Coordinates coordinates;
	/**
     * The status of the bicycle: available or not_available
     */
	public String status; //available or not_available
	
	/**
	 * The first unique ID
	 */
	protected static int uniqid = 0; 
	/**
	 * Generating a unique ID for the bicycle
	 * @return the unique ID
	 */
	protected static int getuniqID() {
		return ++uniqid;
	}
	
	/**
     * Gets the ID of the bicycle
     *
     * @return the ID of the bicycle
     */
	public int getID() {
		return ID;
	}
	
	/**
     * Gets the type of the bicycle
     *
     * @return the type of the bicycle
     */
	public String getType() {
		return type;
	}
	
	/**
     * Sets the type of the bicycle
     *
     * @param type the type of the bicycle
     */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
     * Gets the coordinates of the bicycle
     *
     * @return the coordinates of the bicycle
     */
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	/**
     * Sets the coordinates of the bicycle
     *
     * @param coordinates the coordinates of the bicycle
     */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	/**
     * Gets the status of the bicycle
     *
     * @return the status of the bicycle
     */
	public String getStatus() {
		return status;
	}

	/**
     * Sets the status of the bicycle
     *
     * @param status the status of the bicycle
     */
	public void setStatus(String status) {
		this.status = status;
	}

	
	/**
     * Instantiates a new Bicycle
     *
     * @param type the bicycle type can be electrical or mechanical
     * @param coordinates the coordinates of the bicycle
     * @param status the status of the bicycle
     */
	public Bicycles(String type, Coordinates coordinates, String status) {
		super();
		this.ID = Bicycles.getuniqID();
		this.type = type;
		this.coordinates = coordinates;
		this.status = status;
	}

	/**
     * Gets the ID, type and status of the bicycle
     *
     * @return String with these information
     */
	@Override
	public String toString() {
		return "Bicycle : (ID = " + ID + ", type = " + type + ", status = " + status + ")";
	}
	
	
}
