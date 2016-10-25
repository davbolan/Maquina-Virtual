package tp.pr4.mv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase encargada de la lectura del programa, ya sea por consola, en la que el usuario va introduccione
 * las instrucciones, o ya sea directamente leyendo las instrucciones de un archivo.
 * @author  Lidia Flores, David Bolanios
 */
public class ProgramLoader{
	private static FileReader fr = null;
	private static BufferedReader br = null;
	
	/**
	 * Metodo encargado de crear el programa a ejecutar pidiendo las instrucciones al usuario.
	 */
	public static ProgramMV readProgramFromUser() {
		Instruction ins = null;
		ProgramMV programMV = new ProgramMV();
		String line = "";
		String promtp = "> ";
		System.out.println("Introduce el programa fuente");
		System.out.print(promtp);
		
		do{
			line = Constants.scan.nextLine();
		}while(line == null || line.isEmpty());

		while (!line.equalsIgnoreCase("END")) {
			try {
				ins = InstructionParser.instructionParser(line);
				programMV.push(ins);
			} 
			catch (WrongInstructionFormatException exeWF) {
				System.err.println("Instruccion '" + line + "' incorrecta");
			}
			
			System.out.print(promtp);
			do{
				line = Constants.scan.nextLine();
			}while(line == null || line.isEmpty());

		}
		
		return programMV;
	}

	/**
	 * Metodo encargado de crear el programa a ejecutar leyendolo desde un archivo especificado.
	 * @throws FileNotFoundException 
	 */
	public static ProgramMV readProgramFromTxt(String asmName) throws FileNotFoundException, IOException {
		ProgramMV programMV = new ProgramMV();
		String line = "";
		String[] partsOfLine;
		String lineToParse = "";
		int contLine = 1;
		Instruction ins = null;
		String error = "Error en el programa."; 
		boolean correct = true; 				// Para la busqueda de lineas incorrectas

		fr = new FileReader(asmName);
		br = new BufferedReader(fr);
		
		line = br.readLine();
		
		while (line != null){
			partsOfLine = line.split(";");
			if (partsOfLine.length > 0) {
				lineToParse = partsOfLine[0].trim();

				if (!lineToParse.isEmpty()) {
					try {
						ins = InstructionParser.instructionParser(lineToParse);
						if(correct)
							programMV.push(ins);
					} 
					catch (WrongInstructionFormatException exeWF) {
						correct = false;
						error = error + Constants.lineSeparator + "Linea "
								+ contLine + ": '" + lineToParse + "'";
					}
				}
			}

			line = br.readLine();
			contLine++;
		}

		if(!correct){
			closeFile();
			ErrorMessages.errorLoadingProgram(error);
			System.exit(2);
		}

		closeFile();

		return programMV;
	}


	/**
	 * Metodo que cierra el archivo Asm, bien cuando ha terminado su lectura, o bien cuando ya habido algun
	 * error.
	 */
	public static void closeFile() {
		if (fr != null) {
			try {
				br.close();
			} catch (IOException e) {}
		}
	}

	
}
