package tp.pr5.mv.instruction.others;

import tp.pr5.mv.CPU;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.instruction.exceptionIns.*;

/**
 * Clase que crea una instruccion de tipo Flip, y sirve para intercambiar el valor de la cima
 * de la pila con la subcima.
 * @author Lidia Flores, David Bolanios
 */
public class Flip extends RestSeq {

	/** 
	 * Constructora por defecto 
	 */
	public Flip() {
		super();
	}

	
	 /**
	  * Intercambia el valor almacenado en la cima de pila con la subcima
	  * @param cpu la cual se modifica.
	  * @throws InstructionExecutionException si no hay suficientes elementos en la pila.
	  */
	@Override
	public void executeAux(CPU cpu) throws InstructionExecutionException {
		int cima;
		int subcima;
		try{
			cima = cpu.pop();	
			try{
				subcima = cpu.pop();	
			}		
			catch(InstructionExecutionException eee){ 
				cpu.push(cima);
				throw new InstructionExecutionException
					("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay 1)");
			}
		}
		catch(InstructionExecutionException eee){ 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay 0)");
		}
		
		cpu.push(cima);
		cpu.push(subcima);
	}
	

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException 
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("flip")) 
			return new Flip();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion Flip en forma de cadena.
	 */
	@Override
	public String toString() {
		return "FLIP";
	}

}
