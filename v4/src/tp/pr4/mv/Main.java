package tp.pr4.mv;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import tp.pr4.mv.executionMode.*;

/**
 * Clase principal de la aplicacion, encargada del parseo de los argumentos. 
 * El programa podrá tener dos modos de ejecucion: Batch o interactivo, en los cuales,
 * las diferencias son que en el modo interactivo puede intervenir el usuario y en el modo
 * Batch, no.
 * Establece las entradas y salidas del programa segun los argumentos de los ficheros y el modo. </br>
 * - La entrada podra ser por teclado, por fichero (el cuál se especifica) o nula(-1). </br>
 * - La salida por consola, por fichero(el cual se especifica) o nula. </br>
 * @author Lidia Flores Tuesta, David Bolaños Calderon
 */
public class Main {
		
	private static final String BATCH 		= "batch";
	private static final String INTERACTIVE = "interactive";
	private static final String WINDOW 		= "window";
	
	private static String ASM_NAME 		= "";
	private static String IN_NAME 		= "";
	private static String OUT_NAME 		= "";
	private static String TYPE_MODE 	= "interactive";
	
	private static boolean hasOptionH = false;
	private static boolean hasOptionA = false;
	private static boolean hasOptionI = false;
	private static boolean hasOptionM = false;
	private static boolean hasOptionO = false;	
	
	/**
	 * Metodo principal de la aplicacion. Se encarga de parsear los parametros, 	 
	 * @param args argumentos de entrada al programa.
	 */
	public static void main(String[] args) {
		CommandLineParser cmdParser;
		CommandLine parser = null;
	
		// CARGA DE LAS OPCIONES
		Options options = loadOptions();
	
		try {
			cmdParser = new PosixParser();
			parser = cmdParser.parse(options, args);
		}
		catch (ParseException eparser) {
			ErrorMessages.errorMissArgument(eparser.getMessage());
			System.exit(1);
		}
		
		hasOptionH = (parser.hasOption("help") || parser.hasOption("h"));
		hasOptionA = (parser.hasOption("asm")  || parser.hasOption("a"));
		hasOptionI = (parser.hasOption("in")   || parser.hasOption("i"));
		hasOptionM = (parser.hasOption("mode") || parser.hasOption("m"));
		hasOptionO = (parser.hasOption("out")  || parser.hasOption("o"));		
		
		// OPCION HELP
		if (hasOptionH)
			printHelp(options);

		// OPCION MODE
		if (hasOptionM) {
			String optionM = parser.getOptionValue("m");
			
			if (optionM.equalsIgnoreCase(BATCH))
				TYPE_MODE = BATCH;
			else if (optionM.equalsIgnoreCase(INTERACTIVE))
				TYPE_MODE = INTERACTIVE;
			else if (optionM.equalsIgnoreCase(WINDOW))
				TYPE_MODE = WINDOW;			
			else{
				ErrorMessages.errorIncorrectMode();
				System.exit(1);
			}
		}

		// OPCION ASM
		if (hasOptionA)
			ASM_NAME = parser.getOptionValue("a");
		else if (TYPE_MODE.equalsIgnoreCase(BATCH) || TYPE_MODE.equalsIgnoreCase(WINDOW)){ 
			ErrorMessages.errorFileAsm();
			System.exit(1);
		}
		
		// OPCION IN
		if (hasOptionI) 
			IN_NAME = parser.getOptionValue("i");

		// OPCION OUT
		if (hasOptionO) 
			OUT_NAME = parser.getOptionValue("o");		

		// ESTABLECE EL MODO DE EJECUCION
		setExecutionMode();
		
		// CARGA DEL PROGRAMA		
		ProgramMV programMV;
		
		if (hasOptionA)
			programMV = readProgramFromFile();			
		else
			programMV = ProgramLoader.readProgramFromUser();
		
		executeMode(programMV);
		
	}

	private static ProgramMV readProgramFromFile() {
		ProgramMV programMV = new ProgramMV();
		try{
			programMV = ProgramLoader.readProgramFromTxt(ASM_NAME);
		}
		catch (FileNotFoundException e) {
			ProgramLoader.closeFile();
			ErrorMessages.errorFileNotFound(ASM_NAME);
			System.exit(2);
		} 
		catch (IOException ioe) {
			ProgramLoader.closeFile();
			System.err.println("ERROR: " + ioe.getMessage());
			System.exit(1);
		}
		return programMV;
	}

	/**
	 * Establece el modo de ejecucion asi como la entrada y salida del programa.
	 * @param programMV el programa a ejecutar.
	 */
	private static void setExecutionMode() {
		if (TYPE_MODE.equalsIgnoreCase(BATCH)){
			if (hasOptionI)
				MVSystem.in = new FromInputStream(IN_NAME);
			else
				MVSystem.in = new StdIn();

			if (hasOptionO)
				MVSystem.out = new ToOutputStream(OUT_NAME);
			else
				MVSystem.out = new StdOut();
			
			MVSystem.MODE = BATCH;
		}

		else if (TYPE_MODE.equalsIgnoreCase(INTERACTIVE)) {
			if (hasOptionI)
				MVSystem.in = new FromInputStream(IN_NAME);
			else
				MVSystem.in = new NullIn();

			if (hasOptionO)
				MVSystem.out = new ToOutputStream(OUT_NAME);
			else
				MVSystem.out = new NullOut();
			
			MVSystem.MODE = INTERACTIVE;
		}
		
		else{
			if (hasOptionI)
				MVSystem.in = new FromInputStream(IN_NAME);
			else
				MVSystem.in = new NullIn();

			if (hasOptionO)
				MVSystem.out = new ToOutputStream(OUT_NAME);
			else
				MVSystem.out = new NullOut();
			
			MVSystem.MODE = WINDOW;
		}
	}		

	/**
	 * Ejecuta  el programa segun el modo correspondiente.
	 * @param programMV el programa a ejecutar.
	 */
	private static void executeMode(ProgramMV programMV){
		CPUExecution cpuExe = new CPUExecution(programMV);
				
		switch(TYPE_MODE.toLowerCase()){
			case BATCH: 
				cpuExe.executeBatchMode();			break;			
			case INTERACTIVE: 
				cpuExe.executeInteractiveMode();	break;			
			case WINDOW: 
				cpuExe.executeWindowMode();			break;					
			default: 
				cpuExe.executeInteractiveMode();
		}
	}
	
	/**
	 * Metodo encargado de crear las distintas opciones que podrán usarse en los argumentos del programa
	 */
	@SuppressWarnings("static-access")
	private static Options loadOptions() {
		Options options = new Options();

		Option helpOption = OptionBuilder.withLongOpt("help")
				.withDescription("Muestra esta ayuda").create("h");

		Option asmOption = OptionBuilder.withLongOpt("asm").withArgName("asmfile")
				.hasArg().withDescription("Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en los modos batch y window.").create("a");

		Option infileOption = OptionBuilder.withLongOpt("in").withArgName("infile")
				.hasArg().withDescription("Entrada del programa de la maquina-p.").create("i");

		Option modeOption = OptionBuilder.withLongOpt("mode").withArgName("mode")
				.hasArg().withDescription("Modo de funcionamiento (batch | interactive | window). Por defecto, batch.").create("m");

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
}
