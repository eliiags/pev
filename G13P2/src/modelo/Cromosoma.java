package modelo;

import java.util.ArrayList;

public abstract class Cromosoma {

	protected ArrayList<Gen> genes;
	
	protected double aptitud; // Funcion de evaluacion fitness (adaptacion)
	
	protected double punt_relativa; // Puntuacion relativa = (aptitud / suma_aptitud)
	
	protected double punt_acumulada; // Puntuacion acumulada para seleccion 
										// (suma de todos los fitness)
	protected int num_genes;
	
	protected String texto_plano;
	
	protected boolean modificado;
	
	
	
	public Cromosoma(int longitud) {
		this.genes = new ArrayList<Gen>();
		this.modificado = false;
	}

	
	
	
	
	
	
	
	/************************** GET & SET *****************************/
	
	public double getAptitud() {
		return this.aptitud;
	}
	
	public void setAptitud(double aptitud) {
		this.aptitud = aptitud;
	}

	public double getPuntuacionRelativa() {
		return this.punt_relativa;
	}
	
	public double getPuntuacionAcumulada() {
		return this.punt_acumulada;
	}
	
	public ArrayList<Gen> getGenes() {
		return this.genes;
	}
	
	public void setGen(Gen gen, int pos) {
		this.genes.set(pos, gen);
	}

	public String getTextoPlano() {
		return this.texto_plano;
	}
	
	public int getLongitudCromosoma() {
		
		int tam = 0;
		
		for (int i = 0; i < genes.size(); i++) {
			tam += genes.get(i).longitud_gen;
		}
		
		return tam;
	}

	/***********************************************************************/
	

	
	

	
	
	
	
	
	/*********************** METODOS ABSTRACTOS ****************************/
	
	/**
	 * Se inicializa el cromosoma
	 */
	public abstract void inicializarCromosoma();
	

	/**
	 * Funcion de fitness
	 */
	public abstract void funcionFitness();
	

	/**
	 * Hace una copia del cromosoma
	 * @return
	 */
	public abstract Cromosoma hacerCopia();
	
	/***********************************************************************/

	
	
	
	
	
	
	
		
	
	
	
	/***************************** METODOS *********************************/
	
	/**
	 * Dado el fitness total, calculado previamente, 
	 * y el valor de la puntuacion relativa de los individuos anteriores,
	 * calcula y actualiza el valor de los atributos this.punt_relativa y 
	 * this.punt_acumulada.
	 * @param total_fitness
	 * @param relativa
	 */
	public void evaluarCromosoma(double total_fitness, double relativa) {
		this.punt_relativa  = this.aptitud / total_fitness;
		this.punt_acumulada = this.punt_relativa + relativa;
	}
	
	
	/**
	 * Sobreescribe el metodo toString. 
	 * Devuelve un String con la representacion del cromosoma.
	 */
	public String toString() {
		String dev = "";
		
		for(int i = 0; i < this.genes.size(); i++) {
			dev += this.genes.get(i).toString();
		} 
		
		return dev;
	}
	
	/***********************************************************************/
	
	public void traducir() {
		
	}

	
	
}

/*********************** METODOS ABSTRACTOS ****************************/
/***********************************************************************/
/***********************************************************************/
/***********************************************************************/	
/***********************************************************************/
/**************************** GET & SET ********************************/
/***********************************************************************/
/***********************************************************************/
/***************************** METODOS *********************************/



