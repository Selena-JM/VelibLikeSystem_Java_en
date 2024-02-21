package clui;

import balance_update_from_log.LogFinishedTrips;
import log_update_from_parking_slots.ParkingSlots;
import main.Bicycles;
import main.Coordinates;
import main.DockingStations;
import main.MyVelib;
import main.Trip;
import main.Users;
import renting.LogOngoingTrips;
import renting.OngoingTrip;
/**
 * The ReturnBike class provides functionality for returning a rented bike to a docking station or a specific location.
 * Users can return a bike by providing their user ID and either a station ID or coordinates as input.
 * The class checks for the availability of parking slots at the specified station or the validity of the coordinates and handles the return process.
 * The class updates ongoing trip information, records finished trips, and updates user balances.
 */
public class ReturnBike {
	/**
	 * The main method of the ReturnBike class.
	 * It is the entry point for returning a rented bike in the MyVelib system.
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean free_parking = true; // to know in the loop if the bike is being parked at a station or not 
		boolean good_station_id = false;
		boolean good_user_id = false;
		boolean good_trip = false;
		
		
		if(args.length == 4) {
			for(Users user : MyVelib.getList_users()) {
				if(user.getID() == Integer.valueOf(args[1])) {
					good_user_id = true;
					
					if(args[2].contains(",")) {
						String[] coor = args[2].split(",");
						Coordinates coordinates = new Coordinates(Double.valueOf(coor[0]), Double.valueOf(coor[1]));
						Trip trip = null;
						OngoingTrip finished_trip = null;
						
						for(OngoingTrip on_trip : LogOngoingTrips.getOngoing_trips()) {
							if(on_trip.getUser().equals(user)) {
								good_trip = true;
								finished_trip = on_trip;

								trip = new Trip(on_trip.getStart_coordinates(), coordinates, user, on_trip.getBike(), Integer.valueOf(args[3]));
								
								for(Bicycles bike : MyVelib.getList_bicycle()) {
									if(bike.getID() == on_trip.getBike().getID()) {
										bike.setStatus("available");
									}
								}
							}
						}
						if(!good_trip) {
							System.out.println("No bike rented by this user");
						}
						else {
						System.out.println("Bike returned to coordinates : " + coordinates);
						
						LogFinishedTrips.addTrip(trip);
						LogOngoingTrips.removeOngoingTrip(finished_trip);
						
						System.out.println(user.getUser_balance());
						}
						
					}
					
					else {
						for(DockingStations station : MyVelib.getList_stations()) {
							if(Integer.valueOf(args[2]) == station.getID()) {
								good_station_id = true;
								
								for(ParkingSlots slot : station.getParkingslots()) {
									if (slot.getStatus().equalsIgnoreCase("free") && free_parking) {
										free_parking = false;
										OngoingTrip finished_trip = null;
										
										for(OngoingTrip on_trip : LogOngoingTrips.getOngoing_trips()) { 
											if(on_trip.getUser().getID() == user.getID()) {
												good_trip = true;
												finished_trip = on_trip;
												
												slot.bike_id = on_trip.getBike().getID();
												slot.status = on_trip.getBike().getType();
												
												for(Bicycles bike : MyVelib.getList_bicycle()) {
													if(bike.getID() == on_trip.getBike().getID()) {
														bike.setStatus("available");
													}
												}
												
												Trip trip = new Trip(on_trip.getStart_coordinates(), station.getCoordinates(), on_trip.getUser(), on_trip.getBike(), Integer.valueOf(args[3]));
												System.out.println(trip);
												
												slot.setBeing_taken(true);
												slot.notifyObserversLog(trip);
												
												System.out.println("Bike returned to station n° : " + station.getID() + " in parking slot n° : " + slot.getID());
												System.out.println(user.getUser_balance());
											}
										}
										if(!good_trip) {
											System.out.println("No bike rented by this user");
										}
										else {
										LogOngoingTrips.removeOngoingTrip(finished_trip);
										}
										
									}
								}
								
								if(free_parking) {
									System.out.println("No available slot in the station n° " + args[2]);
								}
							}
						}
						if(!good_station_id) {
							System.out.println("No station with such id");
						}
						
					}
				}	
				
			}
			if(!good_user_id) {
				System.out.println("No user with such id");
			}
		}
		else {
			System.err.println("Please enter userID, stationID, duration, or userID, GPS coordinates, duration");
		}
	}
}
