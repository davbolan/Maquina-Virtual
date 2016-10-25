package tp.pr5.mv.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tp.pr5.mv.Constants;

/**
 * Clase que representa el panel de ayuda
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class HelpPanel extends JPanel{
	
	/**
	 * Construye el panel de ayuda
	 */
	public HelpPanel(){
		initHelpPanel();
	}

	/**
	 * Inicialzia el panel de ayuda
	 */
	private void initHelpPanel() {
		this.setLayout(new BorderLayout());
		String texto = "Bienvenidos a la máquina virtual de Lidia Flores y David Bolaños."
				+ Constants.lineSeparator + "Donde podras crear tus propios programas y hacerlos funcionar" + Constants.lineSeparator
				+ Constants.lineSeparator + "1. En la parte superior tendras 5 botones:"
				+ Constants.lineSeparator + "   - Step: Para ejecutar la siguiente instrucción."
				+ Constants.lineSeparator + "   - Run: Para que la máquina ejecute automáticamente todas las instrucciones."
				+ Constants.lineSeparator + "   - Pause: Para pausar la ejecución de la máquina."
				+ Constants.lineSeparator + "   - Reset: Para dejar el estado de la máquina como al principio."
				+ Constants.lineSeparator + "   - Quit: Para salir del programa." + Constants.lineSeparator 
				+ Constants.lineSeparator + "2. En la parte izquierda podrás ver tu programa cargado con un asterisco " 
				+ Constants.lineSeparator + "indicando la instrucción que se va a ejecutar:" + Constants.lineSeparator
				+ Constants.lineSeparator + "3. La pila de operandos indica el estado de la pila de la máquina." 
				+ Constants.lineSeparator + "   - Push: Para modificar en tiempo real la pila con el valor que tu quieras."
				+ Constants.lineSeparator + "   - Pop: Para eliminar en tiempo real la cima de la pila." + Constants.lineSeparator
				+ Constants.lineSeparator + "4. La memoria de la máquina indica el estado de la memoria de la máquina:"
				+ Constants.lineSeparator + "   - Write: Escribir en la memoria el valor que tu indiques en la posición que tu desees." + Constants.lineSeparator
				+ Constants.lineSeparator + "5. En la entrada podrás ver la entrada que hayas introducido.Los asteriscos indicaran los" 
				+ Constants.lineSeparator +	"caracteres ya leidos."+ Constants.lineSeparator
				+ Constants.lineSeparator + "6. En la salida podrás ver en tiempo real el resultado de tu programa." + Constants.lineSeparator
				+ Constants.lineSeparator + "7. Aquí podrás ver el estado de tu máquina así como: Ejecutando, el estado de la máquina,"
				+ Constants.lineSeparator + "el número de instrucciones ejecutadas y que ha modificado la última instruccion ejecutada.";
				
		JTextArea text = new JTextArea(texto);
		text.setFont(new Font("Comic Sans SM", Font.BOLD, 12));
		text.setEditable(false);
		text.setBackground(new Color(232,254, 255));
		
		JLabel image = new JLabel(new ImageIcon((getClass().getResource("Icons/helpPanel.png"))));
		JScrollPane splitPane = new JScrollPane(text);
		
		JPanel aux = new JPanel(new BorderLayout());
		aux.add(image, BorderLayout.NORTH);
		aux.add(splitPane);
		aux.setBackground(new Color(232,254, 255));
		
		this.add(aux);
	}
}
