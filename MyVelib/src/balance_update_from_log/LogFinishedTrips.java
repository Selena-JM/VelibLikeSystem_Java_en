package balance_update_from_log;

import java.util.ArrayList;

import log_update_from_parking_slots.LogObserver;
import main.Trip;

/**
 * This class describes the log of all finished trips.
 * It implements the interface LogObserver and observes when a bike is parked in a parking slot and is updated in consequences.
 * It also extends BalanceObservable so that the user balance is updated when the user's finished trip is written in LogFinishedTrips.
 */
public class LogFinishedTrips extends BalanceObservable implements LogObserver {
	/**
	 * The list of observers for each balance
	 */
	private static ArrayList<BalanceObserver> observers = new ArrayList<BalanceObserver>();
	/**
	 * The list of finished trips
	 */
	private static ArrayList<Trip> trips = new ArrayList<Trip>();
	/**
	 * If it has been changed or not. If yes, the information is sent through BalanceObservable to BalanceObserver
	 * in order to update the user balance.
	 */
	private static boolean changed;
	
	
	/**
	 * Gets the list of balance observers
	 * 
	 * @return the list of balance observers
	 */
	public ArrayList<BalanceObserver> getObservers() {
		return observers;
	}
	
	/**
	 * Sets the list of balance observers
	 * 
	 * @param observers the list of balance observers
	 */
	public void setObservers(ArrayList<BalanceObserver> observers) {
		LogFinishedTrips.observers = observers;
	}

	/**
	 * Gets the list of finished trips
	 * 
	 * @return the list of finished trips
	 */
	public static ArrayList<Trip> getTrips() {
		return trips;
	}

	/**
	 * Sets the list of finished trips
	 * 
	 * @param trips the list of finished trips
	 */
	public void setTrips(ArrayList<Trip> trips) {
		LogFinishedTrips.trips = trips;
	}

	/**
	 * Gets the boolean indicating if the log has been changed
	 * 
	 * @return the boolean indicating if the log has been changed
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * Sets the boolean indicating if the log has been changed
	 * 
	 * @param changed the boolean indicating if the log has been changed
	 */
	public void setChanged(boolean changed) {
		LogFinishedTrips.changed = changed;
	}
	
	/**
	 * Notifies the observes when the log is updated
	 */
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		if (LogFinishedTrips.changed) {
			for (BalanceObserver ob : observers)
				ob.update(LogFinishedTrips.trips.get(trips.size()-1));
			LogFinishedTrips.changed = false;
			}
	}
	
	/**
	 * Register a new observer
	 */
	@Override
	public void registerObserver(BalanceObserver observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}

	/**
	 * Removes an observer
	 */
	@Override
	public void removeObserver(BalanceObserver observer) {
		// TODO Auto-generated method stub
		observers.remove(observer);
	}

	
	/**
	 * Method to add information to the Log
	 * 
	 * @param trip the finished trip that is added
	 */
	public static void addTrip(Trip trip) {
		getTrips().add(trip);
		LogFinishedTrips.changed = true;
		
		LogFinishedTrips logFinishedTrips = new LogFinishedTrips();
		logFinishedTrips.notifyObservers();
	}


	/**
	 * Method to update the log with the state of the parking slots (for LogObserver)
	 * 
	 * @param trip the finished trip that is added
	 */
	@Override
	public void updateLog(Trip trip) {
		// TODO Auto-generated method stub
		LogFinishedTrips.addTrip(trip);
	}
}
