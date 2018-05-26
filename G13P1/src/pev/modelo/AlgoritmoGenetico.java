package pev.modelo;

import java.util.Arrays;

import pev.funcion.Funcion1;
import pev.funcion.Funcion2;
import pev.funcion.Funcion3;
import pev.funcion.Funcion4;
import pev.funcion.Funcion5;
import pev.modelo.cruce.Cruce;
import pev.modelo.cruce.CruceMonoPunto;
import pev.modelo.mutacion.Mutacion;
import pev.modelo.mutacion.MutacionBitBit;
import pev.modelo.seleccion.Seleccion;
import pev.modelo.seleccion.SeleccionEstocastico;
import pev.modelo.seleccion.SeleccionRuleta;
import pev.modelo.seleccion.SeleccionTorneoDeterministico;
import pev.modelo.seleccion.SeleccionTorneoProbabilistico;
import pev.modelo.seleccion.SeleccionTruncamiento;


public class AlgoritmoGenetico{
	
	// Poblacion con la que se trabajar
	private Cromosoma[] poblacion;
	
	private int tam_poblacion;

	private int num_generaciones; // Numero maximo de generaciones

	private double porcentaje_elite;
	
	private Cromosoma el_mejor; // Mejor individuo
	
	private Cromosoma el_mejor_absoluto;
	
	private Seleccion seleccion;
	
	private Cruce cruce;
	
	private Mutacion mutacion;
	
	private int pos_mejor; // Posicion del mejor cromosoma
	
	private double prob_cruce; // Probabilidad de cruce
	
	private double prob_mutacion; // Probabilidad de mutacion
	
	private double tolerancia; // Tolerancia de la representacion
	
	private double total_fitness;
	
	private int genes;
	
	private double[] media_fitness;
	
	private double[] mejor_absoluto;
	
	private double[] mejor_fitness;
	
	private int cont_generaciones;
	
	
	
	
	/**
	 * 
	 * @param tam_poblacion Tamanno de la poblacion
	 * @param tolerancia Tolerancia
	 * @param prob_cruce Probabilidad de cruce
	 * @param prob_mutacion Probabilidad de mutacion
	 * @param tipo_funcion Tipo de funcion (Cromosoma)
	 * @param num_generaciones Numero de generaciones
	 * @param porcentaje_elite Porcentaje de elite
	 * @param genes Numero de genes (Para funcion 5)
	 */
	public AlgoritmoGenetico(
            int tipo_funcion,
            int genes,
            int tam_poblacion,
            int num_generaciones,
            double prob_cruce,
            double prob_mutacion,
            double tolerancia,
            double porcentaje_elite, 
            int opcion_seleccion,
            int opcion_cruce,
            int opcion_mutacion) {
	
        
            this.tam_poblacion    = tam_poblacion; 
			this.tolerancia 	  = tolerancia;
			this.prob_cruce 	  = prob_cruce;
			this.prob_mutacion 	  = prob_mutacion;
			this.num_generaciones = num_generaciones;
			this.porcentaje_elite = porcentaje_elite;
			this.genes		  	  = genes;
	
			// Creamos el array de poblacion
			this.poblacion = new Cromosoma[this.tam_poblacion];
			
			// Para mostrar en la grafica
			this.media_fitness = new double[this.num_generaciones];
			this.mejor_absoluto = new double[this.num_generaciones];
			this.mejor_fitness = new double[this.num_generaciones];
			this.cont_generaciones = 0;
			
			seleccion(opcion_seleccion);
			cruce(opcion_cruce);
			mutacion(opcion_mutacion);
			
			boolean elite = this.porcentaje_elite == 0.0 ? false : true;
			
			ejecuta(elite, tipo_funcion);
		}

	
	
	
	/**************************** GET & SET ********************************/
	
	
	public int getNumeroGeneraciones() {
		return this.num_generaciones;
	}
	
	public double[] getMediaFitness() {
		return this.media_fitness;
	}
	
	public double[] getMejorFitness() {
		return this.mejor_fitness;
	}
	
	public double[] getMejorAbsoluto() {
		return this.mejor_absoluto;
	}
	
	public Cromosoma getElMejorAbsoluto() {
		return this.el_mejor_absoluto;
	}
	
	
	/***********************************************************************/

	
	
	
	
	
	/***************************** METODOS *********************************/

