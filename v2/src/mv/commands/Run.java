package tp.pr2.mv.commands;

/**
 * Clase que implementa el comando Run, que se encarga de ejecutar todo el programa.
 * @author Lidia Flores, David Bolanios
 */
public class Run extends Step{
	
	/**
	 * Ejecuta el comando Run, es decir, todas las instrucciones del programa hasta que llegue
	 * al final, o haya un error en alguna ejecucion.
	 */
	@Override
	public boolean executeCommand() {
		boolean ok = true;
		cpu.resetCPU();
		while(ok && !this.isFinished && !cpu.abortComputation()){
			ok = cpu.step();
			if(ok)
				CommandInterpreter.printStateMachine();
		}
		
		if(ok){
			this.isFinished = true;
			cpu.exit();
		}
		
		return ok;
	}

	/**
	 * Parsea el comando Run.
	 */
	@Override
	public CommandInterpreter parse(String[] s) {
		if (s.length == 1 && s[0].equalsIgnoreCase("run")) 
			return new Run();
		else 
			return null;
	}
}
