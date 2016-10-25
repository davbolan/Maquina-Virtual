package tp.pr4.mv.instruction.others;

import tp.pr4.mv.CPU;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.*;

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
		int dir;
		
		try{
			dir = cpu.pop();	
		}
		catch (InstructionExecutionException iee){
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay 0)");
		}	
		
		if (dir >= 0) 
			cpu.load(dir);		
		else{
			cpu.push(dir);
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
