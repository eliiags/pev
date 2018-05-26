package modelo;

import java.util.ArrayList;

public class Gen {

	// Longitud del gen
	private final static int LONGITUD = 26;

	// Array list de alelos
	private ArrayList<Character> alelos;

	
	public Gen() {
		this.alelos = new ArrayList<Character>();
	}
	

	/**************************** GET & SET ********************************/
	
	public char getAlelo(int pos) {
		return (char) this.alelos.get(pos);
	}

	public void setAlelo(char alelo, int pos) {
		this.alelos.set(pos, alelo);
	}

	public ArrayList<Character> getAlelos() {
		return this.alelos;
	}
	
	public int getLongitud() {
		return LONGITUD;
	}

	/***********************************************************************/
	
	
	
	
	public void inicializaGen() {

		ArrayList<Character> abc = new ArrayList<Character>();
		
		int rand = 0;
		
		for (int i = 0; i < LONGITUD; i++) {
			abc.add((char) (i + 97));
		}
		
		for (int i = 0; i < LONGITUD; i++) {
			rand = ((int) (Math.random() * (abc.size())));
			
			this.alelos.add(abc.get(rand));
			
			abc.remove(rand);
		}
		
	}

	
	public Gen hacerCopia() {
		
		Gen gen = new Gen();

		for (int i = 0; i < LONGITUD; i++) {
			gen.alelos.add(this.alelos.get(i));
//			gen.setAlelo(this.alelos.get(i), i);
		}
		
		return gen;
	}

	
	public String toString() {
		String dev = "";
		
		for (int i = 0; i < this.alelos.size(); i++) {
			dev += this.alelos.get(i) + " "; 
		} 
		
		return dev;
	}



	
}
