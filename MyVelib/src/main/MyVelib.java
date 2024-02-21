package main;

import java.util.ArrayList;

import balance_update_from_log.LogFinishedTrips;
import balance_update_from_log.StationBalance;
import balance_update_from_log.UserBalance;
import log_update_from_parking_slots.ParkingSlots;

import java.util.Random;

/**
 * This class describes the whole model of the situation.
 */
public class MyVelib{
	/**
	 * The name of the model
	 */
	public static String name;
	/**
	 * The list of all the docking stations in the model
	 */
	public static ArrayList<DockingStations> list_stations = new ArrayList<DockingStations>();
	/**
	 * The list of all the users in the model
	 */
	private static ArrayList<Users> list_users = new ArrayList<Users>();
	/**
	 * The list of all the bicycles in the model
	 */
	private static ArrayList<Bicycles> list_bicycle = new ArrayList<Bicycles>();
	
	/**
     * Adds a new station
     *
     * @param station the new station
     */
	public void addStation(DockingStations station){
		list_stations.add(station);
	}
	
	/**
     * Adds a new user
     *
     * @param user the new user
     */
	public static void addUser(Users user) {
		list_users.add(user);
	}
	
	/**
     * Adds a new bicycle
     *
     * @param bike the new bicycle
     */
	public void addBicycle(Bicycles bike) {
		list_bicycle.add(bike);
	}
	

	/**
     * Gets the list of the stations
     *
     * @return the list of the stations
     */
	public static ArrayList<DockingStations> getList_stations() {
		return list_stations;
	}

	/**
     * Sets the list of the stations
     *
     * @param list the list of the stations
     */
	public static void setList_stations(ArrayList<DockingStations> list) {
		MyVelib.list_stations = list;
	}

	/**
     * Gets the list of the users
     *
     * @return the list of the users
     */
	public static ArrayList<Users> getList_users() {
		return list_users;
	}

	/**
     * Sets the list of the users
     *
     * @param list_users the list of the users
     */
	public static void setList_users(ArrayList<Users> list_users) {
		MyVelib.list_users = list_users;
	}

	/**
     * Gets the list of the bicycles
     *
     * @return the list of the bicycles
     */
	public static ArrayList<Bicycles> getList_bicycle() {
		return list_bicycle;
	}

	/**
     * Sets the list of the bicycles
     *
     * @param list_bicycle the list of the bicycles
     */
	public static void setList_bicycle(ArrayList<Bicycles> list_bicycle) {
		MyVelib.list_bicycle = list_bicycle;
	}
	
	/**
     * Gets the name of the model
     *
     * @return the name of the model
     */
	public static String getName() {
		return name;
	}

	/**
     * Sets the name of the model
     *
     * @param name the name of the model
     */
	public static void setName(String name) {
		MyVelib.name = name;
	}
	
	/**
     * Gets the number of stations, users and bicycles
     *
     * @return String with these information
     */
	@Override
	public String toString() {
		return "MyVelib [Number stations :" + MyVelib.getList_stations().size() + ", number of users : " + MyVelib.getList_users().size() + ", number of bikes :  " + MyVelib.getList_bicycle().size() + "]";
	}