	public void inicializaPoblacion(int tipo_funcion) {
	
		// Para cada uno de los individuos de la poblacion se crea un tipo de Funcion
		for (int i = 0; i < this.poblacion.length; i++) {
			
			switch (tipo_funcion) {
			case 1:
				this.poblacion[i] = new Funcion1(this.tolerancia);
				this.poblacion[i].inicializarCromosoma();
				break;
			case 2:
				this.poblacion[i] = new Funcion2(this.tolerancia);
				this.poblacion[i].inicializarCromosoma();
				break;
			case 3:
				this.poblacion[i] = new Funcion3(this.tolerancia);
				this.poblacion[i].inicializarCromosoma();
				break;
			case 4:
				this.poblacion[i] = new Funcion4(this.tolerancia);
				this.poblacion[i].inicializarCromosoma();
				break;
			case 5:
				this.poblacion[i] = new Funcion5(this.tolerancia, this.genes);
				this.poblacion[i].inicializarCromosoma();
				break;
			default:
				System.out.println("Introduzca una funcion");
				break;
			}
			
		}
		
	}
	
	
	public void evaluarPoblacion() {
		
		// Primeramente calculamos el fitness total
		calcularTotalFitness();
		
		// Inicializamos los valores del primer cromosoma
		this.poblacion[0].evaluarCromosoma(this.total_fitness, 0.0);
		this.pos_mejor = 0;
		this.el_mejor = this.poblacion[0].hacerCopia();
		
		if (this.cont_generaciones == 0)
			this.el_mejor_absoluto = this.poblacion[0].hacerCopia();
		
		
		// Inicializamos los valores del resto de cromosomas
		for (int i = 1; i < this.poblacion.length; i++) {
			this.poblacion[i].evaluarCromosoma(this.total_fitness, 
					this.poblacion[i-1].getPuntuacionAcumulada());
			
			// Si es Funcion1 o Funcion3 (MAXIMIZAR)
			if (this.poblacion[0].getMaximizar()){
				if (this.poblacion[i].getAptitud() > this.el_mejor.getAptitud()) {
					this.pos_mejor = i;
					this.el_mejor = this.poblacion[i].hacerCopia();
				}
				if(this.el_mejor.getAptitud() > this.el_mejor_absoluto.getAptitud()) {
					this.el_mejor_absoluto = this.el_mejor.hacerCopia();
				}
			}
			else{
				if (this.poblacion[i].getAptitud() < this.el_mejor.getAptitud()) {
					this.pos_mejor = i;
					this.el_mejor = this.poblacion[i].hacerCopia();
				}	
				if (this.el_mejor.getAptitud() < this.el_mejor_absoluto.getAptitud()) {
					this.el_mejor_absoluto = this.el_mejor.hacerCopia();
				}
			}

			
			
		}	
	}
	
	
	
	public void actualizarValoresGrafica() {
		if (cont_generaciones < this.num_generaciones) {
			this.media_fitness[cont_generaciones] = this.total_fitness / this.tam_poblacion;
			this.mejor_fitness[cont_generaciones] = this.el_mejor.getAptitud();
			this.mejor_absoluto[cont_generaciones] = this.el_mejor_absoluto.getAptitud();	
		}
	}
	
	
	
	/**
	 * Devuelve la suma total de fitness
	 * @return
	 */
	public void calcularTotalFitness() {
		
		this.total_fitness = 0.0;
		
		for (int i = 0; i < this.poblacion.length; i++) {
			this.total_fitness += this.poblacion[i].getAptitud();
		}
		
	}
	
	
	public void seleccion(int opcion_seleccion) {
		
		switch (opcion_seleccion) {
		case 0:
			seleccion = new SeleccionEstocastico();
			break;
		case 1:
			seleccion = new SeleccionRuleta();
			break;
		case 2:
			seleccion = new SeleccionTorneoDeterministico();
			break;
		case 3:
			seleccion = new SeleccionTorneoProbabilistico();
			break;
		case 4:
			seleccion = new SeleccionTruncamiento(0.5);
			break;
		default:
			System.out.println("Introduzca tipo de seleccion");
			break;
		}
	}
	
	
	public void cruce(int opcion_cruce) {
		cruce = new CruceMonoPunto();
	}
	
	
	public void mutacion(int opcion_mutacion) {
		mutacion = new MutacionBitBit();
	}

	
	public void ejecuta (boolean elitismo, int tipo_funcion) {

		int tam_elite = 0;
		
		if (elitismo) {
			// Extraemos los mejores individuos de la población (hacemos una copia)
			tam_elite = calcularTamElite();
		}
		
		inicializaPoblacion(tipo_funcion);
		evaluarPoblacion();
		// pinta();
		
		for (int i = 0; i < this.num_generaciones; i++){
			
			Cromosoma[] elite = new Cromosoma[tam_elite]; 
			
			if (elitismo) {
				elite = separarMejores(tam_elite);
			}
			
			// Aplicamos el proceso de seleccion/reproduccion/mutacion		
			this.poblacion = seleccion.seleccionar(this.poblacion);
			this.poblacion = cruce.reproduccion(this.poblacion, this.prob_cruce);
			this.poblacion = mutacion.muta(this.poblacion, this.prob_mutacion);
			
			
			if (elitismo) {
				//Volvemos a integrar a la élite
				incluirMejores(elite);
			}
			
			evaluarPoblacion();
			actualizarValoresGrafica();
			
			this.cont_generaciones++;
		}
		
		// pinta();

	}
	
	
	
