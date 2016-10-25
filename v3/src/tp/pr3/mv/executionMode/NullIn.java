package tp.pr3.mv.executionMode;

/**
 * Clase que implementa las operaciones sobre una entrada nula.
 * @author Lidia Flores, David Bolanios
 */
public class NullIn implements InMethod{

	/**
	 * Al ser nula la entrada, se devuelve -1.
	 * @return el valor nulo de la entrada: -1
	 */
	@Override
	public int readChar(){
		return -1;
	}

	/**
	 * Al ser nula la entrada, no se cierra nada.
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
