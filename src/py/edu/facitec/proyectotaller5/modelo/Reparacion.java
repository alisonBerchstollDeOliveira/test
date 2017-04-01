package py.edu.facitec.proyectotaller5.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Reparacion {

	@Id
	@GenericGenerator(name="emp_generator",strategy="increment")
	@GeneratedValue(generator="emp_generator")
	@Column(length=8)
	private int rep_id;
	@Column
	private Date rep_fecha;
	
	@Column
	private boolean rep_arreglo;
	@Column(length=200)
	private String rep_obsTecnico;
	@Column(length=100, precision=2)
	private double rep_monto;
	@Column(length=15, precision=2)
	private double rep_presupuesto;
	
	@OneToMany(mappedBy="reparacion", cascade= CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Deuda> deudas;
	
	
	@ManyToOne
	@JoinColumn(name="tec_id", referencedColumnName="tec_id")
	private Tecnico tecnico;
	
	
	@ManyToOne
	@JoinColumn(name="eq_id", referencedColumnName="eq_id")
	private Equipos equipos;
	
	
	@ManyToOne
	@JoinColumn(name="cli_id", referencedColumnName="cli_id")
	private Clientes clientes;


	public int getRep_id() {
		return rep_id;
	}


	public void setRep_id(int rep_id) {
		this.rep_id = rep_id;
	}


	public Date getRep_fecha() {
		return rep_fecha;
	}


	public void setRep_fecha(Date rep_fecha) {
		this.rep_fecha = rep_fecha;
	}

	public String getRep_obsTecnico() {
		return rep_obsTecnico;
	}


	public void setRep_obsTecnico(String rep_obsTecnico) {
		this.rep_obsTecnico = rep_obsTecnico;
	}


	public double getRep_monto() {
		return rep_monto;
	}


	public void setRep_monto(double rep_monto) {
		this.rep_monto = rep_monto;
	}


	public double getRep_presupuesto() {
		return rep_presupuesto;
	}


	public void setRep_presupuesto(double rep_presupuesto) {
		this.rep_presupuesto = rep_presupuesto;
	}


	public List<Deuda> getDeudas() {
		return deudas;
	}


	public void setDeudas(List<Deuda> deudas) {
		this.deudas = deudas;
	}


	public Tecnico getTecnico() {
		return tecnico;
	}


	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}


	public Equipos getEquipos() {
		return equipos;
	}


	public void setEquipos(Equipos equipos) {
		this.equipos = equipos;
	}


	public Clientes getClientes() {
		return clientes;
	}


	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}


	public boolean isRep_arreglo() {
		return rep_arreglo;
	}


	public void setRep_arreglo(boolean rep_arreglo) {
		this.rep_arreglo = rep_arreglo;
	}
	
	
	
}
