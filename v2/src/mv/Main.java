package tp.pr2.mv;

import java.util.Scanner;

import tp.pr2.mv.commands.CommandInterpreter;
import tp.pr2.mv.commands.ParseCommand;
import tp.pr2.mv.instruction.Instruction;

/**
 * Clase principal de la aplicacion.
 * @author  Lidia Flores, David Bolanios
 */
public class Main {
	
	public final static String lineSeparator = System.getProperty("line.separator");
	/** 
	 * Metodo principal de la aplicacion que crea la CPU a partir de una memoria y una pila
	 * y luego lo ejecuta.
	 * @param args 
	 */
	public static void main(String[] args) {
		 boolean end = false;
		 String line = "";
		 String promtp = "> ";
		 CommandInterpreter command;	 
		 ProgramMV program = readProgram();
		 Scanner scan = new Scanner(System.in); 
		 System.out.println(program.toString());
		 System.out.print(promtp); 								// Muestra el promt
		 line = scan.nextLine();								// Lee una instruccion
		 CPU cpu = new CPU();									// Crea la CPU...
		 cpu.loadProgram(program);								// ...y carga el programa
		 CommandInterpreter.configureCommandInterpreter(cpu);	// Pasa al interprete de comandos la CPU
			
		 while(!end){	
			 command = ParseCommand.readCommandInstruction(line);
			 if(command != null){					
				 if(command.executeCommand())
					 end = command.isFinished() || cpu.abortComputation();	 
				 else
					 System.out.println("Error en la ejecucion de la instruccion"); 
			 }
	
			 else
				 System.out.println("Comando incorrecto");
			 
			if(!end){				
				System.out.print(promtp);
				line = scan.nextLine();	 
			}	
		 }
		
		scan.close();
	}

	static private ProgramMV readProgram(){
		Instruction ins = null;
		ProgramMV programMV = new ProgramMV();
		Scanner scan = new Scanner(System.in);
		String line = "";
		System.out.print("Introduce el programa fuente" + lineSeparator + "> ");
		line = scan.nextLine();
		
		while(!line.equalsIgnoreCase("END")){
			ins = InstructionParser.instructionParser(line);
			if(ins != null)
				programMV.push(ins);
			else
				System.out.println("Instruccion incorrecta");
		
			System.out.print("> ");
			line = scan.nextLine();
		}
		
		//scan.close();
		return programMV;
	}
}

