package py.edu.facitec.proyectotaller5.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.proyectotaller5.dao.CobranzaDao;
import py.edu.facitec.proyectotaller5.modelo.Clientes;
import py.edu.facitec.proyectotaller5.modelo.Cobranza;
import py.edu.facitec.proyectotaller5.modelo.Deuda;
import py.edu.facitec.proyectotaller5.modelo.Reparacion;
import py.edu.facitec.proyectotaller5.util.FechaUtil;
import py.edu.facitec.proyectotaller5.util.NumberUtil;

public class ModeloTablaCobranza extends AbstractTableModel{

	private String [] columnas = {
			"Código",  "Cliente","Marca","Modelo", "Monto", "Fecha"
	};

	private Object[][] datos = new Object[0][columnas.length];

	public void setLista(List<Deuda> lista){
		
		datos = new Object[lista.size()][columnas.length];
		for (int i = 0; i < lista.size(); i++) {
		
				datos[i][0]=lista.get(i).getReparacion().getRep_id();
				datos[i][1]=lista.get(i).getReparacion().getClientes().getCli_nombre();
				datos[i][2]=lista.get(i).getReparacion().getEquipos().getEq_marca();
				datos[i][3]=lista.get(i).getReparacion().getEquipos().getEq_modelo();
				datos[i][4]=lista.get(i).getReparacion().getRep_monto();
				datos[i][5]=FechaUtil.fechaAString(lista.get(i).getReparacion().getRep_fecha());
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
