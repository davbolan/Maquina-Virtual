package tp.pr3.mv.commands;

import tp.pr3.mv.commands.exceptionCom.CommandExecutionException;
import tp.pr3.mv.commands.exceptionCom.WrongCommandFormatException;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;

/**
 * Clase que implementa el comando Step, que se encarga de ejecutar la siguiente instruccion.
 * @author Lidia Flores, David Bolanios
 */
public class Step extends CommandInterpreter{

	/**
	 * Ejecuta el comando Step. Si la ejecucion no esta abortada, se ejecuta
	 * la siguiente instruccion. 
	 * @throws CommandExecutionException si la CPU esta abortada. 
	 * @throws InstructionExecutionException si hay error en la ejecucion de alguna instruccion.
	 */
	@Override
	public void executeCommand() throws CommandExecutionException, InstructionExecutionException {
		if(!cpu.abortComputation()){
			cpu.step();
			CommandInterpreter.printStateMachine();
		}
		else throw new CommandExecutionException("Error en la ejecucion del comando Step");
	}

	/**
	 * Parsea el comando Step.
	 * @throws WrongCommandFormatException si hay error en el parseo.
	 */
	@Override
	public CommandInterpreter parse(String[] s) throws WrongCommandFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("step")) 
			return new Step();
		else throw new WrongCommandFormatException();
	}	
}
