package py.edu.facitec.proyectotaller5.modelotabla;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.proyectotaller5.modelo.Clientes;
import py.edu.facitec.proyectotaller5.modelo.Reparacion;
import py.edu.facitec.proyectotaller5.util.NumberUtil;

public class ModeloTablaReparacion extends AbstractTableModel{

	
	private String [] columnas = {
			"Código",  "Cliente", "Equipos ","Deuda", "Presupuesto"
	};
	
	private Object[][] datos = new Object[0][columnas.length];
	
	public void setLista(List<Reparacion> lista){
		datos = new Object[lista.size()][columnas.length];
		for (int i = 0; i < lista.size(); i++) {
			datos[i][0]=lista.get(i).getRep_id();
			datos[i][1]=lista.get(i).getClientes().getCli_nombre();
			datos[i][2]=lista.get(i).getEquipos().getEq_marca();
			datos[i][3]=NumberUtil.getNumeroFormateado(lista.get(i).getRep_monto());
			datos[i][4]=NumberUtil.getNumeroFormateado(lista.get(i).getRep_presupuesto());
			
			
		}
	}
	
	
	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public String getColumnName(int i) {
		return columnas[i];
	}
	
	@Override
	public int getRowCount() {
		return datos.length;
	}


	@Override
	public Object getValueAt(int f, int c) {
		return datos[f][c];
	}

}
