package py.edu.facitec.proyectotaller5.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Equipos {
	
	@Id
	@GenericGenerator(name="emp_generator",strategy="increment")
	@GeneratedValue(generator="emp_generator")
	@Column(length=8)
	private int eq_id;
	
	@Column(length=10)
	private String eq_marca;
	
	@Column(length=10)
	private String eq_modelo;
	@Column(length=10)
	private String eq_version;
	@Column(length=50)
	private String eq_descripcion;
	
	@OneToMany(mappedBy="equipos", cascade= CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Reparacion> reparaciones;

	public int getEq_id() {
		return eq_id;
	}

	public void setEq_id(int eq_id) {
		this.eq_id = eq_id;
	}

	public String getEq_marca() {
		return eq_marca;
	}

	public void setEq_marca(String eq_marca) {
		this.eq_marca = eq_marca;
	}

	public String getEq_modelo() {
		return eq_modelo;
	}

	public void setEq_modelo(String eq_modelo) {
		this.eq_modelo = eq_modelo;
	}

	public String getEq_version() {
		return eq_version;
	}

	public void setEq_version(String eq_version) {
		this.eq_version = eq_version;
	}

	public List<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(List<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}

	public String getEq_descripcion() {
		return eq_descripcion;
	}

	public void setEq_descripcion(String eq_descripcion) {
		this.eq_descripcion = eq_descripcion;
	}

	
	
	
}
