package py.edu.facitec.proyectotaller5.formulario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import py.edu.facitec.proyectotaller5.boton.BotonAbm;
import py.edu.facitec.proyectotaller5.buscadores.BuscadorClientes;
import py.edu.facitec.proyectotaller5.buscadores.BuscadorEquipo;
import py.edu.facitec.proyectotaller5.campos.CampoTestPane;
import py.edu.facitec.proyectotaller5.campos.CampoTextoPersonalizado;
import py.edu.facitec.proyectotaller5.dao.ClientesDao;
import py.edu.facitec.proyectotaller5.dao.DeudaDao;
import py.edu.facitec.proyectotaller5.dao.EquiposDao;
import py.edu.facitec.proyectotaller5.dao.ReparacionesDao;
import py.edu.facitec.proyectotaller5.interfaz.InterfazBuscadorClientes;
import py.edu.facitec.proyectotaller5.interfaz.InterfazBuscadorEquipos;
import py.edu.facitec.proyectotaller5.interfaz.InterfazUsuario;
import py.edu.facitec.proyectotaller5.modelo.Clientes;
import py.edu.facitec.proyectotaller5.modelo.Deuda;
import py.edu.facitec.proyectotaller5.modelo.Equipos;
import py.edu.facitec.proyectotaller5.modelo.Reparacion;
import py.edu.facitec.proyectotaller5.modelo.Tecnico;
import py.edu.facitec.proyectotaller5.modelotabla.ModeloTablaReparacion;
import py.edu.facitec.proyectotaller5.util.FechaUtil;
import py.edu.facitec.proyectotaller5.util.NumberUtil;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;
import javax.swing.Box;

