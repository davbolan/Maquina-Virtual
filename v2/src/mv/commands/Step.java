package tp.pr2.mv.commands;

/**
 * Clase que implementa el comando Step, que se encarga de ejecutar la siguiente instruccion.
 * @author Lidia Flores, David Bolanios
 */
public class Step extends CommandInterpreter{

	/**
	 * Ejecuta el comando Step. Si la ejecucion no esta abortada, se ejecuta
	 * la siguiente instruccion. 
	 */
	@Override
	public boolean executeCommand() {
		boolean exito = false;
		if(!cpu.abortComputation())
			exito = cpu.step();
		if(exito)
			CommandInterpreter.printStateMachine();
		return exito;
	}

	/**
	 * Parsea el comando Step.
	 */
	@Override
	public CommandInterpreter parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("step")) 
			return new Step();
		else return null;
	}	
}
