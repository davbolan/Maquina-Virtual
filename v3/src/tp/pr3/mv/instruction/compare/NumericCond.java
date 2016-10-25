package tp.pr3.mv.instruction.compare;

import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;

/**
 * Clase abstracta que tiene metodos comunes a las instrucciones de comparacion,
 * apila un 0 o 1 segun lo que hubiera en la cima y subcima.
 * @author Lidia Flores, David Bolanios
 */
public abstract class NumericCond implements Instruction {
	protected int result;
	
	/**
	 * Metodo implementado por las subclases las cuales implementara la 
	 * comparacion correspondiente.
	 * @param cima La cima.
	 * @param subcima La subcima.
	 * @return el valor booleano de la comparacion.
	 */
	abstract protected boolean compare (int cima, int subcima);
	
	/**
	 * Metodo encargado de modificar la pila de operando. Para ello, extrae
	 * la cima y la subcima y ejecuta la operacion de comparacion correspondiente.
	 * @param cpu La cpu la cual se modifica su pila de operandos
	 * @return exito true si la ejecucion ha tenido exito, false en caso contrario.
	 * @throws InstructionExecutionException si no hay suficientes elementos en la pila.
	 */
	public void execute(CPU cpu) throws InstructionExecutionException {
		 if (cpu.getNumElem() > 1){
		   int cima = cpu.pop();
		   int subcima = cpu.pop();
 
		   if(this.compare(cima,subcima))
		    	cpu.push(1);
		   else
		    	cpu.push(0);
		   
		   cpu.increaseProgramCounter();

		 }
		 else 
			 throw new InstructionExecutionException
		 		("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");
	}

}
