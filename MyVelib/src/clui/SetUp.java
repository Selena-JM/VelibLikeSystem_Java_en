package clui;


import main.*;

/**
 * The SetUp class provides functionality for setting up the MyVelib system.
 * It allows users to create a new MyVelib instance and configure its parameters.
 * The class accepts command-line arguments to customize the system's settings.
 */
public class SetUp {
	/**
	 * The main method of the SetUp class.
	 * It is the entry point for setting up the MyVelib system.
	 * 
	 * @param arguments the command-line arguments
	 */
	public static void main(String[] arguments) {
		// TODO Auto-generated method stub
		MyVelib myvelib = new MyVelib();
		
		if(arguments.length == 2) {
			myvelib.myVelibSetUp(10, 10, 70, 4);
			MyVelib.setName(arguments[1]);
			System.out.println("Name of the MyVelib created : " + MyVelib.getName());
		}
		
		else if(arguments.length == 6) {
			
			System.out.println("Name of the MyVelib created : " + arguments[1] + ", number of stations : " + arguments[2] + ", number of parking slots per station : " + arguments[3] + ", number of bikes : " + arguments[5] + ", size of the square : " + arguments[4]);
			myvelib.myVelibSetUp(Integer.valueOf(arguments[2]), Integer.valueOf(arguments[3]), Integer.valueOf(arguments[5]), Integer.valueOf(arguments[4]));
			MyVelib.setName(arguments[1]);
		}
		
		else if(arguments.length == 7) {
			
			System.out.println("Name of the MyVelib created : " + arguments[1] + ", number of users : " + arguments[6] + ", number of stations : " + arguments[2] + ", number of parking slots per station : " + arguments[3] + ", number of bikes : " + arguments[5] + ", size of the square : " + arguments[4]);
			myvelib.myVelibSetUp(Integer.valueOf(arguments[2]), Integer.valueOf(arguments[3]), Integer.valueOf(arguments[5]), Integer.valueOf(arguments[4]), Integer.valueOf(arguments[6]));
			MyVelib.setName(arguments[1]);
		}
		
		else {
			System.err.println("Try with only the name or the name and the 4 integer values wanted");
		}
		
	}
}
