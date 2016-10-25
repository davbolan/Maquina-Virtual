package tp.pr3.mv;

import java.util.ArrayList;

import tp.pr3.mv.instruction.Instruction;

/**
 * Clase que almacena las instrucciones del programa a ejecutar.
 * @author Lidia Flores, David Bolaños
 */
public class ProgramMV {
	private ArrayList<Instruction> program;
		
	/**
	 * Constructora que crea un arraylist para almacenar las instrucciones a ejecutar.  
	 */
	public ProgramMV(){
		this.program = new ArrayList<Instruction>();
	}

	/**
	 * Añade una instruccion.
	 * @param instr Instruccion a aniadir.
	 */
	public void push(Instruction instr){
		this.program.add(instr);
	}
		
	/**
	 * Devuelve la instruction almacenada en la posicion "i".
	 * @param i La posicion.
	 * @return La instruccion en "i".
	 */
	public Instruction get(int i){
		return this.program.get(i);
	}
	
	/**
	 * Devuelve el tamanio del programa, esto es, el numero de instrucciones almacenadas.
	 * @return El nuemro de instrucciones.
	 */
	public int getSizeProgram() {
		return this.program.size();
	}
	
	/**
	 * Devuelve en una cadena las instrucciones almacenadas.
	 * @return set La cadena con las instrucciones.
	 */
	public String toString(){
		String set = "El programa introducido es: ";
		for(int i = 0; i < this.program.size(); i++){
			set += Constants.lineSeparator + i + ": " + this.program.get(i).toString(); 
		}
		return set;
	}	

}
