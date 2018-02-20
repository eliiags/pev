package pev1.modelo;

public class Individuo {
	
	private Gen[] genes; // cadena de bits
	private int longitud;

	private double[] fenotipo;
	private int long_fenotipo;
	
	private double aptitud; // funcion de evaluacion (fitness)
	private double puntuacion; // fitness/suma
	private double punt_acum; // puntuacion acumulada
	
}
