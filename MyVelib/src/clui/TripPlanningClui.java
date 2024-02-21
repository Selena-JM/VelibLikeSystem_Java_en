package clui;

import main.Coordinates;

import main.MyVelib;
import planning.TripPlanningMethods;
import java.util.ArrayList;
/**
 * The TripPlanningClui class provides functionality for trip planning in the MyVelib system using command-line interface.
 * It accepts command-line arguments to specify the MyVelib network, start and end coordinates, bike type, and trip planning strategy.
 */
public class TripPlanningClui {
	/**
	 * The main method of the TripPlanningClui class.
	 * It is the entry point for trip planning in the MyVelib system using command-line interface (CLI).
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean correct_start = false;
		boolean correct_end = false;
		boolean correct_bike = false;
		String type_bike = null;
		Coordinates coor_end = null;
		Coordinates coor_start = null;
		new ArrayList<Coordinates>();
		
		//name start_coor end_coor bike start_type plus
		if (args.length == 7) {
			if(MyVelib.getName().equalsIgnoreCase(args[1])){
				if (args[2].contains(",")) {
		        	String[] start = args[2].split(",");
		        	coor_start = new Coordinates(Double.valueOf(start[0]), Double.valueOf(start[1]));
		        	correct_start = true;
				}
		        else {
		        	System.err.println("Enter valid coordinates for the start");
		        }
				
		        if (args[3].contains(",")) {
		        	String[] end = args[2].split(",");
		        	coor_end = new Coordinates(Double.valueOf(end[0]), Double.valueOf(end[1]));;
		        	correct_end = true;
	        	}
		        else {
		        	System.err.println("Enter valid coordinates for the end");
		        }

				if (args[4].equalsIgnoreCase("electrical")) {
		        	type_bike = "electrical";
		        	correct_bike = true;
		        }
		        else if (args[4].equalsIgnoreCase("mechanical")) {
		        	type_bike = "mechanical";
		        	correct_bike = true;
		        }
		        else {
		        	System.err.println("Enter electrical or mechanical for the type of bike");
		        }
				
				TripPlanningMethods tripplanning = new TripPlanningMethods(coor_start, coor_end, type_bike);
				
				if(args[5].equalsIgnoreCase("non_parked_bike_only")) {
					if(args[6].equalsIgnoreCase("avoid_plus_stations")&& correct_bike && correct_end && correct_start) {
							TripPlanningMethods.setJourney_free_to_avoid_plus_stations(tripplanning);
						}
						else if (args[6].equalsIgnoreCase("prefer_plus_stations")&& correct_bike && correct_end && correct_start) {
							TripPlanningMethods.setJourney_free_to_prefer_plus_stations(tripplanning);
						}
						else if (args[6].equalsIgnoreCase("either")&& correct_bike && correct_end && correct_start) {
							TripPlanningMethods.setJourney_free_start_only(tripplanning);
						}
						else {
							System.err.println("Enter : avoid_plus_stations, prefer_plus_stations or either");
						}
				}
				else if(args[5].equalsIgnoreCase("station_only")) {
						if(args[6].equalsIgnoreCase("avoid_plus_stations") && correct_bike && correct_end && correct_start) {
							TripPlanningMethods.setJourney_station_to_avoid_plus_stations(tripplanning);
							}
						else if (args[6].equalsIgnoreCase("prefer_plus_stations") && correct_bike && correct_end && correct_start) {
							TripPlanningMethods.setJourney_station_to_prefer_plus_stations(tripplanning);
						}
						else if (args[6].equalsIgnoreCase("either") && correct_bike && correct_end && correct_start) {
							TripPlanningMethods.setJourney_stations_only(tripplanning);
						}
						else {
							System.err.println("Enter : avoid_plus_stations, prefer_plus_stations or either");
						}
				}
				
				else if(args[5].equalsIgnoreCase("either")) {
						if(args[6].equalsIgnoreCase("avoid_plus_stations") && correct_bike && correct_end && correct_start) {
							TripPlanningMethods.setJourney_either_to_avoid_plus_stations(tripplanning);
							}
						else if (args[6].equalsIgnoreCase("prefer_plus_stations")&& correct_bike && correct_end && correct_start) {
							TripPlanningMethods.setJourney_either_to_prefer_plus_stations(tripplanning);
						}
						else if (args[6].equalsIgnoreCase("either")&& correct_bike && correct_end && correct_start) {
							TripPlanningMethods.setJourney_station_or_free_start(tripplanning);
						}
						else {
							System.err.println("Enter : avoid_plus_stations, prefer_plus_stations or either");
						}
				}
				else {
					System.err.println("Enter : non_parked_bike_only or station_only or either");
				}
			}
			else {
				System.err.println("No myVelib network with this name existing");
			}
		}
		else {
			System.err.println("Please enter the name of the myVelib network, the start coordinates, the end coordinates, the start_type and the strategy towards plus stations");
		}
	}

}
