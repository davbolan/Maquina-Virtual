package tp.pr5.mv.executionMode;

import java.io.IOException;
import java.util.ArrayList;

import tp.pr5.mv.Constants;
import tp.pr5.mv.observables.InOutObserver;
import tp.pr5.mv.observables.Observable;

/**
 * Clase que implementa las operaciones de un modo de entrada estandar, es decir,
 * la entrada por teclado.
 * @author  Lidia Flores, David Bolanios
 */
public class StdIn extends Observable<InOutObserver> implements InMethod{
	private StringBuilder content;
	private int pos = 0;
	
	/**
	 * Al ser la entrada estándar, no se abre nada.
	 */
	@Override
	public void open() {
		this.content = new StringBuilder();	
	}
	
	/**
	 * Lee un caracter desde el teclado. Si falla la entrada, se lee -1.
	 * @throws IOException si hay error en la lectura.
	 */
	@Override
	public int readChar(){
		int car = -1;
		
		if(content.length() == 0)
			content = new StringBuilder(Constants.scan.nextLine());
		if(pos < content.length()){
			car = content.charAt(pos);
			pos++;		
		
		}
		return car;
	}

	/**
	 * Al ser la entrada estandar, no se cierra nada.
	 */
	@Override
	public void close() {
	}

	/**
	 * Al ser estándar, no se muestra nada
	 */
	@Override
	public String readAll() {
		return "";
	}

	/**
	 * Al ser estándar, no se resetea nada
	 */
	@Override
	public void reset() {
	}

	/**
	 * Al ser entrada estándar, se ignora la ruta
	 */
	@Override
	public void changedIn(String path) {
	}

	/**
	 * Añade los observadores a la entrada estandar
	 * @param listObserver Lista con los obsevadores
	 */
	@Override
	public void addObservers(ArrayList<InOutObserver> listObserver) {
		ob.addAll(listObserver);
	}

	/**
	 * Elimina y devuelve la lista de observadores de la entrada estandar
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