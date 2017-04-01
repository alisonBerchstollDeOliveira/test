package py.edu.facitec.proyectotaller5.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Ambiente {

	@Id
	@GenericGenerator(name="emp_generator",strategy="increment")
	@GeneratedValue(generator="emp_generator")
	@Column(length=8)
	private Integer amb_id;
	@Column(length=80)
	private String amb_nombre;
	@Column
	private int telefono;
	public Integer getAmb_id() {
		return amb_id;
	}
	public void setAmb_id(Integer amb_id) {
		this.amb_id = amb_id;
	}
	public String getAmb_nombre() {
		return amb_nombre;
	}
	public void setAmb_nombre(String amb_nombre) {
		this.amb_nombre = amb_nombre;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	
}
