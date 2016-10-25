package tp.pr2.mv.instruction.others;


import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;


public class Push extends RestSeq {
	private int number;
	
	public Push(int number) {
		super();
		this.number = number;
		// TODO Auto-generated constructor stub
	}

	public Push() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean executeAux(CPU cpu) {
		cpu.push(number);
		return true;
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if(s.length == 2 && s[0].equalsIgnoreCase("push") && Instruction.isNumber(s[1]))
			return new Push(Integer.parseInt(s[1]));
		
		else
			return null;			
	}

	@Override
	public String toString() {
		return "PUSH " + this.number;
	}
	
	


}
