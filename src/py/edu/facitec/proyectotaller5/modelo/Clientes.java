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
public class Clientes {

	
	@Id
	@GenericGenerator(name="emp_generator",strategy="increment")
	@GeneratedValue(generator="emp_generator")
	@Column(length=8)
	private Integer cli_id;
	@Column(length=80)
	private String cli_nombre;
	@Column(length=12)
	private int cli_ci;
	@Column(length=12)
	private Integer cli_telefono;
	@Column(length=60)
	private String cli_direccion;
	@Column(length=24)
	private String cli_email;
	
	
	@OneToMany(mappedBy="clientes", cascade= CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Reparacion>reparaciones;


	


	public String getCli_nombre() {
		return cli_nombre;
	}


	public void setCli_nombre(String cli_nombre) {
		this.cli_nombre = cli_nombre;
	}





	public int getCli_id() {
		return cli_id;
	}


	public void setCli_id(int cli_id) {
		this.cli_id = cli_id;
	}


	public int getCli_ci() {
		return cli_ci;
	}


	public void setCli_ci(Integer cli_ci) {
		this.cli_ci = cli_ci;
	}


	public Integer getCli_telefono() {
		return cli_telefono;
	}


	public void setCli_telefono(int cli_telefono) {
		this.cli_telefono = cli_telefono;
	}


	public String getCli_direccion() {
		return cli_direccion;
	}


	public void setCli_direccion(String cli_direccion) {
		this.cli_direccion = cli_direccion;
	}


	public String getCli_email() {
		return cli_email;
	}


	public void setCli_email(String cli_email) {
		this.cli_email = cli_email;
	}


	public List<Reparacion> getReparaciones() {
		return reparaciones;
	}


	public void setReparaciones(List<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}

	
	
	
}
