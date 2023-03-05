/*																										
 * COSC 121 Instructor: Arthur Bohem
 * 
 * Input Class: The components, and operations of an Input device
 *
 * Author: Brian Whitnack
 * 
 * Date: 06/15/2020
 */
import java.util.Scanner; // Importing a Scanner object

public class Input {

	private short value; // Instance short variable "value" 

	/*****************************************************************************************
	 * Input Accessor: value
	 ****************************************************************************************/
	public short getInput() {
		Scanner in = new Scanner(System.in); // Initializing a new Scanner "in"
		System.out.print("?");
		return in.nextShort(); // Returning the next short input
	}
	// ---------------------------------------------------------------------------------------/

	/*****************************************************************************************
	 * toString override (Input)
	 ****************************************************************************************/
	public String toString() {
		String s = new String();
		return s += "Input: " + value;
	}
	// ---------------------------------------------------------------------------------------/
}
//==============================================================================================//