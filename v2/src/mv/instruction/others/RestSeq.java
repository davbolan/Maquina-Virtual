package tp.pr2.mv.instruction.others;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;


public abstract class RestSeq extends Instruction {	
	protected int result;
	
	abstract protected boolean executeAux (CPU cpu);
	
	@Override
	public boolean execute(CPU cpu) {
		boolean exito = false;
		if (this.executeAux(cpu)){
			cpu.increaseProgramCounter();
			exito = true;
		}
		return exito;
	}


}
