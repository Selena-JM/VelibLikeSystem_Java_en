package clui;

import main.DockingStations;
import main.MyVelib;
/**
 * The Plus class provides functionality to set a docking station in the MyVelib system as a "plus" station.
 * Users can set a station as "plus" by providing the MyVelib network name and the station ID as input.
 * The class searches for the station with the given ID and sets its type as "plus" if found.
 */
public class Plus {
	/**
	 * The main method of the Plus class.
	 * It is the entry point for setting a docking station as a "plus" station in the MyVelib system.
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
						station.setType("plus");
						correct_id = true;
						System.out.println("Station n° : " + station.getID() + " set as plus");
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
