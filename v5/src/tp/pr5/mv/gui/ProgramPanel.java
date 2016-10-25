package tp.pr5.mv.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.observables.CPUObserver;
import tp.pr5.mv.observables.ProgramObserver;

/**
 * Clase que representa el panel del programa
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class ProgramPanel extends JPanel implements ProgramObserver , CPUObserver{
	
	private JTextArea programText;
	
	/**
	 * Constructora del panel del programa
	 */
	public ProgramPanel() {
		initProgramPanel();
	} 

	/**
	 * Inicializa el panel del programa
	 */
	private void initProgramPanel() {
		
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Programa"));

		this.setPreferredSize(new Dimension(250,50));
		
		programText = new JTextArea(10,20);
		programText.setEditable(false);
		programText.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		JScrollPane scrollProgram = new JScrollPane(programText);
		this.add(scrollProgram);
	
	}

	/**
	 * Actualiza la lista de instrucciones con el programa pasado por parámetro
	 */
	@Override
	public void initProgramInstructions(final String set) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				programText.setText(set);
			}
		});	
	}
	
	/**
	 * Cambia la fuente de los componentes
	 * @param font La nueva fuente
	 * @param i
	 */
	public void changeFontColor(String font, int tam) {
		programText.setFont(new Font(font,  Font.BOLD ,tam));		
	}
	
	@Override
	public void programEnd(boolean end) {
	}

	@Override
	public void initProgram(String set) {
	}

	@Override
	public void showCPUState(String cpuState) {
	}
	
	@Override
	public void instructionStarting(String msg){
	}

	@Override
	public void machineStopped() {		
	}
	
	@Override
	public void machineStarted() {
	}

	@Override
	public void onReset() {
	}

	@Override
	public void raiseError(String message) {
	}

	@Override
	public void requestQuit() {
	}

}
