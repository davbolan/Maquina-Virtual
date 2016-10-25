package tp.pr3.mv.commands;

import tp.pr3.mv.Constants;
import tp.pr3.mv.commands.exceptionCom.WrongCommandFormatException;

/**
 * Clase que implementa el comando push, que permite apilar un elemento si lo necesitamos
 * durante la ejecucion para corregir la pila.
 * @author Lidia Flores, David Bolanios
 */
public class Push extends CommandInterpreter{
	private int number;

	/**
	 * Constructora por defecto
	 */
	public Push(){
		super();
	}
	
	/**
	 * Crea un comando Push con el numero a apilar.
	 * @param number El numero a apilar.
	 */
	public Push(int number) {
		super();
		this.number = number;
	}
	
	/**
	 * Ejecuta el comando Push, es decir, apila un elemento en la pila.
	 */
	@Override
	public void executeCommand() {
		cpu.push(this.number);
	}

	/**
	 * Parsea el comando Push.
	 * @throws WrongCommandFormatException si hay error en el parseo.
	 */
	@Override
	public CommandInterpreter parse(String[] s) throws WrongCommandFormatException {
		if(s.length == 2 && s[0].equalsIgnoreCase("push") && Constants.isNumber(s[1]))
			return new Push(Integer.parseInt(s[1]));
		else
			throw new WrongCommandFormatException();
	}

}
