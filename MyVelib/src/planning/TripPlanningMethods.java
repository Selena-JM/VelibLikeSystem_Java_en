package planning;

import java.util.ArrayList;

import log_update_from_parking_slots.ParkingSlots;
import main.Bicycles;
import main.Coordinates;
import main.DockingStations;
import main.MyVelib;

/**
 * This class describes trip planning.
 * It allows to calculate the best trip using bicycles from our model by entering:
 * - The coordinates of the start of the trip
 * - The coordinates of the end of the trip
 * - The type of bicycle that we want to use (electrical or mechanical)
 * There are different methods for this regarding the constraints added.
 */
public class TripPlanningMethods {
	/**
	 * The coordinates of the start of the trip
	 */
	private Coordinates start_journey;
	/** 
	 * The coordinates of the end of the trip
	 */
	private Coordinates end_journey;
	/**
	 * The type of bicycle chosen by the user
	 */
	private String type_bicycle;
	
	
	/**
	 * Gets the coordinates of the start of the trip
	 * 
	 * @return the coordinates of the start of the trip
	 */
	public Coordinates getStart_journey() {
		return start_journey;
	}
	
	/**
	 * Sets the coordinates of the start of the trip
	 * 
	 * @param start_journey the coordinates of the start of the trip
	 */
	public void setStart_journey(Coordinates start_journey) {
		this.start_journey = start_journey;
	}
	
	/**
	 * Gets the coordinates of the chosen destination
	 * 
	 * @return the coordinates of the chosen destination
	 */
	public Coordinates getEnd_journey() {
		return end_journey;
	}
	
	/**
	 * Sets the coordinates of the chosen destination
	 * 
	 * @param end_journey the coordinates of the chosen destination
	 */
	public void setEnd_journey(Coordinates end_journey) {
		this.end_journey = end_journey;
	}
	
	/**
	 * Gets the chosen type of the bicycle
	 * 
	 * @return the chosen type of the bicycle
	 */
	public String getType_bicycle() {
		return type_bicycle;
	}
	
	/**
	 * Sets the chosen type of the bicycle
	 * 
	 * @param type_bicycle the chosen type of the bicycle
	 */
	public void setType_bicycle(String type_bicycle) {
		this.type_bicycle = type_bicycle;
	}
	
	/**
	 * Instantiates a TripPlanningMethods
	 * 
	 * @param start_journey the coordinates of the start of the journey
	 * @param end_journey the coordinates of the chosen destination
	 * @param type_bicycle the chosen type of bicycle
	 */
	public TripPlanningMethods(Coordinates start_journey, Coordinates end_journey, String type_bicycle) {
		super();
		this.start_journey = start_journey;
		this.end_journey = end_journey;
		this.type_bicycle = type_bicycle;
	}

	/**
	 * Method to find the nearest (in a straight line) start docking station
	 * 
	 * @param tripplanning a planning
	 * 
	 * @return the closest docking station from the coordinates of the start of the planning
	 */
	public static DockingStations nearest_available_start_station(TripPlanningMethods tripplanning) {
		double dist = Coordinates.distance(tripplanning.getStart_journey(), MyVelib.getList_stations().get(0).getCoordinates());
		DockingStations current_best_station = MyVelib.getList_stations().get(0);
		for(DockingStations station: MyVelib.getList_stations()) {
			if(Coordinates.distance(tripplanning.getStart_journey(), station.getCoordinates()) < dist && station.getState() == "online") { 
				for (ParkingSlots parkingslots : station.getParkingslots()) {
					if (parkingslots.getStatus() == tripplanning.getType_bicycle()){  // get the nearest station and verify that there is a parking slot in this station that contains the type of bike wanted
						dist = Coordinates.distance(tripplanning.getStart_journey(), station.getCoordinates());
						current_best_station = station;
					}
				}
			}
			if(Coordinates.distance(tripplanning.getStart_journey(), station.getCoordinates()) == dist && station.getState() == "online") { // if equal distance, verify which is closer to the end point of the journey
				if(Coordinates.distance(station.getCoordinates(), tripplanning.getEnd_journey()) < Coordinates.distance(current_best_station.getCoordinates(),tripplanning.end_journey)) {
					for (ParkingSlots parkingslots : station.getParkingslots()) {
						if (parkingslots.getStatus() == tripplanning.getType_bicycle()){  
							dist = Coordinates.distance(tripplanning.getStart_journey(), station.getCoordinates());
							current_best_station = station;
						}
					}
				}
			}
		}
		return current_best_station;
	}
	
