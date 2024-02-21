package terminals;

import java.util.ArrayList;
import java.util.Scanner;
import main.Coordinates;
import planning.TripPlanningMethods;

/**
 * This class acts as a terminal for a user. It allows to the user to get the coordinates of its upcoming trip on a bike by entering:
 * - start_journey_x : the x coordinates of the start of its journey
 * - start_journey_y : the y coordinates of the start of its journey
 * - type_bike : the chosen type of bike
 * - start : if the user prefers to start at a station, not at a station or either
 * - plus : if the user prefers at the end to avoid plus stations, to go to a plus station or either
 */
public class TripPlanningTerminal {
	/**
	 * This method gives the coordinates of the trip to the user listening to its choices
	 * 
	 * @return the list containing the coordinates of the trip
	 */
	public static ArrayList<Coordinates> tripPlanningTerminal(){
		Scanner scanner = new Scanner(System.in); 
		
		Double start_journey_x = null;
		Double start_journey_y = null;
		boolean error = true;
		
		System.out.println("GPS coordinates of the start : ");
		while (error) {
	        if (scanner.hasNextDouble()) {
	        	start_journey_x = scanner.nextDouble();
	        	}
	        else {
	        	System.err.println("Numbers only, enter them in a row");
	            scanner.next();
	            continue;
	        }
	        if (scanner.hasNextDouble())
	        	start_journey_y = scanner.nextDouble();
	        else {
	        	System.err.println("Numbers only, enter them in a row");
	            scanner.next();
	            continue;
	        }
	        error = false;
	    }
		Coordinates start_journey = new Coordinates(start_journey_x, start_journey_y);
		System.out.println(start_journey);
		
		
		System.out.println("GPS coordinates of the end : ");
		Double end_journey_x = null;
		Double end_journey_y = null;
		error = true;
		while (error) {
	        if (scanner.hasNextDouble()) {
	        	end_journey_x = scanner.nextDouble();}
	        else {
	        	System.err.println("Numbers only, enter them in a row");
	            scanner.next();
	            continue;
	        }
	        if (scanner.hasNextDouble()) {
	        	end_journey_y = scanner.nextDouble();}
	        else {
	        	System.err.println("Numbers only, enter them in a row");
	            scanner.next();
	            continue;
	        }
	        error = false;
	    }

		Coordinates end_journey = new Coordinates(end_journey_x, end_journey_y);
		System.out.println(end_journey);
		
		
		System.out.println("Type of bicycle : ");
		String type_bike = null;
		//Scanner scanner2 = new Scanner(System.in);
		
		error = true;
		while (error) {
	        if (scanner.hasNext("electrical")) {
	        	type_bike = "electrical";
	        }
	        else if (scanner.hasNext("mechanical")) {
	        	type_bike = "mechanical";
	        }
	        else {
	        	System.err.println("Enter electrical or mechanical");
	            scanner.next();
	            continue;
	        }
	        error = false;
	    }
		
		System.out.println("Type of bicycle entered: " + type_bike);
		
		
		TripPlanningMethods tripplanning = new TripPlanningMethods(start_journey, end_journey, type_bike);
		ArrayList<Coordinates> start_end = null;
		
		System.out.println("Start : non_parked_bike_only, station_only or either ? ");
		Scanner scanner3 = new Scanner(System.in);
		Scanner scanner4 = new Scanner(System.in);
		String start = null;
		String plus = null;
		error = true;
		boolean error2 = true;
		
		while (error) {
			if(scanner4.hasNext("non_parked_bike_only")) {
				start = "non_parked_bike_only";
				System.out.println("Do you want to : avoid_plus_stations, prefer_plus_stations or either");
				while(error2) {
					if(scanner3.hasNext("avoid_plus_stations")) {
						plus = "avoid";
						start_end = TripPlanningMethods.setJourney_free_to_avoid_plus_stations(tripplanning);
						}
					else if (scanner3.hasNext("prefer_plus_stations")) {
						plus = "prefer";
						start_end = TripPlanningMethods.setJourney_free_to_prefer_plus_stations(tripplanning);
					}
					else if (scanner3.hasNext("either")) {
						plus = "either";
						start_end = TripPlanningMethods.setJourney_free_start_only(tripplanning);
					}
					else {
						System.err.println("Enter : avoid_plus_stations, prefer_plus_stations or either");
			            scanner3.next();
			            continue;
					}
					error2 = false;
				}
			}
			else if(scanner4.hasNext("station_only")) {
				start = "station_only";
				System.out.println("Do you want to : avoid_plus_stations, prefer_plus_stations or either");
				
				while(error2) {
					if(scanner3.hasNext("avoid_plus_stations")) {
						plus = "avoid";
						start_end = TripPlanningMethods.setJourney_station_to_avoid_plus_stations(tripplanning);
						}
					else if (scanner3.hasNext("prefer_plus_stations")) {
						plus = "prefer";
						start_end = TripPlanningMethods.setJourney_station_to_prefer_plus_stations(tripplanning);
					}
					else if (scanner3.hasNext("either")) {
						plus = "either";
						start_end = TripPlanningMethods.setJourney_stations_only(tripplanning);
					}
					else {
						System.err.println("Enter : avoid_plus_stations, prefer_plus_stations or either");
			            scanner3.next();
			            continue;
					}
					error2 = false;
				}
			}
			else if(scanner4.hasNext("either")) {
				start = "either";
				System.out.println("Do you want to : avoid_plus_stations, prefer_plus_stations or either");
				
				while(error2) {
					if(scanner3.hasNext("avoid_plus_stations")) {
						plus = "avoid";
						start_end = TripPlanningMethods.setJourney_either_to_avoid_plus_stations(tripplanning);
						}
					else if (scanner3.hasNext("prefer_plus_stations")) {
						plus = "prefer";
						start_end = TripPlanningMethods.setJourney_either_to_prefer_plus_stations(tripplanning);
					}
					else if (scanner3.hasNext("either")) {
						plus = "either";
						start_end = TripPlanningMethods.setJourney_station_or_free_start(tripplanning);
					}
					else {
						System.err.println("Enter : avoid_plus_stations, prefer_plus_stations or either");
			            scanner3.next();
			            continue;
					}
					error2 = false;
				}
			}
			else {
				System.err.println("Enter : non_parked_bike_only, station_only or either");
	            scanner4.next();
	            continue;
			}
			error = false;
		}
		
		//scanner.close();
		//scanner2.close();
		//scanner3.close();
		//scanner4.close();
		
		System.out.println("Choice of start : " + start);
		System.out.println("Plus stations : " + plus);
		
		if (start_end == null) {
			System.out.println("No such trip is possible");
		}
		else {
		System.out.println("Coordinates of the planned trip : " + start_end);
		}
		return start_end;
	}
	
}

