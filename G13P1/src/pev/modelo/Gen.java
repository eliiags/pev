package pev.modelo;

public abstract class Gen {
	
	// Longitud del gen. Longitud del array
	protected int longitud_gen;
	
	
	public Gen(int longitud_gen) {
		this.longitud_gen = longitud_gen;
	}
		
		
	/**************************** GET & SET ********************************/
	
	public int getLongitudGen() {
		return this.longitud_gen;
	}
	
	
	/***********************************************************************/
	
	
	
	
	
	/***************************** METODOS *********************************/
	
	
	public abstract void inicializaGen();
	
	public abstract Gen hacerCopia();
	
	public abstract void muta(int pos); 

	public abstract String toString();

	
	/***********************************************************************/

}
