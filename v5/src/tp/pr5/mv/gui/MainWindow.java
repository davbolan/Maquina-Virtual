package tp.pr5.mv.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.mv.CPU;
import tp.pr5.mv.Controller;
import tp.pr5.mv.observables.CPUObserver;

/**
 * Clase que representa el modo WINDOW, en la cuál el usuario puede ejecutar los comandos de forma gráfica e 
 * interactiva
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame implements CPUObserver{
	
	private ActionsPanel actionsPanel;
	private InfoPanel infoPanel;
	private Container mainPanel;
	private MenuOption menu;
	private CPU cpu;
	private Controller controller;
		
	/**
	 * Crea la ventan con el modelo a observar
	 * @param cpu El modelo a observar
	 */
	public MainWindow(CPU cpu){
		super("Maquina virtual LFYDB");
		this.cpu = cpu;
		initMainWindow();
		this.controller.registerCPUObserver(this);
		this.cpu.requestStart();
	}

	/**
	 * Inicializa los componentes de la ventana
	 */
	private void initMainWindow() {		
		
		JPanel aux = new JPanel(new BorderLayout());
		this.controller = new Controller(cpu);
		this.setPreferredSize (new Dimension (870, 680));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		
		mainPanel = this.getContentPane();
		menu = new MenuOption(controller, this);
		initActionsPanel();
		initInfoPanel();
		
		mainPanel.add(menu, BorderLayout.NORTH);
		aux.add(actionsPanel, BorderLayout.NORTH);
		aux.add(infoPanel, BorderLayout.CENTER);
		mainPanel.add(aux, BorderLayout.CENTER);
		
		this.setIconImage(new ImageIcon(getClass().getResource("Icons/lmm.png")).getImage());
		
		this.setCloseOperation();
	}

	/**
	 * Establece la operación por defecto que se debe hacer al pulsar el botón salir de la ventana
	 */
	private void setCloseOperation() {
		this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
            	actionsPanel.optionExit();
            }
        }
		);	
	}

	/**
	 * Inicializa el panel infoPanel, que contiene el pane de la meoria y la pila, y el panel con la entrada y salida.
	 */
	private void initInfoPanel() {
		infoPanel = new InfoPanel(this.controller);
		infoPanel.setVisible(true);
	}

	/**
	 * Inicializa el panel de botones
	 */
	private void initActionsPanel() {
		actionsPanel = new ActionsPanel(this.controller);
		actionsPanel.setVisible(true);
	}
		
	/**
	 * Muestra la ventana
	 */
	public void startMv(){
		this.pack();							// coloca los componentes y los ajusta al espacio
		setLocationRelativeTo(null);			// centra la ventana en la pantalla 
		setVisible(true);        	
	}

	/**
	 * Cambia el color del panel
	 * @param color
	 */
	public void changeColor(Color color) {
		actionsPanel.changeColor(color);
		infoPanel.changeColor(color);
	}
	
	/**
	 * Cambia la fuente de los componentes
	 * @param font La nueva fuente
	 * @param i
	 */
	public void changeFontColor(String font, int i) {
		infoPanel.changeFontColor(font, 14);
	}
	
	/**
	 * Muestra en una ventana de diálogo con el error producido
	 */
	@Override
	public void raiseError(String message) {
		JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void requestQuit(){	
	}

	@Override
	public void programEnd(boolean end){	
	}

	@Override
	public void instructionStarting(String msg){	
	}
	@Override
	public void showCPUState(String string){	
	}

	@Override
	public void machineStopped(){	
	}

	@Override
	public void machineStarted(){	
	}

	@Override
	public void onReset(){	
	}
}
