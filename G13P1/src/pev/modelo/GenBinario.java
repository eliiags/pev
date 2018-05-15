package pev.modelo;

public class GenBinario extends Gen {

	// Array de alelos. Contendra valores true o false
	private boolean[] alelos;

	
	
	public GenBinario(int longitud) {
		super(longitud);
	}
	
	
	
	/**************************** GET & SET ********************************/
	
	public boolean getAleloBin(int pos) {
		return this.alelos[pos];
	}

	public void setAleloBin(int pos, boolean alelo) {
		this.alelos[pos] = alelo;
	}

	/***********************************************************************/

	
	
	
	
	
	/***************************** METODOS *********************************/
	
	@Override
	public String toString() {
		String dev = "";
		
		for(int i = 0; i < this.alelos.length; i++) {
			dev = this.alelos[i] ? dev + 1 : dev + 0; 
		} 
		
		return dev;
	}


	@Override
	public void inicializaGen() {

		this.alelos = new boolean[this.longitud_gen];

		for (int i = 0; i < this.longitud_gen; i++) {
			double n = Math.random();
			this.alelos[i] = (n < 0.5) ? false : true;
		}

	}


	@Override
	public Gen hacerCopia() {
		
		GenBinario gen = new GenBinario(this.longitud_gen);
		 
		for (int i = 0; i < this.longitud_gen; i++) {
			gen.setAleloBin(i, this.alelos[i]);
		}
		
		return gen;
	}



	@Override
	public void muta(int pos) {
		if(this.alelos[pos]) {
			this.alelos[pos] = false;
		}
		else {
			this.alelos[pos] = true;
		}
		
//		if (this.nueva_poblacion[i].getGenes()[j].getAleloBin(k)) {
//			this.nueva_poblacion[i].getGenes()[j].setAleloBin(k, false);
//		}
//		else {
//			this.nueva_poblacion[i].getGenes()[j].setAleloBin(k, true);
//		}
		
	}

		
	/***********************************************************************/


	
}
