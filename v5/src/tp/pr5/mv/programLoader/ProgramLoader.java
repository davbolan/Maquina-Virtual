package tp.pr5.mv.programLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tp.pr5.mv.Constants;
import tp.pr5.mv.InstructionParser;
import tp.pr5.mv.ProgramMV;
import tp.pr5.mv.instruction.Instruction;
import tp.pr5.mv.instruction.exceptionIns.WrongInstructionFormatException;

/**
 * Clase encargada de la lectura del programa, ya sea por consola, en la que el usuario va introduccione
 * las instrucciones, o ya sea directamente leyendo las instrucciones de un archivo.
 * @author  Lidia Flores, David Bolanios
 */
public class ProgramLoader{
	private static FileReader fr = null;
	private static BufferedReader br = null;
	private static String errorMessage;
	
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
	 * @throws IncorrectProgramException 
	 */
	public static ProgramMV readProgramFromTxt(String asmName) throws FileNotFoundException, IOException, IncorrectProgramException {
		ProgramMV programMV = new ProgramMV();
		String line = "";
		String[] partsOfLine;
		String lineToParse = "";
		int contLine = 1;
		Instruction ins = null;
		errorMessage = "Error en el programa."; 
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
						errorMessage = errorMessage + Constants.lineSeparator + "Linea "
								+ contLine + ": '" + lineToParse + "'";
					}
				}
			}

			line = br.readLine();
			contLine++;
		}

		if(!correct){
			programMV = null;
			closeFile();
			throw new IncorrectProgramException(errorMessage);
		}
		closeFile();

		return programMV;
	}

	/**
	 * Método encargado de crear un archivo con un nuevo programa programa
	 * @param path La ruta donde crear el archivo
	 * @param text El programa
	 * @return el programa
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws IncorrectProgramException
	 */
	public static ProgramMV readNewProgram(String path, String text) throws FileNotFoundException, IOException, IncorrectProgramException{
		ProgramMV program = null;
		File file = newFile(path, text);
		try{
			program = readProgramFromTxt(path);
		}
		catch(IncorrectProgramException e){
			file.delete();
			throw new IncorrectProgramException(errorMessage);
		}
		return program;
	}

	/**
	 * Método encargado de crear un archivo con una nueva entrada
	 * @param path La ruta donde crear el archivo
	 * @param text La entrada
	 * @return el archivo creado
	 */
	public static File newFile(String path, String text) {
		File file = new File(path);
		FileWriter fileW = null;
		try {
			fileW = new FileWriter(file);
			fileW.write(text);
		} catch (IOException e) {
		}
		finally {
			if(fileW != null){
				try {
					fileW.close();
				} catch (IOException e){}
			}
			else{
				file.delete();
			}
		}
		return file;
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
