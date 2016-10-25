package tp.pr4.mv;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.observables.Observable;
import tp.pr4.mv.observables.ProgramObserver;

/**
 * Clase que almacena las instrucciones del programa a ejecutar.
 * @author Lidia Flores, David Bolaños
 */
public class ProgramMV extends Observable<ProgramObserver>{
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
	 * Devuelve la instruction almacenada en la posicion "pos".
	 * @param pos La posicion.
	 * @return La instruccion en "pos".
	 */
	public Instruction get(int pos){
		return this.program.get(pos);
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
		String prog = "El programa introducido es: ";
		
		for(int i = 0; i < this.program.size(); i++){
			prog += Constants.lineSeparator + i + ": " + this.program.get(i).toString(); 
		}
		
		return prog;
	}
	
	public void programInit(int pc) {
		String prog  = "";
		String space = "";
		
		
		if(!program.isEmpty()){
			for(int i = 0; i < this.program.size(); i++){			
				if(i == pc)
					space = "  *";
				else
					space = "   ";
				
				if(i < 10) 
					space+= " ";		
				
				prog += space + "  " + i + ":  " + this.program.get(i).toString() + Constants.lineSeparator ;
			}
		}
		
		updateProgram(prog);	
	}

	private void updateProgram(String prog) {
		Iterator<ProgramObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().initProgramInstructions(prog);
		}
	}	
	
	public void notifyProgram(String prog){
		Iterator<ProgramObserver> it = super.iterator();	
		while(it.hasNext()){
			it.next().initProgram(prog);
		}
	}
}
