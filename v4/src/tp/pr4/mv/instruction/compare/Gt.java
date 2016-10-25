package tp.pr4.mv.instruction.compare;

import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo Gt, y sirve para apilar un 1
 * si la subcima es mayor que la cima.
 * @author Lidia Flores, David Bolanios
 */
public class Gt extends NumericCond {

	/**
	 * Constructora por defecto.
	 */
	public Gt() {
		super();
	}
	
	/**
	 * Metodo que comprueba si la subcima es mayor que la cima.
	 * @return true si la subcima es mayor que la cima, false en caso contrario.
	 */
	@Override
	protected boolean compare(int cima, int subcima) {
		return (subcima > cima);
	}
	
	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("gt")) 
			return new Gt();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion Gt en forma de cadena.
	 */
	@Override
	public String toString() {
		return "GT";
	}
	

}
