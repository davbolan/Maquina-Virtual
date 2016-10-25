package tp.pr3.mv.instruction.others;

import tp.pr3.mv.CPU;
import tp.pr3.mv.executionMode.MVSystem;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo In, y sirve para leer apilar el valor numerico 
 * de un caracter ASCII en la pila de operandos.
 * @author Lidia Flores, David Bolanios
 */
public class In extends RestSeq {

	/**
	 * Constructora por defecto.
	 */
	public In(){
		
	}
	
	/**
	 * Apila un caracter de la entrada y apila su valor numerico ASCII.
	 * En caso de que sea negativo lo hace de su valor absoluto.
	 * @param cpu la cual se modifica.
	 */
	@Override
	protected void executeAux(CPU cpu) {
		int car = MVSystem.in.readChar();
		cpu.push(car);
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if(s.length == 1 && s[0].equalsIgnoreCase("in"))
			return new In();
		else throw new WrongInstructionFormatException();
	}

	@Override
	public String toString() {
		return "IN";
	}

}
