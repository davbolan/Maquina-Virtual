package tp.pr3.mv.executionMode;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interfaz que sera implementada por los distintos modos de salida del programa y que contiene
 * utilizar las operaciones sobre dichos modos.
 * @author Lidia Flores, David Bolanios
 */
public interface OutMethod {
	
	/**
	 * Abre el modo de entrada.
	 * @throws FileNotFoundException si elarchivo de apertura no existe.
	 */
	public void open() throws IOException;
	
	/**
	 * Escribe un caracter C en la salida.
	 * @param c El caracter a escribir.
	 */
	public void writeChar(char c);
	
	/**
	 * Cierra el modo de salida.
	 */
	public void close();
}
