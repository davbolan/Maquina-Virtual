package tp.pr3.mv;

import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.arithmetics.Add;
import tp.pr3.mv.instruction.arithmetics.Div;
import tp.pr3.mv.instruction.arithmetics.Mult;
import tp.pr3.mv.instruction.arithmetics.Sub;
import tp.pr3.mv.instruction.booleans.And;
import tp.pr3.mv.instruction.booleans.Not;
import tp.pr3.mv.instruction.booleans.Or;
import tp.pr3.mv.instruction.branch.Bf;
import tp.pr3.mv.instruction.branch.Bt;
import tp.pr3.mv.instruction.branch.Jump;
import tp.pr3.mv.instruction.branch.JumpInd;
import tp.pr3.mv.instruction.branch.RBf;
import tp.pr3.mv.instruction.branch.RBt;
import tp.pr3.mv.instruction.branch.RJump;
import tp.pr3.mv.instruction.compare.Eq;
import tp.pr3.mv.instruction.compare.Gt;
import tp.pr3.mv.instruction.compare.Le;
import tp.pr3.mv.instruction.compare.Lt;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;
import tp.pr3.mv.instruction.others.Dup;
import tp.pr3.mv.instruction.others.Flip;
import tp.pr3.mv.instruction.others.Halt;
import tp.pr3.mv.instruction.others.In;
import tp.pr3.mv.instruction.others.Load;
import tp.pr3.mv.instruction.others.LoadInd;
import tp.pr3.mv.instruction.others.Neg;
import tp.pr3.mv.instruction.others.Out;
import tp.pr3.mv.instruction.others.Pop;
import tp.pr3.mv.instruction.others.Push;
import tp.pr3.mv.instruction.others.Store;
import tp.pr3.mv.instruction.others.StoreInd;


/**
 * Clase encargada de crear una instruccion a partir de la linea ejecutada por el usuario o leida del fichero Asm.
 * @author Lidia Flores, David Bolanios
 */
public class InstructionParser {
	
	/**
	 * Conjunto de instrucciones posibles que se pueden ejecutar en la CPU.
	 */
	private static Instruction instructionSet[] = {
		 new Add(),
		 new Sub(), 
		 new Mult(), 
		 new Div(),		 
		 new And(),
		 new Not(),
		 new Or(),		 
		 new Bf(),
		 new Bt(),
		 new Jump(),
		 new JumpInd(),
		 new RBf(),
		 new RBt(),
		 new RJump(),		 
		 new Eq(),
		 new Gt(),
		 new Le(),
		 new Lt(),		 
		 new Dup(),
		 new Flip(),
		 new Halt(),
		 new In(),
		 new Load(),
		 new LoadInd(),
		 new Neg(),
		 new Out(),
		 new Pop(),
		 new Push(),
		 new Store(),	 
		 new StoreInd(),
		};

	/**
	 * Parsea la linea pasada por parametro y devuelve la instruccion correspondiente.
	 * Si no existe la instruccion,se lanza una excepcion.
	 * @param line Linea a parsear.
	 * @return instruction Instruccion para poder ejecutarse.
	 * @throws WrongInstructionFormatException en caso de no encontrarse
	 * la instruccion correspondiente.
	 */
	public static Instruction instructionParser(String line) throws WrongInstructionFormatException{
		int i = 0;
		Instruction instruction = null;
		boolean stop = false;
		while (i < InstructionParser.instructionSet.length && !stop){
			try{
				instruction = InstructionParser.instructionSet[i].parse(line.split(" "));					
				stop = true;
			}
			catch(WrongInstructionFormatException exeWF){}
				
			i++;
		}
		
		if(!stop)
			throw new WrongInstructionFormatException();
		
		return instruction;
	
		}
}
