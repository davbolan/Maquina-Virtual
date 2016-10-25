package tp.pr2.mv.commands;



/**
 * Clase encargada de crear un commando a partir de la linea ejecutada por el usuario.
 * @author Lidia Flores, David Bolanios
 */
public class ParseCommand {
	
	
	/**
	 * Conjunto de comandos posible que puede ordenar el usuario.
	 */
	private static CommandInterpreter commandSet[] = {
		new Run(),
		new Steps(),
		new Step(),
		new Quit()
	};
	
	/**
	 * Parsea la linea pasada por parametro y devuelve el comando correspondiente.
	 * Si no existe el comando, se devuelve null.
	 * @param line Linea a parsear.
	 * @return command Comando para poder ejecutarse.
	 */
	public static CommandInterpreter readCommandInstruction(String line){
		int i=0;
		CommandInterpreter command = null;
		boolean stop = false;
		
		while (i< ParseCommand.commandSet.length && !stop){
			command = ParseCommand.commandSet[i].parse(line.split(" "));
			 if (command != null)
				 stop = true;
			 else 
				 i++;
			}
		
		return command;
		}
}