public class FormularioReparaciones extends JDialog implements InterfazBuscadorEquipos,
InterfazBuscadorClientes, InterfazUsuario{
	
	private JTextField tfCodigo;
	private JTextField tfCliente;
	private JTextField tfEquipo;
	private JFormattedTextField tfFecha;
	private BotonAbm btnbmGuardar;
	private BotonAbm btnbmNuevo;
	private JTable table;
	private ModeloTablaReparacion modeloTablaReparacion;
	private BotonAbm btnBuscarCliente;
	private BotonAbm btnBuscarEquipo;
	private FormularioUsuario formularioUsuario;
	
	private Tecnico tecnico;
	private Clientes cliente;
	private List<Clientes> clientes= new ArrayList<>();
	private Equipos equipos;
	private List<Equipos> listaEquipos= new ArrayList<>();
	
	private List<Reparacion>reparaciones;

	
	private Reparacion reparacion;
	private ReparacionesDao reparacionesDao;
	private JLabel lblEquipoSe;
	private JLabel lblRegistroDeEquipo;
	private JLabel lblClienteSe;
	private JLabel lblNDelCliente;
	private JLabel lblNDelEquipo;
	private BotonAbm btnbmTcnico;
	private CampoTextoPersonalizado tfPresupuesto;
	private CampoTextoPersonalizado tfDeuda;
	public CampoTestPane tpObservacion;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnS;
	private JPanel panel;
	private BotonAbm btnAtualizar;
	private JTextField tfBuscar;
	private ClientesDao clientesDao;
	private Deuda deuda;
	private DeudaDao deudaDao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioReparaciones dialog = new FormularioReparaciones();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioReparaciones() {
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().setBackground(new Color(245, 245, 245));
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		setLocationRelativeTo(this);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCodigo.setBounds(568, 61, 83, 14);
		lblCodigo.setHorizontalAlignment(lblCodigo.RIGHT);
		getContentPane().add(lblCodigo);
		
		lblNDelCliente = new JLabel("N\u00B0 del cliente:");
		lblNDelCliente.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNDelCliente.setHorizontalAlignment(lblNDelCliente.RIGHT);
		lblNDelCliente.setBounds(568, 106, 83, 14);
		getContentPane().add(lblNDelCliente);
		
		lblNDelEquipo = new JLabel("N\u00B0 del equipo:");
		lblNDelEquipo.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNDelEquipo.setHorizontalAlignment(lblNDelEquipo.RIGHT);
		lblNDelEquipo.setBounds(568, 148, 83, 14);
		getContentPane().add(lblNDelEquipo);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(661, 59, 86, 20);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfCliente = new JTextField();
		tfCliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(verificarCodigo()==false){
					lblNDelCliente.setVisible(true);
					lblClienteSe.setVisible(true);
					tfCliente.requestFocus();
					tfCliente.selectAll();
				}
				verificarCodigo();
			}
		});
		tfCliente.setEditable(false);
		tfCliente.setBounds(661, 104, 86, 20);
		getContentPane().add(tfCliente);
		tfCliente.setColumns(10);
		
		tfEquipo = new JTextField();
		tfEquipo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificarCodigo1()==true){
					lblNDelEquipo.setVisible(true);
					lblEquipoSe.setVisible(true);
				}
			}
		});
		tfEquipo.setEditable(false);
		tfEquipo.setBounds(661, 146, 86, 20);
		getContentPane().add(tfEquipo);
		tfEquipo.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(lblFecha.RIGHT);
		lblFecha.setBounds(605, 194, 46, 14);
		getContentPane().add(lblFecha);
		
		tfFecha = new JFormattedTextField(FechaUtil.getFormato());
		tfFecha.setEnabled(false);
		tfFecha.setBounds(661, 194, 86, 20);
		getContentPane().add(tfFecha);
		
		btnbmGuardar = new BotonAbm();
		btnbmGuardar.setEnabled(false);
		btnbmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnbmGuardar.setText("Guardar");
		btnbmGuardar.setIcon("guardarChico");
		btnbmGuardar.setHorizontalAlignment(btnbmGuardar.LEFT);
		btnbmGuardar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnbmGuardar.setVerticalTextPosition(SwingConstants.CENTER);
		btnbmGuardar.setBounds(661, 244, 113, 26);
		getContentPane().add(btnbmGuardar);
		
		btnbmNuevo = new BotonAbm();
		btnbmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfFecha.setValue(FechaUtil.fechaAString(new Date()));
				habilitarCampos(false);
			}
		});
		btnbmNuevo.setText("Nuevo");
		btnbmNuevo.setIcon("nuevoChico");
		btnbmNuevo.setHorizontalAlignment(btnbmNuevo.LEFT);
		btnbmNuevo.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnbmNuevo.setVerticalTextPosition(SwingConstants.CENTER);
		btnbmNuevo.setBounds(534, 244, 100, 26);
		getContentPane().add(btnbmNuevo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 504, 266);
		getContentPane().add(scrollPane);
		
		modeloTablaReparacion = new ModeloTablaReparacion();
		table = new JTable(modeloTablaReparacion);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				seleccionarReparacion();
			}
		});
		scrollPane.setViewportView(table);
		
		btnBuscarCliente = new BotonAbm();
		btnBuscarCliente.setEnabled(false);
		btnBuscarCliente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				abrirBuscadorCliente();
				
			}
		});
		btnBuscarCliente.setIcon("search");
		btnBuscarCliente.setBounds(750, 101, 24, 24);
		getContentPane().add(btnBuscarCliente);
		
		btnBuscarEquipo = new BotonAbm();
		btnBuscarEquipo.setEnabled(false);
		btnBuscarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirBuscadorEquipo();
			}
		});
		btnBuscarEquipo.setIcon("search");
		btnBuscarEquipo.setBounds(749, 143, 24, 24);
		getContentPane().add(btnBuscarEquipo);
		
		JLabel lblRegistroDeCliente = new JLabel("Registro de cliente no existente");
		lblRegistroDeCliente.setVisible(false);
		lblRegistroDeCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegistroDeCliente.setForeground(Color.RED);
		lblRegistroDeCliente.setBounds(549, 11, 225, 26);
		getContentPane().add(lblRegistroDeCliente);
		
		lblClienteSe = new JLabel("*");
		lblClienteSe.setVisible(false);
		
		lblEquipoSe = new JLabel("*");
		lblEquipoSe.setVisible(false);
		lblEquipoSe.setForeground(Color.RED);
		lblEquipoSe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEquipoSe.setBounds(549, 149, 24, 14);
		getContentPane().add(lblEquipoSe);
		lblClienteSe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClienteSe.setForeground(Color.RED);
		lblClienteSe.setBounds(549, 107, 21, 14);
		getContentPane().add(lblClienteSe);
		
		lblRegistroDeEquipo = new JLabel("Registro de equipo no existente");
		lblRegistroDeEquipo.setVisible(false);
		lblRegistroDeEquipo.setForeground(Color.RED);
		lblRegistroDeEquipo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegistroDeEquipo.setBounds(552, 11, 222, 26);
		getContentPane().add(lblRegistroDeEquipo);
		
		btnbmTcnico = new BotonAbm();
		btnbmTcnico.setEnabled(false);
		btnbmTcnico.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				abriFormularioUsuario();
				
			}

		});
		btnbmTcnico.setIcon("tecnico");
		btnbmTcnico.setText("T\u00E9cnico");
		btnbmTcnico.setBounds(134, 372, 90, 55);
		getContentPane().add(btnbmTcnico);
		
		panel = new JPanel();
		panel.setBounds(409, 299, 365, 201);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		rdbtnS = new JRadioButton("S\u00ED");
		rdbtnS.setEnabled(false);
		rdbtnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNo.setSelected(false);
				arregloClick();
			}
		});
		rdbtnS.setBounds(142, 7, 48, 23);
		panel.add(rdbtnS);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setEnabled(false);
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arregloClick();
			}
		});
		rdbtnNo.setBounds(227, 7, 62, 23);
		panel.add(rdbtnNo);
		
		JLabel lblObservacin = new JLabel("Observaci\u00F3n:");
		lblObservacin.setHorizontalAlignment(lblObservacin.RIGHT);
		lblObservacin.setBounds(24, 36, 82, 14);
		panel.add(lblObservacin);
		
		JLabel lblPresupuesto = new JLabel("Presupuesto:");
		lblPresupuesto.setHorizontalAlignment(lblPresupuesto.RIGHT);
		lblPresupuesto.setBounds(24, 101, 82, 14);
		panel.add(lblPresupuesto);
		
		JLabel lblDeuda = new JLabel("Deuda:");
		lblDeuda.setHorizontalAlignment(lblDeuda.RIGHT);
		lblDeuda.setBounds(24, 132, 82, 14);
		panel.add(lblDeuda);
		
		tpObservacion = new CampoTestPane();
		tpObservacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					tfPresupuesto.requestFocus();
				}
			}
		});
		tpObservacion.setBounds(129, 37, 220, 50);
		panel.add(tpObservacion);
		
		tfPresupuesto = new CampoTextoPersonalizado();
		tfPresupuesto.recibeDecimales();
		tfPresupuesto.setBounds(129, 97, 220, 20);
		panel.add(tfPresupuesto);
		
		tfDeuda = new CampoTextoPersonalizado();
		tfDeuda.setBounds(129, 128, 220, 20);
		panel.add(tfDeuda);
		
		btnAtualizar = new BotonAbm();
		btnAtualizar.setText("Actualizar");
		btnAtualizar.setIcon("update");
		btnAtualizar.setHorizontalTextPosition(btnAtualizar.RIGHT);
		btnAtualizar.setFont(new Font("Dialog", Font.BOLD, 13));
		btnAtualizar.setBounds(203, 159, 146, 31);
		panel.add(btnAtualizar);
		btnAtualizar.setEnabled(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 299, 204, 49);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		tfBuscar = new JTextField();
		tfBuscar.setBounds(10, 18, 131, 20);
		panel_1.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		JLabel lblBusqueda = new JLabel("Filtro de Busqueda por Nombre");
		lblBusqueda.setBounds(10, 0, 190, 14);
		panel_1.add(lblBusqueda);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}
		});
		

		consultarReparaciones();
		
	}
	
	private void habilitarCampos(boolean b){
		tfFecha.setEditable(!b);
		btnbmGuardar.setEnabled(!b);
		btnBuscarCliente.setEnabled(!b);
		btnBuscarEquipo.setEnabled(!b);
		
		btnbmNuevo.setEnabled(b);
		
	}
	
	private void abrirBuscadorCliente() {
		BuscadorClientes buscadorClientes = new BuscadorClientes();
		buscadorClientes.setInterfaz(this);
		buscadorClientes.setVisible(true);

	}
	
	
	private void abrirBuscadorEquipo(){
		BuscadorEquipo buscadorEquipo = new BuscadorEquipo();
		buscadorEquipo.setInterfaz(this);
		buscadorEquipo.setVisible(true);
		
	}

	@Override
	public void setClientes(Clientes cliente) {
		tfCliente.setText(cliente.getCli_id()+"");
		this.cliente= cliente;
		
	}

	@Override
	public void setEquipos(Equipos equipos) {
		tfEquipo.setText(equipos.getEq_id()+"");
		this.equipos=equipos;
		
	}

	@Override
	public void setTecnico(Tecnico tecnico) {
		this.tecnico=tecnico;		
	}

	@Override
	public void setHabilitar() {
		habilitarPanel2(true);		
	}

	
	private void cargarDatos() {
		if(tfCodigo.getText().isEmpty()){
			reparacion = new Reparacion();			
		}
		reparacion.setRep_fecha(FechaUtil.stringAFecha(tfFecha.getText()));
		reparacion.setClientes(cliente);
		reparacion.setEquipos(equipos);
	}
	
	private void cargarDatosTecnico() {	
	
		
		reparacion.setRep_id(Integer.parseInt(tfCodigo.getText()));
		reparacion.setRep_obsTecnico(tpObservacion.getText());
		reparacion.setRep_presupuesto(NumberUtil.getValorDouble(tfPresupuesto.getText()));
		reparacion.setRep_arreglo(arregloResultado());
		reparacion.setRep_monto(reparacion.getRep_presupuesto());
		reparacion.setTecnico(tecnico);

	}
	private void guardar() {
						
		cargarDatos();
		//luego de cargar los datos confirmamos si la fecha se cargo correctamente
		if(reparacion.getRep_fecha()==null){
			return;
		}
		reparacionesDao = new ReparacionesDao();
		reparacionesDao.insertarOModificar(reparacion);
		try {
			reparacionesDao.ejecutar();
		} catch (Exception e) {
			reparacionesDao.cancelar();
		}
		habilitarCampos(true);
		limpiarCampos();
		consultarReparaciones();
		}
	private void consultarReparaciones() {
		reparacionesDao = new ReparacionesDao();
		reparaciones= reparacionesDao.recuperarPorPagadp();
		modeloTablaReparacion.setLista(reparaciones);
		modeloTablaReparacion.fireTableDataChanged();
	}
	private void limpiarCampos(){
		tfCliente.setText("");
		tfCodigo.setText("");
		tfEquipo.setText("");
		tfFecha.setText("");
	}
	private boolean verificarCodigo(){
		boolean a= false;			
		for (int i = 0; i < clientes.size(); i++) {
			if (Integer.parseInt(tfCliente.getText())==clientes.get(i).getCli_id()) {
				a = true;	
		}
		}
		return a;		
	}
	private boolean verificarCodigo1(){
		boolean a= false;
		
		for (int i = 0; i < listaEquipos.size(); i++) {
			if (Integer.parseInt(tfEquipo.getText())==listaEquipos.get(i).getEq_id()) {
				a = true;
			}	
		}
		return a;		
	}
	private boolean arregloResultado(){
		boolean resul=false;
			if(rdbtnS.isSelected()==true){
				resul=true;
				crearDeuda();
			}if(rdbtnNo.isSelected()==true){
				resul=false;
			}
		return resul;
	}
	private void arregloClick(){
		if(rdbtnNo.isSelected()){
			rdbtnS.setSelected(false);
		}if(rdbtnS.isSelected()){
			rdbtnNo.setSelected(false);
		}
	}
	
	private void seleccionarReparacion() {
		if(table.getSelectedRow()<0){
			return;
		}
		reparacion= reparaciones.get(table.getSelectedRow());
		tfCodigo.setText(reparacion.getRep_id()+"");
		tfCliente.setText(reparacion.getClientes().getCli_id()+"");
		tfEquipo.setText(reparacion.getEquipos().getEq_id()+"");
		tfFecha.setText(FechaUtil.fechaAString(reparacion.getRep_fecha()));
		
		btnbmGuardar.setEnabled(false);
		btnbmNuevo.setEnabled(false);
		btnbmTcnico.setEnabled(true);
	}
	private void actualizar(){
		if(!tfCodigo.getText().isEmpty()){
			cargarDatosTecnico();
			if(reparacion.getRep_fecha()==null){
				return;
			}
			reparacionesDao = new ReparacionesDao();
			reparacionesDao.insertarOModificar(reparacion);
			try {
				reparacionesDao.ejecutar();
			} catch (Exception e) {
				reparacionesDao.cancelar();
			}
			habilitarCampos(true);
			limpiarCampos();
			consultarReparaciones();
			habilitarPanel2(false);
			btnbmTcnico.setEnabled(false);
		}
	}
	
	private void habilitarPanel2(Boolean b){
		tpObservacion.setEditable(b);
		tfPresupuesto.setEditable(b);
		rdbtnNo.setEnabled(b);
		rdbtnS.setEnabled(b);
		btnAtualizar.setEnabled(b);
		
	}

	private void abriFormularioUsuario() {
		FormularioUsuario formularioUsuario = new FormularioUsuario();
		formularioUsuario.setInterfaz(this);
		formularioUsuario.setVisible(true);
		
	}
	private void consultaPorFiltro() {
		clientesDao= new ClientesDao();
		clientes = clientesDao.recuperarPorFiltro(tfBuscar.getText());
		modeloTablaReparacion.setLista(reparaciones);
		modeloTablaReparacion.fireTableDataChanged();
		
		reparacionesDao = new ReparacionesDao();
		reparaciones= reparacionesDao.recuperarTodo();
		modeloTablaReparacion.setLista(reparaciones);
		modeloTablaReparacion.fireTableDataChanged();
	}
	
	private void crearDeuda(){
		deudaDao = new DeudaDao();
		deuda = new Deuda();
		deuda.setDeu_fecha(new Date());
		deuda.setReparacion(reparacion);
		
		
		deudaDao.insertarOModificar(deuda);
		try {
			deudaDao.ejecutar();
			
		} catch (Exception e) {
			deudaDao.cancelar();
		}
		
	}

	
}
