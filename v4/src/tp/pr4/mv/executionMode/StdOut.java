package tp.pr4.mv.executionMode;

import tp.pr4.mv.observables.InOutObserver;

/**
 * Clase que implementa las operaciones de un modo de salida estandar, es decir,
 * la salida por consola.
 * @author  Lidia Flores, David Bolanios
 */
public class StdOut implements OutMethod{
	
	/**
	 * Al ser la salida estandar, no se abre nada.
	 */
	@Override
	public void open() {
	}
	
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

	@Override
	public void addObserver(InOutObserver outObserver) {
	}

	
}
