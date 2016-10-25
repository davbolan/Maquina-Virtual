package tp.pr3.mv.instruction.arithmetics;

import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;

/**
 * Clase abstracta que tiene metodos comunes a las instrucciones aritmeticas,
 * que son aquellas que trabajan con la pila de operando mediante operaciones 
 * matematicas.
 * @author Lidia Flores, David Bolanios
 */
public abstract class Arithmetics implements Instruction{
	
	protected int result;
	
	/**
	 * Metodo implementado por cada una de las subclases, que realiza la
	 * operacion aritmetica correspondiente.
	 * @param n1 La subcima.
	 * @param n2 La cima.
	 * @return true si la operacion ha tenido exito, false en caso contrario.
	 * @throws InstructionExecutionException si en la division, el denominador es 0.
	 */
	abstract protected void execute (int n1, int n2) throws InstructionExecutionException;
	
	/**
	 * Metodo encargado de modificar la pila de operando. Para ello, extrae
	 * la cima y la subcima y ejecuta la operacion matematica correspondiente.
	 * Si no hay error, apila el resultado, sino, deja la pila como estaba.
	 * @param cpu La cpu la cual se modifica su pila de operandos.
	 * @throws InstructionExecutionException si no hay suficientes elementos en la pila
	 * o el denominador en la division es 0.
	 */
	public void execute(CPU cpu) throws InstructionExecutionException {
		if(cpu.getNumElem() >=2){
			int n1 = cpu.pop();
		    int n2 = cpu.pop();
		   
		    try{
		    	this.execute(n1,n2);
		    	cpu.push(this.result);
		    	cpu.increaseProgramCounter();
		    }
		    
		    catch(InstructionExecutionException exe){
		    	cpu.push(n2);
		    	cpu.push(n1);
		    	throw new InstructionExecutionException(exe.getMessage());
		    }
		}
		else 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");
	}
	
}
