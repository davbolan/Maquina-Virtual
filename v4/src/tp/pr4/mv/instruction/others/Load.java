package tp.pr4.mv.instruction.others;

import tp.pr4.mv.CPU;
import tp.pr4.mv.Constants;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.*;

/**
 * Clase que crea una instruccion de tipo Load, y sirve para cargar un dato de memoria en
 * la pila.
 * @author Lidia Flores, David Bolanios
 */
public class Load extends RestSeq {
	private int dir;
	
	/**
	 * Constructora por defecto.
	 */
	public Load() {
	}
	
	/**
	 * Crea una instruccion Load indicando la posicion de memoria donde coger el dato.
	 * @param number La posicion de memoria de donde coger el dato.
	 */
	public Load(int dir) {
		super();
		this.dir = dir;
	}
	
	/**
	 * Coge el valor de la posicion de la memoria indicada y lo apila en la pila.
	 * @param cpu la cual se modifica.
	 * @throws InstructionExecutionException si la direccion es negativa.
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException {
		if(dir >= 0){
			cpu.load(dir);
		}
		else 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": direccion incorrecta (" + dir + ")");
	}
	
	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay algun error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if(s.length == 2 && s[0].equalsIgnoreCase("load") && Constants.isNumber(s[1]))
			return new Load(Integer.parseInt(s[1]));
		else throw new WrongInstructionFormatException();		
	}
	
	/**
	 * Devuelve la instruccion Load en forma de cadena.
	 */
	@Override
	public String toString() {
		return "LOAD " + dir;
	}
}
