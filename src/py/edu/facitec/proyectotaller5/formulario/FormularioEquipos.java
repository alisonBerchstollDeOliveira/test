package py.edu.facitec.proyectotaller5.formulario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import py.edu.facitec.proyectotaller5.boton.BotonAbm;
import py.edu.facitec.proyectotaller5.campos.CampoTestPane;
import py.edu.facitec.proyectotaller5.campos.CampoTextoPersonalizado;
import py.edu.facitec.proyectotaller5.dao.EquiposDao;
import py.edu.facitec.proyectotaller5.modelo.Equipos;
import py.edu.facitec.proyectotaller5.modelotabla.ModeloTablaEquipos;

public class FormularioEquipos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private CampoTextoPersonalizado tfMarca;
	private CampoTextoPersonalizado tfModelo;
	private CampoTextoPersonalizado tfVersion;
	private JTextField tfCodigo;
	private JTable table;
	private ModeloTablaEquipos modeloTablaEquipo;
	private CampoTextoPersonalizado tfBuscar;
	private Equipos equipos;
	
	private List<Equipos> listaEquipos = new ArrayList<>();
	private EquiposDao equiposDao;
	private JLabel lblCodigo;
	
	private BotonAbm btnNuevo;
	private BotonAbm btnModificar;
	private BotonAbm btnEliminar;
	private BotonAbm btnCancelar;
	private BotonAbm btnGuardar;
	private BotonAbm btnSalir;
	private JButton btnBuscar;
	private CampoTestPane tpDescripcion;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			FormularioEquipos dialog = new FormularioEquipos();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Create the dialog.
