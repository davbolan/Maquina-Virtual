package tp.pr3.mv.executionMode;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que implementa las operaciones sobre un modo de salida a partir de un fichero.
 * @author  Lidia Flores, David Bolanios
 */
public class ToOutputStream implements OutMethod{
	
	private FileWriter out;
	private String fileOutName;
	
	/**
	 * Crea el modo de salida a partir de un nombre de fichero.
	 * @param fileOutName el nombre del fichero.
	 */
	public ToOutputStream(String fileOutName){
		this.fileOutName = fileOutName;
	}
	
	/**
	 * Abre el fichero de salida a partir de un nombre.
	 */
	@Override
	public void open() throws IOException {
		this.out = new FileWriter(fileOutName);
	}
	
	/** 
	 * Escribe el caracter C en el fichero.
	 * @param c el caracter a escribir.
	 * */
	
	@Override
	public void writeChar(char c) {
		try {
			this.out.write(c);
		} 
		catch (IOException e) {}
	}

	/**
	 * Cierra el modo de salida, es decir, el fichero.
	 */
	@Override
	public void close() {
		try {
			if(this.out != null)
				this.out.close();
		} 
		catch (IOException e) {}
	}

}
