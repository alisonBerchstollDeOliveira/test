package py.edu.facitec.proyectotaller5.modelotabla;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.proyectotaller5.modelo.Equipos;

public class ModeloTablaEquipos extends AbstractTableModel{
	
	
	private String [] columnas = {  "Marca", "Modelo", "Versión", "Descripción"};
	
	private Object [][] datos = new Object[0][columnas.length];
	
	public void setLista(List<Equipos> lista){
		
		datos= new Object[lista.size()][columnas.length];
		
		for (int i = 0; i < lista.size(); i++) {
			datos[i][0]= lista.get(i).getEq_marca();
			datos[i][1]= lista.get(i).getEq_modelo();
			datos[i][2]= lista.get(i).getEq_version();
			datos[i][3]= lista.get(i).getEq_descripcion();
		}
		
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return datos.length;
	}

	@Override
	public Object getValueAt(int f, int c) {
		return datos[f][c];
	}
	@Override
	public String getColumnName(int i) {
		return columnas[i];
	}

}
