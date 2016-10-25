package tp.pr2.mv.instruction.compare;


import tp.pr2.mv.instruction.Instruction;

/**
 * Clase que crea una instruccion de tipo Gt, y sirve para apilar un 1
 * si la subcima es menor que la cima.
 * @author Lidia Flores, David Bolanios
 */
public class Gt extends NumericCond {

	/**
	 * Constructora por defecto.
	 */
	public Gt() {
		super();
	}
	
	
	/**
	 * Metodo que comprueba si la subcima es mayor que la cima.
	 * return true si la subcima es mayor que la cima, false en caso contrario.
	 */
	@Override
	protected boolean compare(int cima, int subcima) {
		return (subcima > cima);
	}
	
	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("gt")) 
			return new Gt();
		else return null;
	}


	/**
	 * Devuelve la instruccion Gt en forma de cadena.
	 */
	@Override
	public String toString() {
		return "GREATER-THAN";
	}
	

}
