package tp.pr4.mv.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import tp.pr4.mv.Controller;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel{
	
	private ProgramPanel programPanel;
	private JPanel cpuIoPanel;
	private CPUPanel cpuPanel;
	private IOPanel ioPanel;
	private Controller controller;
	
	
	public InfoPanel(Controller controller) {
		this.controller = controller;
		initInfoPanel();
		controller.registerMemoryObserver(cpuPanel);
		controller.registerStackObserver(cpuPanel);
		controller.registerProgramObserver(programPanel);
		controller.registerCPUObserver(programPanel);
		controller.registerInOutObverser(ioPanel);
	}


	private void initInfoPanel() {
		this.setLayout(new BorderLayout());
		
		programPanel = new ProgramPanel();
		cpuIoPanel = new JPanel(new GridLayout(2,1));
		
		cpuPanel = new CPUPanel(controller);		
		ioPanel = new IOPanel(controller);
		
		cpuIoPanel.add(cpuPanel);
		cpuIoPanel.add(ioPanel);
		
		this.add(programPanel,BorderLayout.LINE_START );
		this.add(cpuIoPanel, BorderLayout.CENTER);
		
		cpuIoPanel.setPreferredSize(new Dimension(500,30));	
	}

}
