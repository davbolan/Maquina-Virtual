package tp.pr3.mv.instruction.branch;

import tp.pr3.mv.CPU;
import tp.pr3.mv.Constants;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo Bf, y sirve para cambiar el
 * contador de programa si la cima de la pila es 0.
 * @author Lidia Flores, David Bolanios
 */
public class Bf extends Jumps {
	
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
	 * Constructora por defecto.
	 */
	public Bf() {
	
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
		if(cpu.getNumElem() > 0 ){
			if(cpu.pop() == 0){
				 if(!cpu.changePC(this.number))
					 throw new InstructionExecutionException
						("Error ejecutando " + this.toString() + ": PC fuera de rango");
			}
			else
				cpu.increaseProgramCounter();
		}
		else 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");
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
		return "BF " + this.number;
	}
}