	private Cromosoma[] separarMejores(int tam_elite) {
		
		// Mejores cromosomas
		Cromosoma[] mejores = new Cromosoma[tam_elite];

		// Array donde se almacenaras las aptitudes para ordenarlas posteriormente
		double[] aptitudes = new double[this.tam_poblacion];
		
		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = this.poblacion[i].getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);
		
		// Si es una funcion de maximizar
		if (this.poblacion[0].getMaximizar()) { // MAXIMIZAR
			for (int i = 0; i < mejores.length; i++) {
				for (int j = 0; j < this.poblacion.length; j++) {
					if (aptitudes[aptitudes.length - 1 - i] == this.poblacion[j].getAptitud()) {
						mejores[i] = this.poblacion[j].hacerCopia();
					}
				}
			}
		}
		// Si es una funcion de maximizar
		else { // MINIMIZAR
			for (int i = 0; i < mejores.length; i++) {
				for (int j = 0; j < this.poblacion.length; j++) {
					if (aptitudes[i] == this.poblacion[j].getAptitud()) {
						mejores[i] = this.poblacion[j].hacerCopia();
					}
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
			aptitudes[i] = this.poblacion[i].getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);

		// Para las funciones donde se tenga que maximizar el valor
		if (this.poblacion[0].getMaximizar()){
			for (int i = 0; i < elite.length; i++) {
				boolean encontrado = false;
				for (int j = 0; j < this.poblacion.length; j++) {
					if (!encontrado && aptitudes[i] == this.poblacion[j].getAptitud()) {
						this.poblacion[j] = elite[i];
						encontrado = true;
					}
				}
			}
		}
		// Para las funciones donde se tenga que minimizar el valor
		else {
			for (int i = 0; i < elite.length; i++) {
				boolean encontrado = false;
				for (int j = 0; j < this.poblacion.length; j++) {
					if (!encontrado && aptitudes[aptitudes.length - 1 - i] == this.poblacion[j].getAptitud()) {
						this.poblacion[j] = elite[i];
						encontrado = true;
					}
				}
			}
		}
		
		
	}

	
	
	
	private int calcularTamElite() {
		return (int) Math.ceil(this.tam_poblacion * this.porcentaje_elite / 100);
	}
	
	
	
	public void pinta() {
		
		for (int i = 0; i < this.poblacion.length; i++) {
			System.out.println("Individuo: " + i);
			System.out.println(this.poblacion[i].toString());
			
			for (int j = 0; j < this.poblacion[i].longitud_fenotipo; j++) {
				System.out.println("Fenotipo:  " + this.poblacion[i].getFenotipo()[j]);
			}
			System.out.println("Fitness:   " + this.poblacion[i].getAptitud());
			System.out.println("Relativa:  " + this.poblacion[i].getPuntuacionRelativa());
			System.out.println("Acumulada: " + this.poblacion[i].getPuntuacionAcumulada());
			System.out.println("");
		}
		
		System.out.println("Fitness Total: " + this.total_fitness);
		for (int j = 0; j < this.poblacion[this.pos_mejor].longitud_fenotipo; j++) {
			System.out.println("x: " + this.poblacion[this.pos_mejor].getFenotipo()[j]);
		}
		System.out.println("Mejor individuo: " + this.pos_mejor 
				/*+" (" + this.poblacion[this.pos_mejor].toString() + ") "*/ 
						+ " con fitness: " + this.el_mejor.getAptitud());
		
		System.out.println("");
		
		System.out.println("El mejor de toda la poblacion ha sido: " + this.el_mejor_absoluto.getAptitud());
		
	}
	
	/***********************************************************************/
	
	
}