package log_update_from_parking_slots;


import main.Trip;

/**
 * This interface allows to enter finished trips in LogFinishedTrips observing the parking slots
 */
public interface LogObserver {
	/**
	 * Updates the log with a finished trip
	 * @param trip the finished trip
	 */
	public void updateLog(Trip trip);
}
