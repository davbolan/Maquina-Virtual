package tp.pr5.mv.instruction.compare;

import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo Le, y sirve para apilar un 1
 * si la subcima es menor o igual que la cima.
 * @author Lidia Flores, David Bolanios
 */
public class Le extends NumericCond {

	/**
	 * Constructora por defecto.
	 */
	public Le() {
		super();
	}

	/**
	 * Metodo que comprueba si la subcima es menor o igual que la cima.
	 * @return true si la subcima es menor o igual que la cima, false en caso contrario.
	 */
	@Override
	protected boolean compare(int cima, int subcima) {
		return (subcima <= cima);
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("le")) 
			return new Le();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion Le en forma de cadena.
	 */
	@Override
	public String toString() {
		return "LE";
	}

	
}
