package py.edu.facitec.proyectotaller5.dao;

import java.util.List;

import py.edu.facitec.proyectotaller5.modelo.Equipos;

public class EquiposDao extends GenericDao<Equipos>{

	public EquiposDao() {
		super(Equipos.class);
		
	}
	
	public List<Equipos> recuperarPorFiltro(String filtro) {
		int filtroId = 0;
		
		try {
			filtroId=Integer.parseInt(filtro);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		instanciarCriteria();
		criteriaQuery.where(
					builder.or(
							builder.like(
									builder.lower(root.<String>get("eq_marca")), "%"+filtro.toLowerCase()+"%"),
							builder.equal(root.<Integer>get("eq_id"), filtroId))
				);
		lista= session.createQuery(criteriaQuery).getResultList();
		cerrar();
		return lista;

	}


}
