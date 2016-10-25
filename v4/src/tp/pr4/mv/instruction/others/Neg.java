package tp.pr4.mv.instruction.others;

import tp.pr4.mv.CPU;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.*;

/**
 * Clase que crea una instruccion de tipo Neg, y sirve para cambiar de signo el valor de la
 * cima de la pila.
 * @author Lidia Flores, David Bolanios
 */
public class Neg extends RestSeq {

	/**
	 * Constructora por defecto.
	 */
	public Neg() {
		super();
	}

	/**
	 * Cambia de signo el valor de la cima de la pila.
	 * @param cpu la cual se modifica.
	 * @throws InstructionExecutionException si no hay suficientes elementos en la pila. 
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException {
		 int neg;
		 
		 try{
			 neg = cpu.pop();
		 }
		 catch (InstructionExecutionException iee){
				throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay 0)");
		 }
		 
		 cpu.push(-neg);
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("neg")) 
			return new Neg();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion load en forma de cadena.
	 */
	@Override
	public String toString() {
		return "NEG";
	}
}
