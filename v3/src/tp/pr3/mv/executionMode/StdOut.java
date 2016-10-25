package tp.pr3.mv.executionMode;

/**
 * Clase que implementa las operaciones de un modo de salida estandar, es decir,
 * la salida por consola.
 * @author  Lidia Flores, David Bolanios
 */
public class StdOut implements OutMethod{
	
	/**
	 * Muestra el caracter C por consola.
	 * @param c el caracter a mostrar.
	 */
	@Override
	public void writeChar(char c){
		System.out.print(c);
	}

	/**
	 * Al ser la salida estandar, no se cierra nada.
	 */
	@Override
	public void close() {	
	}

	/**
	 * Al ser la salida estandar, no se abre nada.
	 */
	@Override
	public void open() {
	}
}
