package terminals;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

import balance_update_from_log.LogFinishedTrips;
import log_update_from_parking_slots.ParkingSlots;
import main.Bicycles;
import main.Coordinates;
import main.Users;
import renting.LogOngoingTrips;
import renting.OngoingTrip;
import main.Trip;
import main.DockingStations;
import main.MyVelib;

/**
 * This terminal (representing the app) allows to 
 * - interact with the user to rent and return a bike
 * - prevent a user from renting more than one bike at a time
 * - create an ongoing trip added to the list ongoing_trips
 * - register when a bike is parked in a docking station
 * - register when a bike is let on the streets
 * - add the trip to the log of finished trips (balances are updated 
 * automatically thanks to observer pattern) 
 */
public class RentingTerminalMethods {
	/**
	 * The identification of a  new user: he has to enter either its credit card or its registration card
	 * @return the user
	 */
	public static Users user_identification() {
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		Users user_logged = null;
		boolean error = true;
		boolean error_2 = true;
		boolean error_3 = true;
		
		// a user goes to the location of a bike (knows it thanks to the trip_planning) and wants to rent it
		System.out.println("Method of identification : credit-card or Vlib-card ?");
		
		while (error) {
			if(scanner.hasNext("credit-card")) {
				System.out.println("Card identification number : ");
				
				while (error_2) {
					int id_number = scanner2.nextInt();
					for (Users user: MyVelib.getList_users()) {
						if (user.getCredit_card().getID() == id_number) {
							user_logged = user;
							error_2 = false;
						}
					}
					if(error_2) {
						System.err.println("Please enter a valid card id");
			            continue;
					}
					error_2 = false;
				}
				
			}
			
			else if (scanner.hasNext("Vlib-card")){ //if a user has a Vlib card, its id is the one of the user
				System.out.println("Card identification number : ");
				
				while (error_3) {
					int id_number = scanner2.nextInt();
					for (Users user: MyVelib.getList_users()) {
						if (user.getID() == id_number) {
							user_logged = user;
							error_3 = false;
						}
					}
					if(error_3) {
						System.err.println("Please enter a valid card id");
			            continue;
					}
					error_3 = false;
				}
				
			}
			else {
				System.err.println("Please enter a valid type of card");
				scanner.next();
	            continue;
			}
			error = false;
		}
		//scanner.close();
		//scanner2.close();
		
		System.out.println("Welcome " + user_logged.getName() + "!");
		System.out.println(user_logged.getUser_balance());
		return user_logged;	
	}
	
	/**
	 * It allows the user to rent at most one bike.
	 * @param user_logged the user
	 * @return the boolean indicating if the user can rent a bike
	 */
	public static boolean renting_allowed(Users user_logged) {
		// check if the user is renting another bike
		boolean rent_allowed = true;
		for(OngoingTrip on_trip : LogOngoingTrips.getOngoing_trips()) {
			if(on_trip.getUser().equals(user_logged)) {
				rent_allowed = false;
				System.err.println("Cannot rent more than one bike at a time");
			}
		}
		return rent_allowed;
	}
	
	/**
	 * This part corresponds to the renting interaction for the user. The user has to choose an existing bike in order to rent it.
	 * @param user_logged the user
	 * @return the bicycle that has been rent
	 */
	public static Bicycles renting(Users user_logged) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Bike ID : ");
		
		boolean error_4 = true;
		boolean error_5 = true;
		int bike_id = 0;
		Bicycles bike_taken = null;
		OngoingTrip new_trip = null;
		
		while(error_4) {
			if(scanner.hasNextInt()) {
				while(error_5) {
					bike_id = scanner.nextInt();
					for(Bicycles bike : MyVelib.getList_bicycle()) {
						if(bike.getID() == bike_id) {
							bike_taken = bike;
							bike.setStatus("not available");
							error_5 = false;
						}
					}
					if(error_5) {
						System.err.println("Please enter a valid bike id");
			            continue;
					}
					error_5 = false;
				}
			}
			else {
				System.err.println("Please enter a number");
				scanner.next();
	            continue;
			}
			error_4 = false;
		}
		
