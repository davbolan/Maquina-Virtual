 package tp.pr5.mv;

import java.util.Iterator;

import tp.pr5.mv.observables.MemoryObserver;
import tp.pr5.mv.observables.Observable;

/**
 * Clase que es la memoria de la maquina donde se almacenaran los datos. <br>
 * La memoria se redimensiona segun la cantidad de datos que haya almacenados. 
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("unchecked")
public class Memory<T> extends Observable<MemoryObserver<T>>{
	private Object[] memory;
	private int capacity;
	private boolean vacia = true;
	private int numElems;

	/**
	 * Constructora por defecto. Crea una memoria de tama�o 2 por defecto.
	 */
	public Memory(){
		this.memory   = new Object[2];
		this.capacity = 2;
		this.numElems = 0;
	}
	
	/**
	 * Redimensiona la memoria con tamanio posicion x 2 cuando se realiza un store sobre una posicion fuera
	 * del tamanio de la memoria.
	 * @param pos Numero para poder calcular el nuevo tamanio de la memoria.
	 */
	private void reSize(int pos){
		int newCapacity = pos * 2;
		Object[] newMemory = new Object[newCapacity];
		
		for(int i = 0; i < capacity; i++){
			newMemory[i] = memory[i];
		}
		
		memory = newMemory;
		capacity = newCapacity;
	}

	/**
	 * Devuelve el valor almacena en la posicion pasada por parametro.
	 * @param pos Posicion de la que queremos obtener el valor almacenado.
	 * @return number El valor de la posicion. Devuelve 0 si accedemos a una direccion fuera
	 * de la memoria.
	 */
	public T getValue(int pos){
		T number = null;
		if(pos < capacity){
			if(memory[pos] != null){
				number = (T)memory[pos];}
		}
		return number;
	}

	
	/**
	 * Devuelve el estado de la memoria.
	 * @return mem La cadena con el estado de la memoria.
	 */
	@Override
	public String toString(){
		String mem = "Memoria:";
		
		if(vacia)
			mem += " <vacia>";
		else{
			for(int i = 0; i < capacity; i++){
				if(memory[i] != null)
					mem += " [" + i + "]:[" + memory[i] + "]";
			}
		}
		return mem;
	}
	
	/**
	 * Almacena el valor "number" en la posicion de memoria indicada tambien por parametro.
	 * Si se almacena fuera de una direccion valida, la memoria se redimensiona.
	 * @param pos Posicion donde almacenar el numero.
	 * @param number Numero a almecenar en la memoria.
	 */
	public void store(int pos, T number){
		if(pos >= capacity)
			this.reSize(pos);

		if(memory[pos] == null)
			numElems++;
		
		memory[pos] = number;	
		vacia = false;	
	}

	/**
	 * Avisa a los observadores que se actualicen con el nuevo valor de la memoria
	 * @param index Posicion donde almacenar el numero.
	 * @param value Numero a almecenar en la memoria.
	 */
	public void updateMemory(int index, T value){
		Iterator<MemoryObserver<T>> it = super.iterator();
		while(it.hasNext()){
			it.next().onWrite(index, value);
		}		
	}
	
	/**
	 * Clase interna utilizada para encapsular en un objeto la dirección y el valor de un regitro de memoria
	 */
	public static class MemCell<T>{
		private int pos;
		private T value;
		
		public MemCell(int pos, T memory){
			this.pos = pos;
			this.value = memory;
		}
		
		public int getPos(){
			return pos;
		}
		
		public T getValue(){
			return value;
		}		
	}
	
	/**
	 * Reinicia la memoria
	 */
	public void clear() {
		this.memory   = new Object[2];
		this.capacity = 2;
		this.numElems = 0;
		clean();
	}
	
	/**
	 * Avisa a los observadores que la memoria ha sido reiniciada
	 */
	private void clean() {
		Iterator<MemoryObserver<T>> it = super.iterator();
		while(it.hasNext()){
			it.next().resetMemory();
		}
	}
}
