package pev.modelo;

public abstract class Cromosoma {

	protected Gen[] genes;
	protected int[] longitud_gen;
	
	protected int num_genes;

	protected double fenotipo[];
	protected int longitud_fenotipo;

	protected double[] xmin;
	protected double[] xmax;
	
	protected double tolerancia;

	protected double aptitud; // Funcion de evaluacion fitness (adaptacion)
	
	protected double punt_relativa; // Puntuacion relativa = (aptitud / suma_aptitud)
	
	protected double punt_acumulada; // Puntuacion acumulada para seleccion 
										// (suma de todos los fitness)
	protected boolean maximizar;
	
	protected boolean modificado;
	
	
	
	public Cromosoma(int longitud) {
		this.longitud_gen = new int[longitud];
		this.fenotipo     = new double[longitud];
		this.xmin = new double[longitud];
		this.xmax = new double[longitud];
	}
	

	/***************************** METODOS *********************************/
	
	public abstract void inicializarCromosoma();
	
	public abstract void funcionFitness();
	
	public abstract void calcularFenotipo();
	
	public abstract Cromosoma hacerCopia();
	
	
	/***********************************************************************/

	
	
	
	
	
	
	/************************** GET & SET *****************************/
	
	public double[] getFenotipo() {
		return this.fenotipo;
	}
	
	public double getAptitud() {
		
		if (this.modificado) {
			this.calcularFenotipo();
			this.funcionFitness();
			this.modificado = false;
		}
		
		return this.aptitud;
	}
	
	public double getPuntuacionRelativa() {
		return this.punt_relativa;
	}
	
	public double getPuntuacionAcumulada() {
		return this.punt_acumulada;
	}
	
	public Gen[] getGenes() {
		return this.genes;
	}
	
	public void setGen(Gen gen, int pos) {
		this.genes[pos] = gen.hacerCopia();
	}

	public boolean getMaximizar() {
		return this.maximizar;
	}
	
	public int getLongitudCromosoma() {
		
		int longitud = 0;
		
		for (int i = 0; i < this.longitud_gen.length; i++) {
			longitud += this.longitud_gen[i];
		}
		
		return longitud;
	}
	
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	/***********************************************************************/
	
	
	
	
	
	
	
	/***************************** METODOS *********************************/
	
	
	
	/**
	 * Calcula y devuelve la longitud del cromosoma.
	 * Formula vista en clase
	 * @return
	 */
	public void calcularLongitudCromosoma() {
		
		for (int i = 0; i < this.num_genes; i++) {
			this.longitud_gen[i] = (int) Math.ceil((Math.log( 1 + 
					( (this.xmax[i] - this.xmin[i]) / this.tolerancia))) 
					/ Math.log(2));
		}
		
	}
	
	
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
	


	public void cambiaGenes(Gen[] genes, int pos_ini, int pos_fin) {
		
		for (int i = pos_ini; i < pos_fin; i++) {
			this.genes[i] = genes[i].hacerCopia();
		}
		
	}
	
	
	/**
	 * Sobreescribe el metodo toString. 
	 * Devuelve un String con la representacion del cromosoma.
	 */
	public String toString() {
		String dev = "";
		
		for(int i = 0; i < genes.length; i++) {
			dev += genes[i].toString();
		} 
		
		return dev;
	}
	
	
	/***********************************************************************/	
		
}

/**************************** GET & SET ********************************/
/***********************************************************************/
/***********************************************************************/
/***************************** METODOS *********************************/

