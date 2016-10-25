package tp.pr4.mv.observables;

public interface InOutObserver {

	public void inChanged(int pos);
	
	public void outChanged(char car);

	public void initTextIn(String readAll);
	
}
