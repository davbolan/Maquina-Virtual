package tp.pr3.mv.instruction.booleans;

import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;

/**
 * Clase abstracta que tiene metodos comunes a las instrucciones booleanas,
 * que son aquellas que trabajan con la pila de operando mediante comparaciones.
 * @author Lidia Flores, David Bolanios
 */
public abstract class BooleanCond implements Instruction {
	
	protected int result;
	
	/**
	 * Metodo implementado por las subclases las cuales implementara la operacion 
	 * booleana correspondiente.
	 * @param cpu La CPU a modificar.
	 * @throws InstructionExecutionException si hay algun error durante la ejecucion. 
	 */
	abstract protected void executeAux (CPU cpu) throws InstructionExecutionException;
	
	/**
	 * Metodo encargado de modificar la pila de operandos. Para ello, ejecuta la instruccion
	 * booleana correspondiente.
	 * Si no hay error, apila el resultado, sino, deja la pila como estaba.
	 * @param cpu La cpu la cual se modifica su pila de operandos.
	 * @throws InstructionExecutionException si hay algun error durante la ejecucion. 
	 */
	public void execute(CPU cpu) throws InstructionExecutionException{
		this.executeAux(cpu);
		cpu.push(this.result);
		cpu.increaseProgramCounter();
	}

}
