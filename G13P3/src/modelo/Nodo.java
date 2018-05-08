package modelo;

public interface Nodo {

	/**
	 * Dada una profundidad inicializa el arbol.
	 * @param profundidad
	 */
	public void inicializar(int profundidad);

	
	/**
	 * Devuelve el valor del nodo (recursivamente calcula 
	 * el valor de los hijos de dicho nodo).
	 * @param valor
	 * @return
	 */
	public double getValor(double valor);
	
	
	/**
	 * Dado un numero aleatorio hace un recorrido preorden 
	 * para encontrar un nodo especifico.
	 * @param aleatorio
	 * @return el nodo buscado.
	 */
	public Nodo encuentraNodo(int aleatorio);
	
	/**
	 * Devuelve el numero de nodos que tiene el arbol. 
	 * Incluido las hojas
	 * @return
	 */
	public int numNodos();
	
	
	/**
	 * Dado un numero de nodo muta.
	 * @param numNodo
	 */
	public void muta(int numNodo);
	
	
	public String toString();
}
