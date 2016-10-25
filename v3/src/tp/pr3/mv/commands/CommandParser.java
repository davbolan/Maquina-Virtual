package tp.pr3.mv.commands;

import tp.pr3.mv.commands.exceptionCom.WrongCommandFormatException;

/**
 * Clase encargada de crear un commando a partir de la linea ejecutada por el usuario.
 * @author Lidia Flores, David Bolanios
 */
public class CommandParser {

	/**
	 * Conjunto de comandos posible que puede ordenar el usuario.
	 */
	private static CommandInterpreter commandSet[] = { 
		new Run(), 
		new Steps(),
		new Step(), 
		new Quit(), 
		new Pop(), 
		new Push(), 
		new Write() 
	};
	

	/**
	 * Parsea la linea pasada por parametro y devuelve el comando
	 * correspondiente. Si no existe el comando, se devuelve null.
	 * @param line Linea a parsear.
	 * @return command Comando para poder ejecutarse.
	 * @throws WrongCommandFormatException en caso de no encontrarse
	 * el comando correspondiente.
	 */
	public static CommandInterpreter readCommandInstruction(String line) throws WrongCommandFormatException {
		int i = 0;
		CommandInterpreter command = null;
		boolean stop = false;

		while (i < CommandParser.commandSet.length && !stop) {
			try {
				command = CommandParser.commandSet[i].parse(line.split(" "));
				stop = true;
			}
			catch (WrongCommandFormatException exeWF) {}
			i++;
		}
		
		if(!stop) 
			throw new WrongCommandFormatException ("Comando '" + line +"' incorrecto");

		return command;
	}
}
