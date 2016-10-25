package tp.pr4.mv.instruction.arithmetics;

import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr4.mv.instruction.exceptionIns.WrongInstructionFormatException;

/** 
 * Clase que crea una instruccion de tipo Div, y sirve para dividir la cima y la subcima
 * de la pila de operandos.
 * @author Lidia Flores, David Bolanios
 *
 */
public class Div extends Arithmetics{

	/**
	 * Constructora por defecto.
	 */
	public Div() {
		super();
	}

	
	 /**
	  * Divide la subcima entre la cima y lo apila en la pila.
	  * @throws InstructionExecutionException si el denominador es 0.
	  */
	@Override
	protected void execute(int n1, int n2) throws InstructionExecutionException {
		if(n1 == 0)
			throw new InstructionExecutionException("Division por cero");
		
		this.result = n2/n1;	
	}
	
	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("div")) 
			return new Div();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion Div en forma de cadena.
	 */
	@Override
	public String toString() {
		return "DIV";
	}

}
