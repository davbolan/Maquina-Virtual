package tp.pr3.mv.commands;


import tp.pr3.mv.Constants;
import tp.pr3.mv.commands.exceptionCom.CommandExecutionException;
import tp.pr3.mv.commands.exceptionCom.WrongCommandFormatException;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;

/**
 * Clase que implementa el comando Step N, que se encarga de ejecutar las siguientes N instrucciones.
 * @author Lidia Flores, David Bolanios
 */
public class Steps extends Step{
	private int position;
	
	/**
	 * Constructora por defecto.
	 */
	public Steps(){
		super();
	}
	
	/**
	 * Constructora que inicializa el numero de instrucciones a ejecutar.
	 * @param position Numero de instrucciones a ejecutar.
	 */
	public Steps(int position){
		super();
		this.position = position;
	}
	
	/**
	 * Ejecuta el comando Step N, es decir, ejecuta N veces el comando Step mientras la
	 * ejecucion no se aborte.
	  * @throws CommandExecutionException si la CPU esta abortada. 
	 * @throws InstructionExecutionException si hay error en la ejecucion de alguna instruccion.
	 */
	@Override
	public  void executeCommand() throws CommandExecutionException, InstructionExecutionException{
		int i = 0;
		
		if(this.position < 0) 
			throw new CommandExecutionException("Error en la ejecucion del comando Steps");
		
		try{
			while(i < this.position && !cpu.abortComputation()){
				super.executeCommand();
				i++;
			}
		}
		catch(CommandExecutionException exc){
			throw new CommandExecutionException("Error en la ejecucion del comando Steps");
		}
		
	}

	/**
	 * Parsea el comando Step N.
	 * @throws WrongCommandFormatException si hay error en el parseo.
	 */
	@Override
	public CommandInterpreter parse(String[] s) throws WrongCommandFormatException {
		if (s.length==2 && s[0].equalsIgnoreCase("step") && Constants.isNumber(s[1]) && Integer.parseInt(s[1]) >= 0 ) 
			return new Steps(Integer.parseInt(s[1]));
		else throw new WrongCommandFormatException();
	}
}
