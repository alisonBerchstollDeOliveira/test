package py.edu.facitec.proyectotaller5.dao;

import java.util.List;

import py.edu.facitec.proyectotaller5.modelo.Clientes;

public class ClientesDao extends GenericDao<Clientes>{

	public ClientesDao() {
		super(Clientes.class);
		
	}
	
	public List<Clientes> recuperarPorFiltro(String filtro) {
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
									builder.lower(root.<String>get("cli_nombre")), "%"+filtro.toLowerCase()+"%"),
							builder.equal(root.<Integer>get("cli_id"), filtroId))
				);
		lista= session.createQuery(criteriaQuery).getResultList();
		cerrar();
		return lista;

	}

	public List<Clientes> recuperarCi(String filtro) {
		int filtroCi = 0;
		
		try {
			filtroCi=Integer.parseInt(filtro);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		instanciarCriteria();
		criteriaQuery.where(
									builder.equal(root.<Integer>get("cli_id"), filtroCi)
				);
		lista= session.createQuery(criteriaQuery).getResultList();
		cerrar();
		return lista;

	}

}
