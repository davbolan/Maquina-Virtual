package tp.pr1.mv;

/**
 * Clase encagarda de crear la instruction correcta. <br>
 * Una instruction consta de Action y puede llevar un operando. <br>
 * Sintaxis: ACTION OPERANDO
 * @author Lidia Flores, David Bolanios
 */
public class Instruction {
	private Action instruction;
	private int number;
	
	/**
	 * Crea una instruccion a partir de una accion.
	 * @param instruction La accion de la instruccion.
	 */
	public Instruction(Action instruction){
		this.instruction = instruction;
	}
	
	/**
	 * Crea una instruccion a partir de una accion y un operando
	 * @param instruction La accion de la instruccion.
	 * @param number El operando de la instruccion.
	 */
	public Instruction(Action instruction, int number){
		this.instruction = instruction;
		this.number = number;
	}
	
	/**
	 * Devuelve la accion de la instruccion.
	 * @return accion de la instruccion.
	 */
	public Action getAction(){
		return this.instruction;
	}
	
	/**
	 * Devuelve el operando de la instruccion.
	 * @return operando de la instruccion.
	 */
	public int getNumber(){
		return this.number;
	}
	
}

