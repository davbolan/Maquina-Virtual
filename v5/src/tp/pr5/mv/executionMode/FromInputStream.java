package tp.pr5.mv.executionMode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.observables.InOutObserver;
import tp.pr5.mv.observables.Observable;

/**
 * Clase que implementa las operaciones sobre un modo de entrada a partir de un fichero.
 * @author  Lidia Flores, David Bolanios
 */
public class FromInputStream extends Observable<InOutObserver> implements InMethod{
	
	private FileReader in;
	private String fileInName;
	private StringBuilder content;
	private int pos;
		
	/**
	 * Crea el modo de entrada a partir de un nombre de fichero.
	 * @param fileInName el nombre del fichero.
	 */
	public FromInputStream(String fileInName){
		this.fileInName = fileInName;
	}
	
	/**
	 * Abre el fichero de entrada a partir de un nombre.
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	@Override
	public void open() throws FileNotFoundException{
		try {
			this.in = new FileReader(fileInName);
		} 
		catch (FileNotFoundException e) {
			throw new FileNotFoundException(fileInName);	
		}
		
		content = new StringBuilder();
		
		char[] car;
		try {
			while(in.ready()){
				car = Character.toChars(in.read());
				content.append(car[0]);
			}
			
		} 
		catch (IOException e) {}
	
		initIn();
		pos = 0;
	}
	
	/**
	 * Lee un caracter del fichero. Si falla la lectura, se lee -1.
	 * @throws IOException 
	 */
	@Override
	public int readChar() throws IOException {
		int car = -1;

		if(pos < content.length()){
			car = content.charAt(pos);
			if(car!=10 && car!=13)
				notifyInObserver(pos);
			pos++;
		}
		return car;
	}
	
	/**
	 * Cierra el modo de entrada, es decir, el fichero.
	 */
	@Override
	public void close() {
		try{
			if(this.in != null)
				this.in.close();
		}
		catch(IOException io){}	
	}

	/**
	 * Devuelve todo el contenido del archivo
	 */
	@Override
	public String readAll() {	
		return content.toString();
	}
	
	/**
	 * Añade un observador de tipo InOutObserver
	 */
	public void addObserver(InOutObserver outObserver) {
		super.addObserver(outObserver);
	}
	
	/**
	 * Avisa a los observadores con la entrada inicial
	 */
	private void initIn(){
		Iterator<InOutObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().initTextIn(readAll());
		}	
	}
	
	/**
	 * Avisa a los observadores con la posicion del caracter que ha sido leido
	 */
	private void notifyInObserver(int pos) {
		Iterator<InOutObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().inChanged(pos);;
		}
	}

	/**
	 * Resetea la entrada. Reiniciando el contador de isntrucciones y mostrando la entrada inicial
	 */
	@Override
	public void reset() {
			pos = 0;
			initIn();
	}
	
	/**
	 * Cambia el archivo de entrada con el que se encuentra en la ruta
	 */
	public void changedIn(String path) throws FileNotFoundException{
		this.fileInName = path;
		open();
		initIn();
	}

	/**
	 * Elimina y devuelve la lista de observadores de la entrada por archivo
	 * @return la lsita con los observadores eliminados
	 */
	public ArrayList<InOutObserver> removeObservers(){
		ArrayList<InOutObserver> listObserver = new ArrayList<InOutObserver>();
		listObserver.addAll(ob);
		ob.removeAll(ob);
		return listObserver;
	}

	/**
	 * Añade los observadores a la entrada por archivo
	 * @param listObserver Lista con los obsevadores
	 */
	public void addObservers(ArrayList<InOutObserver> listObserver) {
		ob.addAll(listObserver);
	}
}
