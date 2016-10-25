package tp.pr5.mv.executionMode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.Constants;
import tp.pr5.mv.observables.InOutObserver;
import tp.pr5.mv.observables.Observable;

/**
 * Clase que implementa las operaciones sobre un modo de salida a partir de un fichero.
 * @author  Lidia Flores, David Bolanios
 */
public class ToOutputStream extends Observable<InOutObserver> implements OutMethod {
	
	private FileWriter out;
	private String fileOutName;
	private ArrayList<Character> buffer;
	
	/**
	 * Crea el modo de salida a partir de un nombre de fichero.
	 * @param fileOutName el nombre del fichero.
	 */
	public ToOutputStream(String fileOutName){
		this.fileOutName = fileOutName;
	}
	
	/**
	 * Abre el fichero de salida a partir de un nombre.
	 * @throws IOException 
	 */
	@Override
	public void open() throws IOException {
		try {
			this.out = new FileWriter(fileOutName);
		} 
		catch (IOException e) {
			throw new IOException(fileOutName);
		}
		buffer = new ArrayList<>();
	}
	
	/** 
	 * Escribe el caracter C en el fichero.
	 * @param c el caracter a escribir.
	 * */
	
	@Override
	public void writeChar(char c) {
		buffer.add(c);
		outChanged(c);
	}

	/**
	 * Cierra el modo de salida, es decir, el fichero.
	 */
	@Override
	public void close() {
		try {
			if(this.out != null){
				writeOutFile();
				this.out.close();			
			}
		} 
		catch (IOException e) {}
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
	 * Vuelca en el archivo de salida el buffer de salida
	 * @throws IOException
	 */
	private void writeOutFile() throws IOException{
		char c;
		for(int i = 0; i < buffer.size(); i++){
			c = buffer.get(i);
			if(c == '\n')
				out.write(Constants.lineSeparator);
			else
				out.write(c);		
		}
	}

	/**
	 * Resetea la salida. Vaciando el buffer y la salida de sus observadores
	 */
	@Override
	public void reset() {
		buffer.clear();
		Iterator<InOutObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().cleanOut();
		}
	}

	/**
	 * Cambia el archivo de salida con el que se encuentra en la ruta
	 */
	@Override
	public void changedOut(String path) throws IOException {
		this.fileOutName = path;
		open();
	}
	
	/**
	 * Elimina y devuelve la lista de observadores de la salida por fichero
	 * @return la lista con los observadores eliminados
	 */
	public ArrayList<InOutObserver> removeObservers(){
		ArrayList<InOutObserver> listObserver = new ArrayList<InOutObserver>();
		listObserver.addAll(ob);
		ob.removeAll(ob);
		return listObserver;
	}

	/**
	 * Añade los observadores a la salida por fichero
	 * @param listObserver Lista con los observadores
	 */
	public void addObservers(ArrayList<InOutObserver> listObserver) {
		ob.addAll(listObserver);
	}
}
