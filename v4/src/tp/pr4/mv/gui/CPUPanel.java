package tp.pr4.mv.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr4.mv.Controller;
import tp.pr4.mv.Memory;
import tp.pr4.mv.observables.MemoryObserver;
import tp.pr4.mv.observables.StackObserver;

@SuppressWarnings("serial")
public class CPUPanel extends JPanel implements MemoryObserver, StackObserver<Integer>{
	
	// Pila de operandos
	private JPanel stackPanel; 
	private JList<Integer> stackList;
	private DefaultListModel<Integer> listModel;
	private JTextField valueStack;
	private JButton pushButton;
	private JButton popButton;
	
	// Tabla de memoria
	private JPanel memoryPanel;
	private JTable table;
	private MemoryTable memTable;
	private JTextField valueMem;
	private JTextField posMem;
	private JButton writeButton;
	
	private Controller controller;
	
	public CPUPanel(Controller controller) {
		this.setLayout(new GridLayout());
		initStackPanel();
		initMemoryPanel();
		this.controller = controller;
		FixButtons();
	}

	private void initStackPanel() {
		stackPanel = new JPanel();
		stackPanel.setLayout(new BorderLayout());
		stackPanel.setBorder(new TitledBorder("Pila de Operandos"));
	
		//Jlist
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		listModel = new DefaultListModel<Integer>();
		stackList = new JList<Integer>(listModel);
		JScrollPane scrollValue = new JScrollPane(stackList);
		listPanel.add(scrollValue);
		
		//TextField y Botones
		JLabel value = new JLabel("Valor:");	
		valueStack = new JTextField();
		valueStack.setName("valueStack");
		valueStack.setPreferredSize(new Dimension(70,27));
		
		pushButton = new JButton();
		pushButton.setName("PushButton");
		pushButton.setText("PUSH");
		
		popButton = new JButton();
		popButton.setName("PopButton");
		popButton.setText("POP");
		
		JPanel miniPanel = new JPanel();		
		miniPanel.add(value);
		miniPanel.add(valueStack);
		miniPanel.add(pushButton);
		miniPanel.add(popButton);	
		
		stackPanel.add(listPanel, BorderLayout.CENTER);
		stackPanel.add(miniPanel, BorderLayout.PAGE_END);
		
		this.add(stackPanel, BorderLayout.LINE_START);
	
	}
	
	private void initMemoryPanel() {
		memoryPanel = new JPanel();
		memoryPanel.setLayout(new BorderLayout());
		memoryPanel.setBorder(new TitledBorder("Memoria de la maquina"));
		
		memTable = new MemoryTable();
		table = new JTable(memTable);
		JScrollPane scrollTable = new JScrollPane(table);

		//Tabla de memoria y Botones			
		JLabel pos = new JLabel("Pos:");
		posMem = new JTextField();
		posMem.setName("posMem");
		posMem.setPreferredSize(new Dimension(70,27));
	
		JLabel val = new JLabel("Val:");
		valueMem = new JTextField();
		valueMem.setName("valueMem");
		valueMem.setPreferredSize(new Dimension(70,27));
		
		writeButton = new JButton();
		writeButton.setName("WriteButton");
		writeButton.setText("WRITE");
		
		JPanel miniPanel = new JPanel();
		miniPanel.setLayout(new FlowLayout());
		miniPanel.add(pos);
		miniPanel.add(posMem);
		miniPanel.add(val);
		miniPanel.add(valueMem);
		miniPanel.add(writeButton);
	
		memoryPanel.add(scrollTable, BorderLayout.CENTER);
		memoryPanel.add(miniPanel, BorderLayout.PAGE_END);
		
		this.add(memoryPanel, BorderLayout.LINE_END);
	
	}


	private void FixButtons(){
		fixPush();
		fixPop();
		fixWrite();
	}
	

	private void fixPush(){
		pushButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = valueStack.getText();
				if(id.isEmpty())
					raiseError("No es valida una instruccion vacia");
				else
					controller.executePushCommand(id);	
				
				
			}
		});
	}
	
	
	private void fixPop(){
		popButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controller.executePopCommand();
			}
		});
	}
	
	
	private void fixWrite() {
		writeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String value = valueMem.getText();
				String pos = posMem.getText();
				if(value.isEmpty() || pos.isEmpty()){
					raiseError("No es valida una instruccion vacia");
				}
				else{
					controller.executeWriteCommand(pos, value);	
				}		
			}
		});
	}
	
	
	public void raiseError(String message) {
		JOptionPane.showOptionDialog(null, message, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
	}

	private class MemoryTable extends AbstractTableModel {

		String[] columnNames = { "DIRECCION", "VALOR"};
		Object[][] rowData;
		
		public MemoryTable(){			
			rowData = new Object[0][columnNames.length];	
		}
		

		@SuppressWarnings("unchecked")
		public void refresh(Object[] memory){
			rowData = new Object[memory.length][columnNames.length];
			
			for(int row = 0; row < memory.length; row++){
				setValueAt(((Memory.MemCell<Integer>)memory[row]).getPos(), row, 0);
				setValueAt((int)((Memory.MemCell<Integer>)memory[row]).getValue(), row, 1);
			}		
				
			CPUPanel.this.memTable.fireTableDataChanged();
		}
		
		public String getColumnName(int col) {
			return columnNames[col];
		}
		
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return rowData.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			return rowData[row][col];
		}
		
		public void setValueAt(Object obj, int row, int col) {
			rowData[row][col] = obj;
			fireTableCellUpdated(row, col);
		}
		public boolean isCellEditable(int row, int col) {
			return false;
		}	
	}

	@Override
	public void memoryUpdate(Object[] memory) {
		memTable.refresh(memory);
	}

	@Override
	public void initStack(ArrayList<Integer> stack) {
		listModel.clear();
		for(int i = 0; i < stack.size(); i++)
			listModel.add(i, stack.get(i));
	}
}
