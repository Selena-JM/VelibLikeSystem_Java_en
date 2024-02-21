package pricing;

import java.util.ArrayList;


import balance_update_from_log.UserBalance;
import main.DockingStations;
import main.MyVelib;
import main.Trip;
import main.Users;

/**
 * This class describes the pricing for a trip starting not in a docking station and ending in a station (standard or plus).
 * It implements the interface PricingStrategy.
 */
public class FreeToStationPrice implements PricingStategy{
	/**
	 * The trip that is priced
	 */
	private Trip trip;
	/**
	 * The price of the trip
	 */
	private double price;
	
	/**
	 * Gets the trip
	 * 
	 * @return the trip
	 */
	public Trip getTrip() {
		return trip;
	}
	
	/**
	 * Sets the trip
	 * 
	 * @param trip the trip
	 */
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	/**
	 * Gets the price of the trip
	 * 
	 * @return the price of the trip
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Instantiates a pricing
	 * 
	 * @param trip the trip that is priced
	 */
	public FreeToStationPrice(Trip trip) {
		super();
		this.trip = trip;
	}
	
	/**
	 * Method to calculate the price for a trip using a bike from the street to a station.
	 * There is a 10% discount on the price at the end.
	 * 
	 * @param trip the trip that is priced
	 * 
	 * @return res: (the price of the trip, the new time credit of the user)
	 */
	public ArrayList<Double> setPrice(Trip trip) {
		ArrayList<Double> res = new ArrayList<Double>();
		double time_credit = 0;
		double actual_time_credit = 0;
		
		for(Users user : MyVelib.getList_users()) {
			if(user.getID() == trip.getUser().getID()) {
				UserBalance user_balance = user.getUser_balance();
				actual_time_credit = user_balance.getTime_credit();
			}
		}
		
		if (trip.getUser().getType_subscribing() == "no card") {
			if(trip.getBicycle().getType() == "mechanical"){
				price = Math.floor((trip.getTime() / 60)) + 1;
			}
			if(trip.getBicycle().getType() == "electrical") {
				price = (Math.floor((trip.getTime() / 60)) + 1)*2;
			}
		}
		if(trip.getUser().getType_subscribing() == "Vlibre") {
			if(trip.getBicycle().getType() == "mechanical"){
				if(actual_time_credit< trip.getTime()) {
					price = Math.floor(((trip.getTime()- actual_time_credit)/ 60));
					time_credit = 0;
				}
				else {
					price = 0;
					time_credit = actual_time_credit - trip.getTime();
				}
			}
			if(trip.getBicycle().getType() == "electrical"){
				if(actual_time_credit < trip.getTime()) {
					price = Math.floor(((trip.getTime()- actual_time_credit)/ 60)*2 + 1);
					time_credit = 0;
				}
				else {
					price = 0;
					time_credit = actual_time_credit - trip.getTime();
				}
				
			}
			
			for (DockingStations station : MyVelib.getList_stations()) {
				if(station.getCoordinates().equals(trip.getEnd_coordinates())) {
					if(station.getType() == "plus") {
						time_credit = time_credit + 5;
					}
				}
			}
		}
		if(trip.getUser().getType_subscribing() == "Vmax") {
			if(actual_time_credit < trip.getTime()) {
				price = Math.floor(((trip.getTime()- actual_time_credit)/ 60));
				time_credit = 0;
			}
			else {
				price = 0;
				time_credit = actual_time_credit - trip.getTime();
			}
			
			for (DockingStations station : MyVelib.getList_stations()) {
				if(station.getCoordinates().equals(trip.getEnd_coordinates())) {
					if(station.getType() == "plus") {
						time_credit = time_credit + 5;
					}
				}
			}
		}
		

		price = price*0.9;
		System.out.println("Price from free parking to station : " + price);
		System.out.println("Time credit : " + time_credit);
		res.add(price);
		res.add(time_credit);
		return res;
	}
}
