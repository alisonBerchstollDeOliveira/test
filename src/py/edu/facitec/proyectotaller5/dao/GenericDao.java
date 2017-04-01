package py.edu.facitec.proyectotaller5.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import py.edu.facitec.proyectotaller5.util.Factory;

public class GenericDao <T>{
	protected Session session;
	private Class<T> entity;
	protected CriteriaBuilder builder;
	protected CriteriaQuery<T> criteriaQuery;
	protected Root<T> root;
	protected List<T> lista;
	
	public GenericDao(Class<T> entity) {
		this.entity = entity;
		session = Factory.getSessionFactory().openSession();

	}
	
	public void insertarOModificar(T entity){
		session.beginTransaction();
		session.saveOrUpdate(entity);
	}
	
	public void eliminar(T entity){
		session.beginTransaction();
		session.delete(entity);
	}
	
	public T recuperarPorId(int id){
		T result = session.get(entity, id);
		cerrar();
		return result;
	}
	
	
	public List<T> recuperarTodo(){
//		criteriaQuery.orderBy(builder.asc(root.get("id")));
//		lista = session.createQuery(criteriaQuery).getResultList();
		
		
		//consulta utilizando hibernate query lenguaje hql
		String hgl = "FROM "+entity.getName()+" ORDER BY id";
		Query query= session.createQuery(hgl);
		lista= query.getResultList();
		cerrar();
		
		return lista;
	}
	public void eliminarTodos(String tabla){
		session.getTransaction().begin();
		
		String deleteAll= "TRUNCATE table "+tabla+ " cascade";
		Query query = session.createNativeQuery(deleteAll);
		query.executeUpdate();
	}
	
	public void ejecutar() throws Exception{
		session.flush();
		session.getTransaction().commit();
		cerrar();
	}
	
	public void cancelar(){
		session.getTransaction().rollback();
		session.clear();
		cerrar();
	}
	
	public void cerrar(){
		session.close();
	}
	
	protected void instanciarCriteria() {
		
		//instanciar los objetos necesarios para utilizar criteria		
		builder = session.getCriteriaBuilder();
		criteriaQuery = builder.createQuery(entity);
		root = criteriaQuery.from( entity );
		criteriaQuery.select( root );

	}
}
