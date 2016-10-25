package tp.pr5.mv;

import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.instruction.arithmetics.*;
import tp.pr5.mv.instruction.booleans.*;
import tp.pr5.mv.instruction.branch.*;
import tp.pr5.mv.instruction.compare.*;
import tp.pr5.mv.instruction.exceptionIns.WrongInstructionFormatException;
import tp.pr5.mv.instruction.others.*;


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
