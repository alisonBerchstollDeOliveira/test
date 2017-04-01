package py.edu.facitec.proyectotaller5.app;

import java.awt.DefaultKeyboardFocusManager;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.hibernate.event.spi.RefreshEvent;

import py.edu.facitec.proyectotaller5.boton.BotonPersonalizado;
import py.edu.facitec.proyectotaller5.contenedor.PanelFondo;
import py.edu.facitec.proyectotaller5.dao.AmbienteDao;
import py.edu.facitec.proyectotaller5.dao.ClientesDao;
import py.edu.facitec.proyectotaller5.dao.EquiposDao;
import py.edu.facitec.proyectotaller5.dao.TecnicosDao;
import py.edu.facitec.proyectotaller5.formulario.FormularioAmbiente;
import py.edu.facitec.proyectotaller5.formulario.FormularioCliente;
import py.edu.facitec.proyectotaller5.formulario.FormularioEquipos;
import py.edu.facitec.proyectotaller5.formulario.FormularioInicializar;
import py.edu.facitec.proyectotaller5.formulario.FormularioTecnico;
import py.edu.facitec.proyectotaller5.modelo.Ambiente;
import py.edu.facitec.proyectotaller5.util.Factory;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaPrincipal extends JFrame implements KeyEventDispatcher {

	private PanelFondo contentPane;
	
	private FormularioCliente formCliente;
	private FormularioTecnico formTecnicos;
	private FormularioEquipos formEquipos;
	private FormularioAmbiente formAmbiente;
	private String name;

	private Ambiente ambiente;
	private List<Ambiente> ambientes = new ArrayList<>();
	private AmbienteDao ambienteDao;

	private PantallaPrincipal frame;

	private JMenuItem mntmConfiguracion;

	private ClientesDao clientesDao;

	private EquiposDao equiposDao;

	private TecnicosDao tecnicoDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private PantallaPrincipal frame;

			public void run() {
				try {
					Factory.setUp();
					frame = new PantallaPrincipal();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/electrocenter.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		setMinimumSize(new Dimension(800, 600));
		setLocationRelativeTo(this);
		ambiente();
		setTitle(name);
		
		
		setExtendedState(MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnProcesos = new JMenu("Procesos");
		mnProcesos.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnProcesos.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/proceso.png")));
		menuBar.add(mnProcesos);
		
		JMenuItem mntmRegistrarReparacion = new JMenuItem("Registrar Reparación");
		mntmRegistrarReparacion.setEnabled(false);
		mntmRegistrarReparacion.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/registro.png")));
		mntmRegistrarReparacion.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnProcesos.add(mntmRegistrarReparacion);
		
		JMenuItem mntmDeudaAPagar = new JMenuItem("Deuda a pagar");
		mntmDeudaAPagar.setEnabled(false);
		mntmDeudaAPagar.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/pago.png")));
		mntmDeudaAPagar.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnProcesos.add(mntmDeudaAPagar);
		
		JMenu mnTablas = new JMenu("Tablas");
		mnTablas.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/tabla.png")));
		mnTablas.setFont(new Font("Dialog", Font.PLAIN, 14));
		menuBar.add(mnTablas);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formCliente= new FormularioCliente();
				formCliente.setVisible(true);
			}
		});
		mntmClientes.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/cliente menu.png")));
		mntmClientes.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnTablas.add(mntmClientes);
		
		JMenuItem mntmTecnicos = new JMenuItem("Técnicos");
		mntmTecnicos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				formTecnicos = new FormularioTecnico();
				formTecnicos.setVisible(true);
			}
		});
		mntmTecnicos.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/tecnico.png")));
		mntmTecnicos.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnTablas.add(mntmTecnicos);
		
		JMenuItem mntmEquipos = new JMenuItem("Equipos");
		mntmEquipos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				formEquipos= new FormularioEquipos();
				formEquipos.setVisible(true);
			}
		});
		mntmEquipos.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/equipos.png")));
		mntmEquipos.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnTablas.add(mntmEquipos);
		
		JMenu mnInformes = new JMenu("Informes");
		mnInformes.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/informes.png")));
		mnInformes.setFont(new Font("Dialog", Font.PLAIN, 14));
		menuBar.add(mnInformes);
		
		JMenuItem mntmListadoDeClientes = new JMenuItem("Listado de Clientes");
		mntmListadoDeClientes.setEnabled(false);
		mntmListadoDeClientes.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/lista cliente.png")));
		mntmListadoDeClientes.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnInformes.add(mntmListadoDeClientes);
		
		JMenuItem mntmListadoDeProductos = new JMenuItem("Listado de T\u00E9cnicos");
		mntmListadoDeProductos.setEnabled(false);
		mntmListadoDeProductos.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/lista producto.png")));
		mntmListadoDeProductos.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnInformes.add(mntmListadoDeProductos);
		
		JMenuItem mntmInformeDeDeuda = new JMenuItem("Informe de Deuda");
		mntmInformeDeDeuda.setEnabled(false);
		mntmInformeDeDeuda.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/infoDeuda.png")));
		mntmInformeDeDeuda.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnInformes.add(mntmInformeDeDeuda);
		
		JMenuItem mntmInformeDeReparaciones = new JMenuItem("Informe de Reparaciones");
		mntmInformeDeReparaciones.setEnabled(false);
		mntmInformeDeReparaciones.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/informe reparacion.png")));
		mntmInformeDeReparaciones.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnInformes.add(mntmInformeDeReparaciones);
		
		JMenuItem mntmInformeDe = new JMenuItem("Informe de Cobranza");
		mntmInformeDe.setEnabled(false);
		mntmInformeDe.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/infoCobranza.png")));
		mntmInformeDe.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnInformes.add(mntmInformeDe);
		
		JMenu mnUtilidades = new JMenu("Utilidades");
		mnUtilidades.setSelectedIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/cobranza.png")));
		mnUtilidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				desabilitarMenu();
			}
		});
		
		mnUtilidades.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/utilidades.png")));
		mnUtilidades.setFont(new Font("Dialog", Font.PLAIN, 14));
		menuBar.add(mnUtilidades);
		
		JMenuItem mntmInicializacionDeDatos = new JMenuItem("Inicialización de Datos");
		mntmInicializacionDeDatos.addActionListener(new ActionListener() {
			private FormularioInicializar formInicializar;

			public void actionPerformed(ActionEvent arg0) {
				formInicializar = new FormularioInicializar();
				formInicializar.setVisible(true);
				setVisible(false);
			}
			
		});
		
			
		mntmInicializacionDeDatos.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/inicializacion.png")));
		mntmInicializacionDeDatos.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnUtilidades.add(mntmInicializacionDeDatos);
		
		mntmConfiguracion = new JMenuItem("Configuraci\u00F3n");
		mntmConfiguracion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				formAmbiente = new FormularioAmbiente();
				formAmbiente.setVisible(true);
				setVisible(false);
			}
		});
		mntmConfiguracion.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/configuracion.png")));
		mntmConfiguracion.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnUtilidades.add(mntmConfiguracion);
		
		JMenu mnSalir = new JMenu("Salir");
		mnSalir.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/salirMenu.png")));
		mnSalir.setSelectedIcon(new ImageIcon(PantallaPrincipal.class.getResource("/py/edu/facitec/proyectotaller5/img/salirMenu.png")));
		mnSalir.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		menuBar.add(mnSalir);
	
		
		contentPane = new PanelFondo();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		
		JLabel lblVersin = new JLabel("Versi\u00F3n: 1.3");
		lblVersin.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblVersin = new GridBagConstraints();
		gbc_lblVersin.insets = new Insets(50, 50, 5, 5);
		gbc_lblVersin.anchor = GridBagConstraints.EAST;
		gbc_lblVersin.gridwidth = 3;
		gbc_lblVersin.gridx = 38;
		gbc_lblVersin.gridy = 3;
		gbc_lblVersin.weightx= 4.0;
		contentPane.add(lblVersin, gbc_lblVersin);
		
		JLabel lblDav = new JLabel("David Francisco Ruiz");
		lblDav.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblDav = new GridBagConstraints();
		gbc_lblDav.insets = new Insets(2, 2, 0, 0);
		gbc_lblDav.anchor = GridBagConstraints.EAST;
		gbc_lblDav.gridwidth = 5;
		gbc_lblDav.gridx = 36;
		gbc_lblDav.gridy = 4;
		gbc_lblDav.weightx=4.0;
		contentPane.add(lblDav, gbc_lblDav);
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setOpaque(false);
		toolBar.setBorderPainted(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.gridheight = 2;
		gbc_toolBar.gridwidth = 6;
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 1;
		gbc_toolBar.gridy = 0;
		contentPane.add(toolBar, gbc_toolBar);
		
		
		BotonPersonalizado btnCobranza = new BotonPersonalizado();
		btnCobranza.setEnabled(false);
		btnCobranza.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				}
		});
		btnCobranza.setFocusable(false);
		btnCobranza.setContentAreaFilled(false);
		btnCobranza.setBorderPainted(false);
		btnCobranza.setText("Cobranza");
		btnCobranza.setIcon("cobranza");
		toolBar.add(btnCobranza);
		
		BotonPersonalizado btnReparaciones = new BotonPersonalizado();
		btnReparaciones.setEnabled(false);
		btnReparaciones.setFocusable(false);
		btnReparaciones.setContentAreaFilled(false);
		btnReparaciones.setBorderPainted(false);
		btnReparaciones.setText("Reparación");
		btnReparaciones.setIcon("reparacion");
		toolBar.add(btnReparaciones);
		
		BotonPersonalizado btnSalir = new BotonPersonalizado();
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		btnSalir.setFocusable(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setText("Salir");
		btnSalir.setIcon("salirPantalla");
		toolBar.add(btnSalir);
			
		
	}



	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID()==e.KEY_PRESSED){
			if(e.getKeyCode()==e.VK_F10){
				e.consume();				
			}	
		}
		return false;
	}
	public void ambiente() {
		ambiente = new Ambiente();
		ambienteDao = new AmbienteDao();
		
		ambientes= ambienteDao.recuperarTodo();
		if(ambientes.isEmpty()){
			name="ElectroCenter";
		}else{
			name = ambientes.get(0).getAmb_nombre();
			System.out.println(name);
			
		}
		setTitle(name);		
		}
	
	private void desabilitarMenu(){
		ambienteDao = new AmbienteDao();
		
		ambientes= ambienteDao.recuperarTodo();
		if(!ambientes.isEmpty()){
			mntmConfiguracion.setEnabled(false);
			System.out.println("oii");
		}

	}

}
