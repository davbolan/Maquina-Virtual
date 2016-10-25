package tp.pr5.mv;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingUtilities;

import tp.pr5.mv.batch.Batch;
import tp.pr5.mv.console.Console;
import tp.pr5.mv.executionMode.MVSystem;
import tp.pr5.mv.gui.MainWindow;


/**
 * Clase con los metodos que ejecutan la CPU segun el modo.
 * @author Lidia Flores Tuesta, David Bola�os Calderon
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
	 * Ejecuta el programa en modo BATCH.
	 */
	public void executeBatchMode() {	
		
		CPU cpu = new CPU(10);
		cpu.loadProgram(programMV);
		
		Controller controller = new Controller(cpu);
		Batch batch = new Batch(controller);
		
		cpu.addCpuObserver(batch);
		
		openInOut();
		batch.start();
	}

	/**
	 * Ejecuta el programa en modo INTERACTIVO.
	 */
	public void executeInteractiveMode() {
		
		CPU cpu = new CPU(20);
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

	/**
	 * Ejecuta el programa en modo WINDOW
	 */
	public void executeWindowMode(){
		
		final CPU cpu = new CPU(200);
		cpu.loadProgram(programMV);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow mw = new MainWindow(cpu);
				openInOut();
				mw.startMv();
			}
		});

	}
	
	/**
	 * Abre la entrada y la salida. Si no se abre correctamente se sale del programa
	 */
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

