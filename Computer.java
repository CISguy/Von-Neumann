/*																										
 * COSC 121 Instructor: Arthur Bohem
 * 
 * Computer Class: The Computer (SimCom)
 *
 * Author: Brian Whitnack
 * 
 * Date: 06/15/2020
 */

import java.io.*; // Importing all of java.io

public class Computer {
	
	private RAM myRam; // Instance RAM variable "myRam"
	private CPU myCpu; // Instance CPU variable "myCpu"
	private Input myInput; // Instance Input variable "myInput"
	private Output myOutput; // Instance Output variable "myOutput"
	
	/******************************************************************************************
	 * Default Computer Constructor
	 *****************************************************************************************/
	public Computer() {
		myRam = new RAM(); // Instantiating a RAM object "myRam" from it's default constructor
		myCpu = new CPU(); // Instantiating a CPU object "myCpu" from it's default constructor
		myInput = new Input(); // Instantiating an Input object "myInput" from it's default constructor
		myOutput = new Output(); // Instantiating an Output object "myOutput" from it's default constructor
	}
	// ---------------------------------------------------------------------------------------/

	/*****************************************************************************************
	 * Utility Methods - load(String fName), busRefresh(), run()
	 ****************************************************************************************/
	public void load(String fName) {
		try { // Creating a try block. *IO exceptions can occur within this block, and must be
				// handled appropriately*

			File myFile = new File(fName); // Creating an object reference to "fName" (The file to be read)
			
			DataInputStream in = new DataInputStream(new FileInputStream(fName)); // Creating a DataInputStream "in" to
																					// read from the file "fName"

			for (int i = 0; i < myRam.getSize(); i++) { // Iterating through "myRam" to parse shorts into "words" , and
														// writing them to "myRam" at index "i"
				if (in.available() > 0) {
					short word = in.readShort();
					myRam.write(word, i);
				} else { // If there are no more shorts to read from File "fName", "0" will be written to
							// index "i" 
					myRam.write((short) 0, i);
				}
			}
			in.close(); // Closing DataInputStream "in"
		} catch (IOException e) { // Catching IO Exceptions
			System.out.println("ERROR: IO Exception");
			e.printStackTrace();
			System.exit(0); // Exiting with exit code "0" (Normal Termination)
		}
	}

	private void busRefresh() { // Flag-handling

		if (myCpu.getStoreFlag()) { // If the store flag is up (True), myRam writes the value of the MDR to the MAR
			myRam.write(myCpu.getMDR(), myCpu.getMAR());
			myCpu.flagReset(); // Taking all flags down (Setting them to false)

		} else if (myCpu.getInputFlag()) { // If the input flag is up (True), myRam writes user input from "in" to the
											// MAR
			short in = myInput.getInput();
			myRam.write(in, myCpu.getMAR());
			myCpu.flagReset(); // Taking all flags down (Setting them to false)

		} else if (myCpu.getOutputFlag()) { // If the Output flag is up (True), myOutput prints the value of the MDR at
											// the MAR
			myOutput.print((short) myRam.read(myCpu.getMAR()));

			myCpu.flagReset(); // Taking all flags down (Setting them to false)
		} else {
			myCpu.setMDR((short) myRam.read(myCpu.getMAR()));
		}
	}

	public void run() { // Fetch-Execute cycle
		do {
			myCpu.preFetch();
			busRefresh();
			myCpu.postFetch();
			myCpu.decode();
			busRefresh();
			myCpu.execute();
			busRefresh();
		} while (!myCpu.getStopFlag());
	}
	// ---------------------------------------------------------------------------------------/
	
	/*****************************************************************************************
	 * toString override (Computer)
	 ****************************************************************************************/
	public String toString() {
		String s = new String();
		s += "Brian's Computer\n";
		s += myRam + "\n";
		s += myCpu + "\n";
		s += myInput + "\n";
		s += myOutput + "\n";
		return s;
	}
	// ---------------------------------------------------------------------------------------/
}
//==============================================================================================//