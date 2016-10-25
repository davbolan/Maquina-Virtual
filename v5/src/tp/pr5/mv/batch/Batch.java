package tp.pr5.mv.batch;

import tp.pr5.mv.Controller;
import tp.pr5.mv.observables.CPUObserver;

/**
 * Clase que representa el modo BATCH, en la cuál se ejecuta el programa de principio a fin
 * @author Lidia Flores, David Bolanios
 */
public class Batch implements CPUObserver{
		
	private Controller controller;
	
	/**
	 * Construye el batch a partir de un controlador
	 */
	public Batch(Controller controller){
		this.controller = controller;
	}
	
	/**
	 * Comienza la ejecución del modo consola. Ejecuta el comando run el primer lugar y luego se sale
	 */
	public void start(){
		controller.start();
		controller.executeRunCommand();		
		controller.executeQuitCommand();
	}

	/**
	 * Muestra el error producido
	 */
	@Override
	public void raiseError(String message) {
		System.err.println(message);
	}

	@Override
	public void requestQuit() {
	}

	@Override
	public void programEnd(boolean end) {
	}

	@Override
	public void showCPUState(String string) {
	}

	@Override
	public void instructionStarting(String msg) {
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
