package tp.pr4.mv;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr4.mv.gui.MainWindow;
import tp.pr4.mv.batch.Batch;
import tp.pr4.mv.console.Console;
import tp.pr4.mv.executionMode.MVSystem;


/**
 * Clase con los metodos que ejecutan la CPU segun el modo.
 * @author Lidia Flores Tuesta, David Bolaños Calderon
 */
public class CPUExecution {
	private ProgramMV programMV;
	
	/**
	 * Constructora por defecto.
	 */
	public CPUExecution(){	
	}
	
	/**
	 * Constructora con el programa a ejecutar.
	 * @param programMV El programa a cargar
	 */
	public CPUExecution(ProgramMV programMV){
		this.programMV = programMV;	
	}
	
	
	/**
	 * Ejecuta el programa en modo interactivo BATCH.
	 */
	public void executeBatchMode() {	
		
		CPU cpu = new CPU();
		cpu.loadProgram(programMV);
		
		Controller controller = new Controller(cpu);
		Batch batch = new Batch(controller);
		
		cpu.addCpuObserver(batch);
		
		openInOut();
		batch.start();
	}

	/**
	 * Ejecuta el programa en modo interactivo INTERACTIVO.
	 */
	public void executeInteractiveMode() {
		
		CPU cpu = new CPU();
		cpu.loadProgram(programMV);
		
		Controller controller = new Controller(cpu);
		Console console = new Console(controller);
		
		cpu.addCpuObserver(console);
		cpu.addMemoryObserver(console);
		cpu.addProgramObserver(console);
		cpu.addStackObserver(console);
	
		openInOut();
		console.start();
	}

	public void executeWindowMode(){
		
		CPU cpu = new CPU();
		cpu.loadProgram(programMV);
		
		MainWindow mw = new MainWindow(cpu);
		
		openInOut();
		mw.startMv();
	}
	

	private void openInOut(){
		
		try{
			MVSystem.open();
		}	
		catch(FileNotFoundException fnfe){
			ErrorMessages.errorFileNotFound(fnfe.getMessage());
			System.exit(2);
		}	
		catch(IOException ioe){
			ErrorMessages.errorCreatingFile(ioe.getMessage());
			System.exit(2);
		}
	}
}

