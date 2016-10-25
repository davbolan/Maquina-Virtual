package tp.pr1.mv;
 
/**
 * Clase que contiene el motor de la aplicacion. Contiene un metodo que va pidiendo instrucciones al usuario, y tras
 * analizarla, las ejecuta modificar convenientemente la memoria y la pila de operandos.
 * @author Lidia Flores, David Bolanios
 *
 */
public class CPU {
	 public final static String lineSeparator = System.getProperty("line.separator");
	 private Memory memory;
	 private OperandStack operand;
	 private boolean salir;
	
	 /**
	  * Constructora por defecto
	  */
	 public CPU(){
		 this.memory = new Memory();
		 this.operand = new OperandStack();
		 this.salir = false;
	 }
	 
	 /**
	  * Es el encargado de ejecutar la instruccion que le llega como parametro modificando convenientemente la memoria 
	  * y/o la pila de operandos.
	  * @param ins La instruccion a ejecutar.
	  * @return true si la instruccion se ha ejecutado correctamente. False en caso contrario.
	  */
	 public boolean execute(Instruction ins){
	
		 boolean ok = false;
		 switch(ins.getAction()){
			 case PUSH:
			 	 ok = push(ins.getNumber());
				break;
			 case POP: 
				 ok = pop();
				 break;
			 case DUP:
				 ok = dup();
				 break;
			 case FLIP:
				 ok = flip();
				 break;	
			 case LOAD:
				 ok = load(ins.getNumber());
				 break; 
			 case STORE:
				 ok = store(ins.getNumber());
				 break; 
			 case ADD:
				 ok = add();
				 break; 
			 case SUB:
				 ok = sub();
				 break;
		 	 case MUL:
		 		 ok = mul();
		 		 break; 
			 case DIV: 
				 ok = div();
				 break; 
			 case OUT:
				 ok = out();
			 	break;
			 case HALT:
				 	exit();
				 	ok = true;
				 break; 
			 default:
				 ok = false;
		 }
			 
		 return ok; 
	 }
	 
	 
	 /**
	  * Método que indica a la CPU que ya queremos salir.
	  */
	 private void exit(){
		 this.salir = true;
	 }
	 
	 /**
	  * Devuelve el estado de salir.
	  * @return salir true si queremos salir, false en caso contrario.
	  */
	 public boolean getSalir(){
		 return salir; 
	 }
	 
	 /**
	  * Coloca el numero pasado por parametro en la pila de operandos de la maquina.
	  * @param number Numero a apilar.
	  * @return true push siempre tiene exito
	  */
	 private boolean push(int number){
		 	this.operand.push(number);
			return true;
		}

	 /**
	  * Muestra el caracter en ASCII asociado a la cima de operandos.
	  * En caso de que sea negativo lo hace de su valor absoluto.
	  * @return true si se existe un operando en la pila, false en caso contrario.
	  */
	 private boolean out() {
		 	boolean exito = false;
		 	if(this.operand.getNumElem() > 0){
		 		System.out.println(Character.toChars(Math.abs(this.operand.pop())));
		 		exito = true;
		 	}
			return exito;
		}

	 /**
	  * Divide la subcima entre la cima y lo apila en la pila.
	  * @return true si se puede realizar la division, false si no hay operandos suficientes
	  * o la cima es 0.
	  */
	 private boolean div() {
		 boolean exito = false;
		 if(this.operand.getNumElem() > 1 && this.operand.cima() != 0){ //Evaluacion perezosa
			 int cima = operand.pop();
			 int subcima = operand.pop();
			 push(subcima / cima);
			 exito = true;			
		 }
		 
		 return exito;
		}

	 /**
	  * Multiplica la subcima y la cima y lo apila en la pila.
	  * @return true si hay operandos suficientes para realizar la multiplicacion, false
	  * en caso contrario.
	  */
	 private boolean mul() {
		 boolean exito = false;
		 if(this.operand.getNumElem() > 1){
			 int cima = operand.pop();
			 int subcima = operand.pop();
			 push(subcima * cima);
			 exito = true;
		 }
		 return exito;
		}

	 /**
	  * Suma la subcima y la cima y lo apila en la pila. 
	  * @return true si hay operandos suficientes para realizar la suma, false
	  * en caso contrario.
	  */
	 private boolean add(){
		 boolean exito = false;
		 if(this.operand.getNumElem() > 1){
			 int cima = operand.pop();
			 int subcima = operand.pop();
			 push(subcima + cima);
			 exito = true;
		 }
		 return exito;
		 }

	 /**
	  * Resta la subcima y la cima y lo apila en la pila.
	  * @return true si hay operandos suficientes para realizar la resta, false
	  * en caso contrario.
	  */
	 private boolean sub() {
		 boolean exito = false;
		 if(this.operand.getNumElem() > 1){
			 int cima = operand.pop();
			 int subcima = operand.pop();
			 push(subcima - cima);
			 exito = true;
		 }
		 return exito;
		}

	 /**
	  * Coge el valor almacenado en la posicion pos de la memoria
	  * @param pos Posicion de la memoria
	  * @return true Siemp
	  */
	 private boolean load(int pos) {
		 boolean exito = false;
		  if(pos >= 0){
			  this.operand.push(this.memory.getValue(pos));
			  exito = true;
		}
			return exito;
		}

	 /**
	  * Extraer el valor almacenado en la cima de la pila.
	  * @return true si hay al menos un elemento en la pila de operandos. False en caso contrario.
	  */
	 private boolean pop() {
		 boolean exito = false;
		 if(this.operand.getNumElem() > 0){
			 this.operand.pop();
		 		exito = true;
		 }
			return exito;
		}

	 /**
	  * Duplica el valor almacenado en la cima de pila
	  * @return true si en la pila hay algun numero en la pila, false en caso contrario.
	  */
	 private boolean dup() {
		 boolean exito = false;
		 if(this.operand.getNumElem() > 0){
			 this.operand.push(this.operand.cima());
		 		exito = true;
		 }
			return exito;
		}

	 /**
	  * Intercambia el valor almacenado en la cima de pila con la subcima
	  * @return true si en la pila hay algun numero en la pila, false en caso contrario.
	  */
	 private boolean flip() {
		 boolean exito = false;
		 if(this.operand.getNumElem() > 1){
			 int cima = operand.pop();
			 int subcima = operand.pop();
			 push(cima);
			 push(subcima);
			 exito = true;
		 }
		 return exito;
		}
	
	 /**
	  * Almecena en la posicion pos de la memoria el valor de la cima de la pila de operandos.
	  * @param pos La posicion donde almacenar la cima de la pila.
	  * @return true si indicamos una posicion no negativa, false en caso contrario.
	  */
	 private boolean store(int pos) {
		boolean exito = false;
		  if(this.operand.getNumElem() > 0 && pos >= 0){
			  int cima = operand.pop();
			  this.memory.store(pos, cima);
			  exito = true;
		  }
			return exito;
		}
	 
	 /**
	  * Muestra el estado de la memoria y de la pila de operandos.
	  * @return El estado.
	  */
	 
	 public String toString(){
		 return memory.toString() + lineSeparator +	 operand.toString();
		 
	 }
}
