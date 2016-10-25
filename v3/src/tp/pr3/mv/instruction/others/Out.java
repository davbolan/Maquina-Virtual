package tp.pr3.mv.instruction.others;


import tp.pr3.mv.CPU;
import tp.pr3.mv.executionMode.MVSystem;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo Out, y sirve para mostrar el caracter en ASCII 
 * asociado a la cima de operandos.
 * @author Lidia Flores, David Bolanios
 */
public class Out extends RestSeq {

	/**
	 * Constructora por defecto.
	 */
	public Out() {
		super();
	}

	/**
	 * Muestra el caracter ASCII asociado a la cima de operandos.
	 * En caso de que sea negativo lo hace de su valor absoluto.
	 * @param cpu la cual se modifica.
	 * @throws InstructionExecutionException 
	 */
	@Override
	public void executeAux(CPU cpu) throws InstructionExecutionException {
		char[] car;
		if(cpu.getNumElem() > 0){
			car = Character.toChars(Math.abs(cpu.pop()));
			MVSystem.out.writeChar(car[0]);
		}
		else 	
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");
		
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException 
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("out")) 
			return new Out();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion Out en forma de cadena.
	 */
	@Override
	public String toString() {
		return "OUT";
	}


}
