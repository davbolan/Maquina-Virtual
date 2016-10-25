package tp.pr5.mv;

import java.util.Scanner;


/**
 * Clase que contiene las constantes y utilidades que ser�n utilizadas en las dem�s clases de la aplicaci�n.
 * @author Lidia Flores, David Bolanios
 */
public class Constants {
	public static Scanner scan = new Scanner(System.in); 
	public final static String lineSeparator = System.getProperty("line.separator");
	
	/**
	 * Este metodo se encarga de comprobar si un string es un numero. 
	 * Para ello, va comprobando que cada caracter del string es un digito.
	 * El primer caracter puede ser el signo '-' para los numero negativos.
	 * @param string String a comprobar si es un numero.
	 * @return true Si es un numero. False si el string es vacio o encuentra algun caracter
	 * que no es un digito.
	 */
	public static boolean isNumber(String string) {
		boolean isNumber = (!(string == null || string.isEmpty()));
			
	    if(isNumber){ 
	    	int i = 0;
		    if (string.charAt(0) == '-') {
		    	isNumber = (string.length() > 1); 
		        i++;	        
		    }
		    while(isNumber && i < string.length()) {
		    	isNumber = Character.isDigit(string.charAt(i));	        
		    	i++;
		    }
	    }
	    
	    return isNumber;
	}
}


