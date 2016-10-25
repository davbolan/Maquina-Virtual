package tp.pr3.mv.commands;

import tp.pr3.mv.commands.exceptionCom.CommandExecutionException;
import tp.pr3.mv.commands.exceptionCom.WrongCommandFormatException;

/**
 * Clase que implementa el comando Pop, que permite extraer un elemento si lo necesitamos
 * durante la ejecucion para corregir la pila.
 * @author Lidia Flores, David Bolanios
 */
public class Pop extends CommandInterpreter{

	/**
	 * Constructora por defecto
	 */
	public Pop(){
		super();
	}
	
	/**
	 * Ejecuta el comando Pop, es decir, extrae un elemento de la pila si hay algun elemento.
	 * @throws CommandExecutionException Si no hay elementos en la pila. 
	 */
	@Override
	public void executeCommand() throws CommandExecutionException {
		if(cpu.getNumElem() <= 0)
			throw new CommandExecutionException("Error en la ejecucion del comando POP");
		cpu.pop();
	}

	/**
	 * Parsea el comando Pop.
	 * @throws WrongCommandFormatException si hay error en el parseo.
	 */
	@Override
	public CommandInterpreter parse(String[] s) throws WrongCommandFormatException {
		if ((s.length==1 && s[0].equalsIgnoreCase("pop"))) 	
			return new Pop();
		else 	
			throw new WrongCommandFormatException("Error en la sintaxis de POP");
	}

}
