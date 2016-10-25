package tp.pr5.mv.observables;

/**
 * Interfaz con los métodos que deben implementar las visas que observen a la pila
 * @author Lidia Flores, David Bolanios
 */
public interface StackObserver <T> {

	public void clearStack();
	
	public void raiseError(String message);

	public void newElem(int number);

	public void quitElem();
}
