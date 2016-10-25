package tp.pr3.mv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.*;

import tp.pr3.mv.commands.CommandInterpreter;
import tp.pr3.mv.commands.CommandParser;
import tp.pr3.mv.commands.Run;
import tp.pr3.mv.commands.exceptionCom.CommandExecutionException;
import tp.pr3.mv.commands.exceptionCom.WrongCommandFormatException;
import tp.pr3.mv.executionMode.*;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.exceptionIns.InstructionExecutionException;
import tp.pr3.mv.instruction.exceptionIns.WrongInstructionFormatException;


/**
 * Clase principal de la aplicacion, encargada del parseo de los argumentos. 
 * El programa podrá tener dos modos de ejecucion: Batch o interactivo, en los cuales,
 * las diferencias son que en el modo interactivo puede intervenir el usuario y en el modo
 * Batch no.
 * Establece las entradas y salidas del programa segun los argumentos de los ficheros y el modo. </br>
 * - La entrada podra ser por teclado, por fichero (el cuál se especifica) o nula(-1). </br>
 * - La salida por consola, por fichero(el cual se especifica) o nula. </br>
 * @author Lidia Flores Tuesta, David Bolaños Calderon
 */
public class Main {
	private static FileReader fr = null;
	private static BufferedReader br = null;

	/**
	 * Metodo principal de la aplicacion. Se encarga de parsear los parametros, 	 
	 * @param args argumentos de entrada al programa.
	 */
	public static void main(String[] args) {
		String asmName 	= "";
		String inName 	= "";
		String typeMode = "";
		String outName 	= "";
		String fileName = ""; // Para errores en la apertura de un archivo

		boolean hasOptionH = false;
		boolean hasOptionA = false;
		boolean hasOptionI = false;
		boolean hasOptionM = false;
		boolean hasOptionO = false;

		CommandLineParser parser = null;
		CommandLine parseador 	 = null;

		Options options = loadOptions();

		ProgramMV programMV = null;

		try {
			parser = new PosixParser();
			parseador = parser.parse(options, args);
		}

		catch (ParseException eparser) {
			errorMissArgument(eparser.getMessage());
		}
		
		hasOptionH = (parseador.hasOption("h") || parseador.hasOption("help"));
		hasOptionA = (parseador.hasOption("a") || parseador.hasOption("asm"));
		hasOptionI = (parseador.hasOption("i") || parseador.hasOption("in"));
		hasOptionM = (parseador.hasOption("m") || parseador.hasOption("mode"));
		hasOptionO = (parseador.hasOption("o") || parseador.hasOption("out"));		
		
		if (hasOptionH)
			printHelp(options);

		if (hasOptionM) {
			if (parseador.getOptionValue("m").equalsIgnoreCase("batch"))
				typeMode = "batch";

			else if (parseador.getOptionValue("m").equalsIgnoreCase("interactive"))
				typeMode = "interactive";

			else
				errorIncorrectMode();
		}

		else
			typeMode = "batch";

		if (hasOptionA)
			asmName = parseador.getOptionValue("a");

		else if (typeMode.equalsIgnoreCase("batch")) 
			errorFileAsm();
		
		if (hasOptionI) 
			inName = parseador.getOptionValue("i");

		if (hasOptionO) 
			outName = parseador.getOptionValue("o");		

		if (typeMode.equalsIgnoreCase("batch")){
			fileName = inName;
			if (inName.isEmpty())
				MVSystem.in = new NullIn();
			else
				MVSystem.in = new FromInputStream(inName);

			fileName = outName;
			if (outName.isEmpty())
				MVSystem.out = new StdOut();
			else
				MVSystem.out = new ToOutputStream(outName);
		}

		else if (typeMode.equalsIgnoreCase("interactive")) {
			fileName = inName;
			if (inName.isEmpty())
				MVSystem.in = new NullIn();
			else
				MVSystem.in = new FromInputStream(inName);

			fileName = outName;
			if (outName.isEmpty())
				MVSystem.out = new NullOut();
			else
				MVSystem.out = new ToOutputStream(outName);
		}
		
		
		try{
			MVSystem.in.open();
			MVSystem.out.open();
		}
		
		catch(FileNotFoundException exc){
			errorFileNotFound(fileName);
		}
		
		catch(IOException exc){
			errorCreatingFile(fileName);
		}

		if (hasOptionA)
			programMV = readProgramFromTxt(asmName);
		else
			programMV = readProgramFromUser();
		
		if(typeMode.equalsIgnoreCase("batch"))
			executeBatch(programMV);
		else
			executeInteractive(programMV);
		
		closeInOut();
	}

