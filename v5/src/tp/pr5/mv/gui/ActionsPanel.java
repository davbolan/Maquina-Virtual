package tp.pr5.mv.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.Controller;
import tp.pr5.mv.observables.CPUObserver;

/**
 * Clase que representa el panel de los botones de la parte superior de la interfaz
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class ActionsPanel extends JPanel implements CPUObserver{

	private JButton stepButton;
	private JButton runButton;
	private JButton exitButton;
	private JButton pauseButton;
	private JButton resetButton;
	private JButton undoButton;
	private Controller controller;
	

	/**
	 * Construye un panel de botones
	 * @param controller el controlador
	 */
	public ActionsPanel(Controller controller) {
		this.controller = controller;
		this.controller.registerCPUObserver(this);
		initActionsPanel();
	}

	/**
	 * Inicializa el panel de los botones
	 */
	private void initActionsPanel() {
		
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Acciones"));
		initButtons();	
	}

	/**
	 * Inicializa los botones
	 */
	private void initButtons() {
		 JPanel buttonsPanel = new JPanel();	
			
		//BOTONES
		stepButton = new JButton();
		stepButton.setName("ButtonStep");
		stepButton.setIcon(new ImageIcon(getClass().getResource("Icons/step.png")));				
				
		runButton = new JButton();
		runButton.setName("ButtonRun");
		runButton.setIcon(new ImageIcon(getClass().getResource("Icons/run.png")));
			
		pauseButton = new JButton();
		pauseButton.setName("ButtonPause");
		pauseButton.setIcon(new ImageIcon(getClass().getResource("Icons/pause.png")));
		pauseButton.setEnabled(false);
		
		resetButton = new JButton();
		resetButton.setName("ButtonReset");
		resetButton.setIcon(new ImageIcon(getClass().getResource("Icons/reset.png")));
		
		undoButton = new JButton();
		undoButton.setName("ButtonUndo");
		undoButton.setIcon(new ImageIcon(getClass().getResource("Icons/undo1.png")));
		
		exitButton = new JButton();
		exitButton.setName("ButtonExit");
		exitButton.setIcon(new ImageIcon(getClass().getResource("Icons/exit.png")));
		
		// AÑADIMOS BOTONES	
		buttonsPanel.add(stepButton);
		buttonsPanel.add(runButton);
		buttonsPanel.add(pauseButton);
		buttonsPanel.add(resetButton);
		buttonsPanel.add(undoButton);
		buttonsPanel.add(exitButton);
		buttonsPanel.setOpaque(false);
		
		this.add(buttonsPanel, BorderLayout.CENTER);
		this.setVisible(true);
		fixButtons();
			
	}

	/**
	 * Fija los actionListener de cada boton
	 */
	private void fixButtons(){
		fixStepButton();
		fixRunButton();
		fixPauseButton();
		fixResetButton();
		fixQuitButton();
	}

	/**
	 * Fija el actionListener del botón STEP
	 */
	private void fixStepButton() {
		stepButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controller.executeStepCommand();
			}
		});	
	}
	
	/**
	 * Fija el actionListener del botón RUN
	 */
	private void fixRunButton() {
		runButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				new Thread() {
					public void run() {
					controller.executeRunCommand();
					}
				}.start();	
			}
		});
	}
	
	/**
	 * Fija el actionListener del botón PAUSE
	 */
	private void fixPauseButton() {
		pauseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controller.executePauseCommand();
			}
		});
	}
	
	/**
	 * Fija el actionListener del botón RESET   
	 */
	private void fixResetButton() {
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controller.executeResetCommand();
			}
		});	
	}
	
	/**
	 * Fija el actionListener del botón QUIT   
	 */
	private void fixQuitButton() {
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				optionExit();
			}
		});
	}

	/**
	 * Muestra una ventana de dialogo preguntando al usuario si quiere salir. Si es asi, se guardan los datos
	 * y la máquina virtual se cierra.
	 */
	protected void optionExit() {
		int option = JOptionPane.showOptionDialog(null,"¿Quieres salir?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		if(option == JOptionPane.YES_OPTION){
			controller.executeQuitCommand();
			System.exit(0);
		}
	}

	/**
	 * Reactiva los botones correspondientes en caso de error.
	 */
	@Override
	public void raiseError(String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stepButton.setEnabled(true);
				runButton.setEnabled(true);
				pauseButton.setEnabled(false);
				resetButton.setEnabled(true);
			}
		});
	}

	/**
	 * Desactiva los botones correspondientes cuando la CPU finalizado
	 */
	@Override
	public void programEnd(boolean end) {
		if(end){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					stepButton.setEnabled(false);
					runButton.setEnabled(false);
					pauseButton.setEnabled(false);
					resetButton.setEnabled(true);
				}
			});
		}
	}

	/**
	 * Reactiva los botones correspondientes cuando la CPU ha sido parada
	 */
	@Override
	public void machineStopped() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stepButton.setEnabled(true);
				runButton.setEnabled(true);
				pauseButton.setEnabled(false);
				resetButton.setEnabled(true);
			}
		});
	}

	/**
	 * Desacctiva los botones correspondientes cuando la CPU empieza a ejecutarse
	 */
	@Override
	public void machineStarted() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stepButton.setEnabled(false);
				runButton.setEnabled(false);
				resetButton.setEnabled(false);
				pauseButton.setEnabled(true);
			}
		});
	}
	
	/**
	 * Reactiva los botones correspondientes cuando la CPU ha sido reseteada
	 */
	@Override
	public void onReset() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stepButton.setEnabled(true);
				runButton.setEnabled(true);
				pauseButton.setEnabled(false);
			}
		});
	}

	/**
	 * Cambia la fuente de los componentes
	 * @param font La nueva fuente
	 * @param i
	 */
	public void changeColor(Color color) {
		this.setBackground(color);
	}

	@Override
	public void requestQuit() {
	}
	
	@Override
	public void showCPUState(String string) {
	}

	@Override
	public void instructionStarting(String msg) {
	}
	
}
