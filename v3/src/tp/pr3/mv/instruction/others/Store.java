package tp.pr3.mv.instruction.others;


import tp.pr3.mv.CPU;
import tp.pr3.mv.Constants;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo Store, y sirve para cargar un elemento en la
 * memoria.
 * @author Lidia Flores, David Bolanios
 */
public class Store extends RestSeq {
	private int number;
	
	/**
	 * Constructora por defecto.
	 */
	public Store() {	
	}
	
	/**
	 * Crea la instruccion store con la posicion de memoria donde almacenar la cima de la pila.
	 * @param number
	 */
	public Store(int number) {
		super();
		this.number = number;
	}

	/**
	 * Coge el valor de la cima de operandos y carga en la posicion "number" de memoria.
	 * @param cpu la cual se modifica.
	 * @throws InstructionExecutionException si no hay suficientes elementos en la pila
	 * o la posicion es negativa.
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException {
		if(cpu.getNumElem() <= 0)	
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");	
		else if(this.number < 0)
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": direccion incorrecta (" + this.number + ")");
		else
			cpu.store(this.number);
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
		return "STORE " + this.number;
	}

}
