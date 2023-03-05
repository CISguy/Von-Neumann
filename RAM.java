/*
 * COSC 121 Instructor: Arthur Bohem
 * 
 * A class used to create objects of type RAM
 *
 * Author: Brian Whitnack
 * 
 * Date: 06/15/2020
 */

public class RAM {
	private BaseMemElem[] mem; // Instantiating an instance array of base memory elements

	private final int SIZE = 100; // Initializing a FINAL variable "SIZE" that holds the final size of the
									// BaseMemElem array

	/******************************************************************************************
	 * Default RAM Constructor
	 *****************************************************************************************/
	public RAM() {
		mem = new BaseMemElem[SIZE];
		for (int i = 0; i < SIZE; i++)
			mem[i] = new BaseMemElem();
	}
	// ---------------------------------------------------------------------------------------/
	
	/******************************************************************************************
	 * Size Accessor (SIZE)
	 *****************************************************************************************/
	public int getSize() {
		return SIZE;
	}
	// ---------------------------------------------------------------------------------------/

	/******************************************************************************************
	 * Utility Methods (read, write)
	 *****************************************************************************************/
	public void write(short value, int location) {
		mem[location].write(value);
	}

	public short read(int location) {
		return mem[location].read();
	}
	// ---------------------------------------------------------------------------------------/

	/******************************************************************************************
	 * toString override (RAM)
	 *****************************************************************************************/
	public String toString() {
		String s = new String();
		for (int i = 0; i < mem.length; i++) {
			if (i % 10 == 0)
				s += "\n";
			s += mem[i].read() + "\t";
		}
		return s;
	}
	// ---------------------------------------------------------------------------------------/
}
//==============================================================================================//