	/**
	 * Ejecuta el programa en modo interactivo BATCH.
	 * @param programMV El programa a ejecutar.
	 */
	private static void executeBatch(ProgramMV programMV) {
		CPU cpu = new CPU();
		cpu.loadProgram(programMV);
		System.out.println(programMV.toString());
		CommandInterpreter.configureCommandInterpreter(cpu);
		CommandInterpreter com = new Run();
		try {
			com.executeCommand();
		} 
		catch(InstructionExecutionException iee){
			System.err.println(iee.getMessage());
			closeInOut();
			errorExecutingProgram();
		}
		catch(CommandExecutionException cee) {
			System.err.println(cee.getMessage());
			closeInOut();
			errorExecutingProgram();
		}
	}

	/**
	 * Ejecuta el programa en modo interactivo INTERACTIVO.
	 * @param programMV El programa a ejecutar.
	 */
	private static void executeInteractive(ProgramMV programMV) {
		boolean end = false;
		String line = "";
		String promtp = "> ";
		CommandInterpreter command = null;
		 
		CPU cpu = new CPU();
		cpu.loadProgram(programMV);
		System.out.println(programMV.toString());
		CommandInterpreter.configureCommandInterpreter(cpu);

		System.out.print(promtp);
		line = Constants.scan.nextLine();
		
		if(cpu.getSizeProgram() > 0){
			 while(!end){	
				 try{
					command = CommandParser.readCommandInstruction(line);				
					try {
						 command.executeCommand();
					} 
					catch (InstructionExecutionException iee) {
						 System.err.println(iee.getMessage());
					}
					catch (CommandExecutionException cee) {
						 System.err.println(cee.getMessage());
					}
					finally{		
						end = command.isFinished() || cpu.abortComputation();	
					}
		 
				 } 
				 catch (WrongCommandFormatException e1) {
					System.err.println(e1.getMessage());
				 }
				  
				if(!end){				
					System.out.print(promtp);
					line = Constants.scan.nextLine();	 
				}
			 }
		 }	
		closeInOut();
	}

	/**
	 * Metodo encargado de crear el programa a ejecutar pidiendo las instrucciones al usuario.
	 */
	private static ProgramMV readProgramFromUser() {
		Instruction ins = null;
		ProgramMV programMV = new ProgramMV();
		String line = "";
		System.out.print("Introduce el programa fuente" + Constants.lineSeparator + "> ");
		line = Constants.scan.nextLine();

		while (!line.equalsIgnoreCase("END")) {
			try {
				ins = InstructionParser.instructionParser(line);
				programMV.push(ins);
			} 
			catch (WrongInstructionFormatException exeWF) {
				System.err.println("Instruccion '" + line + "' incorrecta");
			}
			System.out.print("> ");
			line = Constants.scan.nextLine();
		}
		return programMV;
	}

	/**
	 * Metodo encargado de crear el programa a ejecutar leyendolo desde un archivo especificado.
	 * @param txt el nombre del archivo con el codigo del programa.
	 */
	private static ProgramMV readProgramFromTxt(String asmName) {
		ProgramMV programMV = new ProgramMV();

		String totalLine = "";
		String lineToParse = "";
		String[] partLine;
		int contLine = 1;
		Instruction ins = null;

		try {
			fr = new FileReader(asmName);
			br = new BufferedReader(fr);
			totalLine = br.readLine();
			while (totalLine != null) {
				partLine = totalLine.split(";");
				if (partLine.length > 0) {
					lineToParse = partLine[0];

					if (!lineToParse.isEmpty()) {
						try {
							ins = InstructionParser.instructionParser(lineToParse);
							programMV.push(ins);
						} 
						catch (WrongInstructionFormatException exeWF) {
							System.err.println("Error en el programa. Linea " + contLine + ":" + Constants.lineSeparator
												+ "'" + totalLine + "' no es una instruccion valida.");
							System.exit(2);
						}
					}
				}
				
				totalLine = br.readLine();
				contLine++;
			}
		} 
		catch (FileNotFoundException e) {
			closeFile();
			errorFileNotFound(asmName);
		} 
		catch (IOException e) {
			closeFile();
			System.out.println("Error. Lectura invalida");
			System.exit(1);
		}

		closeFile();

		return programMV;
	}

