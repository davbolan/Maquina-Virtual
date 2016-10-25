package tp.pr2.mv.instruction.others;


import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Flip extends RestSeq {

	public Flip() {
		super();
	}

	
	
	 /**
	  * Intercambia el valor almacenado en la cima de pila con la subcima
	  * @return true si en la pila hay algun numero en la pila, false en caso contrario.
	  */
	@Override
		 public boolean executeAux(CPU cpu) {
			 boolean exito = false;
			 if(cpu.getNumElem() > 1){
				 int cima = cpu.pop();
				 int subcima = cpu.pop();
				 cpu.push(cima);
				 cpu.push(subcima);
				 exito = true;
			 }
			 
			 return exito;
		}
	

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("flip")) 
			return new Flip();
		else return null;
	}


	@Override
	public String toString() {
		return "FLIP";
	}


}
