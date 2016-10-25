package tp.pr1.mv;

import java.util.Scanner;

/**
 * Clase principal de la aplicacion.
 * @author  Lidia Flores, David Bolanios
 */
public class Main {
	
	public final static String lineSeparator = System.getProperty("line.separator");
	/** 
	 * Metodo principal de la aplicacion que crea la CPU a partir de una memoria y una pila
	 * y luego lo ejecuta.
	 * @param args 
	 */
	public static void main(String[] args) {
		 CPU cpu = new CPU();
		 Scanner scan = new Scanner(System.in);
		 String line = "";
		 String promtp = "Instruccion a ejecutar: "; 
		 InstructionParser insPar = new InstructionParser();
		 Instruction ins;
	
		while(!cpu.getSalir()){
			 System.out.print(promtp); 
			 line = scan.nextLine();
			 ins = InstructionParser.InstructionParse(line);
			 
			 if(ins != null){
				System.out.println(insPar.toString()); 
				 if(cpu.execute(ins)){
					 System.out.println("El estado de la maquina tras ejecutar la instrucción es:" + lineSeparator +
								   cpu.toString());
				 }
				 else
					 System.out.println("Error en la ejecución de la instruccion");
			 }
			 
			 else
				 System.out.print(insPar.error());
		 }
			
		scan.close();
	}

}

