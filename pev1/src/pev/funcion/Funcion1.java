package pev.funcion;

import pev.modelo.Cromosoma;
import pev.modelo.Gen;
import pev.modelo.GenBinario;

public class Funcion1 extends Cromosoma {

	public Funcion1(double tolerancia) {
		super(1);
		
		this.num_genes = 1;
		this.longitud_fenotipo = 1;
		
		this.xmin[0] = 0;
		this.xmax[0] = 32;
		
		this.tolerancia = tolerancia;
		
		this.maximizar = true;
		
		// Calculamos la longitud del cromosoma
		this.calcularLongitudCromosoma();
		// Inicializamos el cromosoma
		this.inicializarCromosoma();
		// Calculamos el fenotipo del cromosoma
		this.calcularFenotipo();
		// Calculamos el fitness del individuo
		this.funcionFitness();
	}
	
	
	@Override
	public void inicializarCromosoma() {
		
		// Inicializo el array
		this.genes = new Gen[this.num_genes];
		
		// 
		for (int i = 0; i < this.genes.length; i++) {
			this.genes[i] = new GenBinario(this.longitud_gen[i]);
		}
		
	}


	@Override
	public void funcionFitness() {
		this.aptitud = 0.0;
		double paso1 = 20 * (Math.pow(Math.E, -0.2 * Math.abs(this.fenotipo[0])));
		double paso2 = Math.pow(Math.E, Math.cos(2 * Math.PI * this.fenotipo[0]));
		this.aptitud = 20 + Math.E - paso1 - paso2;
	}


	@Override
	public void calcularFenotipo() {
		this.fenotipo[0] = this.xmin[0] + (this.xmax[0] - 
				this.xmin[0]) * bin2dec() / (Math.pow(2, this.longitud_gen[0]) - 1);
	}

	
	public int bin2dec() {
		
		return Integer.parseInt(this.toString(), 2);

	}


	@Override
	public Cromosoma hacerCopia() {
		// Creamos un cromosoma
		Cromosoma cromosoma = new Funcion1(this.tolerancia);
		// Hacemos una copia de cada uno de los genes y los
		// establecemos en el cromosoma creado. 
		cromosoma.setGen(this.genes[0].hacerCopia(), 0);
		// Calculamos el fenotipo
		cromosoma.calcularFenotipo();
		// Calculamos su aptitud
		cromosoma.funcionFitness();
		// Lo devolvemos
		return cromosoma;
	}


}
