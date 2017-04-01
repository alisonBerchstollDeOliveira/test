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
public class Tecnico {
	
	
	@Id
	@GenericGenerator(name="emp_generator",strategy="increment")
	@GeneratedValue(generator="emp_generator")
	@Column(length=8)
	private int tec_id;
	@Column(length=80)
	private String tec_nombre;
	@Column(length=80)
	private String tec_direcc;
	@Column(length=60)
	private int tec_telefono;
	@Column(length=20)
	private String tec_usuario;
	@Column(length=100)
	private String tec_password;
	
	

	
	@OneToMany(mappedBy="tecnico", cascade= CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Reparacion> reparaciones;


	public int getTec_id() {
		return tec_id;
	}


	public void setTec_id(int tec_id) {
		this.tec_id = tec_id;
	}


	public String getTec_nombre() {
		return tec_nombre;
	}


	public void setTec_nombre(String tec_nombre) {
		this.tec_nombre = tec_nombre;
	}


	public List<Reparacion> getReparaciones() {
		return reparaciones;
	}


	public void setReparaciones(List<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}


	public String getTec_direcc() {
		return tec_direcc;
	}


	public void setTec_direcc(String tec_direcc) {
		this.tec_direcc = tec_direcc;
	}



	public int getTec_telefono() {
		return tec_telefono;
	}


	public void setTec_telefono(int tec_telefono) {
		this.tec_telefono = tec_telefono;
	}


	public String getTec_usuario() {
		return tec_usuario;
	}


	public void setTec_usuario(String tec_usuario) {
		this.tec_usuario = tec_usuario;
	}


	public String getTec_password() {
		return tec_password;
	}


	public void setTec_password(String tec_password) {
		this.tec_password = tec_password;
	}
	
	
}
