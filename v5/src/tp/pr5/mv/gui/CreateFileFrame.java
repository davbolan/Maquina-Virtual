package tp.pr5.mv.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import tp.pr5.mv.Controller;

/**
 * Clase que representa a ventana para la creación de un nuevo archivo
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class CreateFileFrame extends JDialog{

	private JPanel newFilePanel;
	private JTextArea programa;
	private JButton acceptButton;
	private JButton cancelButton;
	private Controller controller;
	
	/**
	 * Construye una ventana para la creación de un nuevo archivo
	 * @param main La ventana padre principal
	 * @param b si es modal o no
	 * @param controller el controlador
	 */
	public CreateFileFrame(MainWindow main, boolean b, Controller controller) {
		super(main, b);
		this.controller = controller;
		initcreateFileFrame();	
	}

	/**
	 * Inicializa la ventana
	 */
	private void initcreateFileFrame() {
		initNewFilePanel();
		this.setTitle("Nuevo Archivo");
		this.pack();
		this.setResizable(false);
		setLocationRelativeTo(null);	
		this.setVisible(true);
	
	}
	
	/**
	 * Inicializa el panel donde se podrá escribir el archivo
	 */
	private void initNewFilePanel() {
		newFilePanel = new JPanel(new BorderLayout());
		newFilePanel.setPreferredSize(new Dimension(600, 300));
	    programa = new JTextArea();
		programa.setFont(new Font("Tahoma",Font.BOLD,12));
		JScrollPane scrollPane = new JScrollPane(programa);
				
		JPanel panelInferior = new JPanel();
		acceptButton = new JButton();
		acceptButton.setName("buttonAccept");
		acceptButton.setText("Aceptar");
		
		cancelButton = new JButton();
		cancelButton.setName("buttonCancel");
		cancelButton.setText("Cancelar");
		
		panelInferior.add(acceptButton);
		panelInferior.add(cancelButton);
		
		newFilePanel.add(scrollPane);
		newFilePanel.add(panelInferior, BorderLayout.SOUTH);
	
		this.add(newFilePanel);
		fixButtons();
	}

	/**
	 * Fija los actionListener de los botones
	 */
	private void fixButtons() {
		fixAccept();
		fixCancel();
	}

	/**
	 * Fija el actionListener del botón ACEPTAR
	 */
	private void fixAccept() {
		
		acceptButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Guardar como...");
				FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("ASM , IN", "asm", "in");
				fileChooser.setFileFilter(fileFilter);
				String extension = "";
				File fileToSave;
				String path;
				
				int userSelection = fileChooser.showSaveDialog(CreateFileFrame.this);
				if (userSelection == JFileChooser.APPROVE_OPTION) {

					fileToSave = fileChooser.getSelectedFile();
					path = fileToSave.getAbsolutePath();
					extension = path.substring(path.lastIndexOf('.') + 1);
					
					controller.newFile(path, programa.getText().trim() , extension);		
					dispose();
				}
				
			}
		});

	}
	
	/**
	 * Fija el actionListener del botón CANCELAR
	 */
	private void fixCancel() {
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				dispose();
			}
		});
	}
}
