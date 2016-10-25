package tp.pr4.mv.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr4.mv.Controller;
import tp.pr4.mv.observables.InOutObserver;

@SuppressWarnings("serial")
public class IOPanel extends JPanel implements InOutObserver{
	
	// InPanel
	private JPanel inPanel;
	private JTextArea inText;
	
	// OutPanel
	private JPanel outPanel;
	private JTextArea outText;

	public IOPanel(Controller controller) {
		this.setLayout(new GridLayout(2,1));		
		initInPanel();
		initOutPanel();	
	}


	private void initInPanel() {
		inPanel = new JPanel();
		inText = new JTextArea(10,60);
		inText.setEditable(false);
		inPanel.setLayout(new BorderLayout());
		inPanel.setBorder(new TitledBorder("Entrada del programa-p"));
		
		JScrollPane inScroll = new JScrollPane(inText);
		inPanel.add(inScroll);
		this.add(inPanel);
	}


	private void initOutPanel() {
		outPanel = new JPanel();
		outText = new JTextArea(10,60);
		outText.setEditable(false);
		outPanel.setLayout(new BorderLayout());
		outPanel.setBorder(new TitledBorder("Salida del programa-p"));
		
		JScrollPane outScroll = new JScrollPane(outText);
		outPanel.add(outScroll);
		this.add(outPanel);
	}
	
	
	public void updateInPanel(String program){
		inText.setText(program);
	}


	@Override
	public void inChanged(int pos) {
		StringBuilder content = new StringBuilder(inText.getText());
		content.setCharAt(pos, '*');
		inText.setText(content.toString());
	}


	@Override
	public void outChanged(char car) {	
		String newText = outText.getText() + car;
		this.outText.setText(newText);	
	}


	@Override
	public void initTextIn(String initText) {
		this.inText.setText(initText);	
	}

}
