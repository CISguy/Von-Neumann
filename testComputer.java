/*																										
 * COSC 121 Instructor: Arthur Bohem
 * 
 * Test Class: Used to create, load, run, and display Computer objects 
 *
 * Author: Brian Whitnack
 * 
 * Date: 06/15/2020
 */

public class testComputer {
	/******************************************************************************************
	 * Main Method
	 *****************************************************************************************/
	public static void main(String[] args) {

		Computer myPi = new Computer(); // Initializing a new Computer "myPi"
		System.out.println(myPi); // Displaying myPi after initialization
		myPi.load("gta.exe"); // Loading from the file "gta.exe" from the project directory
		System.out.println(myPi); // Displaying myPi after loading to memory
		myPi.run(); // Running "gta.exe"
		System.out.println(myPi); // Displaying myPi after execution		
	}   
	// ---------------------------------------------------------------------------------------/
}
//=================================================================================================/