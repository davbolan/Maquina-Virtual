package tp.pr2.mv.commands;

import tp.pr2.mv.CPU;

/**
 * Clase abstracta que representa los distintos comandos que podemos
 * ejecutar sobre la máquina virtual.
 * @author Lidia Flores, David Bolanios
 */
public abstract class CommandInterpreter {
	// checks if the QUIT instruction is executed
	protected boolean isFinished;
	protected static CPU cpu;
	
	/**
	 * Constructora por defecto.
	 */
	public CommandInterpreter(){
		this.isFinished = false;
	}
	
	/**
	 * Inicializa el valor del atributo cpu una vez que la CPU dispone del programa a
	 * ejecutar.
	 * @param cpu El CPU para inicializar.
	 */
	public static void configureCommandInterpreter(CPU cpu){
		CommandInterpreter.cpu = cpu;
	}

	/**
	 * Este metodo es implementado por sus distintas subclases de acuerdo con el comando 
	 * que se desee ejecutar.
	 * @return true si ha tenido exito la ejecucion. False en caso contrario.
	 */
	public abstract boolean executeCommand();
	
	/**
	 * Este metodo es implementado por sus distintas subclases de acuerdo con el comando 
	 * que se desee parsear.
	 * @return El comando correspondiente.
	 */
	public abstract CommandInterpreter parse(String[] s);
	
	/**
	 * Comprueba si la ejecucion del programa ha finaliado porque se ha ejecutado HALT.
	 * @return true si la ejecucion termina, false en caso contrario.
	 */
	public boolean isFinished(){
		return this.isFinished;
	}
	
	/**
	  * Muestra el estado de la memoria y de la pila de operandos.
	  */
	public static void printStateMachine(){
		System.out.println(CommandInterpreter.cpu.toString());
	}
	
}
