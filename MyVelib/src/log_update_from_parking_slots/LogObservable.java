package log_update_from_parking_slots;

import main.Trip;

/**
 * This interface allows to observe parking slots in order to write finished trips in LogFinishedTrips.
 */
public interface LogObservable {
	/**
	 * Notifies observers when a trip finishes
	 * @param trip a finished trip
	 */
	public void notifyObserversLog(Trip trip);
	/**
	 * Register a new observer 
	 * @param observer a new observer
	 */
	public void registerObserverLog(LogObserver observer);
	/**
	 * Removes an existing observer
	 * @param observer the removed observer
	 */
	public void removeObserverLog(LogObserver observer);

}
