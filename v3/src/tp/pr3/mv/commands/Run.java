package tp.pr3.mv.commands;

import tp.pr3.mv.commands.exceptionCom.CommandExecutionException;
import tp.pr3.mv.commands.exceptionCom.WrongCommandFormatException;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;

/**
 * Clase que implementa el comando Run, que se encarga de ejecutar todo el programa
 * de principio a fin. Se pausa si hay algun error de ejecucion en alguna instruccion.
 * Se sale si hay algun error en el PC.
 * @author Lidia Flores, David Bolanios
 */
public class Run extends Step{
	
	/**
	 * Ejecuta el comando Run, es decir, todas las instrucciones del programa hasta que llegue
	 * al final, o haya un error en alguna ejecucion.
	 * @throws CommandExecutionException Si falla alguna instruccion durante la ejecucion de run.
	 */
	@Override
	public void executeCommand() throws CommandExecutionException, InstructionExecutionException {
		cpu.resetCPU();
		try{
			while(!this.isFinished && !cpu.abortComputation())
				super.executeCommand();
			this.isFinished = true;
			cpu.exit();
		}
		catch(CommandExecutionException exc){
			throw new CommandExecutionException("Error en la ejecucion del comando Run");
		}
		
	}

	/**
	 * Parsea el comando Run.
	 * @throws WrongCommandFormatException si hay error en el parseo.
	 */
	@Override
	public CommandInterpreter parse(String[] s) throws WrongCommandFormatException {
		if (s.length == 1 && s[0].equalsIgnoreCase("run")) 
			return new Run();
		else 
			 throw new WrongCommandFormatException();
	}
}
