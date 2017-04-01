package py.edu.facitec.proyectotaller5.formulario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import py.edu.facitec.proyectotaller5.app.PantallaPrincipal;
import py.edu.facitec.proyectotaller5.boton.BotonAbm;
import py.edu.facitec.proyectotaller5.dao.AmbienteDao;
import py.edu.facitec.proyectotaller5.modelo.Ambiente;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormularioAmbiente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private BotonAbm btnGuardar;
	private Ambiente ambiente;
	private JTextField tfNombre;
	private AmbienteDao ambienteDao;
	private List<Ambiente> ambientes = new ArrayList<>();
	private PantallaPrincipal frame;
	private JLabel lblTelefono;
	private JTextField tfTelefono;
	private JLabel lblConfiguracinDeLa;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			FormularioAmbiente dialog = new FormularioAmbiente();
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
	public FormularioAmbiente() {
		setUndecorated(true);
		setBounds(100, 100, 450, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBorder(new LineBorder(new Color(192, 192, 192), 3));
		setLocationRelativeTo(this);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(this);
		
		JLabel lblNombre = new JLabel("Empresa:");
		lblNombre.setBounds(11, 55, 100, 14);
		lblNombre.setHorizontalAlignment(lblNombre.RIGHT);
		contentPanel.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(113, 52, 216, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		btnGuardar = new BotonAbm();
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		btnGuardar.setFocusable(false);
		btnGuardar.setDefaultCapable(false);
		btnGuardar.setText("Guardar");
		btnGuardar.setIcon("guardar");
		btnGuardar.setBounds(339, 39, 90, 75);
		contentPanel.add(btnGuardar);
		
		BotonAbm btnSalir = new BotonAbm();
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame= new PantallaPrincipal();
				frame.setVisible(true);
				dispose();
			}
		});
		btnSalir.setText("Salir");
		btnSalir.setHorizontalTextPosition(btnSalir.RIGHT);
		btnSalir.setFont(new Font("Dialog", Font.BOLD, 13));
		btnSalir.setIcon("exit");
		btnSalir.setBounds(164, 131, 100, 33);
		contentPanel.add(btnSalir);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(lblTelefono.RIGHT);
		lblTelefono.setBounds(37, 96, 74, 14);
		contentPanel.add(lblTelefono);
		
		tfTelefono = new JTextField();
		tfTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		tfTelefono.setBounds(113, 93, 216, 20);
		contentPanel.add(tfTelefono);
		tfTelefono.setColumns(10);
		
		lblConfiguracinDeLa = new JLabel("Configuraci\u00F3n de la empresa");
		lblConfiguracinDeLa.setFont(new Font("Dialog", Font.BOLD, 15));
		lblConfiguracinDeLa.setBounds(111, 15, 244, 14);
		contentPanel.add(lblConfiguracinDeLa);
	}
	private void guardar(){
		ambienteDao = new AmbienteDao();
		ambientes=ambienteDao.recuperarTodo();
		
		if(ambientes.isEmpty()){
			ambienteDao = new AmbienteDao();
			ambiente = new Ambiente();
			ambiente.setAmb_nombre(tfNombre.getText());
			ambiente.setTelefono(Integer.parseInt(tfTelefono.getText()));
			ambienteDao.insertarOModificar(ambiente);
			try {
				ambienteDao.ejecutar();
			} catch (Exception e) {
				ambienteDao.cancelar();
			}
			
		}
		frame = new PantallaPrincipal();
		frame.setVisible(true);
		setVisible(false);
		}
	}

