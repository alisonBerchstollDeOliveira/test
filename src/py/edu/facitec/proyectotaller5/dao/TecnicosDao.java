package py.edu.facitec.proyectotaller5.dao;

import java.util.List;

import py.edu.facitec.proyectotaller5.modelo.Tecnico;

public class TecnicosDao extends GenericDao<Tecnico>{

	public TecnicosDao() {
		super(Tecnico.class);
		
	}
	
	public List<Tecnico> recuperarPorFiltro(String filtro) {
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
									builder.lower(root.<String>get("tec_nombre")), "%"+filtro.toLowerCase()+"%"),
							builder.equal(root.<Integer>get("tec_id"), filtroId))
				);
		lista= session.createQuery(criteriaQuery).getResultList();
		cerrar();
		return lista;

	}


}
