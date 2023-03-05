/*
 * COSC 121 Instructor: Arthur Bohem
 * 
 * Instruction Register: A Class that inherits the properties of it's parent Class "BaseMemElem" 
 * 
 * Author: Brian Whitnack
 * 
 * Date: 06/15/2020
 */
public class InstructionRegister extends BaseMemElem {
	/******************************************************************************************
	 * Default Instruction Register Constructor
	 *****************************************************************************************/
	public InstructionRegister() { // Calling super-class (Parent) constructor
		super(); 
	}
	// ---------------------------------------------------------------------------------------/
	
	/******************************************************************************************
	 * Getters - opCode, Operand
	 *****************************************************************************************/
	public short getOpCode() {
		return (short) (read() / 100);
	}
	
	public short getOperand() {
		return (short) (read() % 100);
	}
	// ---------------------------------------------------------------------------------------/

	/******************************************************************************************
	 * toString override (InstructionRegister)
	 *****************************************************************************************/
	public String toString() {
		return new String("" + read());
	}
	// ---------------------------------------------------------------------------------------/
}
//==============================================================================================//