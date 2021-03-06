package pev.funcion;

import pev.modelo.Cromosoma;
import pev.modelo.Gen;
import pev.modelo.GenBinario;

public class Funcion3 extends Cromosoma {

	public Funcion3(double tolerancia) {
		super(2);
		
		this.num_genes = 2;
		this.longitud_fenotipo = 2;
		
		this.xmin[0] = -3.0;
		this.xmax[0] = 12.1;
		
		this.xmin[1] = 4.1;
		this.xmax[1] = 5.8;
		
		this.tolerancia = tolerancia;
		
		this.maximizar = true;
				
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
		this.aptitud = 21.5 + 
						this.fenotipo[0] * Math.sin(4 * Math.PI * this.fenotipo[0]) + 
						this.fenotipo[1] * Math.sin(20 * Math.PI * this.fenotipo[1]);
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
		Funcion3 crm = new Funcion3(this.tolerancia);

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