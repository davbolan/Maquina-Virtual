package tp.pr4.mv.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr4.mv.Controller;
import tp.pr4.mv.observables.CPUObserver;

@SuppressWarnings("serial")
public class ActionsPanel extends JPanel implements CPUObserver{

	private JButton stepButton;
	private JButton runButton;
	private JButton exitButton;
	private Controller controller;
	

	public ActionsPanel(Controller controller) {
		this.controller = controller;
		this.controller.registerCPUObserver(this);
		initActionsPanel();
	}


	private void initActionsPanel() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Acciones"));
		initButtons();	
	}


	private void initButtons() {
		JPanel buttonsPanel = new JPanel();	
			
		//BOTONES
		stepButton = new JButton();
		stepButton.setName("ButtonStep");
		stepButton.setIcon(new ImageIcon(getClass().getResource("Icons/step.png")));				
				
		runButton = new JButton();
		runButton.setName("ButtonRun");
		runButton.setIcon(new ImageIcon(getClass().getResource("Icons/run.png")));
			
		exitButton = new JButton();
		exitButton.setName("ButtonExit");
		exitButton.setIcon(new ImageIcon(getClass().getResource("Icons/exit.png")));
		
		// AÑADIMOS BOTONES	
		buttonsPanel.add(stepButton);
		buttonsPanel.add(runButton);
		buttonsPanel.add(exitButton);
		
		this.add(buttonsPanel, BorderLayout.CENTER);
		this.setVisible(true);
		fixButtons();
			
	}

	
	private void fixButtons(){
		fixStepButton();
		fixRunButton();
		fixQuitButton();
	}


	private void fixStepButton() {
		stepButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controller.executeStepCommand();
			}
		});
		
	}

	private void fixRunButton() {
		runButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controller.executeRunCommand();
			}
		});
	}

	private void fixQuitButton() {
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				optionExit();
			}
		});
	}

	protected void optionExit() {
		int option = JOptionPane.showOptionDialog(null,"¿Quieres salir?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		if(option == JOptionPane.YES_OPTION){
			controller.executeQuitCommand();
			System.exit(0);
		}
	}


	@Override
	public void pcUpdate(int pc) {
	}


	@Override
	public void raiseError(String message) {
	}


	@Override
	public void requestQuit() {
	}


	@Override
	public void programEnd(boolean end) {
		if(end){
			stepButton.setEnabled(false);
			runButton.setEnabled(false);
		}
	}


	@Override
	public void showCPUState(String string) {
	}

	@Override
	public void instructionStarting(String msg) {
	}
	
	

	
}
