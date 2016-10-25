package tp.pr5.mv.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr5.mv.Controller;
import tp.pr5.mv.observables.CPUObserver;
import tp.pr5.mv.observables.MemoryObserver;
import tp.pr5.mv.observables.StackObserver;

/**
 * Clase que representa el panel de la pila y la memoria de la CPU
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class CPUPanel extends JPanel implements MemoryObserver<Integer>, StackObserver<Integer>, CPUObserver{
	
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
	
	/**
	 * Constructora encargada de inicializar sus componentes internos.
	 * @param controller el controlador
	 */
	public CPUPanel(Controller controller) {
		this.setLayout(new GridLayout());
		initStackPanel();
		initMemoryPanel();
		this.controller = controller;
		fixButtons();
	}

	/**
	 * Inicializa el panel de la pila
	 */
	private void initStackPanel() {
		stackPanel = new JPanel();
		stackPanel.setLayout(new BorderLayout());
		stackPanel.setBorder(new TitledBorder("Pila de Operandos"));
		stackPanel.setOpaque(false);
		
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
		miniPanel.setOpaque(false);
		miniPanel.add(value);
		miniPanel.add(valueStack);
		miniPanel.add(pushButton);
		miniPanel.add(popButton);	
		
		stackPanel.add(listPanel, BorderLayout.CENTER);
		stackPanel.add(miniPanel, BorderLayout.PAGE_END);
		
		this.add(stackPanel, BorderLayout.LINE_START);
	
	}
	
	/**
	 * Inicializa el panel de memoria
	 */
	private void initMemoryPanel() {
		memoryPanel = new JPanel();
		memoryPanel.setLayout(new BorderLayout());
		memoryPanel.setBorder(new TitledBorder("Memoria de la maquina"));
		
		memoryPanel.setOpaque(false);
		
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
		miniPanel.setOpaque(false);
		miniPanel.add(pos);
		miniPanel.add(posMem);
		miniPanel.add(val);
		miniPanel.add(valueMem);
		miniPanel.add(writeButton);
	
		memoryPanel.add(scrollTable, BorderLayout.CENTER);
		memoryPanel.add(miniPanel, BorderLayout.PAGE_END);
		
		this.add(memoryPanel, BorderLayout.LINE_END);
	}
	
	/**
	 * Fija los actionListener de cada botón
	 */
	private void fixButtons(){
		fixPush();
		fixPop();
		fixWrite();
	}
	
	/**
	 * Fija la operación a realizar al mostrar el botón PUSH
	 */
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
	
	/**
	 * Fija la operación a realizar al mostrar el botón POP
	 */
	private void fixPop(){
		popButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				controller.executePopCommand();
			}
		});
	}
	
	/**
	 * Fija la operación a realizar al mostrar el botón WRITE
	 */
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
	
	/**
	 * Activa los botones al haber un error
	 */
	public void raiseError(String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				pushButton.setEnabled(true);
				popButton.setEnabled(true);
				writeButton.setEnabled(true);
			}
		});	
	}

	/**
	 * Clase interna que representa la tabla de memoria
	 * @author Lidia Flores, David Bolanios
	 */
	private class MemoryTable extends AbstractTableModel {

		protected String[] columnNames = { "DIRECCION", "VALOR"};
		protected TreeMap<Integer, Integer> content; 

		/**
		 * Construye una Tabla con un contenido vacío
		 */
		public MemoryTable(){			
			content = new TreeMap<Integer, Integer>();
		}
			
		/**
		 * Actualiza la  celda correspondiente de la tabla con el dato de memoria 
		 */
		public void setValue(int index, int value) {
			content.put(index, (Integer) value );
			memTable.fireTableDataChanged();
		}
		
		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}
		
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return content.keySet().size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			if(col == 0)
				return content.keySet().toArray()[row];
			else
				return content.values().toArray()[row];
		}
		
		@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}	
		
		public void clear(){
			content.clear();
			memTable.fireTableDataChanged();
		}
	}

	/**
	 * Limpia la lista de la pila
	 */
	@Override
	public void clearStack() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				listModel.clear();
			}
		});
	}

	/**
	 * Añade un elemento a la lista de la pila
	 */
	@Override
	public void newElem(final int number) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				listModel.addElement(number);
			}
		});
		
	}

	/**
	 * Añade el último elemento de la lista de la pila
	 */
	@Override
	public void quitElem() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(!listModel.isEmpty())
					listModel.remove(listModel.size()-1);
				}
		});	
	}

	/**
	 * Actualiza la celda correspondiente de la tabla con el dato de memoria 
	 */
	@Override
	public void onWrite(final int index, final Integer value) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				memTable.setValue(index, value);
			}
		});

	}

	/**
	 * Actualiza la  celda correspondiente de la tabla con el dato de memoria 
	 */
	@Override
	public void resetMemory() {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				memTable.clear();
			}
		});	
	}


	/**
	 * Desactiva los botones correspondientes si la CPU ha finalizado
	 */
	@Override
	public void programEnd(final boolean end) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				pushButton.setEnabled(end);
				popButton.setEnabled(end);
				writeButton.setEnabled(end);
			}
		});	
		
	}

	/**
	 * Activa los botones correspondientes al parar la CPU
	 */
	@Override
	public void machineStopped(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				pushButton.setEnabled(true);
				popButton.setEnabled(true);
				writeButton.setEnabled(true);
			}
		});	
	}

	/**
	 * Activa los botones correspondientes al parar la CPU
	 */
	@Override
	public void machineStarted(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				pushButton.setEnabled(false);
				popButton.setEnabled(false);
				writeButton.setEnabled(false);
			}
		});	
	}

	/**
	 * Activa los botones correspondientes al resetear la CPU
	 */
	@Override
	public void onReset(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				pushButton.setEnabled(true);
				popButton.setEnabled(true);
				writeButton.setEnabled(true);
			}
		});	
	}

	/**
	 * Cambia la fuente de los componentes
	 * @param font La nueva fuente
	 * @param tam El tamaño de la fuente
	 */
	public void changeFontColor(String font, int tam){
		stackList.setFont(new Font(font, Font.BOLD, tam));
	}	
	
	@Override
	public void requestQuit(){
	}
	
	@Override
	public void showCPUState(String string){
	}

	@Override
	public void instructionStarting(String msg){
	}

}
