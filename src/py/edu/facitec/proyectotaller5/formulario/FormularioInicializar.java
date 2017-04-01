package py.edu.facitec.proyectotaller5.formulario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import py.edu.facitec.proyectotaller5.app.PantallaPrincipal;
import py.edu.facitec.proyectotaller5.boton.BotonAbm;
import py.edu.facitec.proyectotaller5.dao.AmbienteDao;
import py.edu.facitec.proyectotaller5.dao.ClientesDao;
import py.edu.facitec.proyectotaller5.dao.EquiposDao;
import py.edu.facitec.proyectotaller5.dao.TecnicosDao;
import py.edu.facitec.proyectotaller5.modelo.Ambiente;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class FormularioInicializar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Ambiente ambiente;
	private AmbienteDao ambienteDao;
	private List<Ambiente> ambientes = new ArrayList<>();
	private PantallaPrincipal frame;
	private EquiposDao equiposDao;
	private TecnicosDao tecnicoDao;
	private ClientesDao clientesDao;
	private BotonAbm btnSalir;
	private BotonAbm btnbmSi;
	private BotonAbm btnbmNo;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			FormularioInicializar dialog = new FormularioInicializar();
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
	public FormularioInicializar() {
		setUndecorated(true);
		setBounds(100, 100, 450, 161);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBorder(new LineBorder(new Color(192, 192, 192), 3));
		setTitle("Ambiente");
		setLocationRelativeTo(this);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(this);
		setModal(true);
		
		btnbmNo = new BotonAbm();
		btnbmNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				frame = new PantallaPrincipal();
				frame.setVisible(true);
				
			}
		});
		btnbmNo.setIcon("exit");
		btnbmNo.setText("No");
		btnbmNo.setBounds(76, 57, 90, 75);
		contentPanel.add(btnbmNo);
		
		btnbmSi = new BotonAbm();
		btnbmSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int respuesta = JOptionPane.showConfirmDialog(null, 
						"Esta seguro que desea Inicializar el Sistema ",
						"Atención",
						JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_OPTION){
					eliminarAmbiente();
					eliminarCliente();
					eliminarEquipos();
					eliminarTecnico();
				}
				frame= new PantallaPrincipal();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnbmSi.setText("Si");
		btnbmSi.setIcon("guardar");
		btnbmSi.setBounds(266, 57, 90, 75);
		contentPanel.add(btnbmSi);
		
		JLabel lblConfirmacion = new JLabel("\u00BFEsta seguro que desea inicializar el sistema?");
		lblConfirmacion.setFont(new Font("Dialog", Font.BOLD, 14));
		lblConfirmacion.setBounds(50, 11, 339, 24);
		contentPanel.add(lblConfirmacion);
	}
	protected void eliminarAmbiente(){
		String cat= "Ambiente";
		ambienteDao = new AmbienteDao();		
		try {
			ambienteDao.eliminarTodos(cat);
			ambienteDao.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void eliminarCliente(){
		String cat= "Clientes";
		clientesDao = new ClientesDao();		
		try {
			clientesDao.eliminarTodos(cat);
			clientesDao.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void eliminarEquipos(){
		String cat= "Equipos";
		equiposDao = new EquiposDao();
		try {
			equiposDao.eliminarTodos(cat);
			equiposDao.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void eliminarTecnico(){
		String cat= "Tecnico";
		tecnicoDao = new TecnicosDao();
		try {
			tecnicoDao.eliminarTodos(cat);
			tecnicoDao.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	}

