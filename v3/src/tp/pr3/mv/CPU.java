package tp.pr3.mv;

import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;

/**
 * Clase que contiene el motor de la aplicacion. Contiene los metodos para modificar la pila y la memoria, asi como un
 * metodo para buscar la siguiente instruccion y ejecutarla. También modifica el PC el cual, si es incorrecto, provoca la
 * salida de la aplicacion.
 * @author Lidia Flores, David Bolanios
 */
public class CPU {
	 private Memory<Integer> memory;
	 private OperandStack<Integer> operand;
	 private boolean salir;
	 private int pc;
	 private boolean correctPC;
	 private ProgramMV program;
	 
	 
	 /**
	  * Constructora por defecto
	  */
	 public CPU(){
		 this.memory = new Memory<Integer>();
		 this.operand = new OperandStack<Integer>();
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
	  * Metodo que indica a la CPU que ya queremos salir.
	  */
	 public void exit(){
		 this.salir = true;
	 }
	 
	 
	 /**
	  * Coloca el numero pasado por parametro en la pila de operandos de la maquina.
	  * @param number Numero a apilar.
	  */
	 public void push(int number){
		 	this.operand.push(number);
	 }

	 
	 /**
	  * Coge el valor almacenado en la posicion pos de la memoria
	  * @param pos Posicion de la memoria
	  */
	 public void load(int pos) {
		 Integer value = this.memory.getValue(pos);
		 if(value == null)
			 value = 0;
		 this.operand.push(value);
	 }

	 
	 /**
	  * Extrae el valor almacenado en la cima de la pila.
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
		 return "El estado de la máquina tras ejecutar la instrucción es:" + Constants.lineSeparator 
				 + memory.toString() + Constants.lineSeparator 
				 + operand.toString();
	 }

	 
	 /**
	  * Devuelve el numero de elementos en la pila de operandos.
	  * @return Numero de elementos.
	  */
	public int getNumElem() {
		return this.operand.getNumElem();
	}


	/**
	 * Incrementa el contador de programa. Tambien comprueba si se ha llegao al final del programa.
	 */
	public void increaseProgramCounter() {
		this.pc++;
		this.salir = (this.pc >= this.program.getSizeProgram());	
	}
	
	
	/**
	 * Ejecuta la siguiente instruccion si no se ha abortado.
	 * true si la ejecucion es correcta, false en caso contrario o la instruccion es nula.
	 * @throws InstructionExecutionException 
	 */
	public boolean step() throws InstructionExecutionException{
		Instruction ins = this.getCurrentInstruction();
		boolean exito = false;
		if(ins != null){
			System.out.println("Comienza la ejecucion de " + ins.toString());
			ins.execute(this);
			exito = true;
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
	 * Resetea todos los atributos de la CPU para, posteriormente, ejecutar el comando "run".
	 */
	public void resetCPU(){
		 this.memory = new Memory<Integer>();
		 this.operand = new OperandStack<Integer>();
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
	 * @return true si el PC es correcto. False en caso contrario.
	 */
	public boolean changePC(int newPc) {
		this.correctPC = (0 <= newPc && newPc < this.getSizeProgram());
		if(this.correctPC)
			this.pc = newPc;	
		return this.correctPC;
	}

	
	/**
	 * Incrementa/decrementa number lineas al PC.
	 * @param number El numero de lineas a sumar.
	 * @return exito true si el PC es correcto. False en caso contrario.
	 */
	public boolean addPC(int number) {
		int aux = this.pc + number;
		this.correctPC = (0 <= aux && aux < this.getSizeProgram() && this.changePC(aux));
		return this.correctPC;
	}

}
