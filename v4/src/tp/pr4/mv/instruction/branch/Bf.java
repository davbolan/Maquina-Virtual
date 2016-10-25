package tp.pr4.mv.instruction.branch;

import tp.pr4.mv.CPU;
import tp.pr4.mv.Constants;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.*;

/**
 * Clase que crea una instruccion de tipo Bf, y sirve para cambiar el
 * contador de programa si la cima de la pila es 0.
 * @author Lidia Flores, David Bolanios
 */
public class Bf extends Jumps {
	
	/**
	 * Constructora por defecto.
	 */
	public Bf() {
	}
	
	
	/**
	 * Constructora que crea una instruccion Bf con el nuevo contador
	 * de programa.
	 * @param number El nuevo contador de programa.
	 */
	public Bf(int number) {
		super();
		this.number = number;
	}


	/**
	 * Metodo que ejecuta la instruccion Bf, es decir, cambia el contador de programa
	 * al indicado si la cima es 0.
	 * @param CPU El CPU a cambiar su contador de programa.
	 * @throws InstructionExecutionException si no hay suficientes elementos en la pila o
	 * hay error en el cambio del PC.
	 */
	@Override
	protected void executeBranch(CPU cpu) throws InstructionExecutionException {
		int numBf;
		
		try{
			numBf = cpu.pop();
		} 
		catch(InstructionExecutionException iee){
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay 0)");
		}
		
		if (numBf == 0)
			cpu.changePC(number);
		else 
			cpu.increaseProgramCounter();
	}

	
	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if(s.length == 2 && s[0].equalsIgnoreCase("bf") && Constants.isNumber(s[1]))
			return new Bf(Integer.parseInt(s[1]));	
		else
			throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion Bt en forma de cadena.
	 */
	@Override
	public String toString() {
		return "BF " + number;
	}
}
