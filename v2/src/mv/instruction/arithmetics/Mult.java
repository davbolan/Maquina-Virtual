package tp.pr2.mv.instruction.arithmetics;

import tp.pr2.mv.instruction.Instruction;

/**
 * Clase que crea una instruccion de tipo Mult, y sirve para multiplicar la cima y la subcima
 * de la pila de operandos.
 * @author Lidia Flores, David Bolanios
 */
public class Mult extends Arithmetics{

	/**
	 * Constructora por defecto
	 */
	public Mult(){
		super();
	}

	 /**
	  * Multiplica la subcima por la cima y lo apila en la pila.
	  * @return true si hay operandos suficientes para realizar la multiplicacion, false
	  * en caso contrario.
	  */
	@Override
	public boolean execute(int n1, int n2) {
		this.result = n1*n2;
		return true;
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("mult")) 
			return new Mult();
		else return null;
	}

	/**
	 * Devuelve la instruccion Mult en forma de cadena.
	 */
	@Override
	public String toString() {
		return "MULT";
	}

}
