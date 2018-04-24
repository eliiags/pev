package pev.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pev.modelo.AlgoritmoGenetico;
import pev.vista.Vista;

public class Controlador implements ActionListener {

	private Vista vista;
	
	public Controlador(Vista vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando  = e.getActionCommand();
		
		// Hacemos las conversiones
		int tipo_funcion;
        	int genes;
		int tam_poblacion;
		int num_generaciones;
		double prob_cruce;
		double prob_mutacion;
		double tolerancia;
		double porcentaje_elite; 
		int opcion_seleccion;
		int opcion_cruce;
		int opcion_mutacion;
		

		if (comando == "INICIAR") {
			
			// Borramos los datos de las graficas
			this.vista.borraDatosGrafica();


			// Obtenemos los valores
			tipo_funcion = this.vista.getComboBoxFuncion().getSelectedIndex() + 1; 
			genes = this.vista.getComboBoxNumFun().getSelectedIndex() + 1;
			tam_poblacion = Integer.parseInt(this.vista.getTextFieldPoblacion().getText());
			num_generaciones = Integer.parseInt(this.vista.getTextFieldGeneraciones().getText()); 
			prob_cruce = Double.parseDouble(this.vista.getTextFieldCruces().getText());
			prob_mutacion = Double.parseDouble(this.vista.getTextFieldMutacion().getText());
			tolerancia = Double.parseDouble(this.vista.getTextFieldTolerancia().getText());
			porcentaje_elite = Double.parseDouble(this.vista.getTextFieldElitismo().getText());
			opcion_seleccion = this.vista.getComboBoxOpcionSeleccion().getSelectedIndex();
			opcion_cruce = this.vista.getComboBoxOpcionCruce().getSelectedIndex();
			opcion_mutacion = this.vista.getComboBoxOpcionMutacion().getSelectedIndex();
			
			
			// Creamos el algoritmo genetico
			AlgoritmoGenetico AG = new AlgoritmoGenetico(tipo_funcion, genes, 
					tam_poblacion, num_generaciones, 
					prob_cruce, prob_mutacion, 
					tolerancia, porcentaje_elite, 
					opcion_seleccion, opcion_cruce, opcion_mutacion);
            
			
			// Generaciones
			double[] generaciones   = new double[AG.getNumeroGeneraciones()];
			// Media de los fitness por cada generacion
			double[] media_fitness  = new double[AG.getNumeroGeneraciones()];
			// Mejor absoluto de todas las generaciones
			double[] mejor_absoluto = new double[AG.getNumeroGeneraciones()];
			// Mejor de cada generacion
			double[] mejor = new double[AG.getNumeroGeneraciones()];
				
			
			for (int i = 0; i < num_generaciones; i++) {
				generaciones[i]= i;
			}
			
			media_fitness  = AG.getMediaFitness();
			mejor_absoluto = AG.getMejorAbsoluto();
			mejor = AG.getMejorFitness();
			

//			for (int i = 0; i < num_generaciones; i++) {
//				System.out.println("Media fitness: " + media_fitness[i]);
//				System.out.println("Mejor fitness: " + mejor[i]);
//				System.out.println("Mejor absoluto: " + mejor_absoluto[i]);
//				System.out.println("-----------------------------------------------");
//			}
//			
			
			this.vista.actualizarGrafica(generaciones, media_fitness, mejor, mejor_absoluto);
			this.vista.actualizarInformacion(AG.getElMejorAbsoluto());
			
		}
	}
		 
	
}
