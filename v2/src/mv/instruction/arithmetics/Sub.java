package tp.pr2.mv.instruction.arithmetics;

import tp.pr2.mv.instruction.Instruction;

/**
 * Clase que crea una instruccion de tipo Sub, y sirve para restar la cima y la subcima
 * de la pila de operandos.
 * @author Lidia Flores, David Bolanios
 *
 */
public class Sub extends Arithmetics{

	/**
	 * Constructora por defecto
	 */
	public Sub() {
		super();
	}
	
	/**
	  * Resta la subcima menos la cima y lo apila en la pila.
	  * @return true si hay operandos suficientes para realizar la resta, false
	  * en caso contrario.
	  */
	@Override
	public boolean execute(int n1, int n2) {
		this.result = n2-n1;
		return true;
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("sub")) 
			return new Sub();
		else return null;
	}

	/**
	 * Devuelve la instruccion Sub en forma de cadena.
	 */
	@Override
	public String toString() {
		return "SUB";
	}
}
