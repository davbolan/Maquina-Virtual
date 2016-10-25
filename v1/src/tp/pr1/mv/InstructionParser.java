package tp.pr1.mv;

/**
 * Clase encargada de crear una instruccion a partir de la linea ejecutada por el usuario.
 * @author Lidia Flores, David Bolanios
 */
public class InstructionParser {
	public final String lineSeparator = System.getProperty("line.separator");
	private static String line1 = "";
	
	/**
	 * Genera la Instruccion en funcion de un string dado.
	 * @param line Linea a parsear
	 * @return ins Instruccion creada.
	 */
	public static Instruction InstructionParse (String line){
		Action act = null;	
		Instruction ins = null;
		String aux[] = line.split(" "); 
		int longitudArray = aux.length;

		switch (longitudArray){
			case 1:			
				if(aux[0].equalsIgnoreCase("pop")){
					act = Action.POP;
				}							
				else if (aux[0].equalsIgnoreCase("dup")){
					act = Action.DUP;
				}
				else if (aux[0].equalsIgnoreCase("flip")){
					act = Action.FLIP;
			    }
				else if(aux[0].equalsIgnoreCase("add")){
					act = Action.ADD;
	            }	
				else if(aux[0].equalsIgnoreCase("sub")){
					act = Action.SUB;
	            }
				else if(aux[0].equalsIgnoreCase("mul")){
					act = Action.MUL;
	            }
				else if(aux[0].equalsIgnoreCase("div")){
					act = Action.DIV;
	            }
				else if(aux[0].equalsIgnoreCase("out")){
					act = Action.OUT;
	            }
				else if(aux[0].equalsIgnoreCase("halt")){
					act = Action.HALT;
	            }		
				else 
					act = null;	
				if(act!= null)
					ins = new Instruction(act);
				break;
				
			case 2:
				if(aux[0].equalsIgnoreCase("push")){
					act = Action.PUSH;
				}
				else if(aux[0].equalsIgnoreCase("load")){
					act = Action.LOAD;
				}
				else if(aux[0].equalsIgnoreCase("store")){
					act = Action.STORE;
				}
				else 
					act = null;			
			
				if(isNumber(aux[1]) && act!=null )
					ins = new Instruction(act, Integer.parseInt(aux[1]));

				break;
				
			default: 
		}
	
		line1 = line.toUpperCase();
		return ins;
	} 
	

	/**
	 * Este metodo se encarga de comprobar si un string es un numero. 
	 * Para ello, va comprobando que cada caracter del string es un digito.
	 * El primer caracter puede ser el signo '-' para los numero negativos.
	 * @param string String a comprobar si es un numero.
	 * @return true Si es un numero. False si el string es vacio o encuentra algun caracter
	 * que no es un digito.
	 */
	private static boolean isNumber(String string) {
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
	
	
	/**
	 * Metodo encargado de mostrar el mensaje de la instruccion que se va a ejecutar.
	 * @return El mensaje
	 */
	@Override
	public String toString(){	
		return "Comienza la ejecución de " + line1;		
	}
	
	/**
	 * Metodo encargado de mostrar el mensaje de error
	 * @return El mensaje de error
	 */
	public String error(){	
		return "Error: Instrucción incorrecta" + lineSeparator;
	}
	

}
