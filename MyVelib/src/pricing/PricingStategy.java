package pricing;

import java.util.ArrayList;

import main.Trip;

/**
 * This interface allows to create different types of pricing strategies and to easily add new ones.
 */
public interface PricingStategy {
	/**
	 * This method returns the price of a trip. It changes depending of which strategy is used for.
	 * @param trip the priced trip
	 * @return (the price of the trip, the new time credit of the user)
	 */
	ArrayList<Double> setPrice(Trip trip);
}
