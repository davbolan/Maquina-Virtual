package tp.pr2.mv.instruction.arithmetics;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

/**
 * Clase abstracta que tiene metodos comunes a las instrucciones aritmeticas,
 * que son aquellas que trabajan con la pila de operando mediante operaciones 
 * matematicas.
 * @author Lidia Flores, David Bolanios
 */
public abstract class Arithmetics extends Instruction{
	protected int result;
	
	/**
	 * Metodo implementado por cada una de las subclases, que realiza la
	 * operacion aritmetica correspondiente.
	 * @param n1 La subcima.
	 * @param n2 La cima.
	 * @return true si la operacion ha tenido exito, false en caso contrario.
	 */
	abstract protected boolean execute (int n1, int n2);
	
	/**
	 * Metodo encargado de modificar la pila de operando. Para ello, extrae
	 * la cima y la subcima y ejecuta la operacion matematica correspondiente.
	 * Si no hay error, apila el resultado, sino, deja la pila como estaba.
	 * @param cpu La cpu la cual se modifica su pila de operandos.
	 * @return exito true si la ejecucion ha tenido exito, false en caso contrario.
	 */
	public boolean execute(CPU cpu) {
		boolean exito = false;
		if(cpu.getNumElem() >=2){
			int n1 = cpu.pop();
		    int n2 = cpu.pop();
		   
		    if(this.execute(n1,n2)){
		    	cpu.push(this.result);
		    	cpu.increaseProgramCounter();
		    	exito = true;
		    }
		    
		    else{
		    	cpu.push(n2);
		    	cpu.push(n1);
		    }
		}
		
	 return exito;
	}
}
