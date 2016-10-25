package tp.pr5.mv.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.Controller;
import tp.pr5.mv.observables.InOutObserver;

/**
 * Clase que representa los paneles de la entrada y salida de la CPU
 * @author Lidia Flores, David Bolaños
 */
@SuppressWarnings("serial")
public class IOPanel extends JPanel implements InOutObserver{
	
	// InPanel
	private JPanel inPanel;
	private JTextArea inText;
	
	// OutPanel
	private JPanel outPanel;
	private JTextArea outText;

	/**
	 * Constructora encargada de inicializar sus componentes internos.
	 * @param controller el controlador
	 */
	public IOPanel(Controller controller) {
		this.setLayout(new GridLayout(2,1));		
		initInPanel();
		initOutPanel();	
	}

	/**
	 * Inicializa el panel de entrada
	 */
	private void initInPanel() {
		inPanel = new JPanel();
		inPanel.setOpaque(false);
		inText = new JTextArea(10,60);
		inText.setEditable(false);
		inPanel.setLayout(new BorderLayout());
		inPanel.setBorder(new TitledBorder("Entrada del programa-p"));
		
		JScrollPane inScroll = new JScrollPane(inText);
		inPanel.add(inScroll);
		this.add(inPanel);
	}

	/**
	 * Inicializa el panel de salida
	 */
	private void initOutPanel() {
		outPanel = new JPanel();
		outPanel.setOpaque(false);
		outText = new JTextArea(10,60);
		outText.setEditable(false);
		outPanel.setLayout(new BorderLayout());
		outPanel.setBorder(new TitledBorder("Salida del programa-p"));
		
		JScrollPane outScroll = new JScrollPane(outText);
		outPanel.add(outScroll);
		this.add(outPanel);
	}
	
	/**
	 * Actualiza el panel de entrada
	 * @param program la nueva entrada
	 */
	public void updateInPanel(final String program){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				inText.setText(program);
			}
		});
	}
		
	/**
	 * Actualiza el panel de entrada, cambiando el carácter leído por un asterisco
	 * @param program la nueva entrada
	 */
	@Override
	public void inChanged(final int pos) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StringBuilder content = new StringBuilder(inText.getText());
				content.setCharAt(pos, '*');
				inText.setText(content.toString());
			}
		});
	}

	/**
	 * Actualiza el panel de salida
	 * @param program la nueva salida
	 */
	@Override
	public void outChanged(final char car) {	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String newText = outText.getText() + car;
				outText.setText(newText);	
			}
		});
	}

	/**
	 * Cambia la entrada inicial
	 */
	@Override
	public void initTextIn(final String initText) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				inText.setText(initText);	
			}
		});
	}

	/**
	 * Limpia la salida
	 */
	@Override
	public void cleanOut() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				outText.setText("");
			}
		});
	}

	/**
	 * Cambia la fuente de los componentes
	 * @param font La nueva fuente
	 * @param i
	 */
	public void changeFontColor(String font, int tam) {
		inText.setFont(new Font(font,  Font.BOLD ,tam));	
		outText.setFont(new Font(font,  Font.BOLD ,tam));	
	}
}
