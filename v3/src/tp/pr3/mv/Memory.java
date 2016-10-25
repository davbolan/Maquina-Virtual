 package tp.pr3.mv;

/**
 * Clase que es la memoria de la maquina donde se almacenaran los datos. <br>
 * La memoria se redimensiona segun la cantidad de datos que haya almacenados. 
 * @author Lidia Flores, David Bolanios
 */
 @SuppressWarnings("unchecked")
public class Memory<T> {
	private int capacity;
	private Object[] memory;
	private boolean vacia = true;

	/**
	 * Constructora por defecto. Crea una memoria de tamaño 20.
	 */
	public Memory(){
		this.memory = new Object[2];
		this.capacity = 2;
	}
	
	/**
	 * Redimensiona la memoria con tamanio posicion x 2 cuando se realiza un store sobre una posicion fuera
	 * del tamanio de la memoria.
	 * @param pos Numero para poder calcular el nuevo tamanio de la memoria.
	 */
	private void reSize(int pos){
		int capacity2 = pos * 2;
		Object[] newMemory = new Object[capacity2];
		
		for(int i = 0; i < this.capacity; i++){
			newMemory[i] = this.memory[i];
		}
		
		this.memory = newMemory;
		this.capacity = capacity2;
	}

	/**
	 * Devuelve el valor almacena en la posicion pasada por parametro.
	 * @param pos Posicion de la que queremos obtener el valor almacenado.
	 * @return number El valor de la posicion. Devuelve 0 si accedemos a una direccion fuera
	 * de la memoria.
	 */
	public T getValue(int pos){
		T number = null;
		if(pos < this.capacity){
			if(this.memory[pos] != null){
				number = (T) this.memory[pos];}
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
			for(int i =0; i < this.capacity; i++){
				if(this.memory[i] != null)
					mem += " [" + i + "]:[" + this.memory[i] + "]";
			}
		}
		return mem;
	}
	
	/**
	 * Almecena el valor "number" en la posicion de memoria indicada tambien por parametro.
	 * Si se almacena fuera de una direccion valirda, la memoria se redimensiona.
	 * @param pos Posicion donde almacenar el numero.
	 * @param number Numero a almecenar en la memoria.
	 */
	public void store(int pos, T number){
		if(pos >= this.capacity)
			this.reSize(pos);

		this.memory[pos] = number;
		this.vacia = false;		
	}
}
