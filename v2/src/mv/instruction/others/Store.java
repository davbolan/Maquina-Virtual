package tp.pr2.mv.instruction.others;


import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Store extends RestSeq {
	private int number;
	public Store(int number) {
		super();
		this.number = number;
	}

	public Store() {
		
	}

	@Override
	protected boolean executeAux(CPU cpu) {
		boolean exito = false;
		if(cpu.getNumElem() > 0 && this.number >= 0){
			cpu.store(this.number);
			exito = true;
		}
		return exito;
	}

	
	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if(s.length == 2 && s[0].equalsIgnoreCase("store") && Instruction.isNumber(s[1]))
			return new Store(Integer.parseInt(s[1]));
		
		else
			return null;			
	}

	@Override
	public String toString() {
		return "STORE " + this.number;
	}

}
