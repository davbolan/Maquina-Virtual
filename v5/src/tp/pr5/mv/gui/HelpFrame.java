package tp.pr5.mv.gui;

import javax.swing.JDialog;

/**
 * Clase que representa una ventana de ayuda y de informacion sobre nosotros
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class HelpFrame extends JDialog{
	
	private HelpPanel helpPanel;
	private AboutAsPanel aboutPanel;

	/**
	 * Construye la ventana de ayuda
	 * @param main La ventana padre principal
	 * @param b si es modal o no
	 * @param controller el controlador
	 */
	public HelpFrame(MainWindow main, boolean b, String mode) {
		super(main, b);
		inithelpFrame(mode);	
	}

	/**
	 * Inicializa la ventana
	 * @param mode "help" para cargar el panel de ayuda o "Sobre nosotros..." para cargar el panel
	 * de información
	 */
	private void inithelpFrame(String mode) {
		
		if(mode.equalsIgnoreCase("help")){
			this.setTitle("Ayuda");
			helpPanel = new HelpPanel();
			this.add(helpPanel);
			
		}
		else{
			this.setTitle("Sobre nosotros...");
			aboutPanel = new AboutAsPanel(this);
			this.add(aboutPanel);
		}
		
		this.pack();
		this.setResizable(false);
		setLocationRelativeTo(null);	
		this.setVisible(true);
	}
}
