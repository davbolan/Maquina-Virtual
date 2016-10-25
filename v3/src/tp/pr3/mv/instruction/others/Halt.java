package tp.pr3.mv.instruction.others;


import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo Halt, y sirve para indicar a la CPU que
 * queremos salir.
 * @author Lidia Flores, David Bolanios
 */
public class Halt extends RestSeq {

	/**
	 * Constructora por defecto.
	 */
	public Halt() {
		super();
	}

	/**
	 * Indica a la CPU que queremos salir.
	 * @param cpu la cual se modifica.
	 */
	@Override
	protected void executeAux(CPU cpu) {
		cpu.exit();
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length == 1 && s[0].equalsIgnoreCase("halt")) 
			return new Halt();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion Halt en forma de cadena.
	 */
	@Override
	public String toString() {
		return "HALT";
	}

}
