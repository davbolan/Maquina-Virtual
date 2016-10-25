package tp.pr4.mv.observables;

public interface CPUObserver {

	public void pcUpdate(int pc);
	
	public void raiseError(String message);
	
	public void requestQuit();

	public void programEnd(boolean end);

	public void showCPUState(String string);

	public void instructionStarting(String msg);
	
}