	/**
	 * Method to find the nearest (in a straight line) end docking station
	 * 
	 * @param tripplanning a planning
	 * 
	 * @return the closest docking station from the coordinates of the end of the planning
	 */
	public static DockingStations nearest_available_end_station(TripPlanningMethods tripplanning) {
		double dist = Coordinates.distance(tripplanning.getEnd_journey(), MyVelib.getList_stations().get(0).getCoordinates());
		DockingStations current_best_station = MyVelib.getList_stations().get(0);
		for(DockingStations station: MyVelib.getList_stations()) {
			if(Coordinates.distance(tripplanning.getEnd_journey(), station.getCoordinates()) < dist && station.getState() == "online") { 
				for (ParkingSlots parkingslots : station.getParkingslots()) {
					if (parkingslots.getStatus() == "free"){  // get the nearest station and verify that there is a free parking slot in this station 
						dist = Coordinates.distance(tripplanning.getEnd_journey(), station.getCoordinates());
						current_best_station = station;
					}
				}
			}
		}
		return current_best_station;
	}
	
	/**
	 * Method to find the nearest (in a straight line) end docking station that is not a "plus" station
	 * 
	 * @param tripplanning a planning
	 * 
	 * @return the closest "standard" docking station from the coordinates of the end of the planning
	 */
	public static DockingStations nearest_available_end_station_not_plus(TripPlanningMethods tripplanning) {
		double dist = Coordinates.distance(tripplanning.getEnd_journey(), MyVelib.getList_stations().get(0).getCoordinates());
		DockingStations current_best_station = MyVelib.getList_stations().get(0);
		for(DockingStations station: MyVelib.getList_stations()) {
			if(Coordinates.distance(tripplanning.getEnd_journey(), station.getCoordinates()) < dist && station.getType() == "standard" && station.getState() == "online") { 
				for (ParkingSlots parkingslots : station.getParkingslots()) {
					if (parkingslots.getStatus() == "free"){  // get the nearest station and verify that there is a free parking slot in this station 
						dist = Coordinates.distance(tripplanning.getEnd_journey(), station.getCoordinates());
						current_best_station = station;
					}
				}
			}
		}
		return current_best_station;
	}
	
	
	/**
	 * Method to find the nearest (in a straight line) end docking station that is "plus"
	 * 
	 * @param tripplanning a planning
	 * 
	 * @return the closest "plus" docking station from the coordinates of the end of the planning
	 */
	public static DockingStations nearest_available_end_station_plus(TripPlanningMethods tripplanning) {
		double dist = Coordinates.distance(tripplanning.getEnd_journey(), MyVelib.getList_stations().get(0).getCoordinates());
		DockingStations current_best_station = MyVelib.getList_stations().get(0);
		for(DockingStations station: MyVelib.getList_stations()) {
			if(Coordinates.distance(tripplanning.getEnd_journey(), station.getCoordinates()) < dist && station.getType() == "plus" && station.getState() == "online") { 
				for (ParkingSlots parkingslots : station.getParkingslots()) {
					if (parkingslots.getStatus() == "free"){  // get the nearest station and verify that there is a free parking slot in this station 
						dist = Coordinates.distance(tripplanning.getEnd_journey(), station.getCoordinates());
						current_best_station = station;
					}
				}
			}
		}
		return current_best_station;
	}
	
	
	/**
	 * Method to get the nearest bike (at a station or free)
	 * 
	 * @param point the coordinates of a certain point
	 * @param type_bike the chosen type of bike
	 * 
	 * @return the coordinates of the closest bike to the point
	 */
	public static Coordinates nearest_available_bike(Coordinates point, String type_bike) {
		double dist = Coordinates.distance(point, MyVelib.getList_bicycle().get(0).getCoordinates());
		Bicycles current_best_bike = MyVelib.getList_bicycle().get(0);
		
		for(Bicycles bike: MyVelib.getList_bicycle()) {
			if(Coordinates.distance(point, bike.getCoordinates()) < dist && bike.getStatus() == "available" && bike.getType() == type_bike) { 
				dist = Coordinates.distance(point, bike.getCoordinates());
				current_best_bike = bike;
			}
		}
		return current_best_bike.getCoordinates();
	}
	
