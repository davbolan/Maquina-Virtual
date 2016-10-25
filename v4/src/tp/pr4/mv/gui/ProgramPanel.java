package tp.pr4.mv.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr4.mv.observables.CPUObserver;
import tp.pr4.mv.observables.ProgramObserver;

@SuppressWarnings("serial")
public class ProgramPanel extends JPanel implements ProgramObserver , CPUObserver{
	
	private JTextArea programText;
	
	public ProgramPanel() {
		initProgramPanel();
	} 

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


	@Override
	public void pcUpdate(int pc) {
		programText.setCaretPosition(pc);
	}

	@Override
	public void raiseError(String message) {
	}

	@Override
	public void requestQuit() {
	}

	@Override
	public void initProgramInstructions(String set) {
		programText.setText(set);
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

	
}
