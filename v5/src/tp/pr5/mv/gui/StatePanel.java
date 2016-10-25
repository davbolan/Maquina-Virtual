package tp.pr5.mv.gui;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.mv.observables.CPUObserver;
import tp.pr5.mv.observables.MemoryObserver;
import tp.pr5.mv.observables.StackObserver;

/**
 * Clase que representa el panel de inforación sobre el estado de la CPU en la parte inferior de la ventana
 * @author Lidia Flores, David Bolanios
 */
@SuppressWarnings("serial")
public class StatePanel extends JPanel implements MemoryObserver<Integer>, StackObserver<Integer>, CPUObserver{

	private JLabel labelNumInstruction;
	private JLabel state;
	private JCheckBox memoryBox;
	private JCheckBox stackBox;
	private static int numInstruction = 0;
	
	/**
	 * Construye el panel de información
	 */
	public StatePanel() {
		initStatePanel();
	}

	/**
	 * Inicializa el panel de información
	 */
	private void initStatePanel(){
		JLabel numInsEje = new JLabel("Numero de instrucciones ejecutadas: ");
		labelNumInstruction = new JLabel(numInstruction + "");
		memoryBox = new  JCheckBox("Memoria modificada");
		memoryBox.setEnabled(false);
		memoryBox.setContentAreaFilled(false);
		stackBox = new JCheckBox("Pila modificada");
		stackBox.setContentAreaFilled(false);
		stackBox.setEnabled(false);
		state = new JLabel("Máquina Preparada");
		state.setForeground(new Color(219, 43, 201));
		
		this.add(state);
		this.add(numInsEje);
		this.add(labelNumInstruction);
		this.add(memoryBox);
		this.add(stackBox);
		
	}

	/**
	 * Actualiza los correspondientes campos de información en caso de error
	 */
	@Override
	public void raiseError(String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stackBox.setSelected(false);	
				memoryBox.setSelected(false);
				state.setText("Máquina Parada");
				state.setForeground(Color.RED);
				numInstruction--;
				labelNumInstruction.setText(numInstruction + "");
			}
		});
	}

	/**
	 * Actualiza los correspondientes campos de información cuando la máquina ha finalizado
	 */
	@Override
	public void programEnd(boolean end) {
		if(end){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					state.setText("Programa Finalizado");
					state.setForeground(Color.BLUE);
				}
			});
		}
			
	}

	/**
	 * Actualiza los correspondientes campos de información cuando la máquina comienza una instrucción
	 */
	@Override
	public void instructionStarting(String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				state.setText("Ejecutando");
				state.setForeground(Color.GREEN);
				numInstruction++;
				labelNumInstruction.setText(numInstruction + "");
			}
		});
		
	}

	/**
	 * Actualiza los correspondientes campos de información cuando se ha modificado la pila
	 */
	@Override
	public void newElem(int number) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stackBox.setSelected(true);	
				memoryBox.setSelected(false);
			}
		});
	}
	
	/**
	 * Actualiza los correspondientes campos de información cuando se ha modificado la pila
	 */
	@Override
	public void quitElem() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stackBox.setSelected(true);	
				memoryBox.setSelected(false);
			}
		});
	}

	/**
	 * Actualiza los correspondientes campos de información cuando se ha modificado la memoria
	 */
	@Override
	public void onWrite(int index, Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				memoryBox.setSelected(true);
				stackBox.setSelected(false);
			}
		});
		
	}

	/**
	 * Actualiza los correspondientes campos de información cuando se ha reseteado la memoria
	 */
	@Override
	public void resetMemory() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				memoryBox.setSelected(false);
				numInstruction = 0;
				labelNumInstruction.setText(numInstruction + "");
				state.setText("Máquina Preparada");
				state.setForeground(new Color(219, 43, 201));
			}
		});	
	}

	/**
	 * Deselecciona el JCheckBox de la pila
	 */
	@Override
	public void clearStack() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stackBox.setSelected(false);
			}
		});
	}

	/**
	 * Actualiza los correspondientes campos de información cuando se ha parado la CPU
	 */
	@Override
	public void machineStopped() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				state.setText("Máquina Parada");
				state.setForeground(Color.RED);
			}
		});
	}
	
	@Override
	public void machineStarted() {
	}

	@Override
	public void onReset() {	
	}

	@Override
	public void showCPUState(String string) {
	}
	
	@Override
	public void requestQuit() {
	}
}
