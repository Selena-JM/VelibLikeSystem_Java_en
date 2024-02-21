package clui;

import main.DockingStations;
import main.MyVelib;

/**
 * The DisplayStation class provides functionality to display information about a specific docking station in the MyVelib system.
 * Users can view the balance information of a station by providing the MyVelib network name and the station ID as input.
 * The class searches for the station with the given ID and displays its balance information if found.
 */
public class DisplayStation {
	/**
	 * The main method of the DisplayStation class.
	 * It is the entry point for displaying station information in the MyVelib system.
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean good_station_id = false;
		
		if (args.length == 3) {
			if(MyVelib.getName().equalsIgnoreCase(args[1])){
				for(DockingStations station : MyVelib.getList_stations()) {
					if(station.getID() == Integer.valueOf(args[2])) {
						good_station_id = true;
						System.out.println(station.getStation_balance());
					}
				}
				if (!good_station_id){
					System.err.println("No station with such id");
				}
			}
			else {
				System.err.println("No myVelib network with this name existing");
			}
		}
		else {
			System.err.println("Please enter a velibnetwork name and a station ID");
		}
	}
}
