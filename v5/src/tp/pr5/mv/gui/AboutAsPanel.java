package tp.pr5.mv.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import tp.pr5.mv.Constants;

/**
 * Clase que representa el panel de información sobre la MV
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class AboutAsPanel extends JPanel{
	private JDialog dialog;
	
	/**
	 * Constructora del panel de informacion
	 * @param dialog
	 */
	public AboutAsPanel(JDialog dialog){
		this.dialog = dialog;
		initAboutPanel();
	}

	/**
	 * Inicializa el panel de informacion
	 */
	private void initAboutPanel() {
		this.setLayout(new BorderLayout());
		
		 JPanel panelSuperior = new JPanel();
		 JLabel lidia = new JLabel(new ImageIcon((getClass().getResource("Icons/f1.jpg"))));
		 JLabel david = new JLabel(new ImageIcon((getClass().getResource("Icons/f2.jpg"))));
		 
		 String texto = "Máquina Virtual LFYDB" + Constants.lineSeparator
				 		+ Constants.lineSeparator + "Authors: Lidia Flores and David Bolaños" + Constants.lineSeparator
				 		+ Constants.lineSeparator + "Version: v5.1"
				 		+ Constants.lineSeparator + "Build id: Fdi 2ºA 1-9-93/30-5-93 "
		 				+ Constants.lineSeparator + Constants.lineSeparator +
		 				" (c) Copyright FLYDB contributors and others 2013, 2014.  All rights reserved."
		 				+ Constants.lineSeparator +	"This product includes software developed by the "
		 				+"Apache Software Foundation http://apache.org/" + Constants.lineSeparator 
		 				+ Constants.lineSeparator + "With thanks to Samir Genaim and Manuel Montenegro.";
		 
		 JTextArea txt = new JTextArea(texto);
		 txt.setFont(new Font("Book Antiqua",Font.BOLD,12));
		 txt.setOpaque(false);
		 txt.setEditable(false);
		 
		 panelSuperior.add(lidia);
		 panelSuperior.add(txt);
		 panelSuperior.add(david);
		 
		 JPanel panelInferior = new JPanel();
		 JButton boton = new JButton();
		 boton.setName("ButtonAceptar");
		 boton.setText("Aceptar");
		 
		 boton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					dialog.dispose();
				}
			});
		 
		 panelInferior.add(boton);
		 
		 this.add(panelSuperior, BorderLayout.NORTH);
		 this.add(panelInferior, BorderLayout.SOUTH);
		 
	}


}
