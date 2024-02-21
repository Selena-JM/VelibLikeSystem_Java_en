package balance_update_from_log;

import java.util.ArrayList;


import main.Trip;
import main.Users;
import pricing.Pricing;

/**
 * This class describes the balance of a user. It implements the interface BalanceObserver
 * by observing when a trip is added in LogFinishedTrips.
 */
public class UserBalance implements BalanceObserver{
	/**
	 * The user
	 */
	private Users user;
	/**
	 * The number of rides of the user
	 */
	private int nb_rides;
	/**
	 * The total time passed on a bike of the user
	 */
	private int total_time_on_bike;
	/**
	 * The total amount charged to the user
	 */
	private double total_amount_charged;
	/**
	 * The time credit of the user gained by parking its bike to a "plus" station
	 */
	private double time_credit;
	
	
	/**
	 * Gets the number of rides
	 * 
	 * @return the number of rides
	 */
	public int getNb_rides() {
		return nb_rides;
	}
	
	/**
	 * Sets the number of rides
	 * 
	 * @param nb_rides the number of rides
	 */
	public void setNb_rides(int nb_rides) {
		this.nb_rides = nb_rides;
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
	 * Gets the total time passed on a bike
	 * 
	 * @return the total time passed on a bike
	 */
	public int getTotal_time_on_bike() {
		return total_time_on_bike;
	}
	
	/**
	 * Sets the total time passed on a bike
	 * 
	 * @param total_time_on_bike the total time passed on a bike
	 */
	public void setTotal_time_on_bike(int total_time_on_bike) {
		this.total_time_on_bike = total_time_on_bike;
	}
	
	/**
	 * Gets the total amount charged to the user
	 * 
	 * @return the total amount charged to the user
	 */
	public double getTotal_amount_charged() {
		return total_amount_charged;
	}
	
	/**
	 * Sets the total amount charged to the user
	 * 
	 * @param total_amount_charged the total amount charged to the user
	 */
	public void setTotal_amount_charged(int total_amount_charged) {
		this.total_amount_charged = total_amount_charged;
	}
	
	/**
	 * Gets the time credit of the user
	 * 
	 * @return the time credit of the user
	 */
	public double getTime_credit() {
		return time_credit;
	}
	
	/**
	 * Sets the time credit of the user
	 * 
	 * @param time_credit the time credit of the user
	 */
	public void setTime_credit(double time_credit) {
		this.time_credit = time_credit;
	}

	
	/**
	 * Instantiates a user balance
	 * 
	 * @param user the user
	 * @param nb_rides the total number of rides of the user
	 * @param total_time_on_bike the total time passed on a bike of the user
	 * @param total_amount_charged the total amount charged to the user
	 * @param time_credit the time credit of the user
	 */
	public UserBalance(Users user, int nb_rides, int total_time_on_bike, int total_amount_charged, double time_credit) {
		super();
		this.user = user;
		this.nb_rides = nb_rides;
		this.total_time_on_bike = total_time_on_bike;
		this.total_amount_charged = total_amount_charged;
		this.time_credit = time_credit;
	}
	
	/**
	 * Gets the user, its subscribing type, number of rides, time on a bike, total amount charged and its time credit
	 * 
	 * @return String with these information
	 */
	@Override
	public String toString() {
		return "User balance : (user id = " + user.getID() + ", type of subscribing = " + user.getType_subscribing() + ", number of rides = " + nb_rides + ", total time on bike = " + total_time_on_bike
				+ ", total amount charged = " + total_amount_charged + ", time credit = " + time_credit + ")";
	}
	
	
	/**
	 * Updates the user balance if the trip is from the the same user as the balance
	 * 
	 * @param trip the trip
	 */
	@Override
	public void update(Trip trip) {
		// TODO Auto-generated method stub
		if(this.user.equals(trip.getUser())) {
			this.nb_rides += 1;
			this.total_time_on_bike += trip.getTime();
			ArrayList<Double> pricingstrategy_price = Pricing.setStrategy(trip).setPrice(trip);
			this.total_amount_charged += pricingstrategy_price.get(0);
			this.time_credit = pricingstrategy_price.get(1);
		}
	}

}
