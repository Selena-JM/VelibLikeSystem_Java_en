package clui;

import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * The Clui class provides a command-line user interface for interacting with the MyVelib system.
 * Users can run tests, enter commands manually, and stop the interface.
 * The class reads inputs to realize desired actions and delegates the execution to respective classes.
 */
public class Clui {

	/**
	 * The main method of the Clui class.
	 * It is the entry point for the command-line interface.
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// initializing the MyVelib
        try {
        	String configFilePath = "../my_velib.ini";
            Properties config = new Properties();
            config.load(new FileInputStream(configFilePath));

            String setupname = config.getProperty("setup");
            
            String string = "setup," +setupname;
            
            String[] setup = string.split(",");
            
            SetUp.main(setup);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // reading the inputs to realize the desired actions
        Scanner scanner3 = new Scanner(System.in);
        String scanned3 = null;
        
		if(args.length == 0) {
			System.out.println("Enter 'runtest' followed by the name of the file or 'enter_manually'");
	    	scanned3 = scanner3.nextLine();
			
	    	args = scanned3.split(" ");
        }
		
		while(!args[0].equalsIgnoreCase("stop")) {
			if(args.length == 2 && args[0].equalsIgnoreCase("runtest")) {
		        try {
		        	String[] split = args[1].split(Pattern.quote("."));
		            PrintStream fileOutput = new PrintStream(new FileOutputStream("../"+split[0]+"output.txt"));
		            System.setOut(fileOutput);
		            System.setErr(fileOutput);
	
		    			try {
		    				Scanner scanner2 = new Scanner(new FileReader("../"+args[1]));
		    				
		    				String scanned2 = scanner2.nextLine();
		    				System.out.println(scanned2);
		    				
		    				args = scanned2.split(" ");
		    				
		    				while(!args[0].equalsIgnoreCase("stop")){
		    					
		    					if(args[0].equalsIgnoreCase("setup")) {
		    						SetUp.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("addUser")) {
		    						AddUser.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("offline")) {
		    						Offline.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("online")) {
		    						Online.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("rentBike")) {
		    						RentBike.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("returnBike")) {
		    						ReturnBike.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("displayStation")) {
		    						DisplayStation.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("displayUser")) {
		    						DisplayUser.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("sortStation")) {
		    						SortStation.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("display")) {
		    						Display.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("plus")) {
		    						Plus.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("standard")) {
		    						Standard.main(args);
		    					}
		    					
		    					else if(args[0].equalsIgnoreCase("tripplanningclui")) {
		    						TripPlanningClui.main(args);
		    					}
		    					
		    					else if (args[0].equalsIgnoreCase("help")) {
		    						Help.main(args);
		    					}
		    					
		    					else {
		    						System.out.println("No such command");
		    					}
		    					
		    					System.out.println("");
		    					System.out.println("Enter next command or stop");
		    					
		    					scanned2 = scanner2.nextLine();
		    					System.out.println(scanned2);
		
		    					args = scanned2.split(" ");
		    					
		    				}
		    			
		    			} catch (FileNotFoundException e) {
		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
		    			}
		    		
	
		            fileOutput.close();
		            
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        }
	        }
	        
	        else if (args.length == 1 && args[0].equalsIgnoreCase("enter_manually")) {
	    		try {
	    			PrintStream fileOutput = new PrintStream(new FileOutputStream("../manualoutput.txt"));
		            System.setOut(fileOutput);
		            System.setErr(fileOutput);
		            
		            Scanner scanner = new Scanner(System.in);
		            
					String scanned = scanner.nextLine();
					System.out.println(scanned);
					args = scanned.split(" ");
		
					while(!args[0].equalsIgnoreCase("stop")){
						
						if(args[0].equalsIgnoreCase("setup")) {
							SetUp.main(args);
						}
						
						else if(args[0].equalsIgnoreCase("addUser")) {
							AddUser.main(args);
						}
						
						else if(args[0].equalsIgnoreCase("offline")) {
							Offline.main(args);
						}
						
						else if(args[0].equalsIgnoreCase("online")) {
							Online.main(args);
						}
						
						else if(args[0].equalsIgnoreCase("rentBike")) {
							RentBike.main(args);
						}
						
						else if(args[0].equalsIgnoreCase("returnBike")) {
							ReturnBike.main(args);
						}
						
						else if(args[0].equalsIgnoreCase("displayStation")) {
							DisplayStation.main(args);
						}
						
						else if(args[0].equalsIgnoreCase("displayUser")) {
							DisplayUser.main(args);
						}
						
						else if(args[0].equalsIgnoreCase("sortStation")) {
							SortStation.main(args);
						}
						
						else if(args[0].equalsIgnoreCase("display")) {
							Display.main(args);
						}
						
    					else if(args[0].equalsIgnoreCase("plus")) {
    						Plus.main(args);
    					}
    					
    					else if(args[0].equalsIgnoreCase("standard")) {
    						Standard.main(args);
    					}
						
    					else if(args[0].equalsIgnoreCase("tripplanningclui")) {
    						TripPlanningClui.main(args);
    					}
						
    					else if(args[0].equalsIgnoreCase("help")) {
    						Help.main(args);
    					}
	
						else {
							System.out.println("No such command");
						}
						
						System.out.println("");
						System.out.println("Enter next command or stop");
						scanned = scanner.nextLine();
						System.out.println(scanned);
						
						args = scanned.split(" ");
						
					}
					
					fileOutput.close();
					scanner.close();
				
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        }
			}
			
	        else if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
	        	Help.main(args);
	        	
	        	System.out.println("");
				System.out.println("Now you can enter 'runtest' followed by the name of the file, 'enter_manually' or 'stop'");
				scanned3 = scanner3.nextLine();
				
				args = scanned3.split(" ");
	        }
			
			else {
				System.out.println("");
				System.out.println("Enter 'runtest' followed by the name of the file or 'enter_manually' or 'stop'");
				scanned3 = scanner3.nextLine();
				
				args = scanned3.split(" ");
			}
		}
		
		scanner3.close();
	}
}
