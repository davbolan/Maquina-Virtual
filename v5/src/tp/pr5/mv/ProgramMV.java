package tp.pr5.mv;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.observables.Observable;
import tp.pr5.mv.observables.ProgramObserver;

/**
 * Clase que almacena las instrucciones del programa a ejecutar.
 * @author Lidia Flores, David Bola�os
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
	 * A�ade una instruccion.
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
	
	/**
	 * Avisa a los observadores que la memoria ha sido reiniciada
	 */
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

	/**
	 * Avisa a los observadores con el programa a mostrar
	 */
	private void updateProgram(String prog) {
		Iterator<ProgramObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().initProgramInstructions(prog);
		}
	}	
	
	/**
	 * Avisa a los observadores con el programa a mostrar
	 */
	public void notifyProgram(String prog){
		Iterator<ProgramObserver> it = super.iterator();	
		while(it.hasNext()){
			it.next().initProgram(prog);
		}
	}
	
	/**
	 * Elimina y devuelve una lista con los observadores de esta clase
	 * @return la lista con los observadores
	 */
	public ArrayList<ProgramObserver> removeObservers(){
		ArrayList<ProgramObserver> listObserver = new ArrayList<ProgramObserver>();
		listObserver.addAll(ob);
		ob.removeAll(ob);
		return listObserver;
	}

	/**
	 * Registra en la clase los observadores de la lista
	 */
	public void addObservers(ArrayList<ProgramObserver> listObserver) {
		ob.addAll(listObserver);
	}
}
