package tp.pr4.mv.executionMode;

import java.util.Iterator;

import tp.pr4.mv.observables.InOutObserver;
import tp.pr4.mv.observables.Observable;

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

	private void outChanged(char c) {
		Iterator<InOutObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().outChanged(c);
		}	
	}
	
	public void addObserver(InOutObserver outObserver) {
		super.addObserver(outObserver);
	}
	
	

}
