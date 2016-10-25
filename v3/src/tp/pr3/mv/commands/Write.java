package tp.pr3.mv.commands;

import tp.pr3.mv.Constants;
import tp.pr3.mv.commands.exceptionCom.CommandExecutionException;
import tp.pr3.mv.commands.exceptionCom.WrongCommandFormatException;

/**
 * Clase que implementa el comando Write, que permite almacenar un valor en una posicion indicada
 * durante la ejecucion.
 * @author Lidia Flores, David Bolanios
 */
public class Write extends CommandInterpreter{
	private int pos;
	private int value;
	
	/**
	 * Constructora por defecto.
	 */
	public Write(){
		
	}
	
	/**
	 * Constructora que crea un comando Write a partir de un elemento y una posicion.
	 * @param pos La posicion de memoria donde almacenar el elemento.
	 * @param value El valor a almacenar.
	 */
	public Write(int pos, int value){
		this.pos = pos;
		this.value = value;
	}
	
	/**
	 * Ejecuta el comando Write, es decir, almacena el valor "value" en la posicion "pos" de memoria.
	 * @return true si la ejecucion ha tenido exito. False si la pos es negativa.
	 * @throws CommandExecutionException Si la direccion de memoria es negativa.
	 */
	@Override
	public void executeCommand() throws CommandExecutionException {
		if(this.pos < 0)
			throw new CommandExecutionException("Error en la ejecucion del comando Write. Direccion negativa.");
			
		cpu.push(this.value); 
		cpu.store(this.pos);
	}

	/**
	 * Parsea el comando Write.
	 * @throws WrongCommandFormatException si hay error en el parseo.
	 */
	@Override
	public CommandInterpreter parse(String[] s) throws WrongCommandFormatException {
		if(s.length == 3 && s[0].equalsIgnoreCase("write") && Constants.isNumber(s[1]) && Constants.isNumber(s[2]))
			return new Write(Integer.parseInt(s[1]), Integer.parseInt(s[2]));	
		else throw new WrongCommandFormatException();
	}
}
