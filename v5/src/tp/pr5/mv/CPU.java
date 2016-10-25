package tp.pr5.mv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.executionMode.FromInputStream;
import tp.pr5.mv.executionMode.MVSystem;
import tp.pr5.mv.executionMode.ToOutputStream;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr5.mv.observables.CPUObserver;
import tp.pr5.mv.observables.InOutObserver;
import tp.pr5.mv.observables.MemoryObserver;
import tp.pr5.mv.observables.Observable;
import tp.pr5.mv.observables.ProgramObserver;
import tp.pr5.mv.observables.StackObserver;
import tp.pr5.mv.programLoader.IncorrectProgramException;
import tp.pr5.mv.programLoader.ProgramLoader;

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
	 private boolean stopped;
	 private int pc;
	 private int delay;
	 private boolean correctPC;
	 private ProgramMV program;
	 
	 
	 /**
	  * Constructora por defecto
	  */
	 public CPU(int delay){
		 this.memory = new Memory<Integer>();
		 this.stack = new OperandStack<Integer>();
		 this.salir = false;
		 this.stopped = false;
		 this.pc = 0;
		 this.delay = delay;
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
			cpuStarted();
			while(!stopped && !salir && !this.abortComputation()){				
				step();
				delay();
			}
		}
		else{
			exitCPU();
			notifyProgramEnd();
		}
		stopped = false;
	}
	
	/**
	 * Resetea los atributos de la CPU
	 */
	public void reset(){
		this.memory.clear();
		this.stack.clear();
		this.salir = false;
		this.stopped = false;
		this.pc = 0;
		this.correctPC = true;
		program.programInit(pc);
		MVSystem.reset();
		cpuReset();
	}
	
	/**
	 * La CPU espera el tiempo marcao por el atributo delay
	 */
	public void delay(){
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			}
	}
	
	/**
	 * Cambia el delay
	 * @param delay
	 */
	public void changeDelay(int delay){
		this.delay = delay;
	}
	
	/**
	 * Para la ejecución de la máquina
	 */
	public void stop(){
		if(!stopped) stopped = true;
		
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().machineStopped();
		}
		
	}
	
	 /**
	  * Coloca el numero pasado por parametro en la pila de operandos de la maquina.
	  * @param number Numero a apilar.
	  */
	 public void push(int number){
		 if(correctPC){
		 	stack.push(number);
		 	stack.addElem(number);
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
				 stack.removeElem();
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
			memory.updateMemory(pos, cima);
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
	 * Devuelve el tama�o del programa. Esto es, el numero de instrucciones.
	 * @return Numero de instrucciones.
	 */
	public int getSizeProgram() {
		return program.getSizeProgram();
	}


	/**
	 * Devuelve la siguiente instrucci�n a ejecutar, es decir la situada en el contador de 
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
		}
		else{
			notifyProgramEnd();
			exitCPU();
		}
		return correctPC;
	}

	
	public void newFile(String path , String text , String extension) throws FileNotFoundException, IOException, IncorrectProgramException{
		
		if(extension.equalsIgnoreCase("in")){
			ProgramLoader.newFile(path, text);
			loadNewIn(path);
		}
		else if(extension.equalsIgnoreCase("asm")){
			if(ProgramLoader.readNewProgram(path, text) != null);
				loadNewProgram(path);		
		}
		else
			requestError("Elije la extensión correcta. (.in o .asm)");
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

	/**
	 * Carga un nuevo programa
	 * @param path la ruta donde se encuentra el archivo
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws IncorrectProgramException
	 */
	public void loadNewProgram(String path) throws FileNotFoundException, IOException, IncorrectProgramException{
		ProgramMV newProgram = ProgramLoader.readProgramFromTxt(path);
		ArrayList<ProgramObserver> listObserver = program.removeObservers();
		program = newProgram;
		program.addObservers(listObserver);
		reset();
	}
	
	/**
	 * Carga un nuevo archivo de entrada
	 * @param path la ruta donde se encuentra el archivo
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws IncorrectProgramException
	 */
	public void loadNewIn(String path) throws FileNotFoundException{
		FromInputStream inMethod = new FromInputStream(path);
		ArrayList<InOutObserver> listObserver = MVSystem.in.removeObservers();
		MVSystem.in = inMethod;
		MVSystem.in.addObservers(listObserver);
		MVSystem.in.changedIn(path);
		reset();
		
	}
	
	/**
	 * Carga un nuevo archivo de salida
	 * @param path la ruta donde se encuentra el archivo
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws IncorrectProgramException
	 */
	public void loadNewOut(String path) throws IOException {		
		ToOutputStream outMethod = new ToOutputStream(path);
		ArrayList<InOutObserver> listObserver = MVSystem.out.removeObservers();
		MVSystem.out = outMethod;
		MVSystem.out.addObservers(listObserver);
		MVSystem.out.changedOut(path);
		reset();
	}
	
	
	/**
	 * Avisa a los observadores del comienzo de la ejecuión de la CPU
	 */
	public void requestStart(){
		program.notifyProgram(program.toString());
		notifyObservers();
	}
	
	/**
	 * Registra un observador de tipo CPUObserver
	 */
	public void addCpuObserver(CPUObserver cpuObserver) {
		this.addObserver(cpuObserver);		
	}

	/**
	 * Registra un observador de tipo ProgramObserver
	 */
	public void addProgramObserver(ProgramObserver programObserver) {
		program.addObserver(programObserver);		
	}

	/**
	 * Registra un observador de tipo StackObserver
	 */
	public void addStackObserver(StackObserver <Integer> stackObserver) {
		stack.addObserver(stackObserver);		
	}

	/**
	 * Registra un observador de tipo MemoryObserver
	 */
	public void addMemoryObserver(MemoryObserver<Integer> memoryObserver) {
		memory.addObserver(memoryObserver);	
	}
	
	/**
	 * Registra un observador de tipo InOutObserver
	 */
	public void addInOutObserver(InOutObserver inOutObserver) {
		ArrayList<InOutObserver> list = new ArrayList<InOutObserver>();
		list.add(inOutObserver);
		MVSystem.out.addObservers(list);
		MVSystem.in.addObservers(list);
	}
	
	/**
	 * Avisa a los observadores con el programa inicial
	 */
	private void notifyObservers(){
		program.programInit(pc);	
	}
	
	/**
	 * Avisa a los observadores del estado de la CPU
	 */
	private void cpuState() {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().showCPUState(this.toString());
		}
	}

	/**
	 * Avisa a los observadores que ha habido un error
	 */
	public void requestError(String message) {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().raiseError(message);
		}	
	}
	
	/**
	 * Avisa a los observadores que la máquina ha finalizado la ejecución
	 */
	public void notifyProgramEnd() {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().programEnd((abortComputation()));
		}
	}
	
	/**
	 * Avisa a los observadores de la salida del CPU
	 */
	public void exitProgram() {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().requestQuit();
		}
	}
	
	/**
	 * Avisa a los observadores que una intrucción comienza a ejecutarse
	 */
	private void startInstruction(String msg) {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().instructionStarting(msg);
		}
	}
	
	/**
	 * Avisa a los observadores que la cPU ha empezado
	 */
	private void cpuStarted() {
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().machineStarted();
		}
	}
	
	/**
	 * Avisa a los observadores que la CPU ha sido reseteada
	 */
	public void cpuReset(){
		Iterator<CPUObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().onReset();
		}
	}
}
