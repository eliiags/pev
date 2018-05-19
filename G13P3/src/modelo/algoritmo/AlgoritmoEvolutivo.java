package modelo.algoritmo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import modelo.Cromosoma;
import modelo.cruce.Cruce;
import modelo.cruce.CruceBasico;
import modelo.mutacion.Mutacion;
import modelo.mutacion.MutacionArbol;
import modelo.mutacion.MutacionFuncional;
import modelo.mutacion.MutacionManuEli;
import modelo.mutacion.MutacionPermutacion;
import modelo.mutacion.MutacionTerminal;
import modelo.seleccion.Seleccion;
import modelo.seleccion.SeleccionEstocastico;
import modelo.seleccion.SeleccionManuEli;
import modelo.seleccion.SeleccionRestos;
import modelo.seleccion.SeleccionRuleta;
import modelo.seleccion.SeleccionTorneoDeterministico;
import modelo.seleccion.SeleccionTorneoProbabilistico;
import modelo.seleccion.SeleccionTruncamiento;
import vista.Vista;

public class AlgoritmoEvolutivo implements ActionListener{

	private ArrayList<Cromosoma> poblacion;
	
	private int tam_poblacion,
				num_generaciones,
				cont_generaciones;
	
	private int profundidad,
				num_terminales, 
				tipo_ini;
	
	private double total_fitness;
	
	private Cromosoma mejor_cromosoma,
					  mejor_absoluto;
	
	
	private double porcentaje_elite;
	

	private Seleccion seleccion;
	private Cruce cruce;
	private Mutacion mutacion;
	private Vista vista;
	
	private double prob_seleccion,
				   prob_mutacion,
				   prob_cruce;
	
	private double mejor,
				   media,
				   absoluto,
				   generaciones;
	

	
	public AlgoritmoEvolutivo() {
		
	}	

	
	public void conectarVista(Vista vista) {
		this.vista = vista;
	}
	
	
	public void setDatos(
			int tam_poblacion,
			int num_generaciones,
			double porcentaje_elite,
			int opcion_seleccion,
			int opcion_cruce,
			int opcion_mutacion,
			double prob_seleccion,
			double prob_cruce,
			double prob_mutacion, 
			int profundidad,
			int terminales, 
			int inicializacion) {

		
		this.tam_poblacion = tam_poblacion;
		
		this.num_generaciones  = num_generaciones;
		this.cont_generaciones = 0;
		
		this.porcentaje_elite = porcentaje_elite;
		
		this.prob_seleccion = prob_seleccion;
		this.prob_cruce     = prob_cruce;
		this.prob_mutacion  = prob_mutacion;
	
		this.profundidad    = profundidad;
		this.num_terminales = terminales;
		
		this.tipo_ini = inicializacion;
		
		this.poblacion = new ArrayList<Cromosoma>();
		
		seleccion(opcion_seleccion);
		cruce();
		mutacion(opcion_mutacion);
		
		boolean elite = this.porcentaje_elite == 0.0 ? false : true; 
		
		this.ejecuta(elite);
	}
	
	
	public void ejecuta(boolean elitismo) {
		
		int tam_elite = 0;
		
		if (elitismo) {
			tam_elite = calcularTamElite();
		}

		Cromosoma[] elite = new Cromosoma[tam_elite];
		
		crearPoblacion();
		evaluarPoblacion();

//		System.out.println("Poblacion inicial: ");
//		for (Cromosoma crm: poblacion) {
//			System.out.println(crm.toString());
//			System.out.println("Fitness: " + crm.getAptitud());
//		}
		
		for (int i = 0; i < this.num_generaciones; i++){
			
			if (elitismo) {
				elite = separarMejores(tam_elite);
			}
			
//			System.out.println("");
//			System.out.println("Elite: ");
//			for (Cromosoma crm: elite) {
//				System.out.println(crm.toString());
//				System.out.println("Fitness: " + crm.getAptitud());
//			}

			// Aplicamos el proceso de seleccion/reproduccion/mutacion		
			poblacion = seleccion.seleccionar(poblacion);
			
//			System.out.println("");
//			System.out.println("Seleccion: ");
//			for (Cromosoma crm: poblacion) {
//				System.out.println(crm.toString());
//				System.out.println("Fitness: " + crm.getAptitud());
//			}
			
			this.cruce.reproduccion(poblacion, this.prob_cruce);
			

			this.mutacion.muta(poblacion, this.prob_mutacion);

//			System.out.println("");
//			System.out.println("Mutacion: ");
//			for (Cromosoma crm: poblacion) {
//				System.out.println(crm.toString());
//				System.out.println("Fitness: " + crm.getAptitud());
//			}
			
			if (elitismo) {
				//Volvemos a integrar a la élite
				incluirMejores(elite);
			}
			
//			System.out.println("");
//			System.out.println("Elite incorporada: ");
//			for (Cromosoma crm: poblacion) {
//				System.out.println(crm.toString());
//				System.out.println("Fitness: " + crm.getAptitud());
//			}
			
			evaluarPoblacion();
			actualizarValoresGrafica();
			this.vista.actualiza(this.generaciones, this.media, 
					 this.mejor, this.absoluto, "");

//			System.out.println("");
//			System.out.println("El mejor de esta generacion es: " + this.mejor_cromosoma.toString());
//			System.out.println("Con un fitness de: " + this.mejor_cromosoma.getAptitud());
//			System.out.println("");

//			System.out.println("El mejor de esta generacion es: " + this.mejor_absoluto.toString());
//			System.out.println("Con un fitness de: " + this.mejor_absoluto.getAptitud());
//			System.out.println("");
			
//			pinta();
//			System.out.println("");
			cont_generaciones++;
		}
		
		System.out.println("El mejor es: " + this.mejor_absoluto.toString());
		System.out.println("Con un fitness de: " + this.mejor_absoluto.getAptitud());
		System.out.println("");
		
		this.vista.borrarDatos();
		
	}
	
	
	
