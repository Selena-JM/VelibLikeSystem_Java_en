package clui;

import main.DockingStations;
import main.MyVelib;
/**
 * The Online class provides functionality to set a docking station in the MyVelib system as online.
 * Users can set a station online by providing the MyVelib network name and the station ID as input.
 * The class searches for the station with the given ID and sets its state as "online" if found.
 */
public class Online {
	/**
	 * The main method of the Online class.
	 * It is the entry point for setting a docking station online in the MyVelib system.
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
						station.setState("online");
						correct_id = true;
						System.out.println("Station n° : " + station.getID() + " put online");
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
