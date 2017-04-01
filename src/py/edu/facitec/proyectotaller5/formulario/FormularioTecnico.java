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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import py.edu.facitec.proyectotaller5.boton.BotonAbm;
import py.edu.facitec.proyectotaller5.campos.CampoTestPane;
import py.edu.facitec.proyectotaller5.campos.CampoTextoPersonalizado;
import py.edu.facitec.proyectotaller5.dao.TecnicosDao;
import py.edu.facitec.proyectotaller5.modelo.Tecnico;
import py.edu.facitec.proyectotaller5.modelotabla.ModeloTablaTecnico;

public class FormularioTecnico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private CampoTextoPersonalizado tfNombre;
	private CampoTextoPersonalizado tfTelefono;
	private CampoTextoPersonalizado tfCodigo;
	private JTable table;
	private CampoTextoPersonalizado tfBuscar;
	
	private List<Tecnico> listaTecnicos = new ArrayList<>();
	private JLabel lblCodigo;
	
	private BotonAbm btnNuevo;
	private BotonAbm btnModificar;
	private BotonAbm btnEliminar;
	private BotonAbm btnCancelar;
	private BotonAbm btnGuardar;
	private BotonAbm btnSalir;
	private JButton btnBuscar;
	private ModeloTablaTecnico modeloTablaTecnico;
	private CampoTestPane tpDireccion;
	private Tecnico tecnico;
	private TecnicosDao tecnicosDao;
	private JComponent lblRestic;
	private JComponent lblRestric1;
	private JTextField tfUsuario;
	private JPasswordField pfSena;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			FormularioTecnico dialog = new FormularioTecnico();
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
	public FormularioTecnico() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormularioTecnico.class.getResource("/py/edu/facitec/proyectotaller5/img/tecnico.png")));
		setModal(true);
		setTitle("Registro Técnicos");
		setBounds(100, 100, 730, 440);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(this);
		setModal(true);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(lblNombre.RIGHT);
		lblNombre.setBounds(22, 69, 65, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setHorizontalAlignment(lblTelefono.RIGHT);
		lblTelefono.setBounds(22, 110, 65, 14);
		contentPanel.add(lblTelefono);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setHorizontalAlignment(lblDireccion.RIGHT);
		lblDireccion.setBounds(22, 155, 65, 14);
		contentPanel.add(lblDireccion);
		
		tfNombre = new CampoTextoPersonalizado();
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					tfTelefono.requestFocus();
				}
			}
		});
		tfNombre.setEditable(false);
		tfNombre.setBounds(97, 66, 185, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfTelefono = new CampoTextoPersonalizado();
		tfTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					tpDireccion.requestFocus();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumero(e);
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
		tfTelefono.setBounds(97, 107, 185, 20);
		contentPanel.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setVisible(false);
		lblCodigo.setHorizontalAlignment(lblCodigo.RIGHT);
		lblCodigo.setBounds(42, 27, 46, 14);
		contentPanel.add(lblCodigo);
		
		tfCodigo = new CampoTextoPersonalizado();
		tfCodigo.setVisible(false);
		tfCodigo.setEnabled(false);
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(97, 24, 86, 20);
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
		btnNuevo.setBounds(339, 23, 90, 75);
		contentPanel.add(btnNuevo);
		
		btnModificar = new BotonAbm();
		btnModificar.setEnabled(false);
		btnModificar.setText("Modificar");
		btnModificar.setIcon("modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, 
						"Esta seguro que desea Modificar el registro del \nTécnico "+tecnico.getTec_nombre(),
						"Atención",
						JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_OPTION){
				habilitarCampos(true);}
				btnGuardar.setText("Actualizar");
			}
		});
		btnModificar.setBounds(339, 104, 90, 75);
		contentPanel.add(btnModificar);
		
		btnEliminar = new BotonAbm();
		btnEliminar.setEnabled(false);
		btnEliminar.setText("Eliminar");
		btnEliminar.setIcon("eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, 
						"Esta seguro que desea Eliminar el registro del \nTécnico "+tecnico.getTec_nombre(),
						"Atención",
						JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_OPTION){
				eliminar();}
			}
			
		});
		btnEliminar.setBounds(339, 186, 90, 75);
		contentPanel.add(btnEliminar);
		
		btnGuardar = new BotonAbm();
		btnGuardar.setEnabled(false);
		btnGuardar.setText("Guardar");
		btnGuardar.setIcon("guardar");
		btnGuardar.setVisible(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
				eliminarModficar(false);
				consultarTecnicos();
				tfNombre.requestFocus();
			}
		});
		btnGuardar.setBounds(17, 312, 90, 75);
		contentPanel.add(btnGuardar);
		
		btnCancelar = new BotonAbm();
		btnCancelar.setEnabled(false);
		btnCancelar.setText("Cancelar");
		btnCancelar.setIcon("cancelar");
		btnCancelar.setVisible(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				consultarTecnicos();
				habilitarCampos(false);
				eliminarModficar(false);
			}
		});
		btnCancelar.setBounds(117, 312, 90, 75);
		contentPanel.add(btnCancelar);
		
		btnSalir = new BotonAbm();
		btnSalir.setText("Salir");
		btnSalir.setIcon("salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarCierre();
			}
		});
		btnSalir.setBounds(217, 312, 90, 75);
		contentPanel.add(btnSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(438, 34, 266, 241);
		contentPanel.add(scrollPane);
		
		modeloTablaTecnico = new ModeloTablaTecnico();
		table = new JTable(modeloTablaTecnico);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!tfNombre.isEditable()){
				seleccionarTecnicos();
				eliminarModficar(true);}
			}
		});
		scrollPane.setViewportView(table);
		
		tfBuscar = new CampoTextoPersonalizado();
		tfBuscar.setEditable(true);
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				consultaPorFiltro();
			}
		});
		tfBuscar.setBounds(438, 8, 127, 20);
		contentPanel.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaPorFiltro();
			}
		});
		btnBuscar.setBounds(593, 7, 89, 23);
		contentPanel.add(btnBuscar);
		
		tpDireccion = new CampoTestPane();
		tpDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					tfUsuario.requestFocus();
				}
			}
		});
		tpDireccion.setEditable(false);
		tpDireccion.setBounds(97, 155, 185, 46);
		contentPanel.add(tpDireccion);
		
		
		lblRestic = new JLabel("*");
		lblRestic.setVisible(false);
		lblRestic.setForeground(Color.RED);
		lblRestic.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRestic.setBounds(292, 109, 19, 14);
		contentPanel.add(lblRestic);
	
		
		lblRestric1 = new JLabel("* Campos que s\u00F3lo aceptan n\u00FAmeros");
		lblRestric1.setVisible(false);
		lblRestric1.setForeground(Color.RED);
		lblRestric1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblRestric1.setBounds(63, 26, 266, 14);
		contentPanel.add(lblRestric1);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(31, 225, 56, 14);
		lblUsuario.setHorizontalAlignment(lblUsuario.RIGHT);
		contentPanel.add(lblUsuario);
		
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(17, 266, 70, 14);
		lblContrasea.setHorizontalAlignment(lblContrasea.RIGHT);
		contentPanel.add(lblContrasea);
		
		tfUsuario = new JTextField();
		tfUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					pfSena.requestFocus();
				}
			}
		});
		tfUsuario.setEditable(false);
		tfUsuario.setBounds(97, 222, 185, 20);
		contentPanel.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSena = new JPasswordField();
		pfSena.setEditable(false);
		pfSena.setBounds(97, 263, 185, 20);
		contentPanel.add(pfSena);
		
		
		consultarTecnicos();
	}
	
	private void habilitarCampos(boolean e) {
		tfNombre.setEditable(e);
		tfTelefono.setEditable(e);
		tpDireccion.setEditable(e);
		tfUsuario.setEditable(e);
		pfSena.setEditable(e);
		
		
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
			tecnico= new Tecnico();
			
			tecnico.setTec_nombre(tfNombre.getText());
			tecnico.setTec_telefono(Integer.parseInt((tfTelefono.getText())));
			tecnico.setTec_direcc(tpDireccion.getText());
			tecnico.setTec_usuario(tfUsuario.getText());
			
			char clave[]=pfSena.getPassword();
			String clavedef=new String(clave);
			tecnico.setTec_password(clavedef);
			
		}else{
			
			tecnico.setTec_id(Integer.parseInt(tfCodigo.getText()));
			tecnico.setTec_nombre(tfNombre.getText());
			tecnico.setTec_telefono(Integer.parseInt((tfTelefono.getText())));
			tecnico.setTec_direcc(tpDireccion.getText());
			tecnico.setTec_usuario(tfUsuario.getText());
			
			char clave[]=pfSena.getPassword();
			String clavedef=new String(clave);
			tecnico.setTec_password(clavedef);;
			
		}
	}
	
	private void guardar(){
		if(btnGuardar.getText()=="Actualizar"){
			cargarDatos();
			btnGuardar.setText("Guardar");
			
			
			tecnicosDao = new TecnicosDao();
			tecnicosDao.insertarOModificar(tecnico);
			try {
				tecnicosDao.ejecutar();
			} catch (Exception e) {
				tecnicosDao.cancelar();
			}
			limpiarCampos();
			
		}else{
		
		if(verificarTecnico()==true){
			tfNombre.requestFocus();
			tfNombre.selectAll();
		}else {
		
		cargarDatos();
		btnGuardar.setText("Guardar");
		tecnicosDao = new TecnicosDao();
		tecnicosDao.insertarOModificar(tecnico);
		try {
			tecnicosDao.ejecutar();
		} catch (Exception e) {
			tecnicosDao.cancelar();
		}
		limpiarCampos();
		}
		}
	}
	
	private void consultarTecnicos(){
		tecnicosDao = new TecnicosDao();
		
		listaTecnicos=tecnicosDao.recuperarTodo();
		modeloTablaTecnico.setLista(listaTecnicos);
		modeloTablaTecnico.fireTableDataChanged();
	}
	
	
	private void eliminar() {

			tecnicosDao = new TecnicosDao();
			tecnicosDao.eliminar(tecnico);
			try {
				tecnicosDao.ejecutar();
			} catch (Exception e) {
				tecnicosDao.cancelar();
			}
			limpiarCampos();
			consultarTecnicos();
		}
	private void seleccionarTecnicos() {
		if(table.getSelectedRow()<0){
			return;
		}
		tecnico= listaTecnicos.get(table.getSelectedRow());
		tfCodigo.setText(tecnico.getTec_id()+"");
		tfNombre.setText(tecnico.getTec_nombre());
		tfTelefono.setText(tecnico.getTec_telefono()+"");
		tpDireccion.setText(tecnico.getTec_direcc());
		tfUsuario.setText(tecnico.getTec_usuario());
		pfSena.setText(tecnico.getTec_password());
		modeloTablaTecnico.fireTableDataChanged();
	}
	
	
	private void limpiarCampos(){
		tfCodigo.setText("");
		tfNombre.setText("");
		tfTelefono.setText("");
		tpDireccion.setText("");
		tfUsuario.setText("");
		pfSena.setText("");
		
	}
	private void consultaPorFiltro() {
		tecnicosDao= new TecnicosDao();
		listaTecnicos = tecnicosDao.recuperarPorFiltro(tfBuscar.getText());
		modeloTablaTecnico.setLista(listaTecnicos);
		modeloTablaTecnico.fireTableDataChanged();
	}
