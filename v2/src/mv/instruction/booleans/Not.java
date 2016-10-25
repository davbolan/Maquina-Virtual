package tp.pr2.mv.instruction.booleans;


import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

/**
 * Clase que crea una instruccion de tipo Not, y sirve para realizar la operacion Not
 * sobrela cimade la pila de operandos.
 * @author Lidia Flores, David Bolanios
 */
public class Not extends BooleanCond {

	/**
	 * Constructora por defecto.
	 */
	public Not() {
		super();
	}

	/**
	 * Realiza la operacion NOT sobre la cima y lo apila en la pila. 
	 * @return true si hay operandos suficientes para realizar la operacion, false
	 * en caso contrario o no hay 1 o 0 apilados.
	 */
	@Override
	protected boolean executeAux(CPU cpu) {
		boolean exito = false; 
		
		if (cpu.getNumElem() > 0){
			int n1 = cpu.pop();
			exito = true;
			if(n1 == 1){
				this.result = 0;	 
			}
			else if(n1 == 0){
				this.result = 1;
			}
			else{
				cpu.push(n1);
				exito = false;
			}
		 }
		return exito;
	}


	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("not")) 
			return new Not();
		else return null;
	}

	/**
	 * Devuelve la instruccion Not en forma de cadena.
	 */
	@Override
	public String toString() {
		return "NOT";
	}




	

}
