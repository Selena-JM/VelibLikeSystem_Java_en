package clui;

import main.DockingStations;
import main.MyVelib;
/**
 * The Offline class provides functionality to set a docking station in the MyVelib system as offline.
 * Users can set a station offline by providing the MyVelib network name and the station ID as input.
 * The class searches for the station with the given ID and sets its state as "offline" if found.
 */
public class Offline {
	/**
	 * The main method of the Offline class.
	 * It is the entry point for setting a docking station offline in the MyVelib system.
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean correct_id = false;
		
		if (args.length == 3) {
			if(MyVelib.getName().equalsIgnoreCase(args[1])){
				for(DockingStations station : MyVelib.getList_stations()) {
					if(station.getID() == Integer.valueOf(args[2])) {
						station.setState("offline");
						correct_id = true;
						System.out.println("Station n° : " + station.getID() + " put offline");
					}
				}
				if(!correct_id) {
					System.err.println("No station with such id");
				}
			}
			else {
				System.err.println("No myVelib network with this name existing");
			}
		}
		else {
			System.err.println("Please enter the name of the myVelib network and the station id");
		}
	}
}
