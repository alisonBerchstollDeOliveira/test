package py.edu.facitec.proyectotaller5.formulario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import py.edu.facitec.proyectotaller5.campos.CampoTextoPersonalizado;
import py.edu.facitec.proyectotaller5.dao.CobranzaDao;
import py.edu.facitec.proyectotaller5.dao.DeudaDao;
import py.edu.facitec.proyectotaller5.dao.ReparacionesDao;
import py.edu.facitec.proyectotaller5.modelo.Cobranza;
import py.edu.facitec.proyectotaller5.modelo.Deuda;
import py.edu.facitec.proyectotaller5.modelo.Reparacion;
import py.edu.facitec.proyectotaller5.modelotabla.ModeloTablaCobranza;
import py.edu.facitec.proyectotaller5.util.FechaUtil;
import py.edu.facitec.proyectotaller5.util.NumberUtil;
import py.edu.facitec.proyectotaller5.boton.BotonAbm;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioCobranza extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField tfFecha;
	private JTable table;
	private ModeloTablaCobranza modeloTablaCobranza;
	private CampoTextoPersonalizado tfDeuda;
	private CampoTextoPersonalizado tfCodDeuda;
	private List<Deuda> deudas;
	
	private Deuda deuda;
	private DeudaDao deudaDao;
	private CampoTextoPersonalizado tfCodigo;
	private BotonAbm btnbmCobrar;
	private BotonAbm btnbmSalir;
	private Cobranza cobranza;
	private CobranzaDao cobranzaDao;
	private ReparacionesDao reparaciondao;
	private List<Reparacion> reparacions;
	private List<Cobranza> cobranzas;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioCobranza dialog = new FormularioCobranza();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioCobranza() {
		setModal(true);
		setBounds(100, 100, 740, 415);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 245, 245));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//setResizable(false);
		setLocationRelativeTo(this);
		
		tfCodigo = new CampoTextoPersonalizado();
		tfCodigo.setBounds(587, 43, 127, 21);
		contentPanel.add(tfCodigo);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCodigo.setHorizontalAlignment(lblCodigo.RIGHT);
		lblCodigo.setBounds(494, 47, 83, 14);
		contentPanel.add(lblCodigo);
		
		JLabel lblCodDeuda = new JLabel("Cod. Deuda:");
		lblCodDeuda.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCodDeuda.setBounds(494, 92, 83, 14);
		lblCodDeuda.setHorizontalAlignment(lblCodDeuda.RIGHT);
		contentPanel.add(lblCodDeuda);
		
		tfCodDeuda = new CampoTextoPersonalizado();
		tfCodDeuda.setBounds(587, 88, 127, 18);
		contentPanel.add(tfCodDeuda);
		
		JLabel lblDeuda = new JLabel("Deuda:");
		lblDeuda.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDeuda.setBounds(494, 139, 83, 14);
		lblDeuda.setHorizontalAlignment(lblDeuda.RIGHT);
		contentPanel.add(lblDeuda);
		
		tfDeuda = new CampoTextoPersonalizado();
		tfDeuda.setBounds(587, 135, 127, 18);
		contentPanel.add(tfDeuda);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblFecha.setBounds(494, 183, 83, 14);
		lblFecha.setHorizontalAlignment(lblFecha.RIGHT);
		contentPanel.add(lblFecha);
		
		
		tfFecha = new JFormattedTextField(FechaUtil.getFormato());
		tfFecha.setEnabled(false);
		tfFecha.setBounds(587, 180, 86, 20);
		contentPanel.add(tfFecha);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 420, 338);
		contentPanel.add(scrollPane);
		
		modeloTablaCobranza = new ModeloTablaCobranza();
		table = new JTable(modeloTablaCobranza);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				seleccionarDeuda();
			}
		});
		scrollPane.setViewportView(table);
		
		btnbmCobrar = new BotonAbm();
		btnbmCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		btnbmCobrar.setIcon("cobro");
		btnbmCobrar.setText("Deuda/Cobrar");
		btnbmCobrar.setBounds(594, 304, 120, 62);
		contentPanel.add(btnbmCobrar);
		
		btnbmSalir = new BotonAbm();
		btnbmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnbmSalir.setIcon("salir1");
		btnbmSalir.setText("Salir");
		btnbmSalir.setBounds(455, 304, 120, 62);
		contentPanel.add(btnbmSalir);
		
		consultarDeuda();
	}

	private void consultarDeuda() {
		deudaDao = new DeudaDao();
		deudas=deudaDao.recuperarPorPagadp();
		modeloTablaCobranza.setLista(deudas);
		modeloTablaCobranza.fireTableDataChanged();
	}
	private void seleccionarDeuda() {
		
			if(table.getSelectedRow()<0){
				return;
			}
			deuda= deudas.get(table.getSelectedRow());
			tfCodigo.setText(deuda.getReparacion().getRep_id()+"");
			tfCodDeuda.setText(deuda.getDeu_id()+"");
			tfDeuda.setText(deuda.getReparacion().getRep_monto()+"");
			tfFecha.setText(FechaUtil.fechaAString(new Date()));
	

	}
	private void cargarDatos() {
		cobranza = new Cobranza();		
		deuda.setDeu_pagado(true);
		System.out.println(deuda.toString());
		cobranza.setCob_fecha(FechaUtil.stringAFecha(tfFecha.getText()));
		cobranza.setCob_cobrado(NumberUtil.getValorDouble(tfDeuda.getText()));
		cobranza.setDeuda(deuda);
	}
	private void guardar() {
		
		cargarDatos();
		//luego de cargar los datos confirmamos si la fecha se cargo correctamente
		if(cobranza.getCob_fecha()==null){
			return;
		}
		deudaDao=new DeudaDao();
		deudaDao.insertarOModificar(deuda);
		
		cobranzaDao = new CobranzaDao();
		cobranzaDao.insertarOModificar(cobranza);
		try {
			cobranzaDao.ejecutar();
			try {
				deudaDao.ejecutar();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				deudaDao.cancelar();
			}
		} catch (Exception e) {
			cobranzaDao.cancelar();
		}
		consultarDeuda();
		}
}
