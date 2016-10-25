package tp.pr5.mv.executionMode;

import java.util.ArrayList;

import tp.pr5.mv.observables.InOutObserver;
import tp.pr5.mv.observables.Observable;

/**
 * Clase que implementa las operaciones sobre una entrada nula.
 * @author Lidia Flores, David Bolanios
 */
public class NullIn extends Observable<InOutObserver> implements InMethod{

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

	/**
	 * Al ser nula la entrada, no se muestra nada.
	 */
	@Override
	public String readAll() {
		return "";
	}

	/**
	 * Al ser nula la entrada, no se reseta.
	 */
	@Override
	public void reset() {
	}
	
	/**
	 * Al ser nula la entrada, ignora la ruta.
	 */
	@Override
	public void changedIn(String path) {		
	}

	/**
	 * Añade los observadores a la entrada nula
	 * @param listObserver Lista con los obsevadores
	 */
	@Override
	public void addObservers(ArrayList<InOutObserver> listObserver) {
		ob.addAll(listObserver);
	}

	/**
	 * Elimina y devuelve la lista de observadores de la entrada nula
	 * @return la lsita con los observadores eliminados
	 */
	@Override
	public ArrayList<InOutObserver> removeObservers() {
		ArrayList<InOutObserver> listObserver = new ArrayList<InOutObserver>();
		listObserver.addAll(ob);
		ob.removeAll(ob);
		return listObserver;
	}
}