	/**
	 * Creamos el cromosoma y calculamos su fitness
	 */
	public void crearPoblacion() {

		if (tipo_ini == 2) {
			rampedAndHalf();
			return;
		}
		
		for (int i = 0; i < this.tam_poblacion; i++) {
			this.poblacion.add(new Cromosoma(this.profundidad, this.num_terminales, this.tipo_ini));
			this.poblacion.get(i).inicializarCromosoma();
		}

	}
	
	
	public void rampedAndHalf() {
		
		int num_grupos     = this.tam_poblacion / (this.profundidad - 1);
		int num_cromosomas = this.tam_poblacion / num_grupos;
		
		int profundidad_grupo = 2;
		int grupo = 0;
		
		for (int i = 0; i < this.tam_poblacion; i++) {
			// 0: Completa
			// 1: Creciente
			this.poblacion.add(new Cromosoma(profundidad_grupo, this.num_terminales, i % 2));
			this.poblacion.get(i).inicializarCromosoma();
			
			if ((i / num_cromosomas) > grupo)  {
				profundidad_grupo++;
				grupo++;
			}
		}
		
	}
	
	
	public void evaluarPoblacion() {
		
		// Calculamos el total del fitness
		calcularTotalFitness();
		
		// Evaluamos el primer cromosoma de la poblacion
		this.poblacion.get(0).evaluarCromosoma(this.total_fitness, 0.0);
		this.mejor_cromosoma = this.poblacion.get(0).hacerCopia();
				
		if (this.cont_generaciones == 0) {
			this.mejor_absoluto = this.poblacion.get(0).hacerCopia();
		}
		
		
		for (int i = 1; i < this.poblacion.size(); i++) {
			this.poblacion.get(i).evaluarCromosoma(this.total_fitness, 
					this.poblacion.get(i - 1).getAcumulada());
			
			if (this.poblacion.get(i).getAptitud() < this.mejor_cromosoma.getAptitud()) {
				this.mejor_cromosoma = this.poblacion.get(i).hacerCopia();
			}	
			
			if (this.mejor_cromosoma.getAptitud() < this.mejor_absoluto.getAptitud()) {
				this.mejor_absoluto = this.mejor_cromosoma.hacerCopia();
			}
			
		}
		
		
	}
	
	
	private void calcularTotalFitness() {
		
		this.total_fitness = 0.0;
		
		for (int i = 0; i < this.poblacion.size(); i++) {
			this.total_fitness += this.poblacion.get(i).getAptitud();
		}
		
	}
	
	
	private void seleccion(int seleccion) {
		
		switch (seleccion) {
		case 0:
			this.seleccion = new SeleccionEstocastico();
			break;
		case 1:
			this.seleccion = new SeleccionManuEli();
			break;
		case 2:
			this.seleccion = new SeleccionRestos();
			break;
		case 3:
			this.seleccion = new SeleccionRuleta();
			break;
		case 4:
			this.seleccion = new SeleccionTorneoDeterministico();
			break;
		case 5:
			this.seleccion = new SeleccionTorneoProbabilistico();
			break;
		case 6:
			this.seleccion = new SeleccionTruncamiento(this.prob_seleccion);
			break;
		default:
			break;
		}
		
	}
	
	
	private void cruce() {
		this.cruce = new CruceBasico();
	}
	
	
	private void mutacion(int mutacion) {

		switch (mutacion) {
		case 0:
			this.mutacion = new MutacionArbol();
			break;
		case 1:
			this.mutacion = new MutacionFuncional();
			break;
		case 2:
			this.mutacion = new MutacionManuEli();
			break;
		case 3:
			this.mutacion = new MutacionPermutacion();
			break;
		case 4:
			this.mutacion = new MutacionTerminal();
			break;
		default:
			break;
		}
	
	}
	
	
	private Cromosoma[] separarMejores(int tam_elite) {
		
		// Mejores cromosomas
		Cromosoma[] mejores = new Cromosoma[tam_elite];

		// Array donde se almacenaras las aptitudes para ordenarlas posteriormente
		double[] aptitudes = new double[this.tam_poblacion];
		
		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = this.poblacion.get(i).getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);
		
