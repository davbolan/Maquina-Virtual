package tp.pr5.mv;

/**
 * Clase con metodos los distintos errores que pueden generarse durante el parseo del Main,
 * asi como los que se generan en la lectural del programa
 * 
 * @author Lidia Flores, David Bolanios
 */
public class ErrorMessages {

	/**
	 * Muestra un mensaje de error al no especificar un fichero ASM cuando es necesario.
	 * Se procede con error de salida 1.
	 */
	public static void errorFileAsm() {
		System.err.println("Uso incorrecto: Fichero ASM no especificado.");
		System.err.println("Use -h|--help para m�s detalles.");
	}

	/**
	 * Muestra un mensaje de error cuando se introduce una opcion pero no su parametro necesario.
	 * Se procede con error de salida 1.
	 * @param message El mensaje de error.
	 */
	public static void errorMissArgument(String message) {
		System.err.println(message);
		System.err.println("Use -h|--help para m�s detalles.");		
	}

	/**
	 * Muestra un mensaje de error cuando se introduce la opcion Mode pero el tipo de modo no existe.
	 * Se procede con error de salida 1.
	 */
	public static void errorIncorrectMode() {
		System.err.println("Uso incorrecto: Modo incorrecto (parametro -m|--mode)");
		System.err.println("Use -h|--help para m�s detalles.");
	}

	/**
	 * Muestra un mensaje de error cuando no se pudo abrir un archivo.
	 * Se procede con error de salida 2.
	 * @param fileName El nombre del archivo que se intento abrir.
	 */
	public static void errorCreatingFile(String fileName) {
		System.err.println("Error al crear el archivo " + fileName);
	}

	/**
	 * Muestra un mensaje de error cuando se introduce la opcion Mode pero el tipo de modo no existe.
	 * Se procede con error de salida 2.
	 * @param fileName Nobmbre del archivo que no se pudo encontrar.
	 */
	public static void errorFileNotFound(String fileName) {
		System.err.println("Uso incorrecto: Error al acceder al fichero de entrada (" + fileName + ")");
		System.err.println("Use -h|--help para m�s detalles.");
	}
	
	/**
	 * Muestra un mensaje de error cuando se ha producido un ejecucion en el modo Batch.
	 * Se procede con error de salida 2. 
	 */
	public static void errorExecutingProgram() {
		System.err.println("Error ejecutando el programa en modo batch. Ejecucion finalizada con errores.");
	}

	/**
	 * Muestra un mensaje con las lineas incorrectas durante la carga del programa.
	 * Se procede con error de salida 2. 
	 * @param error La cadena con los errores
	 */
	public static void errorLoadingProgram(String error) {
		System.err.println(error);
	}
}
