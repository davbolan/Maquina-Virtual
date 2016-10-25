package tp.pr5.mv.instruction.compare;

import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo Eq, y sirve para apilar un 1
 * si la cima y subcima son iguales.
 * @author Lidia Flores, David Bolanios
 */
public class Eq extends NumericCond {

	/**
	 * Constructora por defecto.
	 */
	public Eq() {
		super();
	}

	/**
	 * Metodo que comprueba si la cima y la subcima son iguales.
	 * @return true si la cima y subcima son iguales, false en caso contrario.
	 */
	@Override
	protected boolean compare(int cima, int subcima) {
		return (subcima == cima);
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("eq")) 
			return new Eq();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion Eq en forma de cadena.
	 */
	@Override
	public String toString() {
		return "EQ";
	}
}
