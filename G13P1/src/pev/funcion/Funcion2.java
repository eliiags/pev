package pev.funcion;

import pev.modelo.Cromosoma;
import pev.modelo.Gen;
import pev.modelo.GenBinario;

public class Funcion2 extends Cromosoma {

	public Funcion2(double tolerancia) {
		super(2);
		
		this.num_genes = 2;
		this.longitud_fenotipo = 2;
		
		this.xmin[0] = -512;
		this.xmax[0] = 512;
		
		this.xmin[1] = -512;
		this.xmax[1] = 512;
		
		this.tolerancia = tolerancia;
		
		this.maximizar = false;
				
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
		this.aptitud = -(this.fenotipo[1] + 47) 
				* Math.sin(Math.sqrt(Math.abs(this.fenotipo[1] + (this.fenotipo[0] / 2) + 47))) 
				- this.fenotipo[0] * Math.sin(Math.sqrt(Math.abs(this.fenotipo[0] - (this.fenotipo[1] + 47))));
	}

	@Override
	public void calcularFenotipo() {
		
		for (int i = 0; i < fenotipo.length; i++) {
			this.fenotipo[i] = this.xmin[i] + (this.xmax[i] - this.xmin[i]) * bin2dec(this.genes[i]) / 
					(Math.pow(2, this.longitud_gen[i]) - 1);
		}
		
	}

	
	public int bin2dec(Gen gen) {
		
		return Integer.parseInt(gen.toString(), 2);

	}
	
	
	@Override
	public Cromosoma hacerCopia() {
		// Creamos un cromosoma
		Cromosoma cromosoma = new Funcion2(this.tolerancia);
		
		// Hacemos una copia
		for (int i = 0; i < this.genes.length; i++) {
			cromosoma.setGen(this.genes[i].hacerCopia(), i);
		}
		
		// Calculamos el fenotipo
		cromosoma.calcularFenotipo();
		
		// Calculamos su aptitud
		cromosoma.funcionFitness();
		
		// Lo devolvemos
		return cromosoma;
	}

}
