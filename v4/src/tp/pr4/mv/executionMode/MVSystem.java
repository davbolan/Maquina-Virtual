package tp.pr4.mv.executionMode;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase donde se establecen y se usaran los modos de entrada y salida del programa.
 * @author Lidia Flores, David Bolanios
 */
public class MVSystem {
	public static InMethod in;
	public static OutMethod out;
	public static String MODE = "interactive";
	
	
	/**
	 * Cierra la entrada y salida.
	 */
	public static void close(){
		MVSystem.in.close();
		MVSystem.out.close();
	}

	/**
	 * Abre la entrada y salida.
	 * @throws FileNotFoundException si no se ha enecontrado del archivo de apertura.
	 */
	public static void open() throws FileNotFoundException, IOException {
		MVSystem.in.open();
		MVSystem.out.open();
	}

	
}