package tp.pr2.mv.instruction.others;


import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Out extends RestSeq {

	public Out() {
		super();
	}

	 /**
	  * Muestra el caracter en ASCII asociado a la cima de operandos.
	  * En caso de que sea negativo lo hace de su valor absoluto.
	  * @return true si se existe un operando en la pila, false en caso contrario.
	  */
	@Override
	 public boolean executeAux(CPU cpu) {
		 	boolean exito = false;
		 	if(cpu.getNumElem() > 0){
		 		System.out.println(Character.toChars(Math.abs(cpu.pop())));
		 		exito = true;
		 	}
		return exito;
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("out")) 
			return new Out();
		else return null;
	}

	@Override
	public String toString() {
		return "OUT";
	}


}
