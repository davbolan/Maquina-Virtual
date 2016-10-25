package tp.pr5.mv.instruction.branch;

import tp.pr5.mv.CPU;
import tp.pr5.mv.Constants;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.instruction.exceptionIns.*;

/**
 * Clase que crea una instruccion de tipo RJump, y sirve para cambiar el
 * contador de programa incrementando o decrementando el pc.
 * @author Lidia Flores, David Bolanios
 */
public class RJump extends Jumps {
	
	/**
	 * Constructora por defecto.
	 */
	public RJump() {
		
	}
	
	/**
	 * Constructora que crea una instruccion RJump con el numero de lineas incrementar o
	 * decrementar el PC.
	 * @param number El numero de lineas a incrementar/decrementar.
	 */
	public RJump(int number) {
		super();
		this.number = number;
	}

	/**
	 * Metodo que ejecuta la instruccion RJump, es decir, cambia el contador de programa
	 * al indicado.
	 * @param CPU El CPU a cambiar su contador de programa.
	 * @throws InstructionExecutionException si hay error en el cambio del PC
	 */
	@Override
	protected void executeBranch(CPU cpu) throws InstructionExecutionException {
		 cpu.addPC(number);
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException 
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if(s.length == 2 && s[0].equalsIgnoreCase("rjump") && Constants.isNumber(s[1]))
			return new RJump(Integer.parseInt(s[1]));
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion RJump en forma de cadena.
	 */
	@Override
	public String toString() {
		return "RJUMP " + number;
	}
}
