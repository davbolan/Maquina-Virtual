package tp.pr5.mv.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

import tp.pr5.mv.Controller;

/**
 * Clase que representa el lenu de opciones de la ventana
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class MenuOption extends JMenuBar{
	
	private Controller controller;
	private MainWindow main;
	
	private JMenu fileOption;
	private JMenu delayOption;
	private JMenu formatOption;
	private JMenu helpOption;
	
	private JMenuItem newFileOption;
	private JMenuItem openFileOption;
	private JMenuItem exitOption;
	
	private JRadioButtonMenuItem ten; 
	private JRadioButtonMenuItem fifty;
	private JRadioButtonMenuItem oneHundred;
	private JRadioButtonMenuItem twoHundred;
	
	private JMenuItem backgroundOption;
	private JMenuItem fontOptionCalibri;
	private JMenuItem fontOptionTahoma;
	private JMenuItem fontOptionComic;
	private JMenuItem fontOptionArial;
	
	private JMenuItem help;
	private JMenuItem aboutOption;

	/**
	 * Crea un menu
	 * @param controller el controlador
	 * @param main ventana donde insertar el menú
	 */
	public MenuOption(Controller controller, MainWindow main){
		this.controller = controller;
		this.main = main;
		initMenus();
		fixMenus();
	}

	/**
	 * Inicializa el menú
	 */
	private void initMenus() {
		initFileMenu();
		initDelayMenu();
		initFormatMenu();
		initHelpMenu();
		
		this.add(fileOption);
		this.add(delayOption);
		this.add(formatOption);
		this.add(helpOption);
	}

	/**
	 * Inicializa las opciones del menú
	 */
	private void initFileMenu() {
		JSeparator separator = new JSeparator();
		
		fileOption = new JMenu("Archivo");
		newFileOption = new JMenuItem("Nuevo archivo");
		openFileOption = new JMenuItem("Abrir archivo...");
		exitOption = new JMenuItem("Salir");
		
		fileOption.add(newFileOption);
		fileOption.add(openFileOption);
		fileOption.add(separator);
		fileOption.add(exitOption);	
	}

	/**
	 * Inicializa la opción DELAY
	 */
	private void initDelayMenu() {
		
		delayOption = new JMenu("Delay");
		ButtonGroup delays = new ButtonGroup();
		ten = new JRadioButtonMenuItem("10 ms"); 
		fifty = new JRadioButtonMenuItem("50 ms");
		oneHundred = new JRadioButtonMenuItem("100 ms");
		twoHundred = new JRadioButtonMenuItem("200 ms");
		
		delays.add(ten);
		delayOption.add(ten);
		delays.add(fifty);
		delayOption.add(fifty);
		delays.add(oneHundred);
		delayOption.add(oneHundred);
		delays.add(twoHundred);
		delayOption.add(twoHundred);
		
		twoHundred.setSelected(true);	
	}

	/**
	 * Inicializa la opción FORMATO
	 */
	private void initFormatMenu() {
		formatOption = new JMenu("Formato");
		backgroundOption = new JMenuItem("Cambiar Fondo");
		fontOptionCalibri = new JMenuItem("Cambiar Fuente Calibri");
		fontOptionTahoma = new JMenuItem("Cambiar Fuente Tahoma");
		fontOptionComic = new JMenuItem("Cambiar Fuente Comic Sans");
		fontOptionArial = new JMenuItem("Cambiar Fuente Arial");
		
		
		formatOption.add(backgroundOption);
		formatOption.add(fontOptionCalibri);
		formatOption.add(fontOptionTahoma);
		formatOption.add(fontOptionComic);
		formatOption.add(fontOptionArial);

	} 
	
	/**
	 * Inicializa la opción AYUDA
	 */
	private void initHelpMenu() {
		helpOption = new JMenu("Ayuda");
		help = new JMenuItem("Ayuda");
		aboutOption = new JMenuItem("Sobre Nosotros...");
		
		helpOption.add(help);
		helpOption.add(aboutOption);
	} 

	/**
	 * Fija los actionListener de las opciones
	 */
	private void fixMenus() {
		fixFileOption();
		fixDelayOption();
		fixFormatOption();
		fixHelpOption();
		
	}

	/**
	 * Fija el actionListener de las opciones newFile, openFile y exit
	 */
	private void fixFileOption() {
		
		newFileOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateFileFrame(main, true , controller);
			}
		});
		
		
		openFileOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileSelector = new JFileChooser();
				FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("ASM , IN, OUT", "asm", "in","out");
				fileSelector.setFileFilter(fileFilter);
				
				int option = fileSelector.showOpenDialog(openFileOption);
				
				if(option == JFileChooser.APPROVE_OPTION){
					String path = fileSelector.getSelectedFile().getPath();
					String extension = path.substring(path.lastIndexOf('.') + 1);
					
					if (extension.equalsIgnoreCase("in")) {
						controller.changeIn(path);
					}
					else if (extension.equalsIgnoreCase("out")) {
						controller.changeOut(path);
					}
					else if (extension.equalsIgnoreCase("asm")) {
						controller.changeProgram(path);
					}
				}
				
			
			}
		});
		
		exitOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				optionExit();
			}
		});
	}

	/**
	 * Muestra una ventana de dialogo preguntando al usuario si quiere salir. Si es asi, se guardan los datos
	 * y la máquina virtual se cierra.
	 */
	protected void optionExit() {
		int option = JOptionPane.showOptionDialog(null,"¿Quieres salir?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		if(option == JOptionPane.YES_OPTION){
			controller.executeQuitCommand();
			System.exit(0);
		}
	}

	/**
	 * Fija el actionListener de la opción FILE
	 */
	private void fixDelayOption() {
		ten.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.changeDelay(10);
			}
		});
		fifty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.changeDelay(50);	
			}
		});
		oneHundred.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.changeDelay(100);
			}
		});
		twoHundred.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.changeDelay(200);
			}
		});
	}

	/**
	 * Fija el actionListener de la opción FORMAT
	 */
	private void fixFormatOption() {
		backgroundOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(new JDialog(),"Seleccione un nuevo color...", Color.white);
				main.changeColor(color);
			}
		});
		
		fontOptionCalibri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.changeFontColor("Calibri", 14);
			}
		});
		
		fontOptionTahoma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.changeFontColor("Tahoma", 14);
			}
		});
		fontOptionComic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.changeFontColor("Comic Sans MS", 14);
			}
		});
		fontOptionArial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.changeFontColor("Arial", 14);
			}
		});
		
	}

	/**
	 * Fija el actionListener de la opción HELP
	 */
	private void fixHelpOption() {
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpFrame(main, true, "help");
			}
		});
		
		aboutOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpFrame(main, true, "about");
			}
		});
		
	}	
}
