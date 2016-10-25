package tp.pr3.mv.instruction.others;

import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo LoadInd, y sirve para cargar un dato de memoria indicada por la cima de la pila.
 * @author Lidia Flores, David Bolanios
 */
public class LoadInd extends RestSeq{

	/**
	 * Constructora por defecto.
	 */
	public LoadInd (){
	}
	
	/**
	 * Coge el valor de la posicion de la memoria indicada por la cima de la pila y lo apila en la pila.
	 * @param cpu la cual se modifica.
	 * @throws InstructionExecutionException si la direccion es negativa.
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException {
		if (cpu.getNumElem() > 0) {
			int pos = cpu.pop();
			if (pos >= 0) {
				cpu.load(pos);
			} 
			else{
				cpu.push(pos);
			 	throw new InstructionExecutionException("Error, la posicion debe ser positiva");
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
		if(s.length == 1 && s[0].equalsIgnoreCase("loadind"))
			return new LoadInd();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion LoadInd en forma de cadena.
	 */
	@Override
	public String toString() {
		return "LOADIND";
	}

}
