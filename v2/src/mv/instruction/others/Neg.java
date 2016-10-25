package tp.pr2.mv.instruction.others;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;



public class Neg extends RestSeq {

	public Neg() {
		super();
	}

	@Override
	protected boolean executeAux(CPU cpu) {
		boolean exito = false;
			  if(cpu.getNumElem() > 0){
				  cpu.push(-cpu.pop());
				  exito = true;
			  }
	    return exito;		
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("neg")) 
			return new Neg();
		else return null;
	}

	@Override
	public String toString() {
		return "NEG";
	}



}
