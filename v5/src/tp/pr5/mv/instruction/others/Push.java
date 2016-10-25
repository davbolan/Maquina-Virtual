package tp.pr5.mv.instruction.others;

import tp.pr5.mv.CPU;
import tp.pr5.mv.Constants;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo Pop, y sirve para apilar un elemento en la
 * pila.
 * @author Lidia Flores, David Bolanios
 */
public class Push extends RestSeq {
	private int number;
	
	/**
	 * Constructora por defecto.
	 */
	public Push() {

	}
	
	/**
	 * Crea una instruccion Push con el numero a apilar.
	 * @param number El numero a apilar.
	 */
	public Push(int number) {
		super();
		this.number = number;
	}


	/**
	 * Apila un elemento de la pila.
	 * @param cpu la cual se modifica.
	 */
	@Override
	protected void executeAux(CPU cpu) {
		cpu.push(number);
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if(s.length == 2 && s[0].equalsIgnoreCase("push") && Constants.isNumber(s[1]))
			return new Push(Integer.parseInt(s[1]));
		else throw new WrongInstructionFormatException();		
	}

	/**
	 * Devuelve la instruccion Push en forma de cadena.
	 */
	@Override
	public String toString() {
		return "PUSH " + number;
	}
	
	


}
