package tp.pr5.mv.observables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Clase genérica con los métodos de un Observable
 * @author Lidia Flores, David Bolanios
 */
public class Observable<T> implements Iterable<T>{
	
	protected Collection<T> ob = new ArrayList<T>();
	
	/**
	 * Añade un observador
	 * @param observer el observador
	 */
	public void addObserver(T observer){
		ob.add(observer);		
	}
	
	/**
	 * Elimina un observador
	 * @param observer el observador
	 */
	public void removeObserver(T observer){	
		ob.remove(observer);	
	}

	/**
	 * Devuelve el iterador de la lista de observadores
	 */
	@Override
	public Iterator<T> iterator() {
		return this.ob.iterator();
	}
}