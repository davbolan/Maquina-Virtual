package tp.pr2.mv.commands;


import tp.pr2.mv.instruction.Instruction;

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
	 * Ejecuta el comando Step N, es decir, ejecuta N veces el comando Step si la
	 * ejecucion no esta abortada.
	 */
	@Override
	public boolean executeCommand(){
		boolean ok = true;
		int i = 0;
		
		if(this.position >= 0) {
			while(i < this.position && ok && !cpu.abortComputation()){
				ok = cpu.step();
				if(ok){
					CommandInterpreter.printStateMachine();
					i++;
				}
			}
		}
		return ok;
	}

	/**
	 * Parsea el comando Step N
	 */
	@Override
	public CommandInterpreter parse(String[] s) {
		if (s.length==2 && s[0].equalsIgnoreCase("step") && Instruction.isNumber(s[1])) 
			return new Steps(Integer.parseInt(s[1]));
		else return null;
	}
}
