package tp.pr5.mv.instruction.others;

import tp.pr5.mv.CPU;
import tp.pr5.mv.Constants;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.instruction.exceptionIns.*;

/**
 * Clase que crea una instruccion de tipo Store, y sirve para cargar un elemento en la
 * memoria.
 * @author Lidia Flores, David Bolanios
 */
public class Store extends RestSeq {
	private int dir;
	
	/**
	 * Constructora por defecto.
	 */
	public Store() {	
	}
	
	/**
	 * Crea la instruccion store con la posicion de memoria donde almacenar la cima de la pila.
	 * @param number
	 */
	public Store(int dir) {
		super();
		this.dir = dir;
	}

	/**
	 * Coge el valor de la cima de operandos y carga en la posicion "number" de memoria.
	 * @param cpu la cual se modifica.
	 * @throws InstructionExecutionException si no hay suficientes elementos en la pila
	 * o la posicion es negativa.
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException {
		if(dir < 0)
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": direccion incorrecta (" + dir + ")");
		
		try{
			cpu.store(dir);
		}
		catch (InstructionExecutionException iee){
			 throw new InstructionExecutionException
			 	("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay 0)");
		}
	}

	
	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if(s.length == 2 && s[0].equalsIgnoreCase("store") && Constants.isNumber(s[1]))
			return new Store(Integer.parseInt(s[1]));
		else throw new WrongInstructionFormatException();		
	}

	/**
	 * Devuelve la instruccion Store en forma de cadena.
	 */
	@Override
	public String toString() {
		return "STORE " + dir;
	}

}
