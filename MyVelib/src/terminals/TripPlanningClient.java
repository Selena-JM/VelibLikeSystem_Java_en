package terminals;

import java.util.ArrayList;
import java.util.Scanner;

import main.Coordinates;

/**
 * This class is the client interface for using the terminal to make a trip planning.
 * Launch this file to enter the terminal.
 */
public class TripPlanningClient {

	/**
	 * The client interface for planning a trip for a user.
	 * @return the coordinates of the starting and of the ending point of the trip
	 */
	public static ArrayList<Coordinates> tripPlanningClient() {
		// TODO Auto-generated method stub
		
		System.out.println("Do you want to plan a trip ? ");
		
		boolean error = true;
		Scanner scanner = new Scanner(System.in);
		ArrayList<Coordinates> start_end = null;
		
		while(error && !scanner.hasNext("no")) {
			if(scanner.hasNext("yes")) {
				start_end = TripPlanningTerminal.tripPlanningTerminal();
			}
			else {
				System.out.println("Do you want to plan a trip ? ");
		        scanner.next();
		        continue;
			}
			error = false;
		}
		//scanner.close();
		return start_end;

	}

}
