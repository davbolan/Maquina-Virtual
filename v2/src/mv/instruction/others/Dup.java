package tp.pr2.mv.instruction.others;


import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Dup extends RestSeq {

	public Dup() {
		super();
	}

	

	 /**
	  * Duplica el valor almacenado en la cima de pila
	  * @return true si en la pila hay algun numero en la pila, false en caso contrario.
	  */
	@Override
	protected boolean executeAux(CPU cpu)  {
		boolean exito = false;
		 if(cpu.getNumElem() > 0){
			int n1 = cpu.pop();
			 cpu.push(n1);
			 cpu.push(n1);
			 exito = true;
		}
		
		 return exito;
	}

	/**
	 * Parsea la instruccion segun el String pasado por parametro.
	 */
	@Override
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("dup")) 
			return new Dup();
		else return null;
	}



	@Override
	public String toString() {
		return "DUP";
	}

}
