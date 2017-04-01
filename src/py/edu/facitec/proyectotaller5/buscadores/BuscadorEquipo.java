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
import py.edu.facitec.proyectotaller5.dao.EquiposDao;
import py.edu.facitec.proyectotaller5.interfaz.InterfazBuscadorEquipos;
import py.edu.facitec.proyectotaller5.modelo.Equipos;
import py.edu.facitec.proyectotaller5.modelotabla.ModeloTablaEquipos;

public class BuscadorEquipo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTable table;
	private JTextField tfBuscar;
	private InterfazBuscadorEquipos interfaz;
	private EquiposDao equiposDao;
	private List<Equipos> equipos;
	private BotonAbm btnBuscar;

	private ModeloTablaEquipos modeloTablaEquipos;

	public void setInterfaz(InterfazBuscadorEquipos interfaz) {
		this.interfaz = interfaz;
	}
	public static void main(String[] args) {
		try {
			BuscadorEquipo dialog = new BuscadorEquipo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorEquipo() {
		setBounds(100, 100, 503, 369);
		getContentPane().setLayout(null);
		setTitle("Buscador de Equipos");
		setLocationRelativeTo(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 467, 278);
		getContentPane().add(scrollPane);
		
		modeloTablaEquipos = new ModeloTablaEquipos();
		table = new JTable(modeloTablaEquipos);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) 
					seleccionarEquipos();
				
			}
		});
		scrollPane.setViewportView(table);
		
		tfBuscar = new JTextField();
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					consultarEquipos();
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
		btnBuscar.setBounds(318, 10, 110, 21);
		getContentPane().add(btnBuscar);
		
		consultarEquiposTodo();
	}
	private void consultarEquipos() {
		equiposDao = new EquiposDao();
		equipos = equiposDao.recuperarPorFiltro(tfBuscar.getText());
		modeloTablaEquipos.setLista(equipos);
		modeloTablaEquipos.fireTableDataChanged();
	}
	private void seleccionarEquipos() {
		if(table.getSelectedRow()<0)
			return;
		Equipos equipo = equipos.get(table.getSelectedRow());
		interfaz.setEquipos(equipo);
		dispose();
	}
	private void consultarEquiposTodo() {
		equiposDao = new EquiposDao();
		equipos = equiposDao.recuperarTodo();
		modeloTablaEquipos.setLista(equipos);
		modeloTablaEquipos.fireTableDataChanged();
	}

}
