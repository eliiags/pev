package vista;

import java.awt.Color;
import java.util.ArrayList;

import modelo.algoritmo.AlgoritmoEvolutivo;

/**
 *
 * @author Elianni Agüero
 */
public class Vista extends javax.swing.JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	ArrayList<Double> generaciones;
	ArrayList<Double> media_fitness;
	ArrayList<Double> mejor_fitness;
	ArrayList<Double> mejor_absoluto; 
	
	
    /**
     * Creates new form Vista
     */
    public Vista() {
    	generaciones   = new ArrayList<Double>();
		media_fitness  = new ArrayList<Double>();
		mejor_fitness  = new ArrayList<Double>();
		mejor_absoluto = new ArrayList<Double>();
		initComponents();
        setVisible(true);
    }


    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        PanelOpciones = new javax.swing.JPanel();
        LabelPoblacion = new javax.swing.JLabel();
        TextFieldPoblacion = new javax.swing.JTextField();
        LabelGeneraciones = new javax.swing.JLabel();
        TextFieldGeneraciones = new javax.swing.JTextField();
        LabelElitismo = new javax.swing.JLabel();
        TextFieldElitismo = new javax.swing.JTextField();
        Separador1 = new javax.swing.JSeparator();
        LabelOpcionSeleccion = new javax.swing.JLabel();
        ComboBoxOpcionSeleccion = new javax.swing.JComboBox<>();
        TextFieldSeleccion = new javax.swing.JTextField();
        LabelOpcionCruces = new javax.swing.JLabel();
        ComboBoxOpcionCruces = new javax.swing.JComboBox<>();
        TextFieldCruces = new javax.swing.JTextField();
        LabelOpcionMutacion = new javax.swing.JLabel();
        ComboBoxOpcionMutacion = new javax.swing.JComboBox<>();
        TextFieldMutacion = new javax.swing.JTextField();
        Separador2 = new javax.swing.JSeparator();
        LabelProfundidad = new javax.swing.JLabel();
        TextFieldProfundidad = new javax.swing.JTextField();
        LabelTerminales = new javax.swing.JLabel();
        TextFieldTerminales = new javax.swing.JTextField();
        ButtonIniciar = new javax.swing.JButton();
        LabelInicializacion = new javax.swing.JLabel();
        ComboBoxInicializacion = new javax.swing.JComboBox<>();
        PanelDiagrama = new javax.swing.JPanel();
        TabbedPaneDiagrama = new javax.swing.JTabbedPane();
        plot = new org.math.plot.Plot2DPanel();
        PanelInformacion = new javax.swing.JPanel();
        ScrollPaneDesencriptar = new javax.swing.JScrollPane();
        TextAreaDesencriptar = new javax.swing.JTextArea();
        ScrollPanePlano = new javax.swing.JScrollPane();
        TextAreaPlano = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LabelPoblacion.setText("Poblacion");

        TextFieldPoblacion.setText("50");

        LabelGeneraciones.setText("Generaciones");

        TextFieldGeneraciones.setText("200");

        LabelElitismo.setText("Elitismo");

        TextFieldElitismo.setText("0.0");

        LabelOpcionSeleccion.setText("Seleccion");

        ComboBoxOpcionSeleccion.setModel(new javax.swing.DefaultComboBoxModel<>(
        		new String[] { "Estocastico",
								"ManuEli",
								"Restos", 
								"Ruleta", 
								"Torneo Deterministico",
								"Torneo Probabilistico",
								"Truncamiento" }));

        TextFieldSeleccion.setText("0.5");

        LabelOpcionCruces.setText("Cruce");

        ComboBoxOpcionCruces.setModel(new javax.swing.DefaultComboBoxModel<>(
        		new String[] { "Basico" }));

        TextFieldCruces.setText("0.6");

        LabelOpcionMutacion.setText("Mutacion");

        ComboBoxOpcionMutacion.setModel(new javax.swing.DefaultComboBoxModel<>(
        		new String[] { "Arbol", 
							   "Funcional", 
							   "ManuEli", 
							   "Permutacion", 
					           "Terminal" }));

        TextFieldMutacion.setText("0.2");

        LabelProfundidad.setText("Profundidad");

        TextFieldProfundidad.setText("5");

        LabelTerminales.setText("Terminales");

        TextFieldTerminales.setText("2");

        LabelInicializacion.setText("Inicializacion");

        ComboBoxInicializacion.setModel(new javax.swing.DefaultComboBoxModel<>(
        		new String[] { "Completa", 
        					   "Creciente", 
        					   "Ramped & Half" }));

        ButtonIniciar.setText("Iniciar");

        javax.swing.GroupLayout PanelOpcionesLayout = new javax.swing.GroupLayout(PanelOpciones);
        PanelOpciones.setLayout(PanelOpcionesLayout);
        PanelOpcionesLayout.setHorizontalGroup(
            PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOpcionesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelPoblacion)
                    .addComponent(LabelGeneraciones)
                    .addComponent(LabelElitismo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldElitismo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldGeneraciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldPoblacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addComponent(Separador1)
            .addComponent(Separador2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelProfundidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TextFieldProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(PanelOpcionesLayout.createSequentialGroup()
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelOpcionesLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(ButtonIniciar))
                    .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelOpcionesLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(LabelInicializacion)
                            .addGap(18, 18, 18)
                            .addComponent(ComboBoxInicializacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelOpcionesLayout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ComboBoxOpcionCruces, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ComboBoxOpcionMutacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ComboBoxOpcionSeleccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TextFieldCruces, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TextFieldSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TextFieldMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelOpcionesLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LabelOpcionSeleccion)
                                .addComponent(LabelOpcionCruces)
                                .addComponent(LabelOpcionMutacion)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelOpcionesLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(LabelTerminales)
                            .addGap(75, 75, 75)
                            .addComponent(TextFieldTerminales))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        PanelOpcionesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {TextFieldElitismo, TextFieldGeneraciones, TextFieldPoblacion});

        PanelOpcionesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ComboBoxOpcionCruces, ComboBoxOpcionMutacion, ComboBoxOpcionSeleccion});

        PanelOpcionesLayout.setVerticalGroup(
            PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOpcionesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPoblacion)
                    .addComponent(TextFieldPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelGeneraciones)
                    .addComponent(TextFieldGeneraciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelElitismo)
                    .addComponent(TextFieldElitismo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelOpcionSeleccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxOpcionSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LabelOpcionCruces)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxOpcionCruces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldCruces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LabelOpcionMutacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxOpcionMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Separador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelProfundidad)
                    .addComponent(TextFieldProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelTerminales)
                    .addComponent(TextFieldTerminales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelInicializacion)
                    .addComponent(ComboBoxInicializacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(ButtonIniciar)
                .addContainerGap())
        );

        PanelOpcionesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {TextFieldElitismo, TextFieldGeneraciones, TextFieldPoblacion});

        TabbedPaneDiagrama.addTab("Ejercicio", plot);

        javax.swing.GroupLayout PanelDiagramaLayout = new javax.swing.GroupLayout(PanelDiagrama);
        PanelDiagrama.setLayout(PanelDiagramaLayout);
        PanelDiagramaLayout.setHorizontalGroup(
            PanelDiagramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDiagramaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabbedPaneDiagrama)
                .addContainerGap())
        );
        PanelDiagramaLayout.setVerticalGroup(
            PanelDiagramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDiagramaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabbedPaneDiagrama)
                .addContainerGap())
        );

        TextAreaDesencriptar.setColumns(20);
        TextAreaDesencriptar.setRows(5);
        TextAreaDesencriptar.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        TextAreaDesencriptar.setEditable(true);
        TextAreaDesencriptar.setLineWrap(true);
        TextAreaDesencriptar.setWrapStyleWord(true);
        ScrollPaneDesencriptar.setViewportView(TextAreaDesencriptar);
        

        TextAreaPlano.setColumns(20);
        TextAreaPlano.setRows(5);
        TextAreaPlano.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
        TextAreaPlano.setLineWrap(true);
        TextAreaPlano.setWrapStyleWord(true);
        ScrollPanePlano.setViewportView(TextAreaPlano);
        ScrollPanePlano.setLocation(0, 0);

        javax.swing.GroupLayout PanelInformacionLayout = new javax.swing.GroupLayout(PanelInformacion);
        PanelInformacion.setLayout(PanelInformacionLayout);
        PanelInformacionLayout.setHorizontalGroup(
            PanelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInformacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPaneDesencriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ScrollPanePlano, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelInformacionLayout.setVerticalGroup(
            PanelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInformacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ScrollPanePlano, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(ScrollPaneDesencriptar))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addComponent(PanelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelDiagrama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addComponent(PanelDiagrama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton ButtonIniciar;
    private javax.swing.JComboBox<String> ComboBoxInicializacion;
    private javax.swing.JComboBox<String> ComboBoxOpcionCruces;
    private javax.swing.JComboBox<String> ComboBoxOpcionMutacion;
    private javax.swing.JComboBox<String> ComboBoxOpcionSeleccion;
    private javax.swing.JLabel LabelElitismo;
    private javax.swing.JLabel LabelGeneraciones;
    private javax.swing.JLabel LabelInicializacion;
    private javax.swing.JLabel LabelOpcionCruces;
    private javax.swing.JLabel LabelOpcionMutacion;
    private javax.swing.JLabel LabelOpcionSeleccion;
    private javax.swing.JLabel LabelPoblacion;
    private javax.swing.JLabel LabelProfundidad;
    private javax.swing.JLabel LabelTerminales;
    private javax.swing.JPanel PanelDiagrama;
    private javax.swing.JPanel PanelInformacion;
    private javax.swing.JPanel PanelOpciones;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JScrollPane ScrollPaneDesencriptar;
    private javax.swing.JScrollPane ScrollPanePlano;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JSeparator Separador2;
    private javax.swing.JTabbedPane TabbedPaneDiagrama;
    private javax.swing.JTextArea TextAreaDesencriptar;
    private javax.swing.JTextArea TextAreaPlano;
    private javax.swing.JTextField TextFieldCruces;
    private javax.swing.JTextField TextFieldElitismo;
    private javax.swing.JTextField TextFieldGeneraciones;
    private javax.swing.JTextField TextFieldPoblacion;
    private javax.swing.JTextField TextFieldProfundidad;
    private javax.swing.JTextField TextFieldSeleccion;
    private javax.swing.JTextField TextFieldTerminales;
    private javax.swing.JTextField TextFieldMutacion;
    private org.math.plot.Plot2DPanel plot;

    
    public void conectaControlador(AlgoritmoEvolutivo c){
		ButtonIniciar.addActionListener(c);
        ButtonIniciar.setActionCommand("INICIAR");
        plot.removeAllPlots();
//        new Thread() {
//        	@Override
//        	public void run() {
//        		generaciones.add(new ArrayList<Double>());
//        		media_fitness.add(new ArrayList<Double>());
//        		mejor_fitness.add(new ArrayList<Double>());
//        		mejor_absoluto.add(new ArrayList<Double>());
//        		
//        	}
//        }.start();
	}
	
	
	
	public void actualiza (double generacion, double media_fitness, 
			double mejor_fitness, double mejor_absoluto, String texto) {
		
		
		
		if (generacion == 0.0) {
			this.generaciones   = new ArrayList<Double>();
			this.media_fitness  = new ArrayList<Double>();
			this.mejor_fitness  = new ArrayList<Double>();
			this.mejor_absoluto = new ArrayList<Double>();
			plot.removeAllPlots();
		}
		
		this.generaciones.add(generacion);
		this.media_fitness.add(media_fitness);
		this.mejor_fitness.add(mejor_fitness);
		this.mejor_absoluto.add(mejor_absoluto);
		
//		this.generaciones.get(this.generaciones.size() - 1).add(generacion);
//		this.media_fitness.get(this.media_fitness.size() - 1).add(media_fitness);
//		this.mejor_fitness.get(this.mejor_fitness.size() - 1).add(mejor_fitness);
//		this.mejor_absoluto.get(this.mejor_absoluto.size() - 1).add(mejor_absoluto);
		
		javax.swing.SwingUtilities.invokeLater(() -> {
			try {
				plot.removeAllPlots();
				TextAreaPlano.setText("");

//				double[] array_generaciones   = this.generaciones.get(this.generaciones.size() - 1).stream().mapToDouble(d -> d).toArray();
//				double[] array_media_fitness  = this.media_fitness.get(this.media_fitness.size() - 1).stream().mapToDouble(d -> d).toArray();
//				double[] array_mejor_fitness  = this.mejor_fitness.get(this.mejor_fitness.size() - 1).stream().mapToDouble(d -> d).toArray();
//				double[] array_mejor_absoluto = this.mejor_absoluto.get(this.mejor_absoluto.size() - 1).stream().mapToDouble(d -> d).toArray();
				
				double[] array_generaciones   = this.generaciones.stream().mapToDouble(d -> d).toArray();
				double[] array_media_fitness  = this.media_fitness.stream().mapToDouble(d -> d).toArray();
				double[] array_mejor_fitness  = this.mejor_fitness.stream().mapToDouble(d -> d).toArray();
				double[] array_mejor_absoluto = this.mejor_absoluto.stream().mapToDouble(d -> d).toArray();
				
				
//				System.out.println("*********VISTA************+");
//				for (int i = 0; i < array_generaciones.length; i++) {
//					System.out.println("Generaciones: " + array_generaciones[i]);
//					System.out.println("Media: " + array_media_fitness[i]);
//					System.out.println("Mejor: " + array_mejor_fitness[i]);
//					System.out.println("Absoluto: " + array_mejor_absoluto[i]);
//				}
//				System.out.println("");
				
				
				
				TextAreaPlano.setText(texto.toString());

				plot.removeAllPlots();
				plot.addLegend("SOUTH");
				
				// add a line plot to the PlotPanel
				plot.addLinePlot("Mejor absoluto", Color.BLUE, array_generaciones, array_mejor_absoluto);
				plot.addLinePlot("Media fitness", Color.RED, array_generaciones, array_media_fitness);
				plot.addLinePlot("Mejor", Color.GREEN, array_generaciones, array_mejor_fitness);
				
				
			}
			catch (Exception ex){
				plot.removeAllPlots();
			}
		});
		

		this.repaint();
	}
	
	
	public void borrarDatos() {
		plot.removeAllPlots();
	}
	
	
	
	public void actualizarGrafica(double[] generaciones, double[] media_fitness, 
			double[] fitness_mejor,	double[] mejor_absoluto){
		
		System.out.println("Vista");
		javax.swing.SwingUtilities.invokeLater(() -> {
			try {
				plot.removeAllPlots();
//				TextAreaPlano.setText("");
				
				for (int i = 0; i < generaciones.length; i++) {
					System.out.println("Generaciones: " + generaciones[i]);
				}
				
				System.out.println("*********VISTA************+");
				for (int i = 0; i < media_fitness.length; i++) {
					System.out.println("Media: " + media_fitness[i]);
					System.out.println("Mejor: " + fitness_mejor[i]);
					System.out.println("Absoluto: " + mejor_absoluto[i]);
				}
				
				
				// define the legend position
				plot.addLegend("SOUTH");
				
				// add a line plot to the PlotPanel
				plot.addLinePlot("Mejor absoluto", Color.BLUE, generaciones, mejor_absoluto);
				plot.addLinePlot("Media fitness", Color.RED, generaciones, media_fitness);
				plot.addLinePlot("Mejor", Color.GREEN, generaciones, fitness_mejor);
  
//				TextAreaPlano.setText(texto.toString());
			}
			catch (Exception ex){
				
			}
		});
	  
	  
	}
	
    

	public javax.swing.JTextField getTextFieldPoblacion() {
		return TextFieldPoblacion;
	}

	public void setTextFieldPoblacion(javax.swing.JTextField textFieldPoblacion) {
		TextFieldPoblacion = textFieldPoblacion;
	}

	
	public javax.swing.JTextField getTextFieldGeneraciones() {
		return TextFieldGeneraciones;
	}

	public void setTextFieldGeneraciones(javax.swing.JTextField textFieldGeneraciones) {
		TextFieldGeneraciones = textFieldGeneraciones;
	}


	public javax.swing.JTextField getTextFieldSeleccion() {
		return TextFieldSeleccion;
	}

	public void setTextFieldSeleccion(javax.swing.JTextField textFieldSeleccion) {
		TextFieldSeleccion = textFieldSeleccion;
	}


	public javax.swing.JTextField getTextFieldCruces() {
		return TextFieldCruces;
	}

	public void setTextFieldCruces(javax.swing.JTextField textFieldCruces) {
		TextFieldCruces = textFieldCruces;
	}


	public javax.swing.JTextField getTextFieldMutacion() {
		return TextFieldMutacion;
	}

	public void setTextFieldMutacion(javax.swing.JTextField textFieldMutacion) {
		TextFieldMutacion = textFieldMutacion;
	}


	public javax.swing.JTextField getTextFieldElitismo() {
		return TextFieldElitismo;
	}

	public void setTextFieldElitismo(javax.swing.JTextField textFieldElitismo) {
		TextFieldElitismo = textFieldElitismo;
	}



	public javax.swing.JComboBox<String> getComboBoxOpcionSeleccion() {
		return ComboBoxOpcionSeleccion;
	}

	public void setComboBoxOpcionSeleccion(javax.swing.JComboBox<String> comboBoxOpcionSeleccion) {
		ComboBoxOpcionSeleccion = comboBoxOpcionSeleccion;
	}


	public javax.swing.JComboBox<String> getComboBoxOpcionCruce() {
		return ComboBoxOpcionCruces;
	}

	public void setComboBoxOpcionCruce(javax.swing.JComboBox<String> comboBoxOpcionCruce) {
		ComboBoxOpcionCruces = comboBoxOpcionCruce;
	}


	public javax.swing.JComboBox<String> getComboBoxOpcionMutacion() {
		return ComboBoxOpcionMutacion;
	}

	public void setComboBoxOpcionMutacion(javax.swing.JComboBox<String> comboBoxOpcionMutacion) {
		ComboBoxOpcionMutacion = comboBoxOpcionMutacion;
	}

	
	public javax.swing.JTextField getTextFieldProfundidad() {
		return TextFieldProfundidad;
	}

	public void setTextFieldProfundidad(javax.swing.JTextField textFieldProfundidad) {
		TextFieldElitismo = textFieldProfundidad;
	}

	
	public javax.swing.JTextField getTextFieldTerminales() {
		return TextFieldTerminales;
	}

	public void setTextFieldTerminales(javax.swing.JTextField textFieldTerminales) {
		TextFieldElitismo = textFieldTerminales;
	}
	
	
	public javax.swing.JComboBox<String> getComboBoxInicializacion() {
		return ComboBoxInicializacion;
	}

	public void setComboBoxInicializacion(javax.swing.JComboBox<String> comboBoxInicializacion) {
		ComboBoxInicializacion = comboBoxInicializacion;
	}
	
}
