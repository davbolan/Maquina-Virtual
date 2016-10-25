package tp.pr3.mv.instruction.branch;

import tp.pr3.mv.CPU;
import tp.pr3.mv.Constants;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase que crea una instruccion de tipo RBt, y sirve para
 * incrementar/decrementar el contador de programa si la cima de la pila es 1.
 * @author Lidia Flores, David Bolanios
 */
public class RBt extends Jumps {
	
	/**
	 * Constructora por defecto.
	 */
	public RBt() {
	
	}
	
	/**
	 * Constructora que crea una instruccion RBt con el numero de lineas a incrementar/
	 * decrementar el PC.
	 * @param number El numero de lineas a incrementar/decrementar.
	 */
	public RBt(int number) {
		super();
		this.number = number;
	}

	/**
	 * Metodo que ejecuta la instruccion RBf, es decir, incrementa/decrementa el contador de programa
	 * al indicado si la cima es 1.
	 * @param CPU El CPU a cambiar su contador de programa.
	 * @throws InstructionExecutionException si no hay suficientes elementos en la pila o
	 * hay error en el cambio del PC.
	 */
	@Override
	protected void executeBranch(CPU cpu) throws InstructionExecutionException {
		if(cpu.getNumElem() > 0 ){
			if(cpu.pop() == 1){
				if (!cpu.addPC(this.number))
					 throw new InstructionExecutionException
					 	("Error ejecutando " + this.toString() + ": PC fuera de rango");
			}
			else
				cpu.increaseProgramCounter();
		}
		else 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() + ": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");
		}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if(s.length == 2 && s[0].equalsIgnoreCase("rbt") && Constants.isNumber(s[1]))
			return new RBt(Integer.parseInt(s[1]));
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion RBt en forma de cadena.
	 */
	@Override
	public String toString() {
		return "RBT " + this.number;
	}
}