package tp.pr4.mv;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr4.mv.observables.Observable;
import tp.pr4.mv.observables.StackObserver;

/**
 * Clase que es la pila de operandos de la maquina donde se apilaran los datos. <br> 
 * La pila se redimensiona segun la cantidad de datos que haya almacenados. 
 * @author Lidia Flores, David Bolanios
 */
public class OperandStack <T> extends Observable<StackObserver<T>>{
	private ArrayList<T> stack;
	
	/**
	 * Constructora por defecto. Crea una pila de tamaño 20. 
	 */
	public OperandStack(){
		stack = new ArrayList<>();
	}
	
	/**
	 * Devuelve el numero de elementos de la pila.
	 * @return Numero de elementos.
	 */
	
	public int getNumElem(){
		return stack.size();
	}
	
	/**
	 * Devuelve el estado de la pila de operandos.
	 * @return oper La cadena con el estado de la pila.
	 */
	public String toString(){
		String oper = "Pila de operandos:";
		
		if(getNumElem() == 0)
			oper += " <vacia>";
		else{
			for(int i = 0; i < getNumElem(); i++)
				oper += " " + stack.get(i);
		}
		return oper;
	}
	

	/**
	 * Apila el numero pasado por parametro.
	 * @param num numero a apilar.
	 */
	public void push(T num){ 	
		stack.add(num);	
	}	

	/**
	 * Extrae un elemento de la cima de la pila.
	 */
	public T pop(){
		int posCima = getNumElem()-1;
		return (T)stack.remove(posCima);
	}	

	/**
	 * Devuelve el valor de la cima de la pila.
	 * @return cima de la pila.
	*/
	public T cima(){
		return (T)stack.get(getNumElem()-1);
	}

	
	public void stackInit() {
		Iterator<StackObserver<T>> it = super.iterator();
		while(it.hasNext()){
			it.next().initStack(stack);
		}
	}
}

