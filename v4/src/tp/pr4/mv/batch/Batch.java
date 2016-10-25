package tp.pr4.mv.batch;

import tp.pr4.mv.Controller;
import tp.pr4.mv.observables.CPUObserver;

public class Batch implements CPUObserver{
		
	private Controller controller;
	
	public Batch(Controller controller){
		this.controller = controller;
	}
	
	public void start(){
		controller.start();
		controller.executeRunCommand();		
		controller.executeQuitCommand();
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
}
