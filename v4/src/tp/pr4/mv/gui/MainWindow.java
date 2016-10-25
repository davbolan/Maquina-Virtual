package tp.pr4.mv.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr4.mv.CPU;
import tp.pr4.mv.Controller;
import tp.pr4.mv.observables.CPUObserver;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements CPUObserver{
	
	private ActionsPanel actionsPanel;
	private InfoPanel infoPanel;
	private Container mainPanel;
	private CPU cpu;
	private Controller controller;
	
	
	public MainWindow(CPU cpu){
		super("Maquina virtual de Lidia Flores y David Bolanios");
		this.cpu = cpu;
		initMainWindow();
		this.controller.registerCPUObserver(this);
		this.cpu.requestStart();
	}

	private void initMainWindow() {		
		this.controller = new Controller(cpu);
		this.setPreferredSize (new Dimension (870, 680));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		
		mainPanel = this.getContentPane();
		initActionsPanel();
		initInfoPanel();
	
		mainPanel.add(actionsPanel, BorderLayout.NORTH);
		mainPanel.add(infoPanel, BorderLayout.CENTER);
		
		this.setCloseOperation();
	}


	private void setCloseOperation() {
		this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
            	actionsPanel.optionExit();
            }
        }
		);	
	}

	private void initInfoPanel() {
		infoPanel = new InfoPanel(this.controller);
		infoPanel.setVisible(true);
	}

	private void initActionsPanel() {
		actionsPanel = new ActionsPanel(this.controller);
		actionsPanel.setVisible(true);
	}
		
	public void startMv(){
		this.pack();							// coloca los componentes y los ajusta al espacio
		setLocationRelativeTo(null);			// centra la ventana en la pantalla 
		EventQueue.invokeLater(new Runnable(){
        	public void run() {
        		setVisible(true);
        	}
        });		
	}


	@Override
	public void raiseError(String message) {
		JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void requestQuit() {	
	}


	@Override
	public void pcUpdate(int pc) {		
	}

	@Override
	public void programEnd(boolean end) {
	}


	@Override
	public void instructionStarting(String msg){
	}

	@Override
	public void showCPUState(String string) {
	}

	
}
