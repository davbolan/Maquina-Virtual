package tp.pr4.mv.instruction.others;

import java.io.IOException;

import tp.pr4.mv.CPU;
import tp.pr4.mv.executionMode.MVSystem;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.*;

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
	 * @throws IOException 
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException{
		int car;
		try{
			car = MVSystem.in.readChar();
		}
		catch(IOException ioe){
			throw new InstructionExecutionException("Error ejecutando " + this.toString() + ": " + ioe.getMessage());
		}
		
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
