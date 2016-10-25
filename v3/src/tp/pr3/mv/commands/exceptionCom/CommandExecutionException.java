package tp.pr3.mv.commands.exceptionCom;


	/**
	 * Crea una excepcion cuando la ejecucion de un commando falla.
	 * La excepcion tiene un mensaje en el cual se explica el error ocurrido.
	 * Esta clase tiene diferentes constructoras, una para cada constructora de 
	 * la clase base.
	 * @author Lidia Flores, David Bolanios
	 */
	public class CommandExecutionException extends Exception{
		private static final long serialVersionUID = -8892949473135957902L;
		
		/**
		 * Constructor por defecto.
		 */
		public CommandExecutionException(){
			super();
		}
		
		/**
		 * Crea una excepcion con un mensaje de error.
		 * @param arg0 Mensaje en el que se explica el error.
		 */
		public CommandExecutionException(String arg0){
			super(arg0);
		}
		
		/**
		 * Crea una excepcion con una causa anidada.
		 * @param arg0 Causa anidada.
		 */
		public CommandExecutionException(Throwable arg0){
			super(arg0);
		}
		
		/**
		 * Crea una excepcion con un mensaje de error y una causa anidada.
		 * @param arg0 Mensaje en el que se explica el error.
		 * @param arg1 Causa anidada.
		 */
		public CommandExecutionException(String arg0, Throwable arg1){
			super(arg0, arg1);
		}
	}
