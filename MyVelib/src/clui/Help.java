package clui;
/**
 * <h1>Help</h1>
 * 
 * <p>The Help class provides a command-line interface to display information about the available commands and their usage in the MyVelib system.
 * It provides a list of mandatory commands and additional commands along with their arguments.</p>
 * 
 * <h2>Usage</h2>
 * <ul>
 *   <li>To enter a command, write the name of the command and the arguments without the '&lt;' and '&gt;'.</li>
 *   <li>The class assumes the availability of the MyVelib system and relevant commands.</li>
 * </ul>
 * 
 * <h2>Mandatory Commands</h2>
 * <ul>
 *   <li><code>setup &lt;velibnetworkName&gt;</code>: Sets up a MyVelib network with the specified name.</li>
 *   <li><code>setup &lt;name&gt; &lt;nstations&gt; &lt;nslots&gt; &lt;s&gt; &lt;nbikes&gt;</code>: Sets up a MyVelib network with the given parameters.</li>
 *   <li><code>addUser &lt;userName&gt; &lt;cardType&gt; &lt;velibnetworkName&gt;</code>: Adds a user with the specified name and card type to the MyVelib network.</li>
 *   <li><code>offline &lt;velibnetworkName&gt; &lt;stationID&gt;</code>: Sets the specified station in the MyVelib network as offline.</li>
 *   <li><code>online &lt;velibnetworkName&gt; &lt;stationID&gt;</code>: Sets the specified station in the MyVelib network as online.</li>
 *   <li><code>rentBike &lt;userID&gt; &lt;stationID&gt;</code>: Rents a bike for the specified user from the specified station.</li>
 *   <li><code>rentBike &lt;userID&gt; &lt;GPS_Position&gt;</code>: Rents a bike for the specified user based on the GPS position.</li>
 *   <li><code>returnBike &lt;userID&gt; &lt;stationID&gt; &lt;duration&gt;</code>: Returns a bike for the specified user to the specified station after the specified duration.</li>
 *   <li><code>returnBike &lt;userID&gt; &lt;GPS_Position&gt; &lt;duration&gt;</code>: Returns a bike for the specified user based on the GPS position after the specified duration.</li>
 *   <li><code>displayStation&lt;velibnetworkName&gt; &lt;stationID&gt;</code>: Displays information about the specified station in the MyVelib network.</li>
 *   <li><code>displayUser&lt;velibnetworkName&gt; &lt;userID&gt;</code>: Displays information about the specified user in the MyVelib network.</li>
 *   <li><code>sortStation&lt;velibnetworkName&gt; &lt;sortpolicy&gt;</code>: Sorts the specified MyVelib network's stations based on the given sort policy (least_occupied or most_used).</li>
 *   <li><code>display &lt;velibnetworkName&gt;</code>: Displays information about the specified MyVelib network.</li>
 * </ul>
 * 
 * <h2>Additional Commands</h2>
 * <ul>
 *   <li><code>runtest testScenarioN.txt</code>: Executes a test scenario specified in the given file.</li>
 *   <li><code>enter_manually</code>: Allows manual entry of commands.</li>
 *   <li><code>setup &lt;name&gt; &lt;nstations&gt; &lt;nslots&gt; &lt;s&gt; &lt;nbikes&gt; &lt;nusers&gt;</code>: Sets up a MyVelib network with users.</li>
 *   <li><code>plus &lt;velibnetworkName&gt; &lt;stationID&gt;</code>: Sets the specified station in the MyVelib network as a plus station.</li>
 *   <li><code>standard &lt;velibnetworkName&gt; &lt;stationID&gt;</code>: Sets the specified station in the MyVelib network as a standard station.</li>
 *   <li><code>tripplanningclui &lt;velibnetworkName&gt; &lt;startcoordinates&gt; &lt;endcoordinates&gt; &lt;typeofbike&gt; &lt;starttype&gt; &lt;strategyforpluststations&gt;</code>: 
 *     Plans a trip in the MyVelib network with the specified start and end coordinates, type of bike, start type, and strategy for plus stations.</li>
 * </ul>
 * 
 */
public class Help {
	/**
	   * The main method prints the help information to the console.
	   * 
	   * @param args The command-line arguments.
	   */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("To enter a command, write the name of the command and the arguments without the '<' and '>'");
		System.out.println("");
		System.out.println("All the mandatory commands possible :");
		System.out.println("setup <velibnetworkName>");
		System.out.println("setup <name> <nstations> <nslots> <s> <nbikes>");
		System.out.println("addUser <userName> <cardType> <velibnetworkName>");
		System.out.println("offline <velibnetworkName> <stationID>");
		System.out.println("online <velibnetworkName> <stationID>");
		System.out.println("rentBike <userID> <stationID>");
		System.out.println("rentBike <userID> <GPS_Position>");
		System.out.println("returnBike <userID> <stationID> <duration>");
		System.out.println("returnBike <userID> <GPS_Position> <duration>");
		System.out.println("displayStation<velibnetworkName> <stationID>");
		System.out.println("displayUser<velibnetworkName> <userID>");
		System.out.println("sortStation<velibnetworkName> <sortpolicy>, sort_policy = least_occupied or most_used");
		System.out.println("display <velibnetworkName>");
		System.out.println("To test a scenario : runtest testScenarioN.txt");
		System.out.println("");
		System.out.println("Additional commands :");
		System.out.println("To enter the commands manually : enter_manually");
		System.out.println("To set up a myvelib with users : setup <name> <nstations> <nslots> <s> <nbikes> <nusers>");
		System.out.println("To set a station as plus : plus <velibnetworkName> <stationID>");
		System.out.println("To set a station as standard : standard <velibnetworkName> <stationID>");
		System.out.println("To plan a trip : tripplanningclui <velibnetworkName> <startcoordinates> <endcoordinates> <typeofbike> <starttype> <strategyforpluststations>. Enter coordinates with a coma (example : 3.65,8.76), the type of bike is 'electrical' or 'mechanical', starttype is station_only, non_parked_bike_only or either and the strategyforplusstations is avoid_plus_stations, prefer_plus_stations or either");
		
		
			
	}

}
