package tp.pr2.mv.instruction.arithmetics;


import tp.pr2.mv.instruction.Instruction;

/**
 * Clase que crea una instruccion de tipo Div, y sirve para dividir la cima y la subcima
 * de la pila de operandos.
 * @author Lidia Flores, David Bolanios
 *
 */
public class Div extends Arithmetics{

	/**
	 * Constructora por defecto.
	 */
	public Div() {
		super();
	}

	
	 /**
	  * Divide la subcima entre la cima y lo apila en la pila.
	  * @return true si se puede realizar la division, false si no hay operandos suficientes
	  * o la cima es 0.
	  */
	@Override
	protected boolean execute(int n1, int n2) {
		boolean exito = false;
		if(n2 != 0){
			this.result = n2/n1;
			exito = true;
		}
		return exito;
	}
	
	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("div")) 
			return new Div();
		else return null;
	}

	/**
	 * Devuelve la instruccion Div en forma de cadena.
	 */
	@Override
	public String toString() {
		return "DIV";
	}

}
