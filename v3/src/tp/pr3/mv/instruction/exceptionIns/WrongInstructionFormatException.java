package tp.pr3.mv.instruction.exceptionIns;

/**
 * Crea una excepcion cuando no ha se parseado correctamente una instruccion.
 * La excepcion tiene un mensaje en el cual se explica el error ocurrido.
 * Esta clase tiene dirferentes constructoras, una para cada constructora de 
 * la clase base.
 * @author Lidia Flores, David Bolanios
 * 
 */
public class WrongInstructionFormatException extends Exception {
	private static final long serialVersionUID = -8892949473135957902L;
	
	
	/**
	 * Constructor por defecto.
	 */
	public WrongInstructionFormatException(){
		super();
	}
	
	/**
	 * Crea una excepcion con un mensaje de error.
	 * @param arg0 Mensaje en el que se explica el error.
	 */
	public WrongInstructionFormatException(String arg0){
		super(arg0);
	}
	
	/**
	 * Crea una excepcion con una causa anidada.
	 * @param arg0 Causa anidada.
	 */
	public WrongInstructionFormatException(Throwable arg0){
		super(arg0);
	}
	
	/**
	 * Crea una excepcion con un mensaje de error y una causa anidada.
	 * @param arg0 Mensaje en el que se explica el error.
	 * @param arg1 Causa anidada.
	 */
	public WrongInstructionFormatException(String arg0, Throwable arg1){
		super(arg0,arg1);
	}
}
