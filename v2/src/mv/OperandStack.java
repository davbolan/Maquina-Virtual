package tp.pr2.mv;

/**
 * Clase que es la pila de operandos de la maquina donde se almacenaran los datos. <br> 
 * La pila se redimensiona segun la cantidad de datos que haya almacenados. 
 * @author Lidia Flores, David Bolanios
 */
public class OperandStack {
	private Integer[] stack;
	private int numElem = 0;
	private int capacity;
	
	
	/**
	 * Constructora por defecto. Crea una pila de tama�o 20. 
	 */
	public OperandStack() {
		this.stack = new Integer[20];
		this.capacity = 20;
	}
	
	
	/**
	 * Duplica el tamanio de la pila cuando insertamos un elemento y la pila ya esta llena.
	 */
	private void reSizeStack(){
		int capacity2 = this.capacity * 2;
		Integer[] newStack = new Integer[capacity2];
		
		for(int i=0; i < this.capacity ; i++){
			newStack[i] = this.stack[i];
		}

		this.stack = newStack;
		this.capacity = capacity2;	
	}
	
	/**
	 * Devuelve el numero de elementos de la pila.
	 * @return Numero de elementos.
	 */
	public int getNumElem(){
		return this.numElem;
	}
	
	/**
	 * Devuelve el estado de la pila de operandos.
	 * @return oper La cadena con el estado de la pila.
	 */
	public String toString(){
		String oper = "Pila de operandos:";
		if(numElem == 0)
			oper += " <vacia>";
		else{
			for(int i = 0; i < numElem; i++)
				oper += " " + this.stack[i] ;
		}
		return oper;
	}
	
	/**
	 * Apila el numero pasado por parametro.
	 * @param num numero a apilar.
	 */
	public void push(int num)
	{ 
		if(this.numElem >= this.capacity)
			this.reSizeStack();
	
		this.stack[numElem] = num;
		this.numElem++;	
	}

	/**
	 * Extrae un elemento de la cima de la pila.
	 */
	public int pop(){
		this.numElem--;
		int aux = this.stack[this.numElem];
		this.stack[this.numElem] = null;
		return aux;
	}
	
	/**
	 * Devuelve el valor de la cima de la pila.
	 * @return cima de la pila.
	 */
	public int cima(){
		return this.stack[numElem-1];
	}
	
	
}

