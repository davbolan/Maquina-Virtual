package tp.pr2.mv.instruction.booleans;


import tp.pr2.mv.instruction.Instruction;

/**
 * Clase que crea una instruccion de tipo And, y sirve para realizar la operacion And
 * usando la cima y la subcima de la pila de operandos.
 * @author Lidia Flores, David Bolanios
 */
public class And extends BinaryCond {

	/**
	 * Constructora por defecto
	 */
	public And() {
		super();
	}

	/**
	 * Realiza la operacion AND usando la subcima y la cima y lo apila en la pila. 
	 * @param n1 La subcima.
	 * @param n2 La cima.
	 * @return true si hay operandos suficientes para realizar la operacion, false
	 * en caso contrario.
	 */
	@Override
	protected boolean execute(int n1, int n2) {
		boolean exito = false;
		if((n1 == 0 || n1 == 1) && (n2 == 0 || n2 ==1)){
			this.result = n1 * n2;
			exito = true;
		}
		return exito;
	}


	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("and")) 
			return new And();
		else return null;
	}

	/**
	 * Devuelve la instruccion And en forma de cadena.
	 */
	@Override
	public String toString() {
		return "AND";
	}

}
