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
import pev.modelo.seleccion.EstocasticoSeleccion;
import pev.modelo.seleccion.RuletaSeleccion;
import pev.modelo.seleccion.Seleccion;
import pev.modelo.seleccion.TorneoDeterministicoSeleccion;
import pev.modelo.seleccion.TruncamientoSeleccion;


public class AlgoritmoGenetico{
	
	// Poblacion con la que se trabajará
	private Cromosoma[] poblacion;
	
	private int tam_poblacion;

	private int num_generaciones; // Numero maximo de generaciones

	private Cromosoma[] elite;
	
	private double porcentaje_elite;
	
	private Cromosoma el_mejor; // Mejor individuo
	
	private Cromosoma el_mejor_absoluto;
	
	private int pos_mejor; // Posicion del mejor cromosoma
	
	private double prob_cruce; // Probabilidad de cruce
	
	private double prob_mutacion; // Probabilidad de mutacion
	
	private double tolerancia; // Tolerancia de la representacion
	
	private int tipo_funcion;
	
	private double total_fitness;
	
	private int genes;
	
	private double[] media_fitness;
	
	private double[] mejor_absoluto;
	
	private double[] mejor_fitness;
	
	private int cont_generaciones;
	
	private int opcion_seleccion;
	
	
	
	
	/**
	 * 
	 * @param tam_poblacion Tamaño de la poblacion
	 * @param tolerancia Tolerancia
	 * @param prob_cruce Probabilidad de cruce
	 * @param prob_mutacion Probabilidad de mutación
	 * @param tipo_funcion Tipo de funcion (Cromosoma)
	 * @param num_generaciones Número de generaciones
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
			this.tipo_funcion 	  = tipo_funcion;
			this.num_generaciones = num_generaciones;
			this.porcentaje_elite = porcentaje_elite;
			this.genes		  	  = genes;
	
			this.opcion_seleccion = 3/*opcion_seleccion*/;
			
			// Creamos el array de poblacion
			this.poblacion = new Cromosoma[this.tam_poblacion];
			
			// Para mostrar en la grafica
			this.media_fitness = new double[this.num_generaciones];
			this.mejor_absoluto = new double[this.num_generaciones];
			this.mejor_fitness = new double[this.num_generaciones];
			this.cont_generaciones = 0;
			
			
			if (this.porcentaje_elite == 0.0) {
				sinElitismo();;
			}
			else {
				elitismo();
			}
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

