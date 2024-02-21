package pricing;

import main.DockingStations;
import main.MyVelib;
import main.Trip;

/**
 * This class describes the pricing operation for a trip.
 * If someone wants to add new pricing strategies, it is needed to add it in this file.
 */
public class Pricing {
	/**
	 * This static attribute is the strategy corresponding to the trip of the user:
	 * - starting parked in the street and ending in a docking station: 10% discount
	 * - starting in a docking station and ending in a docking station: normal fees
	 * - starting in a docking station and ending parked in the street: 10% malus
	 */
	private static PricingStategy pricingstrategy;

	/**
	 * Instantiates a pricing
	 * 
	 * @param pricingstrategy the strategy applied to the pricing
	 */
	public Pricing(PricingStategy pricingstrategy) {
		super();
		Pricing.pricingstrategy = pricingstrategy;
	}
	
	/**
	 * Method that sets the right pricing strategy regarding the trip given.
	 * 
	 * @param trip the trip that will be priced
	 * @return the right pricing strategy
	 */
	public static PricingStategy setStrategy(Trip trip){
		boolean start_station = false;
		boolean end_station = false;
		
		for(DockingStations station : MyVelib.getList_stations()) {
			if(station.getCoordinates().equals(trip.getStart_coordinates())) {
				start_station = true;
			}
			if(station.getCoordinates().equals(trip.getEnd_coordinates())) {
				end_station = true;
			}
		}
		if(start_station && end_station) {
			pricingstrategy = new StationToStationPrice(trip);
		}
		else if(!start_station && end_station) {
			pricingstrategy = new FreeToStationPrice(trip);
		}
		
		else {
			pricingstrategy = new ToFreePrice(trip);
		}

		return pricingstrategy;
	}
}
