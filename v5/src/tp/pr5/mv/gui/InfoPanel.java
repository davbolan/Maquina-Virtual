package tp.pr5.mv.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import tp.pr5.mv.Controller;

/**
 * Panel que representa los paneles CPUPanel e IOPanel, 
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class InfoPanel extends JPanel{
	
	private ProgramPanel programPanel;
	private JPanel cpuIoPanel;
	private CPUPanel cpuPanel;
	private IOPanel ioPanel;
	private StatePanel statePanel;
	private Controller controller;
	
	/**
	 * Constructora encargada de inicializar sus componentes internos.
	 * @param controller el controlador
	 */
	public InfoPanel(Controller controller) {
		this.controller = controller;
		initInfoPanel();
		controller.registerMemoryObserver(cpuPanel);
		controller.registerStackObserver(cpuPanel);
		controller.registerCPUObserver(cpuPanel);
		controller.registerProgramObserver(programPanel);
		controller.registerCPUObserver(programPanel);
		controller.registerInOutObverser(ioPanel);
		controller.registerMemoryObserver(statePanel);
		controller.registerStackObserver(statePanel);
		controller.registerCPUObserver(statePanel);
	}

	/**
	 * Inicializa el infoPanel
	 */
	private void initInfoPanel() {
		this.setLayout(new BorderLayout());
		
		programPanel = new ProgramPanel();
		cpuIoPanel = new JPanel(new GridLayout(2,1));
		
		cpuPanel = new CPUPanel(controller);		
		ioPanel = new IOPanel(controller);
		
		statePanel = new StatePanel();
		
		cpuIoPanel.add(cpuPanel);
		cpuIoPanel.add(ioPanel);
		
		programPanel.setOpaque(false);
		cpuIoPanel.setOpaque(false);
		ioPanel.setOpaque(false);
		cpuPanel.setOpaque(false);
		statePanel.setOpaque(false);
		
		this.add(programPanel,BorderLayout.LINE_START );
		this.add(cpuIoPanel, BorderLayout.CENTER);
		this.add(statePanel, BorderLayout.PAGE_END);
		
		cpuIoPanel.setPreferredSize(new Dimension(500,30));	
	}


	/**
	 * Cambia el color del panel
	 * @param color
	 */
	public void changeColor(Color color) {
		this.setBackground(color);
		cpuIoPanel.setBackground(color);
	}

	/**
	 * Cambia la fuente de los componentes
	 * @param font La nueva fuente
	 * @param i
	 */
	public void changeFontColor(String font, int tam) {
		programPanel.changeFontColor(font, tam);
		ioPanel.changeFontColor(font, tam);
		cpuPanel.changeFontColor(font, tam);
	}
}
