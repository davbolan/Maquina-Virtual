package tp.pr3.mv.executionMode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que implementa las operaciones sobre un modo de entrada a partir de un fichero.
 * @author  Lidia Flores, David Bolanios
 */
public class FromInputStream implements InMethod{
	
	private FileReader in;
	private String fileInName;
		
	/**
	 * Crea el modo de entrada a partir de un nombre de fichero.
	 * @param fileInName el nombre del fichero.
	 */
	public FromInputStream(String fileInName){
		this.fileInName = fileInName;
	}
	
	/**
	 * Abre el fichero de entrada a partir de un nombre.
	 */
	@Override
	public void open() throws FileNotFoundException{
		this.in = new FileReader(fileInName);
	}
	
	
	/**
	 * Lee un caracter del fichero. Si falla la lectura, se lee -1.
	 */
	@Override
	public int readChar() {
		int car = -1;
		try {
			if(this.in.ready())
				car = this.in.read();
		} 
		catch (IOException e) {}
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
	
}
