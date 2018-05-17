package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Terminal implements Nodo {

	private int[] datos;
	
	private String terminal;
	
	private int num_terminales;
	
	
	@Override
	public void inicializar(int profundidad, int num_terminales) {
		
		datos = new int[2];
		datos[0] = profundidad;
		datos[1] = num_terminales;
		
		this.num_terminales = num_terminales;
		
		ArrayList<Character> terminales = new ArrayList<Character>();
		
		for (int i = 0; i < num_terminales; i++) {
			terminales.add((char) (i + 65));
		}
		
		int i;
		
		Random random = new Random();
		i = random.nextInt(terminales.size());
			
		this.terminal = terminales.get(i) + "";
		
	}
	
	
	@Override
	public double getValor(double valor) {
		return valor;
	}

	
//	@Override
//	public Nodo encuentraNodo(int aleatorio) {
//		
//		if (aleatorio == 0) {
//			return this;
//		}
//		
//		return null;
//	}

	
	@Override
	public int numNodos() {
		return 1;
	}

	
	@Override
	public void muta(int numNodo) {

		if (numNodo == -1) {
			return;
		}
		
		if (numNodo == 0) {

			Random random = new Random();
			
			ArrayList<Character> terminales = new ArrayList<Character>();
			
			for (int i = 0; i < this.num_terminales; i++) {
				terminales.add((char) (i + 65));
			}
			
			terminales.remove(new Character(this.terminal.charAt(0)));
			this.terminal = terminales.get(random.nextInt(terminales.size())) + "";
			
		}
		
	}

	
	public String toString() {
		return this.terminal;
	}


	@Override
	public Nodo hacerCopia() {
		Terminal nodo = new Terminal();
		nodo.terminal = this.terminal;
		nodo.num_terminales = this.num_terminales;
		return nodo; 
	}
	
	@Override
	public Nodo getNodo(int num_nodo) {
		
		if (num_nodo == 0){
			return this;
		}
		
		return null;
	}


	@Override
	public void setNodo(int num_nodo, Nodo nodo) {
		
	}


	@Override
	public boolean esHoja() {
		return true;
	}


	@Override
	public boolean muta(int tipo_mutacion, double prob) {
		Random random = new Random(System.currentTimeMillis());
		double p = Math.random(); 
		
		switch (tipo_mutacion) {
		case 0:
			break;
		case 1:
			if (p > prob) {
				ArrayList<Character> terminales = new ArrayList<Character>();
				
				for (int i = 0; i < this.num_terminales; i++) {
					terminales.add((char) (i + 65));
				}
				
				terminales.remove(new Character(this.terminal.charAt(0)));
				this.terminal = terminales.get(random.nextInt(terminales.size())) + "";
				
				return true;
			}
			break;
		default:
			break;
		}
		
		return false;
	}


	@Override
	public void mutacionArbol(int num_nodo) {
		
	}

	
}
