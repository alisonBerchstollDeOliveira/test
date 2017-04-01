package py.edu.facitec.proyectotaller5.buscadores;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import py.edu.facitec.proyectotaller5.boton.BotonAbm;
import py.edu.facitec.proyectotaller5.dao.ClientesDao;
import py.edu.facitec.proyectotaller5.interfaz.InterfazBuscadorClientes;
import py.edu.facitec.proyectotaller5.modelo.Clientes;
import py.edu.facitec.proyectotaller5.modelotabla.ModeloTablaCliente;

public class BuscadorClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ModeloTablaCliente modeloTablaClientes;
	private JTable table;
	private JTextField tfBuscar;
	private InterfazBuscadorClientes interfaz;
	private ClientesDao clientesDao;
	private List<Clientes> clientes;
	private BotonAbm btnBuscar;

	public void setInterfaz(InterfazBuscadorClientes interfaz) {
		this.interfaz = interfaz;
	}
	public static void main(String[] args) {
		try {
			BuscadorClientes dialog = new BuscadorClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorClientes() {
		setBounds(100, 100, 503, 369);
		getContentPane().setLayout(null);
		setTitle("Buscador Clientes");
		setLocationRelativeTo(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 467, 278);
		getContentPane().add(scrollPane);
		
		modeloTablaClientes = new ModeloTablaCliente();
		table = new JTable(modeloTablaClientes);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) 
					seleccionarClientes();
				
			}
		});
		scrollPane.setViewportView(table);
		
		tfBuscar = new JTextField();
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					consultarClientes();
				}
			}
		});
		tfBuscar.setBounds(80, 11, 190, 20);
		getContentPane().add(tfBuscar);
		tfBuscar.setColumns(10);
		
		btnBuscar = new BotonAbm();
		btnBuscar.setText("Buscar");
		btnBuscar.setIcon("buscar");
		btnBuscar.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnBuscar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBuscar.setVerticalTextPosition(SwingConstants.CENTER);
		btnBuscar.setBounds(318, 10, 110, 20);
		getContentPane().add(btnBuscar);
		
		consultarClientesTodo();
	}
	private void consultarClientes() {
		clientesDao = new ClientesDao();
		clientes = clientesDao.recuperarPorFiltro(tfBuscar.getText());
		modeloTablaClientes.setLista(clientes);
		modeloTablaClientes.fireTableDataChanged();
	}
	private void seleccionarClientes() {
		if(table.getSelectedRow()<0)
			return;
		Clientes cliente = clientes.get(table.getSelectedRow());
		interfaz.setClientes(cliente);
		dispose();

	}
	private void consultarClientesTodo() {
		clientesDao = new ClientesDao();
		clientes = clientesDao.recuperarTodo();
//		System.out.println(clientes.get(1).getCli_ci());
		modeloTablaClientes.setLista(clientes);
		modeloTablaClientes.fireTableDataChanged();
	}

}
