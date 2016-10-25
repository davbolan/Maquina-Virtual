package tp.pr5.mv.executionMode;

import java.util.ArrayList;

import tp.pr5.mv.observables.InOutObserver;
import tp.pr5.mv.observables.Observable;

/**
 * Clase que implementa las operaciones de un modo de salida estandar, es decir,
 * la salida por consola.
 * @author  Lidia Flores, David Bolanios
 */
public class StdOut extends Observable<InOutObserver> implements OutMethod{
	
	/**
	 * Al ser la salida estándar, no se abre nada.
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
	 * Al ser la salida estándar, no se cierra nada.
	 */
	@Override
	public void close() {	
	}

	/**
	 * Al ser estándar, no se resetea nada
	 */
	@Override
	public void reset() {
	}
	
	/**
	 * Al ser salida estándar, se ignora la ruta
	 */
	@Override
	public void changedOut(String path) {
	}

	/**
	 * Añade los observadores a la salida estándar
	 * @param listObserver Lista con los observadores
	 */
	public ArrayList<InOutObserver> removeObservers(){
		ArrayList<InOutObserver> listObserver = new ArrayList<InOutObserver>();
		listObserver.addAll(ob);
		ob.removeAll(ob);
		return listObserver;
	}
	
	/**
	 * Elimina y devuelve la lista de observadores de la salida estándar
	 * @return la lista con los observadores eliminados
	 */
	public void addObservers(ArrayList<InOutObserver> listObserver) {
		ob.addAll(listObserver);
	}

	
}
