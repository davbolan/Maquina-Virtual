package tp.pr4.mv.instruction.branch;

import tp.pr4.mv.CPU;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.*;

/**
 * Clase que crea una instruccion de tipo JumpInd, y sirve para cambiar el
 * contador de programa a la posicion indicada por la cima de la pila.
 * @author Lidia Flores, David Bolanios
 */
public class JumpInd extends Jumps {

	/**
	 * Constructora por defecto.
	 */
	public JumpInd() {
		
	}

	/**
	 * Metodo que ejecuta la instruccion JumpInd, es decir, cambia el contador de programa
	 * al indicado por la cima de la pila.
	 * @param CPU El CPU a cambiar el contador de programa.
	 * @throws InstructionExecutionException si hay error en el cambio del PC.
	 */
	@Override
	protected void executeBranch(CPU cpu) throws InstructionExecutionException {
		int number;
		try{
			number = cpu.pop();	
		} 
		catch(InstructionExecutionException iee){
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay 0)");
		}
		
		cpu.changePC(number);	
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length == 1 && s[0].equalsIgnoreCase("jumpind"))
			return new JumpInd();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion JumpInd en forma de cadena.
	 */
	@Override
	public String toString() {
		return "JUMPIND";
	}

}
