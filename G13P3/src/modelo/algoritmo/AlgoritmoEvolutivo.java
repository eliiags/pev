package modelo.algoritmo;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	private int tam_poblacion,
				num_generaciones,
				cont_generaciones;
	
	private double total_fitness;
	
	private Cromosoma mejor_cromosoma,
					  mejor_absoluto;
	
	
	private boolean elitismo;
	
	private double porcentaje_elite;
	

	private Seleccion seleccion;
	
	private Mutacion mutacion;
	
	private double prob_mutacion,
				   prob_cruce;
	
	
	
	
	public AlgoritmoEvolutivo() {
		
		this.poblacion = new ArrayList<Cromosoma>();
		this.tam_poblacion = 3;
		this.num_generaciones = 2;
		this.cont_generaciones = 0;
		seleccion(0);
		mutacion();
		this.prob_mutacion = 1.0;
		this.elitismo = true;
		this.porcentaje_elite = 0.5;
		
	}	

	
	public void ejecuta() {
		
		int tam_elite = 0;
		
		if (elitismo) {
			// Extraemos los mejores individuos de la población (hacemos una copia)
			tam_elite = calcularTamElite();
		}

		Cromosoma[] elite = new Cromosoma[tam_elite];
		
		crearPoblacion();
		evaluarPoblacion();
//		pinta();
		
		System.out.println("Poblacion inicial: ");
		for (Cromosoma crm: poblacion) {
			System.out.println(crm.toString());
			System.out.println("Fitness: " + crm.getAptitud());
		}
		
		for (int i = 0; i < this.num_generaciones; i++){
			
			if (elitismo) {
				elite = separarMejores(tam_elite);
			}
			
			System.out.println("");
			System.out.println("Elite: ");
			for (Cromosoma crm: elite) {
				System.out.println(crm.toString());
				System.out.println("Fitness: " + crm.getAptitud());
			}
			
			// Aplicamos el proceso de seleccion/reproduccion/mutacion		
			poblacion = seleccion.seleccionar(poblacion);
			
			System.out.println("");
			System.out.println("Seleccion: ");
			for (Cromosoma crm: poblacion) {
				System.out.println(crm.toString());
				System.out.println("Fitness: " + crm.getAptitud());
			}
			
			this.mutacion.muta(poblacion, this.prob_mutacion);
			
			System.out.println("");
			System.out.println("Mutacion: ");
			for (Cromosoma crm: poblacion) {
				System.out.println(crm.toString());
				System.out.println("Fitness: " + crm.getAptitud());
			}
			

			if (elitismo) {
				//Volvemos a integrar a la élite
				incluirMejores(elite);
			}
			
			System.out.println("");
			System.out.println("Elite incorporada: ");
			for (Cromosoma crm: poblacion) {
				System.out.println(crm.toString());
				System.out.println("Fitness: " + crm.getAptitud());
			}
			
			evaluarPoblacion();
//			actualizarValoresGrafica();

			System.out.println("");
			System.out.println("El mejor de esta generacion es: " + this.mejor_cromosoma.toString());
			System.out.println("Con un fitness de: " + this.mejor_cromosoma.getAptitud());
			System.out.println("");

			System.out.println("El mejor de esta generacion es: " + this.mejor_absoluto.toString());
			System.out.println("Con un fitness de: " + this.mejor_absoluto.getAptitud());
			System.out.println("");
			
//			pinta();
			System.out.println("");
			cont_generaciones++;
		}
		

		
		
	}
	
	
	/**
	 * Creamos el cromosoma y calculamos su fitness
	 */
	public void crearPoblacion() {

		for (int i = 0; i < this.tam_poblacion; i++) {
			this.poblacion.add(new Cromosoma(3, 2));
			this.poblacion.get(i).inicializarCromosoma();
		}

	}
	
	
	
	public void evaluarPoblacion() {
		
		// Calculamos el total del fitness
		calcularTotalFitness();
		
		// Evaluamos el primer cromosoma de la poblacion
		this.poblacion.get(0).evaluarCromosoma(this.total_fitness, 0.0);
		this.mejor_cromosoma = this.poblacion.get(0).hacerCopia();
				
		if (this.cont_generaciones == 0) {
			this.mejor_absoluto = this.poblacion.get(0).hacerCopia();
		}
		
		
		for (int i = 1; i < this.poblacion.size(); i++) {
			this.poblacion.get(i).evaluarCromosoma(this.total_fitness, 
					this.poblacion.get(i - 1).getAcumulada());
			
			if (this.poblacion.get(i).getAptitud() < this.mejor_cromosoma.getAptitud()) {
				this.mejor_cromosoma = this.poblacion.get(i).hacerCopia();
			}	
			
			if (this.mejor_cromosoma.getAptitud() < this.mejor_absoluto.getAptitud()) {
				this.mejor_absoluto = this.mejor_cromosoma.hacerCopia();
			}
			
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
	
	
	private void mutacion() {
		this.mutacion = new MutacionBasica();
	}
	
	
	private Cromosoma[] separarMejores(int tam_elite) {
		
		// Mejores cromosomas
		Cromosoma[] mejores = new Cromosoma[tam_elite];

		// Array donde se almacenaras las aptitudes para ordenarlas posteriormente
		double[] aptitudes = new double[this.tam_poblacion];
		
		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = this.poblacion.get(i).getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);
		
		for (int i = 0; i < mejores.length; i++) {
			for (int j = 0; j < this.poblacion.size(); j++) {
				if (aptitudes[i] == this.poblacion.get(j).getAptitud()) {
					mejores[i] = this.poblacion.get(j).hacerCopia();
				}
			}
		}
		
			
				
		return mejores;

	}
	
	
	private void incluirMejores(Cromosoma[] elite) {

		// Buscamos los peores
		double[] aptitudes = new double[this.tam_poblacion];
		
		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = this.poblacion.get(i).getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);

		for (int i = 0; i < elite.length; i++) {
			for (int j = 0; j < this.poblacion.size(); j++) {
				if (aptitudes[aptitudes.length - 1 - i] == this.poblacion.get(j).getAptitud()) {
					this.poblacion.set(j, elite[i].hacerCopia());
				}
			}
		}
	}

	
	private int calcularTamElite() {
		return (int) Math.ceil(this.tam_poblacion * this.porcentaje_elite / 100);
	}	
	
	
	private void pinta() {
		
		for (Cromosoma crm: poblacion) {
			System.out.println(crm.toString());
			System.out.println(crm.getAptitud());
		}
		
	}
	
	
	
}
