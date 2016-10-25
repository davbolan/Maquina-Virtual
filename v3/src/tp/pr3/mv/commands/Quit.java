package tp.pr3.mv.commands;

import tp.pr3.mv.commands.exceptionCom.WrongCommandFormatException;

/**
 * Clase que implementa el comando Quit, que se encarga de salir de la aplicacion.
 * @author Lidia Flores, David Bolanios
 */
public class Quit extends CommandInterpreter {

	/**
	 * Ejecuta el comando Quit.
	 */
	@Override
	public void executeCommand() {
		cpu.exit();
	}

	/**
	 * Parsea el comando Quit.
	 * @throws WrongCommandFormatException si hay error en el parseo.
	 */
	@Override
	public CommandInterpreter parse(String[] s) throws WrongCommandFormatException {
		if (s.length==1 && s[0].equalsIgnoreCase("quit")) 
			return new Quit();
		else throw new WrongCommandFormatException();
	}

}
