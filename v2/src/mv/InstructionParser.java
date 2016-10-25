package tp.pr2.mv;

import tp.pr2.mv.instruction.Instruction;
import tp.pr2.mv.instruction.arithmetics.*;
import tp.pr2.mv.instruction.booleans.*;
import tp.pr2.mv.instruction.branch.*;
import tp.pr2.mv.instruction.compare.*;
import tp.pr2.mv.instruction.others.*;


/**
 * Clase encargada de crear una instruccion a partir de la linea ejecutada por el usuario.
 * @author Lidia Flores, David Bolanios
 */
public class InstructionParser {
	public final String lineSeparator = System.getProperty("line.separator");
	private static String line1 = "";
	
	
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
		 new Eq(),
		 new Gt(),
		 new Le(),
		 new Lt(),
		 new Dup(),
		 new Flip(),
		 new Halt(),
		 new Load(),
		 new Neg(),
		 new Out(),
		 new Pop(),
		 new Push(),
		 new Store()		
		};

	/**
	 * Parsea la linea pasada por parametro y devuelve la instruccion correspondiente.
	 * Si no existe la instruccion, se devuelve null.
	 * @param line Linea a parsear.
	 * @return instruction Instruccion para poder ejecutarse.
	 */
	public static Instruction instructionParser(String line){
		int i = 0;
		Instruction instruction = null;
		boolean stop=false;
			while (i< InstructionParser.instructionSet.length && !stop){
				 instruction = InstructionParser.instructionSet[i].parse(line.split(" "));
				 stop = (instruction !=null);
				 i++;
			}
		return instruction;
	
		}
	
	
	/**
	 * Metodo encargado de mostrar el mensaje de la instruccion que se va a ejecutar.
	 * @return El mensaje
	 */
	@Override
	public String toString(){	
		return "Comienza la ejecución de " + line1;		
	}
}
