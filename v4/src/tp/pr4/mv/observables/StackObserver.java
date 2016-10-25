package tp.pr4.mv.observables;

import java.util.ArrayList;

public interface StackObserver <T> {

	public void initStack(ArrayList<T> stack);
	
	public void raiseError(String message);

}
