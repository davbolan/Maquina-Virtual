package tp.pr3.mv.instruction.arithmetics;

import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

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
	public void execute(int n1, int n2) {
		this.result = n2 - n1;
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("sub")) 
			return new Sub();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion Sub en forma de cadena.
	 */
	@Override
	public String toString() {
		return "SUB";
	}
}
