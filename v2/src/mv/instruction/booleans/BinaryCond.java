package tp.pr2.mv.instruction.booleans;

import tp.pr2.mv.CPU;

/**
 * Clase abstracta que tiene metodos comunes a las instrucciones booleanas que 
 * necesita dos operandos.
 * @author Lidia Flores, David Bolanios
 */
public abstract class BinaryCond  extends BooleanCond{
	
	/**
	 * Metodo implementado por cada una de las subclases, que realiza la
	 * operacion boolean correspondiente.
	 * @param n1 La subcima.
	 * @param n2 La cima.
	 * @return true si la operacion ha tenido exito, false en caso contrario.
	 */
	abstract protected boolean execute (int n1, int n2);
	
	/**
	 * Metodo encargado de modificar la pila de operando. Para ello, extrae
	 * la cima y la subcima y ejecuta la operacion booleana binaria correspondiente.
	 * Si no hay error, apila el resultado, sino, deja la pila como estaba.
	 * @param cpu La cpu la cual se modifica su pila de operandos.
	 * @return exito true si la ejecucion ha tenido exito, false en caso contrario.
	 */
	@Override
	protected boolean executeAux(CPU cpu) {
		boolean exito = false; 
		
		if (cpu.getNumElem() > 1){
			int n1 = cpu.pop();
			int n2 = cpu.pop();
			   
			 if(this.execute(n1,n2))
				 exito = true;
			 
			 else{
				   cpu.push(n2);
				   cpu.push(n1);
			  }
		 }
		
		return exito;
	}
}
