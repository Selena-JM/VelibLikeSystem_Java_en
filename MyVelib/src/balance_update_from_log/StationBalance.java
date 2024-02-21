package balance_update_from_log;

import main.DockingStations;
import main.Trip;

/**
 * This class describes the balance of a docking station. It implements the interface BalanceObserver by observing LogFinishedTrip.
 * When a trip is finished, the balance of the docking station where the bike has been parked is updated.
 */
public class StationBalance implements BalanceObserver{
	/**
	 * The docking station of the balance
	 */
	public DockingStations dockingstation;
	/**
	 * The total number of rents in the history of the station
	 */
	public int total_rents;
	/**
	 * The total number of returns in the history of the station
	 */
	public int total_returns;
	
	/**
	 * Gets the docking station
	 * 
	 * @return the docking station
	 */
	public DockingStations getDockingstation() {
		return dockingstation;
	}
	
	/**
	 * Sets the docking station
	 * 
	 * @param dockingstation the docking station
	 */
	public void setDockingstation(DockingStations dockingstation) {
		this.dockingstation = dockingstation;
	}
	
	/**
	 * Gets the total of rents
	 * 
	 * @return the total of rents
	 */
	public int getTotal_rents() {
		return total_rents;
	}
	
	/**
	 * Sets the total of rents
	 * 
	 * @param total_rents the total of rents
	 */
	public void setTotal_rents(int total_rents) {
		this.total_rents = total_rents;
	}
	
	/**
	 * Gets the total of returns
	 * 
	 * @return the total of returns
	 */
	public int getTotal_returns() {
		return total_returns;
	}
	
	/**
	 * Sets the total of returns
	 * 
	 * @param total_returns the total of returns
	 */
	public void setTotal_returns(int total_returns) {
		this.total_returns = total_returns;
	}
	
	
	/**
	 * Instantiates the balance of the station
	 * 
	 * @param dockingstation the docking station
	 * @param total_rents the total number of rents
	 * @param total_returns the total number of returns
	 */
	public StationBalance(DockingStations dockingstation, int total_rents, int total_returns) {
		super();
		this.dockingstation = dockingstation;
		this.total_rents = total_rents;
		this.total_returns = total_returns;
	}
	
	/**
	 * Gets the docking station, the total number of rents and the total number of returns of the balance
	 * 
	 * @return String with these information
	 */
	@Override
	public String toString() {
		return "Station balance : (station id = " + dockingstation.getID() + ", total rents = " + total_rents + ", total returns = "
				+ total_returns + ")";
	}
	
	/**
	 * Updates the balance of the station with a the new trip. It updates the number of rents, the number of returns or none of them.
	 * 
	 * @param trip a trip
	 */
	@Override
	public void update(Trip trip) {
		// TODO Auto-generated method stub
		if(this.dockingstation.getCoordinates().equals(trip.getStart_coordinates())){
			this.total_rents = this.total_rents + 1;
		}
		if(this.dockingstation.getCoordinates().equals(trip.getEnd_coordinates())) {
			this.total_returns = this.total_returns + 1;
		}
	}
}