		for (int i = 0; i < mejores.length; i++) {
			for (int j = 0; j < this.poblacion.size(); j++) {
				if (aptitudes[i] == this.poblacion.get(j).getAptitud()) {
					mejores[i] = this.poblacion.get(j).hacerCopia();
				}
			}
		}
		
			
				
		return mejores;

	}
	
	
	private void incluirMejores(Cromosoma[] elite) {

		// Buscamos los peores
		double[] aptitudes = new double[this.tam_poblacion];
		
		// Actualizamos el array de aptitudes con el fitness de cada individuo;
		for (int i = 0; i < aptitudes.length; i++) {
			aptitudes[i] = this.poblacion.get(i).getAptitud();
		}
		
		// Ordenador de mayor a menor
		Arrays.sort(aptitudes);

		for (int i = 0; i < elite.length; i++) {
			boolean encontrado = false;
			for (int j = 0; j < this.poblacion.size(); j++) {
				if (!encontrado && aptitudes[aptitudes.length - 1 - i] == this.poblacion.get(j).getAptitud()) {
					this.poblacion.set(j, elite[i].hacerCopia());
					encontrado = true;
				}
			}
		}
	}

	
	private int calcularTamElite() {
		return (int) Math.ceil(this.tam_poblacion * this.porcentaje_elite / 100);
	}	
	


	public void actualizarValoresGrafica() {

		if (this.cont_generaciones < this.num_generaciones) {
			
			this.media = this.total_fitness / this.tam_poblacion;
			this.mejor = this.mejor_cromosoma.getAptitud();
			this.absoluto     = this.mejor_absoluto.getAptitud();
			this.generaciones = this.cont_generaciones;
			
		}

			
	}

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando  = e.getActionCommand();

		if (comando == "INICIAR") {
			new Thread() {
				@Override
				public void run () {
					iniciar();
				}
			}.start();
		}
		
	}
	
	
	
	
	public void iniciar() {

		// Hacemos las conversiones
        int tam_poblacion;
        int num_generaciones;
        double prob_seleccion;
        double prob_cruce;
        double prob_mutacion;
        double porcentaje_elite; 
        int opcion_seleccion;
        int opcion_cruce;
        int opcion_mutacion;
        int profundidad;
        int terminales;
        int inicializacion;
		
		// Obtenemos los valores
        tam_poblacion = Integer.parseInt(this.vista.getTextFieldPoblacion().getText());
        num_generaciones = Integer.parseInt(this.vista.getTextFieldGeneraciones().getText()); 
        
        prob_seleccion = Double.parseDouble(this.vista.getTextFieldSeleccion().getText());
        prob_cruce     = Double.parseDouble(this.vista.getTextFieldCruces().getText());
        prob_mutacion  = Double.parseDouble(this.vista.getTextFieldMutacion().getText());
        
        porcentaje_elite = Double.parseDouble(this.vista.getTextFieldElitismo().getText());
        
        opcion_seleccion = this.vista.getComboBoxOpcionSeleccion().getSelectedIndex();
        opcion_cruce     = this.vista.getComboBoxOpcionCruce().getSelectedIndex();
        opcion_mutacion  = this.vista.getComboBoxOpcionMutacion().getSelectedIndex();

        profundidad    = Integer.parseInt(this.vista.getTextFieldProfundidad().getText());
        terminales     = Integer.parseInt(this.vista.getTextFieldTerminales().getText());
        inicializacion = this.vista.getComboBoxInicializacion().getSelectedIndex();
		
        
        // Creamos el algoritmo genetico
		setDatos(
				tam_poblacion, 
        		num_generaciones, 
        		porcentaje_elite, 
        		
        		opcion_seleccion, 
        		opcion_cruce, 
        		opcion_mutacion, 
        		
        		prob_seleccion, 
        		prob_cruce, 
        		prob_mutacion, 
        		
				profundidad,
				terminales, 
				inicializacion);
	
	}
	
	
}