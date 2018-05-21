package modelo;

import java.util.ArrayList;

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
	
	
	/**
	 * Hace una copia del gen y lo devuelve.
	 * @return
	 */
	public abstract Gen hacerCopia();


	
	/**
	 * Dado una posicion devuelve el valor
	 * @param pos
	 * @return
	 */
	public abstract Object getAlelo(int pos);
	
	
	/**
	 * dado una posicion y un valor, lo establece
	 * @param alelo
	 * @param pos
	 */
	public abstract void setAlelo(Object alelo, int pos);
	
	
	public abstract ArrayList<Character> getAlelos();
	
	/***********************************************************************/
	
	
}
