package clui;

import java.time.LocalTime;

import log_update_from_parking_slots.ParkingSlots;
import main.Bicycles;
import main.Coordinates;
import main.DockingStations;
import main.MyVelib;
import main.Users;
import renting.LogOngoingTrips;
import renting.OngoingTrip;
import terminals.RentingTerminalMethods;
/**
 * The RentBike class provides functionality for renting a bike from a docking station or a specific location.
 * Users can rent a bike by providing their user ID and either a station ID or coordinates as input.
 * The class checks for the availability of bikes at the specified station or location and handles the rental process.
 * Ongoing trip information is recorded and logged.
 */
public class RentBike {
	/**
	 * The main method of the RentBike class.
	 * It is the entry point for renting a bike in the MyVelib system.
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean bike_available = false;
		Bicycles bike_to_take = null;
		boolean good_user_id = false;
		boolean good_station_id = false;
		boolean good_coordinates = false;
		OngoingTrip new_trip = null;
		
		if (args.length == 3) {
			for(Users user : MyVelib.getList_users()) {
				if(user.getID() == Integer.valueOf(args[1])) {
					good_user_id = true;
					
					if(args[2].contains(",")) {
						String[] coor = args[2].split(",");
						Coordinates coordinates = new Coordinates(Double.valueOf(coor[0]), Double.valueOf(coor[1]));
						
						for(Bicycles bike : MyVelib.getList_bicycle()) {
							if(bike.getCoordinates().equals(coordinates) && !good_coordinates) {
								good_coordinates = true;
								
								if (RentingTerminalMethods.renting_allowed(user)) {
									bike_to_take = bike;
									bike_to_take.setStatus("not_available");
									System.out.println("Bike n° : " + bike_to_take.getID() + " taken from coordinates : " + coordinates);
									
									
									new_trip = new OngoingTrip(coordinates, LocalTime.now(), user, bike_to_take);
									
									LogOngoingTrips.addOngoingTrip(new_trip);
									
									System.out.println("New ongoing trip : " + new_trip);
								}
							}
						}
					}
					
					else {
						for(DockingStations station : MyVelib.getList_stations()) {
							if(station.getID() == Integer.valueOf(args[2])) {
								good_station_id = true;
								if(station.getState().equalsIgnoreCase("offline")) {
									System.out.println("Station n° : " + station.getID() + " offline, can't rent");
								}
								else {
									for(ParkingSlots slot : station.getParkingslots()) {
										if ((slot.getStatus() == "electrical" || slot.getStatus() == "mechanical") && !bike_available) {
											for(Bicycles bike : MyVelib.getList_bicycle()) {
												if (bike.getID() == slot.getBike_id()) {
													bike_to_take = bike;
												}
											}
											bike_available = true;
											if(RentingTerminalMethods.renting_allowed(user)) {
												slot.setBike_id(0);
												slot.setStatus("free");
												bike_to_take.setStatus("not_available");
												System.out.println("Bike n° : " + bike_to_take.getID() + " taken from slot n° : " + slot.getID() + " in station n° : " + args[2]);
											
												new_trip = new OngoingTrip(station.getCoordinates(), LocalTime.now(), user, bike_to_take);
												
												LogOngoingTrips.addOngoingTrip(new_trip);
												
												System.out.println("New ongoing trip : " + new_trip);
											
											}
										}
									}
									if(!bike_available) {
										System.err.println("No bike available at this station");
									}
								}
							}
						}
					}
					
					if(!good_station_id && !good_coordinates) {
						System.err.println("No station with such id or bike parked at such a location");
					}
					
				}
			}
			if(!good_user_id) {
				System.err.println("No user with such id");
			}
		}
		else {
			System.err.println("Please enter user id and station id");
		}
	}
}
