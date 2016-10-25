package tp.pr5.mv.executionMode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import tp.pr5.mv.observables.InOutObserver;

/**
 * Interfaz que sera implementada por los distintos modos de entrada del programa y que contiene
 * utilizar las operaciones sobre dichos modos.
 * @author Lidia Flores, David Bolanios
 */
public interface InMethod{
	
	/**
	 * Abre el modo de entrada.
	 * @throws FileNotFoundException si el archivo de apertura no existe.
	 */
	public void open() throws FileNotFoundException;
	
	/**
	 * Lee una caracter de la entrada.
	 * @return el valor en ASCII del caracter
	 * @throws IOException si error en la lectura.
	 */
	public int readChar() throws IOException;
	
	/**
	 * Cierra el modo de entrada.
	 */
	public void close();
	
	/**
	 * Devuelve toda la entrada leida.
	 */
	public String readAll();
	
	/**
	 * Reseta la entrada
	 */
	public void reset();
	
	/**
	 * Cambia la entrada
	 * @param La ruta dodne se encuentra la entrada
	 */
	public void changedIn(String path) throws FileNotFoundException;

	/**
	 * Añade los observadores a la entrada correspondiente
	 * @param listObserver Lista con los observadores
	 */
	public void addObservers(ArrayList<InOutObserver> listObserver);

	/**
	 * Elimina y devuelve la lista de observadores de la entrada correspondiente
	 * @return la lista con los observadores eliminados
	 */
	public ArrayList<InOutObserver> removeObservers();

}
