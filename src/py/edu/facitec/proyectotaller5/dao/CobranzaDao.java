package py.edu.facitec.proyectotaller5.dao;

import java.util.List;

import py.edu.facitec.proyectotaller5.modelo.Cobranza;

public class CobranzaDao extends GenericDao<Cobranza>{

	public CobranzaDao() {
		super(Cobranza.class);
		
	}
//	
//	public List<Clientes> recuperarPorFiltro(String filtro) {
//		int filtroId = 0;
//		
//		try {
//			filtroId=Integer.parseInt(filtro);
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		}
//		
//		instanciarCriteria();
//		criteriaQuery.where(
//					builder.or(
//							builder.like(
//									builder.lower(root.<String>get("cli_nombre")), "%"+filtro.toLowerCase()+"%"),
//							builder.equal(root.<Integer>get("cli_id"), filtroId))
//				);
//		lista= session.createQuery(criteriaQuery).getResultList();
//		cerrar();
//		return lista;
//
//	}
	public List<Cobranza> listaPago(){
		instanciarCriteria();
		criteriaQuery.where(builder.equal(root.<Integer>get("deu_id"),1));
		lista=session.createQuery(criteriaQuery).getResultList();
		return lista;
	}


}
