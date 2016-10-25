package tp.pr3.mv.executionMode;

/**
 * Clase que implementa las operaciones sobre una salida nula.
 * @author Lidia Flores, David Bolanios
 */
public class NullOut implements OutMethod{

	/**
	 * Al ser nula la salida, no se muestra nada.
	 */
	@Override
	public void writeChar(char c){
	}

	/**
	 * Al ser nula la salida, no se cierra nada.
	 */
	@Override
	public void close() {
	}
	
	/**
	 * Al ser nula la entrada, no se abre nada.
	 */
	@Override
	public void open() {
	}

}
