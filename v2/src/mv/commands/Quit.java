package tp.pr2.mv.commands;

/**
 * Clase que implementa el comando Quit, que se encarga de salir de la aplicacion.
 * @author Lidia Flores, David Bolanios
 */
public class Quit extends CommandInterpreter {

	/**
	 * Ejecuta el comando Quit.
	 */
	@Override
	public boolean executeCommand() {
		cpu.exit();
		return true;
	}

	/**
	 * Parsea el comando Quit.
	 */
	@Override
	public CommandInterpreter parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("quit")) 
			return new Quit();
		else return null;
	}

}
