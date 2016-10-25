package tp.pr4.mv.executionMode;

import java.io.IOException;

import tp.pr4.mv.Constants;
import tp.pr4.mv.observables.InOutObserver;

/**
 * Clase que implementa las operaciones de un modo de entrada estandar, es decir,
 * la entrada por teclado.
 * @author  Lidia Flores, David Bolanios
 */
public class StdIn implements InMethod{
	private StringBuilder content;
	private int pos = 0;

	
	/**
	 * Al ser la entrada estandar, no se abre nada.
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
			readId();
		if(pos < content.length()){
			car = content.charAt(pos);
			pos++;		
		
		}
		return car;
	}


	private void readId() {
		content = new StringBuilder(Constants.scan.nextLine());
	}


	/**
	 * Al ser la entrada estandar, no se cierra nada.
	 */
	@Override
	public void close() {
	}


	@Override
	public String readAll() {
		return "";
	}


	@Override
	public void addObserver(InOutObserver inOutObserver) {
	}
}