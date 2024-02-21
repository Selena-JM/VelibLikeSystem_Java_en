package clui;

import main.DockingStations;
import main.MyVelib;
/**
 * The Standard class provides functionality for setting a docking station as a standard station in the MyVelib system.
 * It accepts command-line arguments to specify the MyVelib network and the station ID.
 * The class searches for the station with the given ID and sets its type as "standard" if found.
 */
public class Standard {
	/**
	 * The main method of the Standard class.
	 * It is the entry point for setting a docking station as a standard station in the MyVelib system.
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
						station.setType("standard");
						correct_id = true;
						System.out.println("Station n° : " + station.getID() + " set as standard");
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
