package modelo.algoritmo;

import java.util.ArrayList;

import modelo.Cromosoma;
import modelo.seleccion.Seleccion;
import modelo.seleccion.SeleccionEstocastico;
import modelo.seleccion.SeleccionManuEli;
import modelo.seleccion.SeleccionRestos;
import modelo.seleccion.SeleccionRuleta;
import modelo.seleccion.SeleccionTorneoDeterministico;
import modelo.seleccion.SeleccionTorneoProbabilistico;
import modelo.seleccion.SeleccionTruncamiento;

public class AlgoritmoEvolutivo {

	private ArrayList<Cromosoma> poblacion;
	
	private int tam_poblacion = 5;
	
	private double total_fitness;
	
	private Cromosoma mejor_cromosoma;
	
	private Cromosoma mejor_absoluto;
	
	
	
	public AlgoritmoEvolutivo() {
		this.poblacion = new ArrayList<Cromosoma>();
	}	

	
	public void ejecuta() {
		
		crearPoblacion();
		evaluarPoblacion();
		
		for (Cromosoma crm: poblacion) {
			System.out.println(crm.toString());
		}
		
		System.out.println("");
		
		Seleccion s = new SeleccionManuEli();
		s.seleccionar(poblacion);
		
		System.out.println("");
		
		for (Cromosoma crm: poblacion) {
			System.out.println(crm.toString());
		}
		
//		for (int i = 0; i < poblacion.size(); i++) {
//			System.out.println(poblacion.get(i).toString());
//			System.out.println("Fitness:   " + poblacion.get(i).getAptitud());
//			System.out.println("Relativa:  " + poblacion.get(i).getRelativa());
//			System.out.println("Acumulada: " + poblacion.get(i).getAcumulada());
//		}
		
	}
	
	
	/**
	 * Creamos el cromosoma y calculamos su fitness
	 */
	public void crearPoblacion() {

		for (int i = 0; i < this.tam_poblacion; i++) {
			this.poblacion.add(new Cromosoma(5));
			this.poblacion.get(i).inicializarCromosoma();
		}

	}
	
	
	public void evaluarPoblacion() {
		
		// Calculamos el total del fitness
		calcularTotalFitness();
		
		// Evaluamos el primer cromosoma de la poblacion
		this.poblacion.get(0).evaluarCromosoma(this.total_fitness, 0.0);
		
		
		for (int i = 1; i < this.poblacion.size(); i++) {
			this.poblacion.get(i).evaluarCromosoma(this.total_fitness, 
					this.poblacion.get(i - 1).getAcumulada());
		}
		
		
	}
	
	
	private void calcularTotalFitness() {
		
		this.total_fitness = 0.0;
		
		for (int i = 0; i < this.poblacion.size(); i++) {
			this.total_fitness += this.poblacion.get(i).getAptitud();
		}
		
	}
	
	
}
