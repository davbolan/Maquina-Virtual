package tp.pr3.mv.instruction.others;


import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

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
		if(cpu.getNumElem() > 1){
			int cima = cpu.pop();
			int subcima = cpu.pop();
			cpu.push(cima);
			cpu.push(subcima);
		}
		
		else 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");
		
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
