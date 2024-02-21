package clui;

import log_update_from_parking_slots.ParkingSlots;
import main.DockingStations;
import main.MyVelib;
import main.Users;

/**
 * 
 * The Display class provides functionality to display information about the MyVelib system.
 * Users can view the number of stations, number of users, number of bikes, and detailed information about each station and user.
 * The class reads the MyVelib name as input and displays the corresponding information.
 */
public class Display {
	/**
	 * The main method of the Display class.
	 * It is the entry point for displaying MyVelib information.
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 2) {
			if(MyVelib.getName().equalsIgnoreCase(args[1])){
				System.out.println("MyVelib [Number stations : " + MyVelib.getList_stations().size() + ", number of users : " + MyVelib.getList_users().size() + ", number of bikes : " + MyVelib.getList_bicycle().size() + "]");
				for(DockingStations station : MyVelib.getList_stations()) {
					System.out.println("");
					System.out.println(station);
					for(ParkingSlots slot : station.getParkingslots()) {
						System.out.println(slot);
					}
				}
				for(Users user : MyVelib.getList_users()) {
					System.out.println(user);
				}
			}
			else {
				System.out.println("No MyVelib with such name");
			}
		}
		else {
			System.out.println("Enter the MyVelib name you want displayed");
		}
	}
}
