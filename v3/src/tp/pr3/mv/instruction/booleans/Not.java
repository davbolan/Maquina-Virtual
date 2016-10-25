package tp.pr3.mv.instruction.booleans;


import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.*;

/**
 * Clase que crea una instruccion de tipo Not, y sirve para realizar la operacion Not
 * sobrela cima de la pila de operandos.
 * @author Lidia Flores, David Bolanios
 */
public class Not extends BooleanCond {

	/**
	 * Constructora por defecto.
	 */
	public Not() {
		super();
	}

	/**
	 * Realiza la operacion NOT sobre la cima y lo apila en la pila.
	 * @throws InstructionExecutionException si no hay suficientes operandos en la pila
	 * o bien, la cima no es ni 0 ni 1.
	 */
	@Override
	protected void executeAux(CPU cpu) throws InstructionExecutionException { 
		
		if (cpu.getNumElem() > 0){
			int n1 = cpu.pop();
			if(n1 == 1){
				this.result = 0;	 
			}
			else if(n1 == 0){
				this.result = 1;
			}
			else{
				cpu.push(n1);
				throw new InstructionExecutionException
					("Error ejecutando " + this.toString() + ": la cima debe valer 0 o 1 (Valor:  " + n1 + ")");
			}
		 }
		else 
			throw new InstructionExecutionException
				("Error ejecutando " + this.toString() +": faltan operandos en la pila (hay " + cpu.getNumElem() + ")");
		;	
	}


	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 * @throws WrongInstructionFormatException si hay error en el parseo.
	 */
	@Override
	public Instruction parse(String[] s) throws WrongInstructionFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("not")) 
			return new Not();
		else throw new WrongInstructionFormatException();
	}

	/**
	 * Devuelve la instruccion Not en forma de cadena.
	 */
	@Override
	public String toString() {
		return "NOT";
	}


}
