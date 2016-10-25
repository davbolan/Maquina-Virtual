package tp.pr2.mv;

import tp.pr2.mv.instruction.Instruction;


 
/**
 * Clase que contiene el motor de la aplicacion. Contiene un metodo que va pidiendo instrucciones al usuario, <br>
 * y tras analizarla, las ejecuta modificar convenientemente la memoria y la pila de operandos.
 * @author Lidia Flores, David Bolanios
 *
 */
public class CPU {
	 public final static String lineSeparator = System.getProperty("line.separator");
	 private Memory memory;
	 private OperandStack operand;
	 private boolean salir;
	 private int pc;
	 private boolean correctPC;
	 private ProgramMV program;
	 
	 /**
	  * Constructora por defecto
	  */
	 public CPU(){
		 this.memory = new Memory();
		 this.operand = new OperandStack();
		 this.salir = false;
		 this.pc = 0;
		 this.correctPC = true;
	 }
	 
	 /**
	  * Carga en la cpu el programa leido previamente.
	  * @param program El programa con las instrucciones a ejecutar.
	  */
	 public void loadProgram(ProgramMV program){
		 this.program = program;
	 }
	 
	 /**
	  * Método que indica a la CPU que ya queremos salir.
	  */
	 public void exit(){
		 this.salir = true;
	 }
	 
	 
	 /**
	  * Coloca el numero pasado por parametro en la pila de operandos de la maquina.
	  * @param number Numero a apilar.
	  * @return true push siempre tiene exito, pues los posibles errores ya han sido comprobados.
	  */
	 public void push(int number){
		 	this.operand.push(number);
	}

	 /**
	  * Coge el valor almacenado en la posicion pos de la memoria
	  * @param pos Posicion de la memoria
	  * @return true pues siempre devuelve un valor. Si esta vacio, devuelve un 0.
	  */
	 public void load(int pos) {
		 this.operand.push(this.memory.getValue(pos));
		}

	 /**
	  * Extraer el valor almacenado en la cima de la pila.
	  * @return El valor de la cima.
	  */
	 public int pop(){
		return this.operand.pop();
	 }

	
	 /**
	  * Almecena en la posicion pos de la memoria el valor de la cima de la pila de operandos.
	  * @param pos La posicion donde almacenar la cima de la pila.
	  */
	 public void store(int pos) {
	        int cima = this.operand.pop();
			this.memory.store(pos, cima);		
		}
	 
	 /**
	  * Muestra el estado de la memoria y de la pila de operandos.
	  * @return El estado.
	  */
	 public String toString(){
		 return memory.toString() + lineSeparator +	 operand.toString();
		
	 }

	 /**
	  * Devuelve el numero de elementos en la pila de operandos
	  * @return Numero de elementos.
	  */
	public int getNumElem() {
		return this.operand.getNumElem();
	}


	/**
	 * Incrementa el contador de programa. Tambien comprueba que el PC sigue siendo correcto
	 */
	public void increaseProgramCounter() {
		this.pc++;
		this.correctPC = (this.pc < this.program.getSizeProgram());
		
	}
	
	
	/**
	 * Ejecuta la siguiente instruccion si no se ha abortado.
	 * true si la ejecucion es correcta, false en caso contrario o la instruccion es nula.
	 */
	public boolean step() {
		Instruction ins = this.getCurrentInstruction();
		boolean exito = false;
		if(ins != null){
			System.out.println("Comienza la ejecucion de " + ins.toString());
			exito = ins.execute(this);
		}
		
		return exito;
	}

	/**
	 * Devuelve el tamaño del programa. Esto es, el numero de instrucciones.
	 * @return Numero de instrucciones.
	 */
	public int getSizeProgram() {
		return this.program.getSizeProgram();
	}
	
	/**
	 * Resetea todos los atributos de la CPU para, posteriormente, ejecutar el comando "run"
	 */
	public void resetCPU(){
		 this.memory = new Memory();
		 this.operand = new OperandStack();
		 this.salir = false;
		 this.pc = 0;
		 this.correctPC = true;
	}

	/**
	 * Devuelve la siguiente instrucción a ejecutar, es decir la situada en el contador de 
	 * programa en caso de que este sea correcto. En otro caso devuelve null.
	 * @return ins La instruccion a ejecutar. Puede valer null si se tiene que abortar la ejecucion.
	 */
	public Instruction getCurrentInstruction() {
		Instruction ins = null;
		if(!this.abortComputation())
			ins = this.program.get(this.pc);
				
		return ins;
	}
	
	/**
	 * Comprueba que se puede seguir con la ejecucion del programa, es decir, si el PC sigue siendo
	 * correcto o no se ha ejecutado la instruccion HALT.
	 * @return true si se puede seguir con la ejecucion del programa.
	 */
	public boolean abortComputation(){
		return !this.correctPC || this.salir;
	}


	/**
	 * Cambia el contador del programa por el parametro que se le pasa.
	 * @param newPc El nuevo PC.
	 * @return true si el PC es correcto, false en caso contrario.
	 */
	public boolean changePC(int newPc) {
		this.correctPC = (newPc <= this.getSizeProgram());
		if(this.correctPC)
			this.pc = newPc;
		
		return this.correctPC;
	}

}
