package py.edu.facitec.proyectotaller5.modelotabla;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.proyectotaller5.modelo.Tecnico;

public class ModeloTablaTecnico extends AbstractTableModel{

	
	private String [] columnas = {
			 "Nombre", "Teléfono", "Dirección"
	};
	
	private Object[][] datos = new Object[0][columnas.length];
	
	public void setLista(List<Tecnico> lista){
		datos = new Object[lista.size()][columnas.length];
		for (int i = 0; i < lista.size(); i++) {
			datos[i][0]=lista.get(i).getTec_nombre();
			datos[i][1]=lista.get(i).getTec_telefono();
			datos[i][2]=lista.get(i).getTec_direcc();
			
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
