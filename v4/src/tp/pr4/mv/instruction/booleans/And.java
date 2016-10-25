package tp.pr4.mv.instruction.booleans;

import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.*;

/**
 * Clase que crea una instruccion de tipo And, y sirve para realizar la operacion And
 * usando la cima y la subcima de la pila de operandos.
 * @author Lidia Flores, David Bolanios
 */
public class And extends BinaryCond {

	/**
	 * Constructora por defecto
	 */
	public And() {
		super();
	}

	/**
	 * Realiza la operacion AND usando la subcima y la cima y lo apila en la pila. 
	 * @param n1 La subcima.
	 * @param n2 La cima.
	 * @throws InstructionExecutionException Si la cima y/o la subcima son valores distintos
	 * de 0 o 1.
	 */
	@Override
	protected void execute(int n1, int n2) throws InstructionExecutionException {
		if(!(n1 == 0 || n1 == 1)) 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": la cima debe valer 0 o 1 (Valor: " + n1 + ")");
		else if(!(n2 == 0 || n2 == 1))
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": la subcima debe valer 0 o 1 (Valor: " + n2 + ")");
		else 
			this.result = n1 * n2;
	}


	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length == 1 && s[0].equalsIgnoreCase("and")) 
			return new And();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion And en forma de cadena.
	 */
	@Override
	public String toString() {
		return "AND";
	}

}