	public void inicializaPoblacion() {
	
		// Para cada uno de los individuos de la poblacion se crea un tipo de Funcion
		for (int i = 0; i < this.poblacion.length; i++) {
			
			switch (this.tipo_funcion) {
			case 1:
				this.poblacion[i] = new Funcion1(this.tolerancia);
				break;
			case 2:
				this.poblacion[i] = new Funcion2(this.tolerancia);
				break;
			case 3:
				this.poblacion[i] = new Funcion3(this.tolerancia);
				break;
			case 4:
				this.poblacion[i] = new Funcion4(this.tolerancia);
				break;
			case 5:
				this.poblacion[i] = new Funcion5(this.tolerancia, this.genes);
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
		
		if(this.cont_generaciones == 0)
			this.el_mejor_absoluto = this.poblacion[0].hacerCopia();
		
		
		// Inicializamos los valores del resto de cromosomas
		for (int i = 1; i < this.poblacion.length; i++) {
			this.poblacion[i].evaluarCromosoma(this.total_fitness, 
					this.poblacion[i-1].getPuntuacionAcumulada());
			
			// Si es Funcion1 o Funcion3 (MAXIMIZAR)
			if (this.tipo_funcion == 1 || this.tipo_funcion == 3){
				if (this.poblacion[i].getAptitud() > this.el_mejor.getAptitud()) {
					this.pos_mejor = i;
					this.el_mejor = this.poblacion[i].hacerCopia();
					if(this.el_mejor_absoluto.getAptitud() < this.el_mejor.getAptitud()) {
						this.el_mejor_absoluto = this.poblacion[i].hacerCopia();
					}
				}
			}
			else{
				if (this.poblacion[i].getAptitud() < this.el_mejor.getAptitud()) {
					this.pos_mejor = i;
					this.el_mejor = this.poblacion[i].hacerCopia();
					if(this.el_mejor_absoluto.getAptitud() > this.el_mejor.getAptitud()) {
						this.el_mejor_absoluto = this.poblacion[i].hacerCopia();
					}
				}
			}

			
			
		}	
	}
	
	
	
	public void actualizarValoresGrafica() {
		if (cont_generaciones < this.num_generaciones) {
			this.media_fitness[cont_generaciones] = this.total_fitness / this.tam_poblacion;
			this.mejor_fitness[cont_generaciones] = this.el_mejor.getAptitud();
			
			if (this.porcentaje_elite == 0.0) {
				this.mejor_absoluto[cont_generaciones] = this.el_mejor_absoluto.getAptitud();	
			}
			else {
				this.mejor_absoluto[cont_generaciones] = this.elite[0].getAptitud();
			}
			
			this.cont_generaciones++;
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
	
	
	public void seleccion() {
		
		Seleccion seleccion;
		
		switch (this.opcion_seleccion) {
		case 0:
			seleccion = new RuletaSeleccion();
			seleccion.seleccionar(this.poblacion);
			this.poblacion = seleccion.getNuevaPoblacion();
			break;
		case 1:
			seleccion = new TorneoDeterministicoSeleccion();
			seleccion.seleccionar(this.poblacion);
			this.poblacion = seleccion.getNuevaPoblacion();
			break;
		case 2:
			seleccion = new EstocasticoSeleccion();
			seleccion.seleccionar(this.poblacion);
			this.poblacion = seleccion.getNuevaPoblacion();
			break;
		case 3:
			seleccion = new TruncamientoSeleccion(0.5);
			seleccion.seleccionar(this.poblacion);
			this.poblacion = seleccion.getNuevaPoblacion();
			break;
		default:
			System.out.println("Introduzca tipo de selección");
			break;
		}
	}
	
	
	public void cruce() {
		Cruce cruce = new CruceMonoPunto();
		cruce.reproduccion(this.poblacion, this.prob_cruce);
		this.poblacion = cruce.getNuevaPoblacion();
	}
	
	
	public void mutacion() {
		Mutacion mutacion = new MutacionBitBit();
		mutacion.muta(this.poblacion, this.prob_mutacion);
		this.poblacion = mutacion.getNuevaPoblacion();
	}


	
	public void sinElitismo() {
    	
		inicializaPoblacion();
		evaluarPoblacion();
		actualizarValoresGrafica();
		// pinta();
		
		for (int i = 0; i < this.num_generaciones; i++) {
			seleccion();
			cruce();
			mutacion();
			evaluarPoblacion();
			actualizarValoresGrafica();
		}
		
		// pinta();
	
	}

	
	
	public void elitismo() {
		
		
		int tam_elite = calcularTamElite();
		this.elite = new Cromosoma[tam_elite];
		
		inicializaPoblacion();
		evaluarPoblacion();
		// pinta();
		
		for(int i = 0; i < this.num_generaciones; i++){
			
			// Extraemos los mejores individuos de la población (hacemos una copia)
			this.elite = separarMejores(tam_elite);
			
			// Aplicamos el proceso de seleccion/reproduccion/mutacion		
			seleccion();
			cruce();
			mutacion();
			
			//Volvemos a integrar a la élite
			incluirMejores();
			evaluarPoblacion();
			actualizarValoresGrafica();
		}
		
		// pinta();

	}
	
	
	
	public Cromosoma[] separarMejores(int tam_elite) {
		
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
		if (this.tipo_funcion == 1 || this.tipo_funcion == 3){
			// MAXIMIZAR
			for (int i = 0; i < mejores.length; i++) {
				for (int j = 0; j < this.poblacion.length; j++) {
					if (aptitudes[aptitudes.length - 1 - i] == this.poblacion[j].getAptitud()) {
						mejores[i] = this.poblacion[j].hacerCopia();
					}
				}
			}
		}
		// Si es una funcion de maximizar
		else {
			// MINIMIZAR
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
	
	
	public void incluirMejores() {
		
		// Buscamos los peores
		double[] aptitudes = new double[this.tam_poblacion];
		
		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = this.poblacion[i].getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);

		// Para las funciones donde se tenga que maximizar el valor
		if (this.tipo_funcion == 1 || this.tipo_funcion == 3){
			for (int i = 0; i < this.elite.length; i++) {
				for (int j = 0; j < this.poblacion.length; j++) {
					if (aptitudes[i] == this.poblacion[j].getAptitud()) {
						this.poblacion[j] = this.elite[i];
					}
				}
			}
		}
		// Para las funciones donde se tenga que minimizar el valor
		else {
			
			for (int i = 0; i < this.elite.length; i++) {
				for (int j = 0; j < this.poblacion.length; j++) {
					if (aptitudes[aptitudes.length - 1 - i] == this.poblacion[j].getAptitud()) {
						this.poblacion[j] = this.elite[i];
					}
				}
			}
		}
		
		
	}

	
	
	
	public int calcularTamElite() {
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