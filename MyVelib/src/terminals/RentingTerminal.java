package terminals;

import main.Coordinates;

import java.util.Scanner;

/**
 * The class describes a terminal that the user will interact with.
 * It simulates the renting system by asking information to the user.
 */
public class RentingTerminal {
	/**
	 * main
	 * @param args main
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Do you want to rent a bike ?");
		
		
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		Scanner scanner3 = new Scanner(System.in);
		
		boolean error = true;
		while(error && !scanner.hasNext("no")) {
			if(scanner.hasNext("yes")) {
				Double start_journey_x = null;
				Double start_journey_y = null;
				boolean error2 = true;
				
				System.out.println("GPS coordinates of the start : ");
				while (error2) {
			        if (scanner2.hasNextDouble())
			        	start_journey_x = scanner2.nextDouble();
			        else {
			        	System.err.println("Numbers only, enter them in a row");
			            scanner2.next();
			            continue;
			        }
			        if (scanner2.hasNextDouble())
			        	start_journey_y = scanner2.nextDouble();
			        else {
			        	System.err.println("Numbers only, enter them in a row");
			            scanner2.next();
			            continue;
			        }
			        error2 = false;
			    }
				Coordinates start_journey = new Coordinates(start_journey_x, start_journey_y);
				System.out.println(start_journey);
				
				
				System.out.println("GPS coordinates of the end : ");
				Double end_journey_x = null;
				Double end_journey_y = null;
				error2 = true;
				while (error2) {
			        if (scanner2.hasNextDouble())
			        	end_journey_x = scanner2.nextDouble();
			        else {
			        	System.err.println("Numbers only, enter them in a row");
			            scanner2.next();
			            continue;
			        }
			        if (scanner2.hasNextDouble())
			        	end_journey_y = scanner2.nextDouble();
			        else {
			        	System.err.println("Numbers only, enter them in a row");
			            scanner2.next();
			            continue;
			        }
			        error2 = false;
			    }

				Coordinates end_journey = new Coordinates(end_journey_x, end_journey_y);
				System.out.println(end_journey);
				
				
				boolean error3 = true;
				int time = 0;
				
				System.out.println("Duration of the ride (enter 0 to use the machine time when returning) : ");
				while (error3) {
			        if (scanner3.hasNextInt())
			        	time = scanner3.nextInt();
			        else {
			        	System.err.println("Enter an integer for the duration of the ride");
			            scanner3.next();
			            continue;
			        }
			        error3 = false;
			    }
				System.out.println("Duration of the ride entered : " + time);
				RentingTerminalMethods.Terminal(start_journey, end_journey, time);
			}
			
			else {
				System.out.println("Do you want to rent a bike ? ");
		        scanner.next();
		        continue;
			}
			error = false;
		}
		
		scanner.close();
		scanner2.close();
		scanner3.close();
	}
}
