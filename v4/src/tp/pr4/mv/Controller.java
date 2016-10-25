package tp.pr4.mv;

import tp.pr4.mv.commands.exceptionCom.CommandExecutionException;
import tp.pr4.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr4.mv.observables.*;

/**
 * Clase encargada de mediar entre las vistas y el CPU
 * @author Lidia Flores, David Bolanios
 */

public class Controller {
	
	private CPU cpu;
	
	public Controller(CPU cpu){
		this.cpu = cpu;
	}
	
	public void start(){
		cpu.requestStart();
	}
	
	public void registerCPUObserver(CPUObserver cpuObserver){
		cpu.addCpuObserver(cpuObserver);
	}
	
	public void registerProgramObserver(ProgramObserver programObserver){
		cpu.addProgramObserver(programObserver);
	}
		
	public void registerStackObserver(StackObserver<Integer> stackObserver){
		cpu.addStackObserver(stackObserver);
	}
	
	public void registerMemoryObserver(MemoryObserver memoryObserver){
		cpu.addMemoryObserver(memoryObserver);
	}
	
	public void registerInOutObverser(InOutObserver inOutObserver) {
		cpu.addInOutObserver(inOutObserver);
	}
	
	public void executePopCommand() {
		try{	
			if(cpu.getNumElem() == 0)
				throw new CommandExecutionException("Error ejecutando el comando POP: Pila vacia.");
			cpu.pop();		
		}
		catch(CommandExecutionException | InstructionExecutionException e){
			cpu.requestError(e.getMessage());
		}
	}
	
	public void executePushCommand(String number) {
		try{
			if(!Constants.isNumber(number))
				throw new CommandExecutionException("Error ejecutando el comando PUSH: '" + number + "' no es un numero.");
			cpu.push(Integer.parseInt(number));
		}
		catch(CommandExecutionException e ){
			cpu.requestError(e.getMessage());
		}
	}
	
	public void executeWriteCommand(String pos, String number) {
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
	
	public void executeQuitCommand() {
		cpu.exitCPU();	
	}
		
	public void executeRunCommand() {
		try{
			cpu.run();
		}
		catch(InstructionExecutionException e ){
			cpu.requestError(e.getMessage());
		}	
	}
	
	public void executeStepsCommand(String number) {
		try{
			String stepsError = "Error ejecutando el comando STEPS: '";
			if(!Constants.isNumber(number))
				throw new CommandExecutionException(stepsError + number + "' no es un numero.");
			
			if(!Constants.isNumber(number))
				throw new CommandExecutionException(stepsError + number + "' debe ser positivo.");
			
			for(int i = 0; i < Integer.parseInt(number); i++)
				cpu.step();					
		}
		catch(InstructionExecutionException | CommandExecutionException e){
			cpu.requestError(e.getMessage());
		}	
	}

	public void executeStepCommand() {
		try{
			cpu.step();
		}
		catch(InstructionExecutionException e ){
			cpu.requestError(e.getMessage());
		}	
	}
	
}
