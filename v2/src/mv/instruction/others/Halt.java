package tp.pr2.mv.instruction.others;


import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Halt extends RestSeq {

	public Halt() {
		super();
	}

	@Override
	protected boolean executeAux(CPU cpu) {
		cpu.exit();
		return true;
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("halt")) 
			return new Halt();
		else return null;
	}

	@Override
	public String toString() {
		return "HALT";
	}

}