//	 */
	public FormularioEquipos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormularioEquipos.class.getResource("/py/edu/facitec/proyectotaller5/img/equipos.png")));
		setTitle("Registro Equipos");
		setModal(true);
		setBounds(100, 100, 715, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(this);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setHorizontalAlignment(lblMarca.RIGHT);
		lblMarca.setBounds(22, 69, 76, 14);
		contentPanel.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setHorizontalAlignment(lblModelo.RIGHT);
		lblModelo.setBounds(22, 110, 76, 14);
		contentPanel.add(lblModelo);
		
		JLabel lblVersion = new JLabel("Versi\u00F3n");
		lblVersion.setHorizontalAlignment(lblVersion.RIGHT);
		lblVersion.setBounds(22, 155, 76, 14);
		contentPanel.add(lblVersion);
		
		tfMarca = new CampoTextoPersonalizado();
		tfMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					tfModelo.requestFocus();
				}
			}
		});
		tfMarca.setEditable(false);
		tfMarca.setBounds(108, 66, 197, 20);
		contentPanel.add(tfMarca);
		tfMarca.setColumns(10);
		
		tfModelo = new CampoTextoPersonalizado();
		tfModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					tfVersion.requestFocus();
				}
			}
		});
		tfModelo.setEditable(false);
		tfModelo.setBounds(108, 107, 197, 20);
		contentPanel.add(tfModelo);
		tfModelo.setColumns(10);
		
		tfVersion = new CampoTextoPersonalizado();
		tfVersion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					tpDescripcion.requestFocus();
				}
			}
		});
		tfVersion.setEditable(false);
		tfVersion.setBounds(108, 152, 197, 20);
		contentPanel.add(tfVersion);
		tfVersion.setColumns(10);
		
		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setVisible(false);
		lblCodigo.setHorizontalAlignment(lblCodigo.RIGHT);
		lblCodigo.setBounds(42, 27, 56, 14);
		contentPanel.add(lblCodigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setVisible(false);
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(108, 24, 197, 20);
		contentPanel.add(tfCodigo);
		tfCodigo.setColumns(10);
		

		btnNuevo = new BotonAbm();
		btnNuevo.setText("Nuevo");
		btnNuevo.setIcon("nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarCampos(true);
				limpiarCampos();
				eliminarModficar(false);
				btnGuardar.setText("Guardar");
			}
		});
		btnNuevo.setBounds(315, 18, 90, 75);
		contentPanel.add(btnNuevo);
		
		btnModificar = new BotonAbm();
		btnModificar.setEnabled(false);
		btnModificar.setText("Modificar");
		btnModificar.setIcon("modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, 
						"Esta seguro que desea Modificar el registro del \nEquipo "+equipos.getEq_marca()+" "+equipos.getEq_modelo(),
						"Atención",
						JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_OPTION){

				habilitarCampos(true);
				btnGuardar.setText("Actualizar");}
			}
		});
		btnModificar.setBounds(315, 101, 90, 75);
		contentPanel.add(btnModificar);
		
		btnEliminar = new BotonAbm();
		btnEliminar.setEnabled(false);
		btnEliminar.setText("Eliminar");
		btnEliminar.setIcon("eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, 
						"Esta seguro que desea Eliminar el registro del \nEquipo "+equipos.getEq_marca()+" "+equipos.getEq_modelo(),
						"Atención",
						JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_OPTION){
				eliminar();}
			}
			
		});
		btnEliminar.setBounds(315, 181, 90, 75);
		contentPanel.add(btnEliminar);
		
		btnGuardar = new BotonAbm();
		btnGuardar.setEnabled(false);
		btnGuardar.setText("Guardar");
		btnGuardar.setIcon("guardar");
		btnGuardar.setVisible(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
				consultarEquipos();
				eliminarModficar(false);
				tfMarca.requestFocus();
			}
		});
		btnGuardar.setBounds(17, 261, 90, 75);
		contentPanel.add(btnGuardar);
		
		btnCancelar = new BotonAbm();
		btnCancelar.setEnabled(false);
		btnCancelar.setText("Cancelar");
		btnCancelar.setIcon("cancelar");

		btnCancelar.setVisible(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				eliminarModficar(false);
				habilitarCampos(false);
			}
		});
		btnCancelar.setBounds(117, 261, 90, 75);
		contentPanel.add(btnCancelar);
		
		btnSalir = new BotonAbm();
		btnSalir.setText("Salir");
		btnSalir.setIcon("salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarCierre();
			}
		});
		btnSalir.setBounds(217, 261, 90, 75);
		contentPanel.add(btnSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(418, 39, 281, 241);
		contentPanel.add(scrollPane);
		
		modeloTablaEquipo = new ModeloTablaEquipos();
		table = new JTable(modeloTablaEquipo);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!tfMarca.isEditable()){
				seleccionarEquipo();
				eliminarModficar(true);}
			}
		});
		scrollPane.setViewportView(table);
		
		tfBuscar = new CampoTextoPersonalizado();
		tfBuscar.setEditable(true);
		tfBuscar.setBounds(418, 8, 148, 20);
		contentPanel.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaPorFiltro();
			}
		});
		btnBuscar.setBounds(599, 8, 89, 23);
		contentPanel.add(btnBuscar);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setHorizontalAlignment(lblDescripcin.RIGHT);
		lblDescripcin.setBounds(17, 200, 81, 14);
		contentPanel.add(lblDescripcin);
		
		tpDescripcion = new CampoTestPane();
		tpDescripcion.setBounds(108, 200, 197, 48);
		contentPanel.add(tpDescripcion);
		
		
		
		
		consultarEquipos();
	}
	
	private void habilitarCampos(boolean e) {
		tfMarca.setEditable(e);
		tfModelo.setEditable(e);
		tfVersion.setEditable(e);
		tpDescripcion.setEditable(e);
		
		
		btnGuardar.setEnabled(e);
		btnGuardar.setVisible(e);
		btnCancelar.setEnabled(e);
		btnCancelar.setVisible(e);
		
		
		table.setEnabled(!e);
		tfBuscar.setEditable(!e);
		btnNuevo.setEnabled(!e);
		btnBuscar.setEnabled(!e);
	}
	
	
	
	
	private void cargarDatos(){
		if(tfCodigo.getText().isEmpty()){
			equipos= new Equipos();
			
			equipos.setEq_marca(tfMarca.getText());
			equipos.setEq_modelo(tfModelo.getText());
			equipos.setEq_version(tfVersion.getText());
			equipos.setEq_descripcion(tpDescripcion.getText());
			
		}else{
			
			equipos.setEq_id(Integer.parseInt(tfCodigo.getText()));
			equipos.setEq_marca(tfMarca.getText());
			equipos.setEq_modelo(tfModelo.getText());
			equipos.setEq_version(tfVersion.getText());
			equipos.setEq_descripcion(tpDescripcion.getText());
			
		}
	}
	
	private void guardar(){
		if(btnGuardar.getText()=="Actualizar"){
			cargarDatos();
			btnGuardar.setText("Guardar");
			
			
			equiposDao = new EquiposDao();
			equiposDao.insertarOModificar(equipos);
			try {
				equiposDao.ejecutar();
			} catch (Exception e) {
				equiposDao.cancelar();
			}
			limpiarCampos();
			
		}else{
		
		if(verificarEquipo()==true){
			JOptionPane.showMessageDialog(null, 
					"Este Equipo ya esta registrado ",
					"Atención",
					JOptionPane.YES_NO_OPTION);
			tfVersion.requestFocus();
			tfVersion.selectAll();
		}else {
		
		cargarDatos();
		btnGuardar.setText("Guardar");
		equiposDao = new EquiposDao();
		equiposDao.insertarOModificar(equipos);
		try {
			equiposDao.ejecutar();
		} catch (Exception e) {
			equiposDao.cancelar();
		}
		limpiarCampos();
		}
		}
	}
	
	private void consultarEquipos(){
		equiposDao = new EquiposDao();
		
		listaEquipos=equiposDao.recuperarTodo();
		modeloTablaEquipo.setLista(listaEquipos);
		modeloTablaEquipo.fireTableDataChanged();
	
	}
	
	
	private void eliminar() {
			equiposDao = new EquiposDao();
			equiposDao.eliminar(equipos);
			try {
				equiposDao.ejecutar();
			} catch (Exception e) {
				equiposDao.cancelar();
			}
			limpiarCampos();
			consultarEquipos();
		}
	

	private void seleccionarEquipo() {
		if(table.getSelectedRow()<0){
			return;
		}
		equipos= listaEquipos.get(table.getSelectedRow());
		tfCodigo.setText(equipos.getEq_id()+"");
		tfMarca.setText(equipos.getEq_marca());
		tfModelo.setText(equipos.getEq_modelo());
		tfVersion.setText(equipos.getEq_version());
		tpDescripcion.setText(equipos.getEq_descripcion());
		modeloTablaEquipo.fireTableDataChanged();
	}
	
	
	private void limpiarCampos(){
		tfCodigo.setText("");
		tfMarca.setText("");
		tfModelo.setText("");
		tfVersion.setText("");
		tpDescripcion.setText("");
		
	}
	private void consultaPorFiltro() {
		equiposDao= new EquiposDao();
		listaEquipos = equiposDao.recuperarPorFiltro(tfBuscar.getText());
		modeloTablaEquipo.setLista(listaEquipos);
		modeloTablaEquipo.fireTableDataChanged();
	}
	
