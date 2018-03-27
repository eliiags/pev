package pev.funcion;

import pev.modelo.Cromosoma;
import pev.modelo.Gen;
import pev.modelo.GenBinario;

public class Funcion4 extends Cromosoma{

	public Funcion4(double tolerancia) {
		super(2);
		
		this.num_genes = 2;
		this.longitud_fenotipo = 2;
		
		this.xmin[0] = -10;
		this.xmax[0] = 10;
		
		this.xmin[1] = -10;
		this.xmax[1] = 10;
		
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
		
		int SUM = 5;
		double paso1 = 0.0,
			   paso2 = 0.0;
		
		for (int i = 1; i < SUM+1; i++) {
			paso1 += i * Math.cos((i + 1) * this.fenotipo[0] + i);
		}
		
		for (int i = 1; i < SUM+1; i++) {
			paso2 += i * Math.cos((i + 1) * this.fenotipo[1] + i);
		}
		
		this.aptitud = paso1 * paso2;
	
	}

	
	@Override
	public void calcularFenotipo() {
		
		for (int i = 0; i < fenotipo.length; i++) {
			this.fenotipo[i] = this.xmin[i] + (this.xmax[i] - 
					this.xmin[i]) * bin2dec(this.genes[i]) / (Math.pow(2, this.longitud_gen[i]) - 1);
		}
		
	}

	
	public int bin2dec(Gen gen) {
		
		return Integer.parseInt(gen.toString(), 2);

	}
	
	
	@Override
	public Cromosoma hacerCopia() {
		// Creamos un cromosoma
		Cromosoma cromosoma = new Funcion4(this.tolerancia);
		
		// Hacemos una copia
		for (int i = 0; i < fenotipo.length; i++) {
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
