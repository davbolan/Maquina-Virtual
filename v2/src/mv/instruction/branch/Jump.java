package tp.pr2.mv.instruction.branch;


import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

/**
 * Clase que crea una instruccion de tipo Jump, y sirve para cambiar el
 * contador de programa.
 * @author Lidia Flores, David Bolanios
 */
public class Jump extends Jumps {
	
	/**
	 * Constructora correspondiente.
	 */
	public Jump() {
		
	}
	
	/**
	 * Constructora que crea una instruccion Jump con el nuevo contador
	 * de programa.
	 * @param number El nuevo contador de programa.
	 */
	public Jump(int number) {
		super();
		this.number = number;
	}

	/**
	 * Metodo que ejecuta la instruccion Jump, es decir, cambia el contador de programa
	 * al indicado.
	 * @param CPU El CPU a cambiar el contador de programa.
	 * @return exito true si el salto ha tenido exito, false en caso contrario.
	 */
	@Override
	protected boolean executeBranch(CPU cpu) {
		boolean exito = false;
		if(this.number <= cpu.getSizeProgram())
			exito = cpu.changePC(this.number) && cpu.step();
		
		else
			cpu.exit();
		
		return exito;
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if(s.length == 2 && s[0].equalsIgnoreCase("jump") && Instruction.isNumber(s[1]))
			return new Jump(Math.abs(Integer.parseInt(s[1])));
		
		else
			return null;
	}

	/**
	 * Devuelve la instruccion Jump en forma de cadena.
	 */
	@Override
	public String toString() {
		return "JUMP " + this.number;
	}
}
