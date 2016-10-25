package tp.pr4.mv.executionMode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import tp.pr4.mv.Constants;
import tp.pr4.mv.observables.InOutObserver;
import tp.pr4.mv.observables.Observable;

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
		/*
		 * try {
			if(c == '\n')
				this.out.write(Constants.lineSeparator);
			else
				this.out.write(c);
			
			outChanged(c);
		} 
		catch (IOException e) {}
		*/
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


	private void outChanged(char c) {
		Iterator<InOutObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().outChanged(c);
		}	
	}
	
	public void addObserver(InOutObserver outObserver) {
		super.addObserver(outObserver);
	}
	
	private void writeOutFile() throws IOException{
		char c;
		for(int i = 0; i < buffer.size(); i++){
			c = buffer.get(i);
			if(c == '\n')
				this.out.write(Constants.lineSeparator);
			else
				this.out.write(c);	
		}
	}
}
