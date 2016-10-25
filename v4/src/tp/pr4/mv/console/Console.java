package tp.pr4.mv.console;

import java.util.ArrayList;

import tp.pr4.mv.Constants;
import tp.pr4.mv.Controller;
import tp.pr4.mv.commands.exceptionCom.WrongCommandFormatException;
import tp.pr4.mv.observables.*;

public class Console implements CPUObserver, ProgramObserver, MemoryObserver, StackObserver<Integer>{

	private Controller console;
	private boolean exit;
	
	public Console (Controller console){
		this.console = console;
		this.exit = false;
	}
	
	public void start(){
		String line = null;
		
		console.start();		
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

	private void firstCase(String[] tokens) throws WrongCommandFormatException {
		switch(tokens[0].toLowerCase()){
			case "quit": 
				console.executeQuitCommand(); break;
			case "run": 
				console.executeRunCommand();  break;
			case "step":
				console.executeStepCommand(); break;
			case "pop": 
				console.executePopCommand();  break;
			default:
				throw new WrongCommandFormatException();		
		}
	}

	private void secondCase(String[] tokens) throws WrongCommandFormatException {
		switch(tokens[0].toLowerCase()){
			case "push": 
				console.executePushCommand(tokens[1]);  break;					
			case "step":
				console.executeStepsCommand(tokens[1]); break;
			default:
				throw new WrongCommandFormatException();
		}
	}

	private void thirdCase(String[] tokens) throws WrongCommandFormatException {
		switch(tokens[0].toLowerCase()){
		case "write":
			console.executeWriteCommand(tokens[1], tokens[2]); break;
		default:
			throw new WrongCommandFormatException();
		}
	}
	
	private void showPrompt() {
		System.out.print("> ");
	}
	
	
	@Override
	public void memoryUpdate(Object[] memory) {
	}

	@Override
	public void initProgramInstructions(String set) {
		
	}

	@Override
	public void pcUpdate(int pc) {
	}

	@Override
	public void raiseError(String message) {
		System.err.println(message);
		
	}

	@Override
	public void requestQuit() {
		this.exit = true;
	}


	@Override
	public void programEnd(boolean end) {
	}

	@Override
	public void initProgram(String set) {	
		System.out.println(set);
	}


	@Override
	public void initStack(ArrayList<Integer> stack) {
	}


	@Override
	public void showCPUState(String cpuState) {
		System.out.println(cpuState);
	}

	@Override
	public void instructionStarting(String msg) {
		System.out.println("Comienza la ejecucion de " + msg);	
	}
}


