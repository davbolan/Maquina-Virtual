package tp.pr5.mv.instruction;


import tp.pr5.mv.CPU;
import tp.pr5.mv.instruction.exceptionIns.*;

/**
 * Interfaz con las operaciones que seran implementadas por las distintas instrucciones.
 * @author Lidia Flores, David Bolanios
 */
public interface Instruction {
		
	/**
	 * Metodo implementado por cada una de las subclases las cuales modifican convenientemente
	 * la CPU. Puede lanzar excepcion si hay algun error.
	 * @param cpu La CPU.
	 * @throws InstructionExecutionException 
	 */
    public abstract void execute(CPU cpu) throws InstructionExecutionException;

    /**
     * Metodo implementado por cada una de las subclases las cuales comprueba si la 
     * instrucion es de su clase (lo parsea). Lanza excepcion si el parseo es incorrecto.
     * @param s Linea a parsear.
     * @throws WrongInstructionFormatException 
     */
    public abstract Instruction parse(String[] s) throws WrongInstructionFormatException;
    
    /**
     * Metodo implementado por cada una de las subclases las cuales devuelve su instruccion en
     * forma de cadena.
     * @return La instruccion en forma de cadena.
     */
    public abstract String toString();
}

