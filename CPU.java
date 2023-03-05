/*																										
 * COSC 121 Instructor: Arthur Bohem
 * 
 * CPU Class: The components, and operations of the SimCom CPU
 *
 * Author: Brian Whitnack
 * 
 * Date: 06/15/2020
 */
public class CPU {
	private BaseMemElem acc; // Instance BaseMemElem variable "acc" - Accumulator
	private ProgramCounter pc; // Instance ProgramCounter variable "pc"
	private InstructionRegister ir; // Instance InstructionRegister variable "ir"
	private BaseMemElem mdr; // Instance BaseMemElem variable "mdr" - Memory Data Register
	private BaseMemElem mar; // Instance BaseMemElem variable "mar" - Memory Address Register
	private boolean inFlag; // Instance boolean variable "inFlag" - Input Flag
	private boolean outFlag; // Instance boolean variable "outFlag" - Output flag
	private boolean storeFlag; // Instance boolean variable "storeFlag"
	private boolean stopFlag; // Instance boolean variable "stopFlag"

	/******************************************************************************************
	 * Default CPU Constructor
	 *****************************************************************************************/
	public CPU() {
		acc = new BaseMemElem();
		pc = new ProgramCounter();
		ir = new InstructionRegister();
		mdr = new BaseMemElem();
		mar = new BaseMemElem();
		inFlag = false;
		outFlag = false;
		storeFlag = false;
		stopFlag = false;
	}
	// ---------------------------------------------------------------------------------------/

	/******************************************************************************************
	 * Accessors/Mutators - Stop flag, Store flag, Input flag, Output flag, MAR, MDR
	 *****************************************************************************************/
	public void setStopFlag() {
		stopFlag = true;
	}

	public boolean getStopFlag() {
		return stopFlag;
	}

	public void setStoreFlag() {
		storeFlag = true;
	}

	public boolean getStoreFlag() {
		return storeFlag;
	}

	public void setInputFlag() {
		inFlag = true;
	}

	public boolean getInputFlag() {
		return inFlag;
	}

	public void setOutputFlag() {
		outFlag = true;
	}

	public boolean getOutputFlag() {
		return outFlag;
	}

	public void setMAR(short n) {
		mar.write(n);
	}

	public int getMAR() {
		return mar.read();
	}

	public void setMDR(short n) {
		mdr.write(n);
	}

	public short getMDR() {
		return (short) mdr.read();
	}
	// ---------------------------------------------------------------------------------------/

	/******************************************************************************************
	 * Utility Methods - preFetch(), postFetch(), decode(), flagReset(), execute()
	 *****************************************************************************************/
	public void preFetch() {
		mar.write(pc.read());
		pc.inc();
	}

	public void postFetch() {
		ir.write(mdr.read());
	}

	public void decode() {
		short opcode = ir.getOpCode();
		short operand = ir.getOperand();
		if (opcode == 0) { // STOP
			getStopFlag();
		} else {
			mar.write(operand);
		}
	}
	
	public void flagReset() {
		inFlag = false;
		outFlag = false;
		storeFlag = false;
		stopFlag = false;
	}
	
	public void execute() { // Separating operation codes from operands, reading the value of acc and
							// executing the appropriate command(s)
		short opcode = ir.getOpCode();
		short operand = ir.getOperand();
		short acu = acc.read();

		if (opcode == 0) {
			setStopFlag();
		} else if (opcode == 1) {
			acc.write(getMDR());
		} else if (opcode == 2) {
			mdr.write(acc.read());
			mar.write(operand);
			setStoreFlag();
		} else if (opcode == 3) {
			acc.write((short) (acu + mdr.read()));
		} else if (opcode == 4) {
			acc.write((short) (acu - mdr.read()));
		} else if (opcode == 5) {
			acc.write((short) (acu * mdr.read()));
		} else if (opcode == 6) {
			acc.write((short) (acu / mdr.read()));
		} else if (opcode == 7) {
			setInputFlag();
			setMAR(operand);
		} else if (opcode == 8) {
			mdr.write((short) (acc.read()));
			setOutputFlag();
		} else if (opcode == 9) {
			pc.set(operand);
		} else if (opcode == 10) {
			if (acu == 0) {
				pc.set(operand);
			}
		} else if (opcode == 11) {
			if (acu > 0) {
				pc.set(operand);
			}
		}
	}
	// ---------------------------------------------------------------------------------------/

	/*****************************************************************************************
	 * toString override (CPU)
	 ****************************************************************************************/
	public String toString() {
		String s = new String();
		s += "ACC: " + acc + " PC: " + pc + " IR: " + ir + "\n" + "MDR: " + mdr + " MAR: " + mar
				+ "\nInput flag status: " + inFlag + "\nOutput flag status: " + outFlag + "\nStore flag status: "
				+ storeFlag + "\nStop flag status: " + stopFlag;
		return s;
	}
	// ---------------------------------------------------------------------------------------/
}
//==============================================================================================//