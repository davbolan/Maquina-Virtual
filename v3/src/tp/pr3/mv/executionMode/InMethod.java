package tp.pr3.mv.executionMode;

import java.io.FileNotFoundException;

/**
 * Interfaz que sera implementada por los distintos modos de entrada del programa y que contiene
 * utilizar las operaciones sobre dichos modos.
 * @author Lidia Flores, David Bolanios
 */
public interface InMethod {
	
	/**
	 * Abre el modo de entrada.
	 * @throws FileNotFoundException si el archivo de apertura no existe.
	 */
	public void open() throws FileNotFoundException;
	
	/**
	 * Lee una caracter de la entrada.
	 * @return el valor en ASCII del caracter
	 */
	public int readChar();
	
	/**
	 * Cierra el modo de entrada.
	 */
	public void close();

}
