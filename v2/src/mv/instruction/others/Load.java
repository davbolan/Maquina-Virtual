package tp.pr2.mv.instruction.others;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Load extends RestSeq {
	private int number;
	public Load(int number) {
		super();
		this.number = number;
	}
	public Load() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected boolean executeAux(CPU cpu) {
		boolean exito = false;
		if(this.number >= 0){
			cpu.load(this.number);
			exito = true;
		}
		return exito;
	}
	
	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if(s.length == 2 && s[0].equalsIgnoreCase("load") && Instruction.isNumber(s[1]))
			return new Load(Integer.parseInt(s[1]));
		
		else
			return null;			
	}
	@Override
	public String toString() {
		return "LOAD " + this.number;
	}

	

}