	/**
	 * Metodo que cierra el archivo Asm, bien cuando ha terminado su lectura, o bien cuando ya habido algun
	 * error.
	 */
	private static void closeFile() {
		if (fr != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Cierra la entrada y salida.
	 */
	private static void closeInOut(){
		MVSystem.in.close();
		MVSystem.out.close();
	}

	/**
	 * Clase encargada de crear las distintas opciones que podrán encontrarse en los argumentos del programa
	 */
	@SuppressWarnings("static-access")
	private static Options loadOptions() {
		Options options = new Options();

		Option helpOption = OptionBuilder.withLongOpt("help")
				.withDescription("Muestra esta ayuda").create("h");

		Option asmOption = OptionBuilder.withLongOpt("asm").withArgName("asmfile")
				.hasArg().withDescription("Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch.").create("a");

		Option infileOption = OptionBuilder.withLongOpt("in").withArgName("infile")
				.hasArg().withDescription("Entrada del programa de la maquina-p.").create("i");

		Option modeOption = OptionBuilder.withLongOpt("mode").withArgName("mode")
				.hasArg().withDescription("Modo de funcionamiento (batch | interactive). Por defecto, batch.").create("m");

		Option outfileOption = OptionBuilder.withLongOpt("out").withArgName("outfile")
				.hasArg().withDescription("Fichero donde se guarda la salida del programa de la maquina-p.").create("o");

		options.addOption(helpOption);
		options.addOption(asmOption);
		options.addOption(infileOption);
		options.addOption(modeOption);
		options.addOption(outfileOption);

		return options;
	}

	/**
	 * Muestra la ayuda cuando se ha introducido el argumento Help.
	 * Termina con salida 0 sin errores.
	 */
	private static void printHelp(Options options){
		new HelpFormatter().printHelp("tp.pr3.mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]", options);
		System.exit(0);
	}

	/**
	 * Muestra un mensaje de error al no especificar un fichero ASM cuando es necesario.
	 * Termina con salida 1.
	 */
	private static void errorFileAsm() {
		System.err.println("Uso incorrecto: Fichero ASM no especificado.");
		System.err.println("Use -h|--help para más detalles.");
		System.exit(1);
	}

	/**
	 * Muestra un mensaje de error cuando se introduce una opcion pero no su parametro necesario.
	 * Termina con salida 1.
	 * @param message El mensaje de error.
	 */
	private static void errorMissArgument(String message) {
		System.err.println(message);
		System.err.println("Use -h|--help para más detalles.");
		closeFile();
		System.exit(1);
	}

	/**
	 * Muestra un mensaje de error cuando no se pudo abrir un archivo.
	 * Termina con salida 1.
	 * @param fileName El nombre del archivo que se intento abri.
	 */
	private static void errorCreatingFile(String fileName) {
		System.err.println("Error al crear el archivo " + fileName);
		System.exit(1);
	}
	
	/**
	 * Muestra un mensaje de error cuando se introduce la opcion Mode pero el tipo de modo no existe.
	 * Termina con salida 1.
	 */
	private static void errorIncorrectMode() {
		System.err.println("Uso incorrecto: Modo incorrecto (parametro -m|--mode)");
		System.err.println("Use -h|--help para más detalles.");
		System.exit(1);
	}

	/**
	 * Muestra un mensaje de error cuando se introduce la opcion Mode pero el tipo de modo no existe.
	 * @param fileName Nobmbre del archivo que no se pudo encontrar.
	 */
	private static void errorFileNotFound(String fileName) {
		System.err.println("Uso incorrecto: Error al acceder al fichero de entrada (" + fileName + ")");
		System.err.println("Use -h|--help para más detalles.");
		System.exit(1);
	}
	
	/**
	 * Muestra un mensaje de error cuando se ha producido un ejecucion en el modo Batch.
	 */
	private static void errorExecutingProgram() {
		System.err.println("Error ejecutando el programa en modo batch. Ejecucion finalizada con errores.");
		System.exit(2);	
	}
	
}
