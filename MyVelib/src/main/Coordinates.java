package main;

/**
 * This class describes cartesian coordinates of a point in the map.
 */
public class Coordinates {
	/**
	 * The x coordinate
	 */
	private double x;
	/**
	 * The y coordinate
	 */
	private double y;
	
	/**
     * Gets x
     *
     * @return x coordinate
     */
	public double getX() {
		return x;
	}
	
	/**
     * Sets x
     *
     * @param x x coordinate
     */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
     * Gets y
     *
     * @return y coordinate
     */
	public double getY() {
		return y;
	}
	
	/**
     * Sets y
     *
     * @param y coordinate
     */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
     * Instantiates new coordinates
     *
     * @param end_journey_x the x coordinate
     * @param end_journey_y the y coordinate
     */
	public Coordinates(Double end_journey_x, Double end_journey_y) {
		super();
		this.x = end_journey_x;
		this.y = end_journey_y;
	}
	
	/**
     * Gets the x and y coordinate
     *
     * @return String with these information
     */
	@Override
	public String toString() {
		return "Coordinates : [x=" + x + ", y=" + y + "]";
	}
	
	/**
	 * Method to calculate a distance between points (in a straight line)
	 * 
	 * @param c1 coordinates of the first point
	 * @param c2 coordinates of the second point
	 * 
	 * @return the distance
	 */
	public static double distance(Coordinates c1, Coordinates c2) {
		return Math.sqrt(Math.pow(c1.x - c2.x, 2) + Math.pow(c1.y - c2.y, 2));
	}
	
	/**
	 * Method to compare coordinates properly
	 * 
	 * @param ob object (normally coordinates)
	 */
	public boolean equals(Object ob){
		if(ob instanceof Coordinates) {
			Coordinates c1 = (Coordinates) ob;
		return (c1.getX() == this.x) && (c1.getY() == this.getY());
		}
		return false;
		}
}