package sorting;

import java.util.Comparator;

import balance_update_from_log.StationBalance;

/**
 * This class describes the sorting algorithm that allows to retrieve the most occupied station.
 * It implements the built-in interface Comparator for our class StationBalance.
 */
public class MostUsedStationSort implements Comparator<StationBalance>{

	/**
	 * This method calculates the difference of the occupation between two docking stations.
	 * It is done by looking at the total returns and the total rents for each station.
	 * 
	 * @param o1 the balance of the first station
	 * @param o2 the balance of the second station
	 * 
	 * @return int: the difference of the occupation between two docking stations
	 */
	@Override
	public int compare(StationBalance o1, StationBalance o2) {
		// TODO Auto-generated method stub
		return (int)(o1.total_rents + o1.total_returns - (o2.total_rents + o2.total_returns));
	}

}
