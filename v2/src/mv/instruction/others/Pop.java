package tp.pr2.mv.instruction.others;


import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Pop extends RestSeq {

	public Pop() {
		super();
	}

	@Override
	protected boolean executeAux(CPU cpu) {
		boolean exito = false;
		if(cpu.getNumElem() > 0)
			cpu.pop();
			exito = true;
		return exito;
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("pop")) 
			return new Pop();
		else return null;
	}

	@Override
	public String toString() {
		return "POP";
	}



}
