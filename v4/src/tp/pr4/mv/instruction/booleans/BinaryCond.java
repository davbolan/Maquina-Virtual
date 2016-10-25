package tp.pr4.mv.instruction.booleans;

import tp.pr4.mv.CPU;
import tp.pr4.mv.instruction.exceptionIns.InstructionExecutionException;

/**
 * Clase abstracta que tiene metodos comunes a las instrucciones booleanas que 
 * necesita dos operandos.
 * @author Lidia Flores, David Bolanios
 */
public abstract class BinaryCond extends BooleanCond{
	
	/**
	 * Metodo implementado por cada una de las subclases, que realiza la
	 * operacion boolean correspondiente.
	 * @param n1 La subcima.
	 * @param n2 La cima.
	 * @return true si la operacion ha tenido exito, false en caso contrario.
	 * @throws InstructionExecutionException 
	 */
	abstract protected void execute (int n1, int n2) throws InstructionExecutionException;
	
	/**
	 * Metodo encargado de modificar la pila de operando. Para ello, extrae
	 * la cima y la subcima y ejecuta la operacion booleana binaria correspondiente.
	 * Si no hay error, apila el resultado, sino, deja la pila como estaba.
	 * @param cpu La cpu la cual se modifica su pila de operandos.
	 * @return exito true si la ejecucion ha tenido exito, false en caso contrario.
	 * @throws InstructionExecutionException 
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException {
		int n1;
		int n2;
		
		try{
			n1 = cpu.pop();	
			try{
				n2 = cpu.pop();	
			}		
			catch(InstructionExecutionException iee){ 
				cpu.push(n1);
				throw new InstructionExecutionException
					("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay 1)");
			}
			
		}
		catch(InstructionExecutionException iee){ 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay 0)");
		}
		
		try{
			this.execute(n1,n2);
		}
		catch(InstructionExecutionException iee){
			   cpu.push(n2);
			   cpu.push(n1);
			   throw new InstructionExecutionException(iee.getMessage());
		 }
	}
}