//	
//	private void habilitarCodigo(boolean e){
//		tfCodigo.setVisible(e);
//		lblCodigo.setVisible(e);
//	}
//	
	private boolean verificarTecnico(){
		boolean a= false;
		for (int i = 0; i < listaTecnicos.size(); i++) {
			if (tfNombre.getText().equals(listaTecnicos.get(i).getTec_nombre())&&tfTelefono.getText().equals(listaTecnicos.get(i).getTec_telefono())){
			JOptionPane.showMessageDialog(null, 
						"Este tecnico ya ésta registrado",
						"Atención",
						JOptionPane.ERROR_MESSAGE);
				a = true;
			}	
		}
		return a;		
	}
	

	private void restriccionNumero(Boolean e){
		lblRestic.setVisible(e);
		lblRestric1.setVisible(e);
	}
	private void eliminarModficar(Boolean e){
		btnEliminar.setEnabled(e);
		btnModificar.setEnabled(e);
	}
	private void verificarCierre(){
		if(tfNombre.getText().equals("")&&tfTelefono.getText().equals("")&&tpDireccion.getText().equals("")){
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
	
	private void soloNumero(KeyEvent e){
		char c = e.getKeyChar();
		if (!Character.isDigit(c)) {
			e.consume();
		}
	}
}