	/**
	 * Method to get the nearest free bike
	 * @param point the coordinates of a certain point
	 * @param type_bike the chosen type of bike
	 * @return the coordinates of the closest free bike to the point
	 */
	public static Coordinates nearest_available_free_bike(Coordinates point, String type_bike) {
		double dist = Coordinates.distance(point, MyVelib.getList_bicycle().get(0).getCoordinates());
		Bicycles current_best_bike = MyVelib.getList_bicycle().get(0);
		boolean free = true;
		
		for(Bicycles bike: MyVelib.getList_bicycle()) {
			if(Coordinates.distance(point, bike.getCoordinates()) < dist && bike.getStatus() == "available" && bike.getType() == type_bike) { 
				dist = Coordinates.distance(point, bike.getCoordinates());
				current_best_bike = bike;
				for(DockingStations station : MyVelib.getList_stations()) {	
					if (bike.getCoordinates() == station.getCoordinates()) {
						free = false;
					}
				}
				if(free) {
					dist = Coordinates.distance(point, bike.getCoordinates());
					current_best_bike = bike;
				}
			}
		}
		return current_best_bike.getCoordinates();
	}
	
	
	/**
	 * Method to get which station to start and to end the trip, gives the final journey
	 * @param tripplanning a planning
	 * @return a list containing the coordinates of the start and the end of the journey
	 */
	public static ArrayList<Coordinates> setJourney_stations_only(TripPlanningMethods tripplanning){
		ArrayList<Coordinates> start_end = new ArrayList<Coordinates>();
		DockingStations start_station = nearest_available_start_station(tripplanning);
		start_end.add(start_station.getCoordinates());
		DockingStations end_station= nearest_available_end_station(tripplanning);
		start_end.add(end_station.getCoordinates());
		System.out.println("Start station : " + start_station.getID() + ", end station : " + end_station.getID());
		return start_end;
	}
	
	/**
	 * Method to get where to start and  which station to end the trip, gives the final journey
	 * @param tripplanning a planning
	 * @return a list containing the coordinates of the start and the end of the journey
	 */
	public static ArrayList<Coordinates> setJourney_station_or_free_start(TripPlanningMethods tripplanning){
		ArrayList<Coordinates> start_end = new ArrayList<Coordinates>();
		Coordinates start_coordinates = nearest_available_bike(tripplanning.getStart_journey(), tripplanning.getType_bicycle());
		start_end.add(start_coordinates);
		Coordinates end_station = nearest_available_end_station(tripplanning).getCoordinates();
		start_end.add(end_station);
		return start_end;
	}
	
	/**
	 * Method to get where to start (excluding stations) and which station to end the trip, gives the final journey
	 * @param tripplanning a planning
	 * @return a list containing the coordinates of the start and the end of the journey
	 */
	public static ArrayList<Coordinates> setJourney_free_start_only(TripPlanningMethods tripplanning){
		ArrayList<Coordinates> start_end = new ArrayList<Coordinates>();
		Coordinates start_coordinates = nearest_available_free_bike(tripplanning.getStart_journey(), tripplanning.getType_bicycle());
		start_end.add(start_coordinates);
		DockingStations end_station = nearest_available_end_station(tripplanning);
		start_end.add(end_station.getCoordinates());
		System.out.println("Coordinates of the free parking bike : " + start_coordinates + ". End station : " + end_station.getID());
		return start_end;
	}
	
	/**
	 * Method to get which station to start and to end the trip (avoiding plus stations at the end), gives the final journey
	 * @param tripplanning a planning
	 * @return a list containing the coordinates of the start and the end of the journey
	 */
	public static ArrayList<Coordinates> setJourney_station_to_avoid_plus_stations(TripPlanningMethods tripplanning){
		ArrayList<Coordinates> start_end = new ArrayList<Coordinates>();
		DockingStations start_station = nearest_available_start_station(tripplanning);
		start_end.add(start_station.getCoordinates());
		DockingStations end_station = nearest_available_end_station_not_plus(tripplanning);
		start_end.add(end_station.getCoordinates());
		System.out.println("Start station : " + start_station.getID() + ", end station : " + end_station.getID());
		return start_end;
	}
	
	/**
	 * Method to get which station to start and to end the trip (preferably plus stations at the end), gives the final journey
	 * @param tripplanning a planning
	 * @return a list containing the coordinates of the start and the end of the journey
	 */
	public static ArrayList<Coordinates> setJourney_station_to_prefer_plus_stations(TripPlanningMethods tripplanning){
		ArrayList<Coordinates> start_end = new ArrayList<Coordinates>();
		DockingStations start_station = nearest_available_start_station(tripplanning);
		start_end.add(start_station.getCoordinates());
		
		DockingStations end_station_plus = nearest_available_end_station_plus(tripplanning);
		DockingStations end_station_global = nearest_available_end_station(tripplanning);
		
		DockingStations end_station = null;
		if(Coordinates.distance(end_station_plus.getCoordinates(), tripplanning.getEnd_journey()) <= 1.1 * Coordinates.distance(end_station_global.getCoordinates(), tripplanning.getEnd_journey())) {
			end_station = end_station_plus;
			start_end.add(end_station.getCoordinates());
		}
		else {
			end_station = end_station_global;
			start_end.add(end_station.getCoordinates());
		}
		
		System.out.println("Start station : " + start_station.getID() + ", end station : " + end_station.getID());
		return start_end;
	}
	
