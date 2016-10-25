package tp.pr3.mv.instruction.branch;

import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;

/**
 * Clase abstracta que tiene metodos comunes a las instrucciones de salto,
 * que son aquellas que trabajan con el contador de programa.
 * @author Lidia Flores, David Bolanios
 */
public abstract class Jumps implements Instruction {
	
	protected int number;
	
	/**
	 * Metodo implementado por las subclases las cuales implementaran la operacion 
	 * de salto correspondiente.
	 * @param cpu La CPU a modificar.
	 * @throws InstructionExecutionException si hay algun error durante la ejecucion de un salto.
	 */
	abstract protected void executeBranch (CPU cpu) throws InstructionExecutionException;
	
	/**
	 * Ejecuta la instruccion de salto correspondiente.
	 * @throws InstructionExecutionException si hay algun error durante la ejecucion de un salto.
	 */
	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		this.executeBranch(cpu);
	}
}
