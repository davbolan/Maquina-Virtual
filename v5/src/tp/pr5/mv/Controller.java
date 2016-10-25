package tp.pr5.mv;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr5.mv.commands.exceptionCom.CommandExecutionException;
import tp.pr5.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr5.mv.observables.CPUObserver;
import tp.pr5.mv.observables.InOutObserver;
import tp.pr5.mv.observables.MemoryObserver;
import tp.pr5.mv.observables.ProgramObserver;
import tp.pr5.mv.observables.StackObserver;
import tp.pr5.mv.programLoader.IncorrectProgramException;

/**
 * Clase encargada de mediar entre las vistas y el CPU
 * @author Lidia Flores, David Bolanios
 */
public class Controller{
	
	private CPU cpu;
	
	public Controller(CPU cpu){
		this.cpu = cpu;
	}
	
	/**
	 * Método que se encarga de comenzar la ejecución de la CPU
	 */
	public void start(){
		cpu.requestStart();
	}
	
	/**
	 * Registra un observador de tipo CPUObserver
	 */
	public void registerCPUObserver(CPUObserver cpuObserver){
		cpu.addCpuObserver(cpuObserver);
	}
	
	/**
	 * Registra un observador de tipo ProgramObserver
	 */
	public void registerProgramObserver(ProgramObserver programObserver){
		cpu.addProgramObserver(programObserver);
	}
	
	/**
	 * Registra un observador de tipo StackObserver
	 */	
	public void registerStackObserver(StackObserver<Integer> stackObserver){
		cpu.addStackObserver(stackObserver);
	}
	
	/**
	 * Registra un observador de tipo MemoryObserver
	 */	
	public void registerMemoryObserver(MemoryObserver<Integer> memoryObserver){
		cpu.addMemoryObserver(memoryObserver);
	}
	
	/**
	 * Registra un observador de tipo InOutObserver
	 */	
	public void registerInOutObverser(InOutObserver inOutObserver){
		cpu.addInOutObserver(inOutObserver);
		}
	
	/**
	 * Comprueba que hay suficientes elementos en la pila de la cpu y ejecuta el comando POP
	 */
	public void executePopCommand(){
		try{	
			if(cpu.getNumElem() == 0)
				throw new CommandExecutionException("Error ejecutando el comando POP: Pila vacia.");
			cpu.pop();		
		}
		catch(CommandExecutionException | InstructionExecutionException e){
			cpu.requestError(e.getMessage());
		}
	}
	
	/**
	 * Comprueba que el string introducido es un número y ejecuta el comando PUSH
	 */
	public void executePushCommand(String number){
		try{
			if(!Constants.isNumber(number))
				throw new CommandExecutionException("Error ejecutando el comando PUSH: '" + number + "' no es un numero.");
			cpu.push(Integer.parseInt(number));
		}
		catch(CommandExecutionException e ){
			cpu.requestError(e.getMessage());
		}
	}
	
	/**
	 * Comprueba que number sea un número y que pos sea un número positivo y ejecuta el comando WRITE
	 */
	public void executeWriteCommand(String pos, String number){
		try{	
			String writeError = "Error ejecutando el comando WRITE: '";
			if(!Constants.isNumber(number))
				throw new CommandExecutionException(writeError + number + "' no es un numero.");
		
			if(!Constants.isNumber(pos))
				throw new CommandExecutionException(writeError + pos + "' no es un numero.");
			
			if(Integer.parseInt(pos) < 0)
				throw new CommandExecutionException(writeError + pos + "' debe ser positivo.");

			cpu.push(Integer.parseInt(number));
			cpu.store(Integer.parseInt(pos));
		}
		catch(CommandExecutionException | InstructionExecutionException e ){
			cpu.requestError(e.getMessage());
		}
	}
	
	/**
	 * Ejecuta el comando QUIT
	 */
	public void executeQuitCommand(){
		cpu.exitCPU();	
	}
	
	/**
	 * Ejecuta el comando RUN
	 */	
	public void executeRunCommand(){
		try{
			cpu.run();
		}
		catch(InstructionExecutionException e ){
			cpu.requestError(e.getMessage());
		}	
	}
	
	/**
	 * Comprueba que number sea un número y ejecuta el comando STEPS
	 */
	public void executeStepsCommand(String number){
		try{
			String stepsError = "Error ejecutando el comando STEPS: '";
			if(!Constants.isNumber(number))
				throw new CommandExecutionException(stepsError + number + "' no es un numero.");
			
			for(int i = 0; i < Integer.parseInt(number); i++)
				cpu.step();					
		}
		catch(InstructionExecutionException | CommandExecutionException e){
			cpu.requestError(e.getMessage());
		}	
	}
	
	/**
	 * Ejecuta el comando STEP
	 */	
	public void executeStepCommand(){
		try{
			cpu.step();
		}
		catch(InstructionExecutionException e ){
			cpu.requestError(e.getMessage());
		}	
	}

	/**
	 * Ejecuta el comando PAUSE/STOP
	 */	
	public void executePauseCommand(){
		cpu.stop();
	}
	
	/**
	 * Ejecuta el comando RESET
	 */	
	public void executeResetCommand(){
		cpu.reset();
	}
	
	/**
	 * Ejecuta el comando CHANGE DELAY
	 */	
	public void changeDelay(int delay){
		cpu.changeDelay(delay);
	}
	
	/**
	 * Cambia el programa por el que se encuentra dentro del archivo de la ruta path
	 */	
	public void changeProgram(String path){
		try {
			cpu.loadNewProgram(path);
		} catch (FileNotFoundException e) {
			cpu.requestError("No se ha encontrado el archivo especificado");
		} catch (IOException e) {
			cpu.requestError("Error de la lectura del archivo");
		} catch (IncorrectProgramException e) {
			cpu.requestError(e.getMessage());
		}
	}
	
	/**
	 * Cambia el archivo de entrada por el que se encuentra dentro del archivo de la ruta path
	 */
	public void changeIn(String path){
		try {
			cpu.loadNewIn(path);
			cpu.reset();
		} catch (FileNotFoundException e) {
			cpu.requestError("No se ha encontrado el archivo especificado");
		}
	}
	
	/**
	 * Cambia el archivo de salida por el que se encuentra dentro del archivo de la ruta path
	 */
	public void changeOut(String path){
		try {
			cpu.loadNewOut(path);
		} catch (IOException e) {
			cpu.requestError("No se ha encontrado el archivo especificado");
		}
	}
	
	/**
	 * Crea un nuevo programa a partir de la ruta, el codigo y la extension
	 */
	public void newFile(String path, String programa, String extension) {
		try {
			cpu.newFile(path, programa, extension);
		} catch (FileNotFoundException e) {
			cpu.requestError("No se ha encontrado el archivo especificado");
		} catch (IOException e) {
			cpu.requestError("Error de la lectura del archivo");
		} catch (IncorrectProgramException e) {
 			cpu.requestError(e.getMessage());
		}	
	}
}
