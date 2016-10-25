package tp.pr4.mv;

import java.util.Iterator;

import tp.pr4.mv.executionMode.MVSystem;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr4.mv.observables.*;

/**
 * Clase que contiene el motor de la aplicacion. Contiene los metodos para modificar la pila y la memoria, asi como un
 * metodo para buscar la siguiente instruccion y ejecutarla. También modifica el PC el cual, si es incorrecto, provoca la
 * salida de la aplicacion.
 * @author Lidia Flores, David Bolanios
 */
public class CPU extends Observable<CPUObserver>{
	 private Memory<Integer> memory;
	 private OperandStack<Integer> stack;
	 private boolean salir;
	 private int pc;
	 private boolean correctPC;
	 private ProgramMV program;
	 
	 
	 /**
	  * Constructora por defecto
	  */
	 public CPU(){
		 this.memory = new Memory<Integer>();
		 this.stack = new OperandStack<Integer>();
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
	  * Muestra el estado de la memoria y de la pila de operandos.
	  * @return El estado.
	  */
	 public String toString(){
		 return "El estado de la máquina tras ejecutar la instrucción es:" + Constants.lineSeparator 
				 + memory.toString() + Constants.lineSeparator 
				 + stack.toString();
	 }

	 
	 /**
	  * Devuelve el numero de elementos en la pila de operandos.
	  * @return Numero de elementos.
	  */
	public int getNumElem() {
		return stack.getNumElem();

	}


	/**
	 * Incrementa el contador de programa. Tambien comprueba si se ha llegao al final del programa.
	 */
	public void increaseProgramCounter() {
		pc++;
		correctPC = (pc < program.getSizeProgram());	

		if(!correctPC)
			notifyProgramEnd();
		
		program.programInit(pc);
		this.cpuState();
		this.pcState();	
	}
	
	
	/**
	 * Ejecuta la siguiente instruccion si no se ha abortado.
	 * @throws InstructionExecutionException si alguna instruccion tiene algun error.
	 */
	public void step() throws InstructionExecutionException{
		if(getSizeProgram() > 0){
			Instruction ins = this.getCurrentInstruction();
	
			if(ins != null){
				this.startInstruction(ins.toString());
				this.pcState();
				ins.execute(this);
			}
		}
		else{
			exitCPU();
			notifyProgramEnd();
		}
	}

	/**
	 * Ejecuta las instrucciones desde el PC actual hasta el fin del programa si no se ha abortado.
	 * @throws InstructionExecutionException si alguna instruccion tiene algun error.
	 */
	public void run() throws InstructionExecutionException{
		if(getSizeProgram() > 0){
			while(!salir && !this.abortComputation())
				step();
		}
		else{
			exitCPU();
			notifyProgramEnd();
		}
	}
	
	 /**
	  * Coloca el numero pasado por parametro en la pila de operandos de la maquina.
	  * @param number Numero a apilar.
	  */
	 public void push(int number){
		 if(correctPC){
		 	stack.push(number);
		 	stack.stackInit();
		 	this.pcState();
		 }
	 }
 
	 /**
	  * Coge el valor almacenado en la posicion pos de la memoria
	  * @param pos Posicion de la memoria
	  */
	 public void load(int pos) {
		 if(correctPC){
			 Integer value = memory.getValue(pos);
			 if(value == null)
				 value = 0;
			 stack.push(value);
			 stack.stackInit();
			 memory.memoryInit();
		 }
	 }

	 /**
	  * Extrae el valor almacenado en la cima de la pila.
	  * @return El valor de la cima.
	 * @throws InstructionExecutionException 
	  */
	 public int pop() throws InstructionExecutionException{
		 int num = -1;
		 if (this.getNumElem() > 0) {
			 if(correctPC){
				 num = stack.pop();
				 stack.stackInit();
			 }
		 }
		 else{
			 throw new InstructionExecutionException("Error en la ejecucion del comando POP: Pila vacia.");
		 }
		return num;
	 }

	 /**
	  * Almecena en la posicion pos de la memoria el valor de la cima de la pila de operandos.
	  * @param pos La posicion donde almacenar la cima de la pila.
	 * @throws InstructionExecutionException 
	  */
	 public void store(int pos) throws InstructionExecutionException {
		if(correctPC){
			int cima = this.pop();
			memory.store(pos, cima);		
			memory.memoryInit();
			stack.stackInit();
		}
	 }
	 
	 /**
	  * Metodo que indica a la CPU que ya queremos salir. Cierra tambien la entrada y salida.
	  */
	 public void exitCPU(){
		 salir = true;
		 MVSystem.close();
		 exitProgram();
	 }
	 
	 
	/**
	 * Devuelve el tamaño del programa. Esto es, el numero de instrucciones.
	 * @return Numero de instrucciones.
	 */
	public int getSizeProgram() {
		return program.getSizeProgram();
	}


	/**
	 * Devuelve la siguiente instrucción a ejecutar, es decir la situada en el contador de 
	 * programa en caso de que este sea correcto. En otro caso devuelve null.
	 * @return ins La instruccion a ejecutar. Puede valer null si se tiene que abortar la ejecucion.
	 */
	public Instruction getCurrentInstruction() {
		Instruction ins = null;
		if(!this.abortComputation())
			ins = program.get(pc);
				
		return ins;
	}
	
	
	/**
	 * Comprueba que se puede seguir con la ejecucion del programa, es decir, si el PC sigue siendo
	 * correcto o no se ha ejecutado la instruccion HALT.
	 * @return true si se puede seguir con la ejecucion del programa.
	 */
	public boolean abortComputation(){
		return !correctPC || salir;
	}


	/**
	 * Cambia el contador del programa por el parametro que se le pasa.
	 * @param newPc El nuevo PC.
	 * @return true si el PC es correcto. False en caso contrario.
	 */
	public boolean changePC(int newPc) {
		correctPC = (0 <= newPc && newPc < this.getSizeProgram());
		if(correctPC){
			pc = newPc;	
			this.cpuState();
			program.programInit(pc);
			this.pcState();
		}
		else{
			notifyProgramEnd();
			exitCPU();
		}
		return correctPC;
	}


	/**
	 * Incrementa/decrementa number lineas al PC.
	 * @param number El numero de lineas a sumar.
	 * @return exito true si el PC es correcto. False en caso contrario.
	 */
	public boolean addPC(int number) {
		int aux = pc + number;
		correctPC = this.changePC(aux);
		return correctPC;
	}


	public void requestStart(){
		program.notifyProgram(program.toString());
		notifyObservers();
	}
	
	public void addCpuObserver(CPUObserver cpuObserver) {
		this.addObserver(cpuObserver);		
	}

	public void addProgramObserver(ProgramObserver programObserver) {
		program.addObserver(programObserver);		
	}

	public void addStackObserver(StackObserver <Integer> stackObserver) {
		stack.addObserver(stackObserver);		
	}

	public void addMemoryObserver(MemoryObserver memoryObserver) {
		memory.addObserver(memoryObserver);	
	}
	
	public void addInOutObserver(InOutObserver inOutObserver) {
		MVSystem.out.addObserver(inOutObserver);
		MVSystem.in.addObserver(inOutObserver);
	}
	

	private void notifyObservers(){
		stack.stackInit();
		program.programInit(pc);	
		this.pcState();
		memory.memoryInit();
	}
	
	private void cpuState() {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().showCPUState(this.toString());
		}
	}

	private void pcState(){
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().pcUpdate(pc);
		}
	}
	
	public void requestError(String message) {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().raiseError(message);
		}	
	}

	public void notifyProgramEnd() {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().programEnd((abortComputation()));
		}
	}
	
	public void exitProgram() {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().requestQuit();
		}
	}
	
	private void startInstruction(String msg) {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().instructionStarting(msg);
		}
	}
}
