package tp.pr5.mv.observables;

/**
 * Interfaz con los métodos que deben implementar las visas que observen la entrada y salida de la CPU
 * @author Lidia Flores, David Bolanios
 */
public interface InOutObserver {

	public void inChanged(int pos);
	
	public void outChanged(char car);

	public void initTextIn(String readAll);

	public void cleanOut();
	
}
