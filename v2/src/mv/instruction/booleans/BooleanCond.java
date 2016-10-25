package tp.pr2.mv.instruction.booleans;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

/**
 * Clase abstracta que tiene metodos comunes a las instrucciones booleanas,
 * que son aquellas que trabajan con la pila de operando mediante comparaciones.
 * @author Lidia Flores, David Bolanios
 */
public abstract class BooleanCond extends Instruction {
	protected int result;
	
	/**
	 * Metodo implementado por las subclases las cuales implementara la operacion 
	 * booleana correspondiente.
	 * @param cpu La CPU a modificar.
	 * @return true si la operacion ha tenido exito.
	 */
	abstract protected boolean executeAux (CPU cpu);
	
	/**
	 * Metodo encargado de modificar la pila de operando. Para ello, ejecuta la instruccion
	 * booleana correspondiente.
	 * Si no hay error, apila el resultado, sino, deja la pila como estaba.
	 * @param cpu La cpu la cual se modifica su pila de operandos.
	 * @return exito true si la ejecucion ha tenido exito, false en caso contrario.
	 */
	public boolean execute(CPU cpu) {
		boolean exito = false;
		if(this.executeAux(cpu)){
			cpu.push(this.result);
			cpu.increaseProgramCounter();
			exito = true;
		}
		
		return exito;
	}

}
