package tp.pr3.mv.instruction.others;

import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo StoreInd, y sirve para almacenar el valor de la cima 
 * en la direccino de memoria indicada por la subcima.
 * @author Lidia Flores, David Bolanios
 */
public class StoreInd extends RestSeq {

	/**
	 * Constructora por defecto.
	 */
	public StoreInd(){
		
	}
	
	/**
	 * Almacena el valor de la posicion de la memoria indicada por la cima de la pila y lo apila en la pila.
	 * @param cpu la cual se modifica.
	 * @throws InstructionExecutionException si la direccion es negativa.
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException {
		if (cpu.getNumElem() > 1) {
			int value = cpu.pop();
			int pos = cpu.pop();
			if (pos >= 0) {
				cpu.push(value);
				cpu.store(pos);
			} 
			else {
				cpu.push(pos);
				cpu.push(value);
				throw new InstructionExecutionException
					("Error ejecutando " + this.toString() + ":  direccion incorrecta (" + pos + ")");
			}
		}
		else 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");	
		}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay algun error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length == 1 && s[0].equalsIgnoreCase("storeind"))
			return new StoreInd();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion StoreInd en forma de cadena.
	 */
	@Override
	public String toString() {
		return "STOREIND ";
	}

}
