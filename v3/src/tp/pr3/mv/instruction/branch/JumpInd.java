package tp.pr3.mv.instruction.branch;

import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

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
		if (cpu.getNumElem() > 0) {
			int number = cpu.pop();
			if (number >= 0) {
				if (!cpu.changePC(number))
					// Aqui hay que volver a apilar?
					throw new InstructionExecutionException
						("Error ejecutando " + this.toString() + ": PC fuera de rango");
			} 
			else
				cpu.push(number);
				throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": direccion incorrecta (" + number + ")");
		} 
		else throw new InstructionExecutionException
			("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");
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
		return "JUMPIND ";
	}

}
