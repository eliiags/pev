package pev.modelo;

public abstract class Gen {
	
	// Longitud del gen. Longitud del array
	protected int longitud_gen;
	
	
	/**
	 * Dada la longitud del gen, actualiza los valores de los
	 * atributos. 
	 * @param longitud_gen
	 */
	public Gen(int longitud_gen) {
		this.longitud_gen = longitud_gen;
		inicializaGen();
	}
	
	
	
	/**************************** GET & SET ********************************/
	
	
	/**
	 * Devuelve la longitud del gen.
	 */
	public int getLongitudGen() {
		return this.longitud_gen;
	}
	
	
	/***********************************************************************/
	
	
	
	
	
	
	
	
	/***************************** METODOS *********************************/
	
	
	/**
	 * Inicializa un gen. Establece valores 0 y 1
	 */
	public abstract void inicializaGen();
	
	
	/**
	 * Devuelve un String con el valor del gen.
	 * Convierte el valor en 0 y 1.
	 */
	public abstract String toString();

	
//	/**
//	 * Dada una posicion, devuelve el valor del alelo.
//	 * @param pos
//	 * @return
//	 */
//	public abstract boolean getAleloBin(int pos);
//
//	
//	/**
//	 * Dada una posicion y un valor, establece el nuevo valor en esa posicion.
//	 * @param pos
//	 * @param alelo
//	 */
//	public abstract void setAleloBin(int pos, boolean alelo);
	
	
	/**
	 * Hace una copia del gen y lo devuelve.
	 * @return
	 */
	public abstract Gen hacerCopia();


	public abstract void muta(int pos);
	
	
	/***********************************************************************/

	
}
