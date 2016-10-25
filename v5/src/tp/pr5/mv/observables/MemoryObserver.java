package tp.pr5.mv.observables;

/**
 * Interfaz con los métodos que deben implementar las visas que observen a la memoria
 * @author Lidia Flores, David Bolanios
 */
public interface MemoryObserver <T>{
	
	public void onWrite(int index, T value);
	
	public void resetMemory();

}
