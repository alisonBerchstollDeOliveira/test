package py.edu.facitec.proyectotaller5.formulario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import py.edu.facitec.proyectotaller5.dao.TecnicosDao;
import py.edu.facitec.proyectotaller5.interfaz.InterfazBuscadorClientes;
import py.edu.facitec.proyectotaller5.interfaz.InterfazUsuario;
import py.edu.facitec.proyectotaller5.modelo.Equipos;
import py.edu.facitec.proyectotaller5.modelo.Tecnico;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormularioUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsuario;
	private JPasswordField pfSena;
	private TecnicosDao tecnicoDao;
	private InterfazUsuario interfaz;

	private List<Tecnico> tecnicos;
	public boolean a =false;
	
	
	
	public void setInterfaz(InterfazUsuario interfaz) {
		this.interfaz = interfaz;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioUsuario dialog = new FormularioUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioUsuario() {
		setUndecorated(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(Color.GRAY, 5, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(this);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(31, 50, 46, 14);
		contentPanel.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(31, 128, 46, 14);
		contentPanel.add(lblSenha);
		
		tfUsuario = new JTextField();		
		tfUsuario.setBounds(92, 47, 86, 20);
		contentPanel.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSena = new JPasswordField();
		pfSena.setEditable(false);
		pfSena.setBounds(92, 125, 188, 20);
		contentPanel.add(pfSena);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(verificarSena()==true){
					dispose();
				}
			}
		});
		btnAceptar.setBounds(201, 209, 89, 23);
		contentPanel.add(btnAceptar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(21, 209, 89, 23);
		contentPanel.add(btnSalir);
		pfSena.setEditable(true);
	}
	

	public boolean verificarSena(){
		tecnicoDao = new TecnicosDao();
		tecnicos = tecnicoDao.recuperarTodo();	
		for (int i = 0; i < tecnicos.size(); i++) {
			if ((tfUsuario.getText().equals(tecnicos.get(i).getTec_usuario()))) {
				char clave[]=pfSena.getPassword();
				String clavedef=new String(clave);
				if(clavedef.equals(tecnicos.get(i).getTec_password())){
					System.out.println("oikooo");
					a= true;
					interfaz.setHabilitar();
					Tecnico tecnico = tecnicos.get(i);
					interfaz.setTecnico(tecnico);
				}else{
					JOptionPane.showMessageDialog(null, 
							"El Usuario y Seña no coiciden",
							"Atención",
							JOptionPane.ERROR_MESSAGE);
					tfUsuario.requestFocus();
				}
			}
		}
		return a;
		}
	
}
