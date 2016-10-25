package tp.pr5.mv.executionMode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import tp.pr5.mv.observables.InOutObserver;

/**
 * Interfaz que sera implementada por los distintos modos de salida del programa y que contiene
 * utilizar las operaciones sobre dichos modos.
 * @author Lidia Flores, David Bolanios
 */
public interface OutMethod {
	
	/**
	 * Abre el modo de entrada.
	 * @throws FileNotFoundException si el archivo de apertura no existe.
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

	/**
	 * Reseta la salida
	 */
	public void reset();
	
	/**
	 * Cambia la salida
	 * @param La ruta donde se encuentra la salida
	 */
	public void changedOut(String path) throws IOException;

	/**
	 * Añade los observadores a la salida correspondiente
	 * @param listObserver Lista con los observadores
	 */
	public void addObservers(ArrayList<InOutObserver> list);
	
	/**
	 * Elimina y devuelve la lista de observadores de la salida correspondiente
	 * @return la lista con los observadores eliminados
	 */
	public ArrayList<InOutObserver> removeObservers();
}
