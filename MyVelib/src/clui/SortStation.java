package clui;

import java.util.ArrayList;
import java.util.Collections;

import balance_update_from_log.StationBalance;
import main.DockingStations;
import main.MyVelib;
import sorting.LeastOccupiedStationSort;
import sorting.MostUsedStationSort;
/**
 * The SortStation class provides functionality for sorting docking stations in the MyVelib system.
 * It allows users to sort the docking stations based on different sorting policies, such as least occupied or most used.
 * The class accepts command-line arguments to specify the MyVelib network and the desired sorting methods.
 */
public class SortStation {
	/**
	 * The main method of the SortStation class.
	 * It is the entry point for sorting the docking stations in the MyVelib system.
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length == 3) {
			if(MyVelib.getName().equalsIgnoreCase(args[1])){
				if(args[2].equalsIgnoreCase("Least_Occupied")) {
					LeastOccupiedStationSort least_occupied = new LeastOccupiedStationSort();
					
					ArrayList<StationBalance> list_station_balances = new ArrayList<StationBalance>();
					
					for (DockingStations station : MyVelib.getList_stations()) {
						list_station_balances.add(station.getStation_balance());
					}
					Collections.sort(list_station_balances,least_occupied);
					
					System.out.println(list_station_balances);
					
				}
				else if(args[2].equalsIgnoreCase("Most_Used")) {
					MostUsedStationSort most_used = new MostUsedStationSort();
					
					ArrayList<StationBalance> list_station_balances = new ArrayList<StationBalance>();
					
					for (DockingStations station : MyVelib.getList_stations()) {
						list_station_balances.add(station.getStation_balance());
					}
					Collections.sort(list_station_balances, most_used);
					
					System.out.println(list_station_balances);
				}
				else {
					System.err.println("No such sorting policy");
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
