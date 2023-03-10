/*
 * COSC 121 Instructor: Arthur Bohem
 * 
 * A class used to create objects of type ProgramCounter (Inherits BaseMemElem)
 *
 * Author: Brian Whitnack
 * 
 * Date: 06/15/2020
 */
public class ProgramCounter extends BaseMemElem {

	/******************************************************************************************
	 * Default ProgramCounter Constructor
	 *****************************************************************************************/
	public ProgramCounter() {
		super(); // Calling super-class (Parent) constructor
	}

	// ---------------------------------------------------------------------------------------/
	
	/******************************************************************************************
	 * Utility Methods - inc(), set(int i)
	 *****************************************************************************************/
	public void inc() { //
		write((short) (read() + 1));
	}

	public void set(int i) {
		write((short) (i));
	}
	
	// ---------------------------------------------------------------------------------------/
	
	/******************************************************************************************
	 * toString Override (ProgramCounter)
	 *****************************************************************************************/
	public String toString() {
		String s = new String();
		s += "" + read();
		return s;
	}
	// ---------------------------------------------------------------------------------------/
}
//==============================================================================================//