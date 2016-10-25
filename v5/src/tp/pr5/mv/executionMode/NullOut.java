package tp.pr5.mv.executionMode;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.observables.InOutObserver;
import tp.pr5.mv.observables.Observable;

/**
 * Clase que implementa las operaciones sobre una salida nula.
 * @author Lidia Flores, David Bolanios
 */
public class NullOut extends Observable<InOutObserver> implements OutMethod{

	/**
	 * Al ser nula la entrada, no se abre nada.
	 */
	@Override
	public void open() {
	}
	
	/**
	 * Al ser nula la salida, no se muestra nada.
	 */
	@Override
	public void writeChar(char c){
		outChanged(c);
	}

	/**
	 * Al ser nula la salida, no se cierra nada.
	 */
	@Override
	public void close() {
	}

	/**
	 * Avisa a los observadores con el carácter a mostrar
	 * @param c
	 */
	private void outChanged(char c) {
		Iterator<InOutObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().outChanged(c);
		}	
	}

	/**
	 * Al ser nula la salida, no se reseta.
	 */
	@Override
	public void reset() {
		Iterator<InOutObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().cleanOut();
		}
	}

	/**
	 * Al ser nula la salida, ignora la ruta.
	 */
	@Override
	public void changedOut(String path) {
	}

	/**
	 * Añade los observadores a la salida nula
	 * @param listObserver Lista con los observadores
	 */
	public ArrayList<InOutObserver> removeObservers(){
		ArrayList<InOutObserver> listObserver = new ArrayList<InOutObserver>();
		listObserver.addAll(ob);
		ob.removeAll(ob);
		return listObserver;
	}

	/**
	 * Elimina y devuelve la lista de observadores de la salida nula
	 * @return la lista con los observadores eliminados
	 */
	public void addObservers(ArrayList<InOutObserver> listObserver) {
		ob.addAll(listObserver);
	}
	

}
