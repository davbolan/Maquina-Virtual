package tp.pr5.mv.observables;

/**
 * Interfaz con los métodos que deben implementar las visas que observen al programa
 * @author Lidia Flores, David Bolanios
 */
public interface ProgramObserver {

	public void initProgramInstructions(String set);

	public void initProgram(String set);
}
