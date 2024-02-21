package terminals;

import java.util.ArrayList;
import java.util.Scanner;

import main.Coordinates;
import main.MyVelib;

/**
 * This class simulates the interaction between the user and the terminal for planning its trip.
 */
public class Client {
	/**
	 * main
	 * @param args main
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyVelib myvelib = new MyVelib();
		
		myvelib.myVelibSetUp(10, 10, 70, 4, 5); //10 stations with 10 slots each, 70 bikes in total, it is a square of 4 km and it has 20 users
		
		
		ArrayList<Coordinates> start_end = TripPlanningClient.tripPlanningClient();
		
		System.out.println("");
		System.out.println("Do you want to do this planned trip ? If you have not planned a trip, enter no");
		
		
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);

		boolean error = true;
		while(error) {
			
			if(scanner.hasNext("yes")) {
				boolean error2 = true;
				int time = 0;
				
				System.out.println("Duration of the ride (enter 0 to use the machine time when returning) : ");
				
				while (error2) {
			        if (scanner2.hasNextInt()) {
			        	time = scanner2.nextInt();
			        }
			        else {
			        	System.err.println("Enter an integer for the duration of the ride");
			        	scanner2.next();
			            continue;
			        }
			        error2 = false;
			    }
				error = false;
				System.out.println("Duration of the ride entered : " + time);
				RentingTerminalMethods.Terminal(start_end.get(0), start_end.get(1), time);
			}
			
			else if (scanner.hasNext("no")) {
				RentingTerminal.main(args);
				error = false;
			}
			
			else {
				System.out.println("Do you want to do the planned trip ? Enter yes or no");
				scanner.next();
				continue;
			}
		}
		
		//scanner.close();
		//scanner2.close();
	}
}
