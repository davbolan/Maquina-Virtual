package tp.pr3.mv.instruction.arithmetics;


import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo Add, y sirve para sumar la cima y la subcima
 * de la pila de operandos.
 * @author Lidia Flores, David Bolanios
 */
public class Add extends Arithmetics{

	/**
	 * Constructora por defecto.
	 */
	public Add() {
		super();
	}

	
	/**
	 * Suma la subcima mas la cima y lo apila en la pila.
	 */
	@Override
	public void execute(int n1, int n2) {
		this.result = n1+n2;
	}

	
	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("add")) 
			return new Add();
		else throw new WrongInstructionFormatException();
	}


	/**
	 * Devuelve la instruccion Add en forma de cadena.
	 */
	@Override
	public String toString() {
		return "ADD";
	}

}
