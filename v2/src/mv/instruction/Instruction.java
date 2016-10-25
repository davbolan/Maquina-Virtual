package tp.pr2.mv.instruction;


import tp.pr2.mv.CPU;

/**
 * Clase encargada de crear la instruction correcta. <br>
 * Sintaxis: ACTION OPERANDO
 * @author Lidia Flores, David Bolanios
 */
public abstract class Instruction {
	
	/**
	 * Constructora por defecto.
	 */
	public Instruction(){
	}
	
	/**
	 * Metodo implementado por cada una de las subclases las cuales modifican convenientemente
	 * la CPU.
	 * @param cpu La CPU.
	 * @return true si la ejecucion ha tenido exito. False en caso contrario.
	 */
    public abstract boolean execute(CPU cpu);

    /**
     * Metodo implementado por cada una de las subclases las cuales comprueba si la 
     * instrucion es de su clase (lo parsea).
     * @param s Linea a parsear.
     * @return
     */
    public abstract Instruction parse(String[] s);
    
    /**
     * Metodo implementado por cada una de las subclases las cuales devuelve su instruccion en
     * forma de cadena.
     * @return La instruccion en forma de cadena.
     */
    public abstract String toString();

	/**
	 * Este metodo se encarga de comprobar si un string es un numero. 
	 * Para ello, va comprobando que cada caracter del string es un digito.
	 * El primer caracter puede ser el signo '-' para los numero negativos.
	 * @param string String a comprobar si es un numero.
	 * @return true Si es un numero. False si el string es vacio o encuentra algun caracter
	 * que no es un digito.
	 */
	public static boolean isNumber(String string) {
		boolean is = true;
		if (string == null || string.isEmpty()) 
	        is = false;
	    
	    else{ 
	    	int i = 0;
		    if (string.charAt(0) == '-') {
		        if (string.length() > 1) 
		            i++;        
		        else 
		            is = false;	        
		    }
		    while(is && i < string.length()) {
		        if (!Character.isDigit(string.charAt(i))) 
		            is = false;;	        
		        i++;
		    }
	    }
	    return is;
	}

}

