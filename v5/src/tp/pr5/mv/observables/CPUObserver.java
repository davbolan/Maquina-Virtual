package tp.pr5.mv.observables;

/**
 * Interfaz con los métodos que deben implementar las visas que observen a la CPU
 * @author Lidia Flores, David Bolanios
 */
public interface CPUObserver {

	public void raiseError(String message);
	
	public void requestQuit();

	public void programEnd(boolean end);

	public void showCPUState(String string);

	public void instructionStarting(String msg);

	public void machineStopped();
	
	public void machineStarted();
	
	public void onReset();
}
