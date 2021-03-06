package modelo;

import java.util.ArrayList;

public interface Nodo {

	public Nodo getNodo(int num_nodo);
	
	public void setNodo(int num_nodo, Nodo nodo);
	
	public double getValor(double valor);


	public void inicializar(int profundidad, int num_terminales, 
			int tipo, ArrayList<ArrayList<String>> operadores);
	
	
	public int numNodos();

	public int numNodosBinarios();
	
	public int numNodosUnarios();
	
	public int numNodosTerminales();
	
	
	public boolean esHoja();
	
	public void muta(int num_nodo);
	
	public boolean muta(int num_nodo, int tipo_mutacion);
	
	
	public void hacerPoda(int profundidad);
	
	public Nodo hacerCopia();
	
	public String toString();
}