	/**
	 * Method to get where to start (excluding stations) and which station to end the trip (avoiding plus stations at the end), gives the final journey
	 * @param tripplanning a planning
	 * @return a list containing the coordinates of the start and the end of the journey
	 */
	public static ArrayList<Coordinates> setJourney_free_to_avoid_plus_stations(TripPlanningMethods tripplanning){
		ArrayList<Coordinates> start_end = new ArrayList<Coordinates>();
		Coordinates start_coordinates = nearest_available_free_bike(tripplanning.getStart_journey(), tripplanning.getType_bicycle());
		start_end.add(start_coordinates);
		
		DockingStations end_station = nearest_available_end_station_not_plus(tripplanning);
		start_end.add(end_station.getCoordinates());
		System.out.println("Free bike coordinates : " + start_coordinates + ", end station : " + end_station.getID());
		return start_end;
	}
	
	/**
	 * Method to get where to start (excluding stations) and which station to end the trip (preferably plus stations at the end), gives the final journey
	 * @param tripplanning a planning
	 * @return a list containing the coordinates of the start and the end of the journey
	 */
	public static ArrayList<Coordinates> setJourney_free_to_prefer_plus_stations(TripPlanningMethods tripplanning){
		ArrayList<Coordinates> start_end = new ArrayList<Coordinates>();
		Coordinates start_coordinates = nearest_available_free_bike(tripplanning.getStart_journey(), tripplanning.getType_bicycle());
		start_end.add(start_coordinates);
		
		DockingStations end_station_plus = nearest_available_end_station_plus(tripplanning);
		DockingStations end_station_global = nearest_available_end_station(tripplanning);
		
		DockingStations end_station = null;
		if(Coordinates.distance(end_station_plus.getCoordinates(), tripplanning.getEnd_journey()) <= 1.1 * Coordinates.distance(end_station_global.getCoordinates(), tripplanning.getEnd_journey())) {
			end_station = end_station_plus;
			start_end.add(end_station.getCoordinates());
		}
		else {
			end_station = end_station_global;
			start_end.add(end_station.getCoordinates());
		}
		
		System.out.println("Free bike coordinates : " + start_coordinates + ", end station : " + end_station.getID());
		return start_end;
	}
	
	/**
	 * Method to get where to start and which station to end the trip (avoiding plus stations at the end), gives the final journey
	 * @param tripplanning a planning
	 * @return a list containing the coordinates of the start and the end of the journey
	 */
	public static ArrayList<Coordinates> setJourney_either_to_avoid_plus_stations(TripPlanningMethods tripplanning){
		ArrayList<Coordinates> start_end = new ArrayList<Coordinates>();
		Coordinates start_coordinates = nearest_available_bike(tripplanning.getStart_journey(), tripplanning.getType_bicycle());
		start_end.add(start_coordinates);
		
		DockingStations end_station = nearest_available_end_station_not_plus(tripplanning);
		start_end.add(end_station.getCoordinates());
		return start_end;
	}
	
	/**
	 * Method to get where to start and which station to end the trip (preferably plus stations at the end), gives the final journey
	 * @param tripplanning a planning
	 * @return a list containing the coordinates of the start and the end of the journey
	 */
	public static ArrayList<Coordinates> setJourney_either_to_prefer_plus_stations(TripPlanningMethods tripplanning){
		ArrayList<Coordinates> start_end = new ArrayList<Coordinates>();
		Coordinates start_coordinates = nearest_available_bike(tripplanning.getStart_journey(), tripplanning.getType_bicycle());
		start_end.add(start_coordinates);
		
		DockingStations end_station_plus = nearest_available_end_station_plus(tripplanning);
		DockingStations end_station_global = nearest_available_end_station(tripplanning);
		
		DockingStations end_station = null;
		if(Coordinates.distance(end_station_plus.getCoordinates(), tripplanning.getEnd_journey()) <= 1.1 * Coordinates.distance(end_station_global.getCoordinates(), tripplanning.getEnd_journey())) {
			end_station = end_station_plus;
			start_end.add(end_station.getCoordinates());
		}
		else {
			end_station = end_station_global;
			start_end.add(end_station.getCoordinates());
		}
		
		System.out.println("Free bike coordinates : " + start_coordinates + ", end station : " + end_station.getID());
		return start_end;
	}
	
	
	//no method if the user want to leave the bike free or at a station, they choose what they want to do as they go
	
	
}
