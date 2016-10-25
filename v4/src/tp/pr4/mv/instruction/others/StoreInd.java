package tp.pr4.mv.instruction.others;

import tp.pr4.mv.CPU;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.*;

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
	 * Almacena el valor de la cima de la pila en la direccion de memoria indicada por la subcima de la pila.
	 * @param cpu la cual se modifica.
	 * @throws InstructionExecutionException si la direccion es negativa.
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException {
		int dir;
		int value;
		
		try{
			value = cpu.pop();	
			try{
				dir = cpu.pop();	
			}		
			catch(InstructionExecutionException eee){ 
				cpu.push(value);
				throw new InstructionExecutionException
					("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay 1)");
			}
		}
		catch(InstructionExecutionException eee){ 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay 0)");
		}
		
	
		if (dir >= 0) {
			cpu.push(value);
			cpu.store(dir);
		} 
		else {
			cpu.push(dir);
			cpu.push(value);
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": direccion incorrecta (" + dir + ")");
		}
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
		return "STOREIND";
	}

}
