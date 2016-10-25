package tp.pr2.mv.instruction.branch;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

/**
 * Clase abstracta que tiene metodos comunes a las instrucciones de salto,
 * que son aquellas que trabajan con el contador de programa.
 * @author Lidia Flores, David Bolanios
 */
public abstract class Jumps extends Instruction {
	protected int number;
	
	/**
	 * Metodo implementado por las subclases las cuales implementara la operacion 
	 * de salto correspondiente.
	 * @param cpu La CPU a modificar.
	 * @return true si la operacion ha tenido exito.
	 */
	abstract protected boolean executeBranch (CPU cpu);
	
	/**
	 * Ejecuta la instruccion de salto correspondiente. 
	 * @return true si el salto ha tenido exito, false en caso contrario.
	 */
	@Override
	public boolean execute(CPU cpu) {
		return this.executeBranch(cpu);
	}
}
