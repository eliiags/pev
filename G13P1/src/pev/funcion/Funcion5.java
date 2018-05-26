package pev.funcion;

import pev.modelo.Cromosoma;
import pev.modelo.Gen;
import pev.modelo.GenBinario;

public class Funcion5 extends Cromosoma {

	public Funcion5(double tolerancia, int num) {
		super(num);
		
		this.num_genes = num;
		this.longitud_fenotipo = num;
		
		for (int i = 0; i < this.num_genes; i++) {
			this.xmin[i] = 0;
			this.xmax[i] = Math.PI;
		}
		
		this.tolerancia = tolerancia;
		
		this.maximizar = false;
		
		// Calculamos la longitud del cromosoma
		this.calcularLongitudCromosoma();

	}

	
	@Override
	public void inicializarCromosoma() {

		// Inicializo el array
		this.genes = new Gen[this.num_genes];
		
		for (int i = 0; i < this.genes.length; i++) {
			this.genes[i] = new GenBinario(this.longitud_gen[i]);
			this.genes[i].inicializaGen();
		}
		
		// Calculamos el fenotipo del cromosoma
		this.calcularFenotipo();
		// Calculamos el fitness del individuo
		this.funcionFitness();

		this.modificado = false;
		
	}

	
	@Override
	public void funcionFitness() {
		
		this.aptitud = 0.0;
		
		for (int i = 1; i < fenotipo.length + 1; i++) {
			this.aptitud += Math.sin(this.fenotipo[i-1]) * 
					Math.pow(Math.sin(((i + 1) * Math.pow(this.fenotipo[i-1], 2)) / Math.PI), 20);
		}
		
		this.aptitud = -this.aptitud;
		
	}

	
	@Override
	public void calcularFenotipo() {
		for (int i = 0; i < fenotipo.length; i++) {
			this.fenotipo[i] = this.xmin[i] + (this.xmax[i] - 
					this.xmin[i]) * bin2dec(this.genes[i]) / (Math.pow(2, this.longitud_gen[i]) - 1);
		}
		
	}


	private int bin2dec(Gen gen) {
		return Integer.parseInt(gen.toString(), 2);
	}

	
	@Override
	public Cromosoma hacerCopia() {
		Funcion5 crm = new Funcion5(this.tolerancia, this.num_genes);

		crm.genes = new Gen[this.num_genes];

		crm.longitud_gen[0] = this.longitud_gen[0];

		for (int i = 0; i < this.genes.length; i++) {
			crm.longitud_gen[i] = this.longitud_gen[i];
			crm.setGen(this.genes[i].hacerCopia(), i);
			crm.fenotipo[i] = this.fenotipo[i];
		}
		
		crm.aptitud = this.aptitud;
		
		return crm;
	}

}