//	private void habilitarCodigo(boolean e){
//		tfCodigo.setVisible(e);
//		lblCodigo.setVisible(e);
//	}
	
	private boolean verificarEquipo(){
		boolean a= false;
		for (int i = 0; i < listaEquipos.size(); i++) {
			if ((tfMarca.getText().equals(listaEquipos.get(i).getEq_marca()))) {
				if(tfModelo.getText().equals(listaEquipos.get(i).getEq_modelo())){
					if(tfVersion.getText().equals(listaEquipos.get(i).getEq_version())){
						if(tpDescripcion.getText().equals(listaEquipos.get(i).getEq_descripcion())){
						a = true;}
					}
				}
				
			}	
		}
		return a;		
	}

	private void eliminarModficar(Boolean e){
		btnEliminar.setEnabled(e);
		btnModificar.setEnabled(e);
	}
	private void verificarCierre(){
		if(tfVersion.getText().equals("")&&tfMarca.getText().equals("")&&tfVersion.getText().equals("")){
			dispose();
			}
		else{
			int respuesta = JOptionPane.showConfirmDialog(null, 
					"Esta seguro que desea salir del registro sin guardar ",
					"Atención",
					JOptionPane.YES_NO_OPTION);
			if(respuesta == JOptionPane.YES_OPTION){
				dispose();
			}
			else{
				tfMarca.requestFocus();
			}
			
		}
		}
}

