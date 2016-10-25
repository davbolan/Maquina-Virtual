package tp.pr3.mv.executionMode;

import java.io.IOException;

/**
 * Clase que implementa las operaciones de un modo de entrada estandar, es decir,
 * la entrada por teclado.
 * @author  Lidia Flores, David Bolanios
 */
public class StdIn implements InMethod{

	/**
	 * Lee un caracter desde el teclado. Si falla la entrada, se lee -1.
	 */
	@Override
	public int readChar() {
		int car = -1;
		try {
			car = System.in.read();
		} 
		catch (IOException e) {}
		
		return car;
	}

	/**
	 * Al ser la entrada estandar, no se cierra nada.
	 */
	@Override
	public void close() {
	}

	/**
	 * Al ser la entrada estandar, no se abre nada.
	 */
	@Override
	public void open() {
	}

}