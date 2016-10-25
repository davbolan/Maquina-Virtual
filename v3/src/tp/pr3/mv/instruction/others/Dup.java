package tp.pr3.mv.instruction.others;


import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo Dup, y sirve para duplicar el valor de la cima
 * de la pila.
 * @author Lidia Flores, David Bolanios
 */
public class Dup extends RestSeq {

	/**
	 * Constructora por defecto.
	 */
	public Dup() {
		super();
	}

	
	/**
	 * Duplica el valor almacenado en la cima de pila
	 * @param cpu la cual se modifica.
	 * @throws InstructionExecutionException si no hay elementos suficientes en la pila.
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException{
		 if(cpu.getNumElem() > 0){
			 int n1 = cpu.pop();
			 cpu.push(n1);
			 cpu.push(n1);
		}
		 else 
			 throw new InstructionExecutionException
			 	("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("dup")) 
			return new Dup();
		else throw new WrongInstructionFormatException();
	}


	/**
	 * Devuelve la instruccion Dup en forma de cadena.
	 */
	@Override
	public String toString() {
		return "DUP";
	}

}
