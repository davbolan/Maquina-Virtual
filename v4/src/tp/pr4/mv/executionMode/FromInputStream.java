package tp.pr4.mv.executionMode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import tp.pr4.mv.observables.InOutObserver;
import tp.pr4.mv.observables.Observable;

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

	@Override
	public String readAll() {	
		return content.toString();
	}
	
	public void addObserver(InOutObserver outObserver) {
		super.addObserver(outObserver);
	}
	
	private void initIn(){
		Iterator<InOutObserver> it = super.iterator();
		while(it.hasNext())
		{
			it.next().initTextIn(readAll());
		}	
	}
	
	private void notifyInObserver(int pos) {
		Iterator<InOutObserver> it = super.iterator();
		while(it.hasNext())
		{
			it.next().inChanged(pos);;
		}
	}
}