	/**
     * Instantiates the model
     *
     * @param nb_stations the number of stations
     * @param nb_parking_slots the number of parking slots for each station
     * @param nb_users the number of users
     * @param nb_bikes the number of bicycles
     * @param size size of the area (considered as the side of the area which is a squared)
     */
	// This set up method resets the MyVelib if it already existed and creates users, which the second setup method does not do
	public void myVelibSetUp(int nb_stations, int nb_parking_slots, int nb_bikes, int size, int nb_users) {
		LogFinishedTrips log_finished_trips = new LogFinishedTrips();

		ArrayList<Users> list_users = new ArrayList<Users>();
		ArrayList<DockingStations> list_stations = new ArrayList<DockingStations>();
		ArrayList<Bicycles> list_bicycle = new ArrayList<Bicycles>();
		
		
		// creating the users 
		ArrayList<String> names = new ArrayList<String>();
		names.add("John");
		names.add("Johnny");
		names.add("Patrick");
		names.add("Mike");
		names.add("Sandra");
		names.add("Gertrude");
		names.add("Genevieve");
		names.add("Sofia");
		
		ArrayList<String> types_card = new ArrayList<String>();
		types_card.add("no card");
		types_card.add("Vlibre");
		types_card.add("Vmax");
		
		Random r = new Random();
		
		for(int i=0; i<nb_users;i++) {
			CreditCard new_card = new CreditCard((Math.random()+1)*1000);
			Users new_user = new Users(names.get(r.nextInt(8)), new_card, types_card.get(r.nextInt(3)));
			list_users.add(new_user);
			UserBalance new_user_balance = new UserBalance(new_user, 0, 0, 0, 0);
			new_user.setUser_balance(new_user_balance);
			log_finished_trips.registerObserver(new_user_balance);
		}
		
		// creating docking stations
		ArrayList<String> types_stations = new ArrayList<String>();
		types_stations.add("service");
		types_stations.add("offline");
		
		ArrayList<String> types_stations_bis = new ArrayList<String>();
		types_stations_bis.add("standard");
		types_stations_bis.add("plus");
		
		ArrayList<String> slot_status = new ArrayList<String>();
		slot_status.add("mechanical");
		slot_status.add("electrical");
		slot_status.add("free");
		slot_status.add("out of order");
		
		for(int i=0; i<nb_stations;i++) {
			Coordinates new_coor = new Coordinates(Math.random()*size, Math.random()*size);
			DockingStations new_station = new DockingStations(10, new_coor, types_stations.get(r.nextInt(2)), types_stations_bis.get(r.nextInt(2)));
			list_stations.add(new_station);
			StationBalance new_station_balance = new StationBalance(new_station, 0, 0);
			new_station.setStation_balance(new_station_balance);
			log_finished_trips.registerObserver(new_station_balance);
			
			for(int j=0; j<=nb_parking_slots;j++) {
				int index;
				if(Math.random() < 0.7) {
					if(Math.random() < 0.3){
						index = 1;
					}
					else {
						index = 0;
					}
				}
				else {
					index = 2;
				}
				
				ParkingSlots new_slot = new ParkingSlots(slot_status.get(index), new_station);
				new_station.addParkingSlots(new_slot);
				new_slot.registerObserverLog(log_finished_trips);
				
				if(slot_status.get(index) != "free" && list_bicycle.size() < nb_bikes) {
					Bicycles new_bike = new Bicycles(slot_status.get(index), new_station.getCoordinates(), "available");
					list_bicycle.add(new_bike);
					new_slot.setBike_id(new_bike.getID());
				}
			}
		}
		
		int index1;
		// creating the free bikes if not enough
		while (list_bicycle.size() < nb_bikes) {
			if(Math.random() < 0.3){
				index1 = 1;
			}
			else {
				index1 = 0;
			}
			
			Coordinates new_coor = new Coordinates(Math.random()*size, Math.random()*size);
			Bicycles new_bike = new Bicycles(slot_status.get(index1), new_coor, "available");
			list_bicycle.add(new_bike);
		}

		MyVelib.setList_stations(list_stations);
		MyVelib.setList_bicycle(list_bicycle);
		MyVelib.setList_users(list_users);
	}
	
	/**
     * Instantiates the model with no users
     *
     * @param nb_stations the number of stations
     * @param nb_parking_slots the number of parking slots for each station
     * @param nb_bikes the number of bicycles
     * @param size size of the area (considered as the side of the area which is a squared)
     */
	// This set up method resets the MyVelib if it already existed
	public void myVelibSetUp(int nb_stations, int nb_parking_slots, int nb_bikes, int size) {
		LogFinishedTrips log_finished_trips = new LogFinishedTrips();
		
		ArrayList<DockingStations> list_stations = new ArrayList<DockingStations>();
		ArrayList<Bicycles> list_bicycle = new ArrayList<Bicycles>();
		
		Random r = new Random();
		
		// creating docking stations
		ArrayList<String> types_stations = new ArrayList<String>();
		types_stations.add("service");
		types_stations.add("offline");
		
		ArrayList<String> types_stations_bis = new ArrayList<String>();
		types_stations_bis.add("standard");
		types_stations_bis.add("plus");
		
		ArrayList<String> slot_status = new ArrayList<String>();
		slot_status.add("mechanical");
		slot_status.add("electrical");
		slot_status.add("free");
		slot_status.add("out of order");
		
		for(int i=0; i<nb_stations;i++) {
			Coordinates new_coor = new Coordinates(Math.random()*size, Math.random()*size);
			DockingStations new_station = new DockingStations(10, new_coor, types_stations.get(r.nextInt(2)), types_stations_bis.get(r.nextInt(2)));
			list_stations.add(new_station);
			StationBalance new_station_balance = new StationBalance(new_station, 0, 0);
			new_station.setStation_balance(new_station_balance);
			log_finished_trips.registerObserver(new_station_balance);
			
			for(int j=0; j<=nb_parking_slots;j++) {
				int index;
				if(Math.random() < 0.7) {
					if(Math.random() < 0.3){
						index = 1;
					}
					else {
						index = 0;
					}
				}
				else {
					index = 2;
				}
				
				ParkingSlots new_slot = new ParkingSlots(slot_status.get(index), new_station);
				new_station.addParkingSlots(new_slot);
				new_slot.registerObserverLog(log_finished_trips);
				
				if(slot_status.get(index) != "free" && list_bicycle.size() < nb_bikes) {
					Bicycles new_bike = new Bicycles(slot_status.get(index), new_station.getCoordinates(), "available");
					list_bicycle.add(new_bike);
					new_slot.setBike_id(new_bike.getID());
				}
			}
		}
		
		int index1;
		// creating the free bikes if not enough
		while (list_bicycle.size() < nb_bikes) {
			if(Math.random() < 0.3){
				index1 = 1;
			}
			else {
				index1 = 0;
			}
			
			Coordinates new_coor = new Coordinates(Math.random()*size, Math.random()*size);
			Bicycles new_bike = new Bicycles(slot_status.get(index1), new_coor, "available");
			list_bicycle.add(new_bike);
		}
		
		MyVelib.setList_stations(list_stations);
		MyVelib.setList_bicycle(list_bicycle);
	}
}
