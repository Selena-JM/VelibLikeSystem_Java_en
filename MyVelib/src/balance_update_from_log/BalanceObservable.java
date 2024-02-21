package balance_update_from_log;

/**
 * This abstract class is extended by LogFinishedTrips and notifies BalanceObserver.
 * When LogFinishedTrips is updated, it is observed in order to update the user balance once the user finishes its trip.
 */
public abstract class BalanceObservable {
	
	/**
	 * Notifies the observers
	 */
	public void notifyObservers() {};
	/**
	 * Register a new observer
	 * 
	 * @param observer the new observer
	 */
	public void registerObserver(BalanceObserver observer) {};
	/**
	 * Removes an existing observer
	 * 
	 * @param observer the removed observer
	 */
	public void removeObserver(BalanceObserver observer) {};
}
