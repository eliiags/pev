package modelo.algoritmo;

import java.util.ArrayList;
import java.util.Random;

import modelo.Cromosoma;
import modelo.mutacion.Mutacion;
import modelo.mutacion.MutacionBasica;
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
	
	private int num_generaciones = 1;
	
	private double total_fitness;
	
	private Cromosoma mejor_cromosoma;
	
	private Cromosoma mejor_absoluto;
	
	private Seleccion seleccion;
	
	private Mutacion mutacion;
	
	
	
	public AlgoritmoEvolutivo() {
		this.poblacion = new ArrayList<Cromosoma>();
		
		seleccion(1);
		this.mutacion = new MutacionBasica(); 
		
	}	

	
	public void ejecuta() {
		
		crearPoblacion();
		evaluarPoblacion();
		
		for (Cromosoma crm : this.poblacion) {
			System.out.println(crm.toString());
			System.out.println(crm.getAptitud());
//			int i = random.nextInt(crm.numNodos());
//			System.out.println("Cant de nodos: " + crm.numNodos());
//			System.out.println("Nodo escogido: " + i);
//			System.out.println("Nodo encontrado: " + crm.encuentraNodo(i));
		}
		
		for (int i = 0; i < 2; i++) {
			System.out.println("generacion: " + i+1);
			System.out.println("");
			this.seleccion.seleccionar(poblacion);
			
			System.out.println("");
			
			for (Cromosoma crm : this.poblacion) {
				System.out.println(crm.toString());
				System.out.println(crm.getAptitud());
			}
			
			this.mutacion.muta(poblacion, 1.0);
			evaluarPoblacion();
			
			System.out.println("");
			
			for (Cromosoma crm : this.poblacion) {
				System.out.println(crm.toString());
				System.out.println(crm.getAptitud());
			}
			System.out.println("");
			
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
	
	private void seleccion(int seleccion) {
		
		switch (seleccion) {
		case 0:
			this.seleccion = new SeleccionEstocastico();
			break;
		case 1:
			this.seleccion = new SeleccionManuEli();
			break;
		case 2:
			this.seleccion = new SeleccionRestos();
			break;
		case 3:
			this.seleccion = new SeleccionRuleta();
			break;
		case 4:
			this.seleccion = new SeleccionTorneoDeterministico();
			break;
		case 5:
			this.seleccion = new SeleccionTorneoProbabilistico();
			break;
		case 6:
			this.seleccion = new SeleccionTruncamiento(0.5);
			break;
		default:
			break;
		}
		
	}
	
	
	
	
}
