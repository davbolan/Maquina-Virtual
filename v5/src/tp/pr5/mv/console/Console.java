package tp.pr5.mv.console;

import tp.pr5.mv.Constants;
import tp.pr5.mv.Controller;
import tp.pr5.mv.commands.exceptionCom.WrongCommandFormatException;
import tp.pr5.mv.observables.CPUObserver;
import tp.pr5.mv.observables.MemoryObserver;
import tp.pr5.mv.observables.ProgramObserver;
import tp.pr5.mv.observables.StackObserver;

/**
 * Clase que representa el modo CONSOLA, en la cuál el usuario va introduciendo los comandos que desee ejecutar
 * @author Lidia Flores, David Bolanios
 */
public class Console implements CPUObserver, ProgramObserver, MemoryObserver<Integer>, StackObserver<Integer>{

	private Controller controller;
	private boolean exit;
	
	/**
	 * Construye la consola a partir de un controlador
	 */
	public Console (Controller console){
		this.controller = console;
		this.exit = false;
	}
	
	/**
	 * Comienza la ejecución del modo consola. Se encarga de pedir, parsear y ejecutar los comandos del usuario
	 */
	public void start(){
		String line = null;
		
		controller.start();		
		while(!exit){		
			line = null;
			showPrompt();
			line = Constants.scan.nextLine();
			String[] tokens = line.split(" ");
		
			try{
				if(tokens.length == 1)
					firstCase(tokens);			
				else if(tokens.length == 2)						
					secondCase(tokens);			
				else if(tokens.length == 3)
					thirdCase(tokens);				
				else
					throw new WrongCommandFormatException();
			}
			catch(WrongCommandFormatException wcfe){
				raiseError("Comando '" + line +"' incorrecto");
			}
		}
	}

	/**
	 * Parsea el comando cuya longitud es 1.
	 * @param tokens Tokens de la liena leida
	 * @throws WrongCommandFormatException si el comando es incorrecto
	 */
	private void firstCase(String[] tokens) throws WrongCommandFormatException {
		switch(tokens[0].toLowerCase()){
			case "quit": 
				controller.executeQuitCommand(); break;
			case "run": 
				controller.executeRunCommand();  break;
			case "step":
				controller.executeStepCommand(); break;
			case "pop": 
				controller.executePopCommand();  break;
			default:
				throw new WrongCommandFormatException();		
		}
	}

	/**
	 * Parsea el comando cuya longitud es 2.
	 * @param tokens Tokens de la liena leida
	 * @throws WrongCommandFormatException si el comando es incorrecto
	 */
	private void secondCase(String[] tokens) throws WrongCommandFormatException {
		switch(tokens[0].toLowerCase()){
			case "push": 
				controller.executePushCommand(tokens[1]);  break;					
			case "step":
				controller.executeStepsCommand(tokens[1]); break;
			default:
				throw new WrongCommandFormatException();
		}
	}

	/**
	 * Parsea el comando cuya longitud es 3.
	 * @param tokens Tokens de la liena leida
	 * @throws WrongCommandFormatException si el comando es incorrecto
	 */
	private void thirdCase(String[] tokens) throws WrongCommandFormatException {
		switch(tokens[0].toLowerCase()){
		case "write":
			controller.executeWriteCommand(tokens[1], tokens[2]); break;
		default:
			throw new WrongCommandFormatException();
		}
	}
	
	/**
	 * Muestra el prompt
	 */
	private void showPrompt() {
		System.out.print("> ");
	}
	
	/**
	 * Muestra el error producido
	 */
	@Override
	public void raiseError(String message) {
		System.err.println(message);	
	}

	/**
	 * Indica que queremos salir
	 */
	@Override
	public void requestQuit() {
		this.exit = true;
	}

	/**
	 * Muestra el programa inicial
	 */
	@Override
	public void initProgram(String set) {	
		System.out.println(set);
	}

	/**
	 * Muestra el estado de la máquina
	 */
	@Override
	public void showCPUState(String cpuState) {
		System.out.println(cpuState);
	}

	/**
	 * Muestra que esta comenzando la ejecución de una isntución
	 */
	@Override
	public void instructionStarting(String msg) {
		System.out.println("Comienza la ejecucion de " + msg);	
	}

	@Override
	public void programEnd(boolean end) {
	}
	
	@Override
	public void initProgramInstructions(String set) {		
	}
	
	@Override
	public void newElem(int number) {	
	}

	@Override
	public void quitElem() {	
	}

	@Override
	public void onWrite(int index, Integer value) {
	}

	@Override
	public void resetMemory() {
	}

	@Override
	public void clearStack() {
	}

	@Override
	public void machineStopped() {
	}

	@Override
	public void machineStarted() {
	}
	
	@Override
	public void onReset() {
	}
}


