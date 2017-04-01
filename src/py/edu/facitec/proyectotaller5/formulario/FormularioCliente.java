package py.edu.facitec.proyectotaller5.formulario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import py.edu.facitec.proyectotaller5.boton.BotonAbm;
import py.edu.facitec.proyectotaller5.campos.CampoTestPane;
import py.edu.facitec.proyectotaller5.campos.CampoTextoPersonalizado;
import py.edu.facitec.proyectotaller5.dao.ClientesDao;
import py.edu.facitec.proyectotaller5.modelo.Clientes;
import py.edu.facitec.proyectotaller5.modelotabla.ModeloTablaCliente;

public class FormularioCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private CampoTextoPersonalizado tfNombre;
	private CampoTextoPersonalizado tfDocumento;
	private CampoTextoPersonalizado tfTelefono;
	private CampoTextoPersonalizado tfBuscar;
	private JTable table;
	private ModeloTablaCliente modeloTablaClientes;
	private CampoTestPane TpDireccion;
	private byte bandera =0;
	
	private BotonAbm btnNuevo;
	private BotonAbm btnModificar;
	private BotonAbm btnEliminar;
	private BotonAbm btnGuardar;
	private BotonAbm btnCancelar;
	private BotonAbm btnSalir;
	
	private List<Clientes> listaClientes = new ArrayList<>();
	
	private Clientes clientes;
	
	private ClientesDao clientesDao;
	private JTextField TfCorreo;
	private JButton btnBuscar;
	private CampoTextoPersonalizado tfCodigo;
	private JLabel lblCodigo;
	private JScrollPane scrollPane;
	private JComponent lblRestic;
	private JLabel lblRestric2;
	private JComponent lblRestric1;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			FormularioCliente dialog = new FormularioCliente();
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
	public FormularioCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormularioCliente.class.getResource("/py/edu/facitec/proyectotaller5/img/cliente menu.png")));
		setTitle("Registro Cliente");
		setModal(true);
		setBounds(100, 100, 725, 415);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(this);
		
		
		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setEnabled(false);
		lblCodigo.setVisible(false);
		lblCodigo.setHorizontalAlignment(lblCodigo.RIGHT);
		lblCodigo.setBounds(12, 14, 77, 14);
		contentPanel.add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(lblNombre.RIGHT);
		lblNombre.setBounds(12, 47, 77, 14);
		contentPanel.add(lblNombre);
		
		tfNombre = new CampoTextoPersonalizado();
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					tfDocumento.requestFocus();
				}
			}
		});
		tfNombre.setEditable(false);
		tfNombre.setBounds(99, 44, 201, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setHorizontalAlignment(lblDocumento.RIGHT);
		lblDocumento.setBounds(12, 89, 77, 14);
		contentPanel.add(lblDocumento);
		
		tfDocumento = new CampoTextoPersonalizado();
		tfDocumento.recibeEnteros();
		tfDocumento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					tfTelefono.requestFocus();
				}
			}			
		});
		tfDocumento.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent arg0) {
					restriccionNumero(true);
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(verificarCi()==true){
					tfDocumento.requestFocus();
					tfDocumento.selectAll();
					if(bandera==0){
						JOptionPane.showMessageDialog(null, 
							"Este numero de cedula ya existe",
							"Atención",
						JOptionPane.ERROR_MESSAGE);
						bandera=1;
					}
				}
				restriccionNumero(false);
			}
			
		
		});
		
		tfDocumento.setEditable(false);
		tfDocumento.setBounds(99, 86, 201, 20);
		contentPanel.add(tfDocumento);
		tfDocumento.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setHorizontalAlignment(lblTelefono.RIGHT);
		lblTelefono.setBounds(12, 133, 77, 14);
		contentPanel.add(lblTelefono);
		
		tfTelefono = new CampoTextoPersonalizado();
		tfTelefono.recibeEnteros();
		tfTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					TfCorreo.requestFocus();
				}
			}
		});
		tfTelefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				restriccionNumero(true);
			}
			@Override
			public void focusLost(FocusEvent e) {
				restriccionNumero(false);
			}
		});
		tfTelefono.setEditable(false);
		tfTelefono.setBounds(99, 130, 201, 20);
		contentPanel.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setHorizontalAlignment(lblDireccion.RIGHT);
		lblDireccion.setBounds(12, 215, 77, 14);
		contentPanel.add(lblDireccion);
		
		TpDireccion = new CampoTestPane();
		TpDireccion.setEditable(false);
		TpDireccion.setBounds(99, 214, 201, 55);
		contentPanel.add(TpDireccion);
		
		btnNuevo = new BotonAbm();
		
		btnNuevo.setText("Nuevo");
		btnNuevo.setIcon("nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfNombre.requestFocus();
				habilitarCampos(true);
				limpiarCampos();
				eliminarModficar(false);
				btnGuardar.setText("Guardar");
			}
		});
		btnNuevo.setBounds(345, 46, 90, 75);
		contentPanel.add(btnNuevo);
		
		btnModificar = new BotonAbm();
		btnModificar.setEnabled(false);
		btnModificar.setText("Modificar");
		btnModificar.setIcon("modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int respuesta = JOptionPane.showConfirmDialog(null, 
						"Esta seguro que desea Modificar el registro del \nCliente "+clientes.getCli_nombre(),
						"Atención",
						JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_OPTION){
				habilitarCampos(true);
				btnGuardar.setText("Actualizar");}
			}
		});
		btnModificar.setBounds(345, 128, 89, 86);
		contentPanel.add(btnModificar);
		
		btnEliminar = new BotonAbm();
		btnEliminar.setEnabled(false);
		btnEliminar.setText("Eliminar");
		btnEliminar.setIcon("eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int respuesta = JOptionPane.showConfirmDialog(null, 
						"Esta seguro que desea eliminar el registro del \nCliente "+clientes.getCli_nombre(),
						"Atención",
						JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_OPTION){
					eliminar();
			}}
		});
		btnEliminar.setBounds(345, 229, 90, 75);
		contentPanel.add(btnEliminar);
		
		btnGuardar = new BotonAbm();
		btnGuardar.setText("Guardar");
		btnGuardar.setIcon("guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
				consultarCliente();
				eliminarModficar(false);
			}
		});
		btnGuardar.setVisible(false);
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(12, 290, 90, 75);
		contentPanel.add(btnGuardar);
		
		btnCancelar = new BotonAbm();
		btnCancelar.setText("Cancelar");
		btnCancelar.setIcon("cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarCampos();
				consultarCliente();
				habilitarCampos(false);
				eliminarModficar(false);
			}
		});
		btnCancelar.setVisible(false);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(111, 290, 90, 75);
		contentPanel.add(btnCancelar);
		
		btnSalir = new BotonAbm();
		btnSalir.setText("Salir");
		btnSalir.setIcon("salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarCierre();
			}
		});
		btnSalir.setBounds(210, 290, 90, 75);
		contentPanel.add(btnSalir);
		
		tfBuscar = new CampoTextoPersonalizado();
		tfBuscar.setEditable(true);
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				consultaPorFiltro();
			}
		});
		tfBuscar.setBounds(445, 27, 145, 20);
		contentPanel.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaPorFiltro();
			}
		});
		btnBuscar.setBounds(614, 26, 82, 23);
		contentPanel.add(btnBuscar);
		
		scrollPane = new JScrollPane();
		
		scrollPane.setBounds(445, 62, 251, 242);
		contentPanel.add(scrollPane);
		
		modeloTablaClientes = new ModeloTablaCliente();
		table = new JTable(modeloTablaClientes);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tfNombre.isEditable()){
				seleccionarCLiente();
				eliminarModficar(true);}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(lblCorreo.RIGHT);
		lblCorreo.setBounds(12, 175, 77, 14);
		contentPanel.add(lblCorreo);
		
		TfCorreo = new JTextField();
		TfCorreo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					TpDireccion.requestFocus();
				}
			}
		});
		TfCorreo.setEditable(false);
		TfCorreo.setAutoscrolls(false);
		TfCorreo.setBounds(99, 172, 201, 20);
		contentPanel.add(TfCorreo);
		TfCorreo.setColumns(10);

		
		tfCodigo = new CampoTextoPersonalizado();
		tfCodigo.setEnabled(false);
		tfCodigo.setVisible(false);
		tfCodigo.setBounds(99, 11, 86, 20);
		contentPanel.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		lblRestic = new JLabel("*");
		lblRestic.setForeground(Color.RED);
		lblRestic.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRestic.setVisible(false);
		lblRestic.setBounds(310, 89, 19, 14);
		contentPanel.add(lblRestic);
		
		lblRestric2 = new JLabel("*");
		lblRestric2.setForeground(Color.RED);
		lblRestric2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRestric2.setVisible(false);
		lblRestric2.setBounds(310, 133, 19, 14);
		contentPanel.add(lblRestric2);
		
		lblRestric1 = new JLabel("* Campos que s\u00F3lo aceptan n\u00FAmeros");
		lblRestric1.setForeground(Color.RED);
		lblRestric1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRestric1.setVisible(false);
		lblRestric1.setBounds(176, 14, 251, 14);
		contentPanel.add(lblRestric1);
		
		consultarCliente();
		
	}
	
	private void habilitarCampos(boolean e) {
		tfNombre.setEditable(e);
		tfDocumento.setEditable(e);
		tfTelefono.setEditable(e);
		TpDireccion.setEditable(e);
		TfCorreo.setEditable(e);
		
		
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
			clientes= new Clientes();
			
			clientes.setCli_ci(Integer.parseInt(tfDocumento.getText()));
			clientes.setCli_direccion(TpDireccion.getText());
			clientes.setCli_email(TfCorreo.getText());
			clientes.setCli_nombre(tfNombre.getText());
			clientes.setCli_telefono(Integer.parseInt(tfTelefono.getText()));
			
		}
		else{
			clientes.setCli_id(Integer.parseInt(tfCodigo.getText()));
			clientes.setCli_ci(Integer.parseInt(tfDocumento.getText()));
			clientes.setCli_direccion(TpDireccion.getText());
			clientes.setCli_email(TfCorreo.getText());
			clientes.setCli_nombre(tfNombre.getText());
			clientes.setCli_telefono(Integer.parseInt(tfTelefono.getText()));
			
		}
		}
	
	
	private void guardar(){
	
		if(btnGuardar.getText()=="Actualizar"){
			cargarDatos();
			btnGuardar.setText("Guardar");
			
			
			clientesDao = new ClientesDao();
			clientesDao.insertarOModificar(clientes);
			try {
				clientesDao.ejecutar();
			} catch (Exception e) {
				clientesDao.cancelar();
			}
			limpiarCampos();
			
		}else{
		
		if(verificarCi()==true){
			tfDocumento.requestFocus();
			tfDocumento.selectAll();
		}else {
		
		cargarDatos();
		btnGuardar.setText("Guardar");
		clientesDao = new ClientesDao();
		clientesDao.insertarOModificar(clientes);
		try {
			clientesDao.ejecutar();
		} catch (Exception e) {
			clientesDao.cancelar();
		}
		limpiarCampos();
		}
		}
		tfNombre.requestFocus();
		}		
	
	
	private void consultarCliente(){
		clientesDao = new ClientesDao();
		
		listaClientes=clientesDao.recuperarTodo();
		modeloTablaClientes.setLista(listaClientes);
		modeloTablaClientes.fireTableDataChanged();
	}
	
	
	private void eliminar() {
			clientesDao = new ClientesDao();
			clientesDao.eliminar(clientes);
			try {
				clientesDao.ejecutar();
			} catch (Exception e) {
				clientesDao.cancelar();
			}
			limpiarCampos();
			consultarCliente();
		}

	private void seleccionarCLiente() {
		if(table.getSelectedRow()<0){
			return;
		}
		clientes= listaClientes.get(table.getSelectedRow());
		tfCodigo.setText(clientes.getCli_id()+"");
		tfDocumento.setText(clientes.getCli_ci()+"");
		tfNombre.setText(clientes.getCli_nombre());
		tfTelefono.setText(clientes.getCli_telefono()+"");
		TfCorreo.setText(clientes.getCli_email());
		TpDireccion.setText(clientes.getCli_direccion());
		modeloTablaClientes.fireTableDataChanged();
	}
	
	
	private void limpiarCampos(){
		tfCodigo.setText("");
		tfDocumento.setText("");
		tfNombre.setText("");
		tfTelefono.setText("");
		TfCorreo.setText("");
		TpDireccion.setText("");
	}
	private void consultaPorFiltro() {
		clientesDao= new ClientesDao();
		listaClientes = clientesDao.recuperarPorFiltro(tfBuscar.getText());
		modeloTablaClientes.setLista(listaClientes);
		modeloTablaClientes.fireTableDataChanged();
	}
	
//	private void habilitarCodigo(boolean e){
//		tfCodigo.setVisible(e);
//		lblCodigo.setVisible(e);
//	}
//	
	private boolean verificarCi(){
		boolean a= false;
		
		if(tfDocumento.getText().isEmpty()){
			a=false;
		}else{		
		for (int i = 0; i < listaClientes.size(); i++) {
			if (Integer.parseInt(tfDocumento.getText())==listaClientes.get(i).getCli_ci()) {
				a = true;
			}	
		}
		}
		return a;		
	}
	private void restriccionNumero(Boolean e){
		lblRestic.setVisible(e);
		lblRestric1.setVisible(e);
		lblRestric2.setVisible(e);
	}
	private void eliminarModficar(Boolean e){
		btnEliminar.setEnabled(e);
		btnModificar.setEnabled(e);
	}
	private void verificarCierre(){
		if(tfDocumento.getText().equals("")&&tfNombre.getText().equals("")&&tfTelefono.getText().equals("")&&TfCorreo.getText().equals("")&&TpDireccion.getText().equals("")&&btnGuardar.getText().equals("Guardar")){
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
				tfNombre.requestFocus();
			}
			
		}
		}
	
	
}
