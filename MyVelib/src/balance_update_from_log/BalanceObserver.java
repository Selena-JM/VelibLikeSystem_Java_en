package balance_update_from_log;

import main.Trip;

/**
 * This interface observes when a trip is written in LogFinishedTrips
 * and then updates the user balance in consequences.
 */
public interface BalanceObserver {
	/**
	 * Updates the balance regarding the trip
	 * @param trip the trip
	 */
	public void update(Trip trip);

}
