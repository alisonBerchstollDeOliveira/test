package py.edu.facitec.proyectotaller5.dao;

import java.util.List;

import py.edu.facitec.proyectotaller5.modelo.Deuda;

public class DeudaDao extends GenericDao<Deuda>{

	public DeudaDao() {
		super(Deuda.class);
		
	}
	
//	public List<Reparacion> recuperarPorFiltro(String filtro) {
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
//							builder.equal(root.<Integer>get("rep_id"), filtroId)
//				));
//		lista= session.createQuery(criteriaQuery).getResultList();
//		cerrar();
//		return lista;
//
//	}
	public List<Deuda> recuperarPorPagadp(){
		instanciarCriteria();
		criteriaQuery.where(builder.isFalse(root.<Boolean>get("deu_pagado")));
		lista = session.createQuery(criteriaQuery).getResultList();
		cerrar();
		return lista;
	}


}