		new_trip = new OngoingTrip(user_logged.getCoordinates(), LocalTime.now(), user_logged, bike_taken);
			
		LogOngoingTrips.addOngoingTrip(new_trip);
		
		System.out.println("New ongoing trip : " + new_trip);
		
		//updating the status of the parking slot if necessary
		for(DockingStations station : MyVelib.getList_stations()) {
			if(station.getCoordinates().equals(bike_taken.getCoordinates())) {
				for(ParkingSlots slot : station.getParkingslots()) {
					if (slot.getBike_id() == bike_taken.getID()) {
						slot.bike_id = 0;
						slot.status = "free";
					}
				}
			}
		}		
		return bike_taken;
		//scanner.close();
	}
	
	/**
	 * This part corresponds to the returning interaction for the user. The user has to park the bike before claiming that the trip is over.
	 * @param user_logged the user
	 * @param bike_taken the returned bike
	 * @param time the duration of the trip
	 */
	// if enter time == 0, then the time is calculated with the time of the machine 
	public static void returning(Users user_logged, Bicycles bike_taken, int time) {
		Scanner scanner = new Scanner(System.in);
		
		// automating the process of ending the trip if the user lets the bike at a station
		boolean free_parking = true; // to know in the loop if the bike is being parked at a station or not 
		for(DockingStations station : MyVelib.getList_stations()) {
			if(user_logged.getCoordinates().equals(station.getCoordinates())) {
				for(ParkingSlots slot : station.getParkingslots()) {
					if (slot.getStatus() == "free" && free_parking) {
						free_parking = false;
						slot.bike_id = bike_taken.getID();
						slot.status = bike_taken.getType();
							for(Bicycles bike : MyVelib.getList_bicycle()) {
								if(bike.getID() == bike_taken.getID()) {
									bike.setStatus("available");
								}
							}
					
						slot.bikeParked(time);
					}
				}
			}
		}
		
		// asking the user if they want to stop their trip (for when they leave the bike in the streets)
		Trip trip = null;
		if(free_parking) {
			OngoingTrip finished_trip = null;
			
			System.out.println("Want to end the trip here ?");
			
			boolean error = true;
			while(error) {
				if(scanner.hasNext("yes")) {
					for(OngoingTrip on_trip : LogOngoingTrips.getOngoing_trips()) {
						if(on_trip.getUser().equals(user_logged)) {
							finished_trip = on_trip;
							int elapsedMinutes = time;
							
							if(time == 0) {
								LocalTime end_time = LocalTime.now();
								elapsedMinutes = (int) Duration.between(on_trip.getStart_time(), end_time).toMinutes();
							}
							trip = new Trip(on_trip.getStart_coordinates(), user_logged.getCoordinates(), user_logged, on_trip.getBike(), elapsedMinutes);
							
							for(Bicycles bike : MyVelib.getList_bicycle()) {
								if(bike.getID() == bike_taken.getID()) {
									bike.setStatus("available");
								}
							}
						}	
					}
				}
				else {
					System.err.println("Trip still ongoing");
					System.out.println("Want to end the trip here ?");
					scanner.next();
		            continue;
				}
				error = false;
				
				LogFinishedTrips.addTrip(trip);
				LogOngoingTrips.removeOngoingTrip(finished_trip);
			}
		}
		//scanner.close();
		System.out.println(user_logged.getUser_balance());
	}
	
	/**
	 * This method simulates the global interaction between the user and the terminal/
	 * @param start_coor the starting coordinates of the trip
	 * @param end_coor the ending coordinates of the trip
	 * @param time the duration of the trip
	 */
	public static void Terminal(Coordinates start_coor, Coordinates end_coor, int time) {
		Users user_logged = RentingTerminalMethods.user_identification();
		
		user_logged.setCoordinates(start_coor);
		
		
		if(RentingTerminalMethods.renting_allowed(user_logged)) {
			Bicycles bike_taken = RentingTerminalMethods.renting(user_logged);

			user_logged.setCoordinates(end_coor);
			
			RentingTerminalMethods.returning(user_logged, bike_taken, time);
		}
		
		// TerminalMethods.renting_allowed(user_logged); // to verify that someone cannot rent 2 bikes at a time
		
	}

}
