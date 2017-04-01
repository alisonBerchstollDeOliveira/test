package py.edu.facitec.proyectotaller5.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cobranza {


	@Id
	@GenericGenerator(name="emp_generator",strategy="increment")
	@GeneratedValue(generator="emp_generator")
	@Column(length=8)
	private int cob_id;
	@Column
	private Date cob_fecha;
	
	@Column(length=15, precision=2)
	private double cob_cobrado;
	
	@ManyToOne
	@JoinColumn(name="deu_id", referencedColumnName="deu_id")
	private Deuda deuda;

	public int getCob_id() {
		return cob_id;
	}

	public void setCob_id(int cob_id) {
		this.cob_id = cob_id;
	}

	public Date getCob_fecha() {
		return cob_fecha;
	}

	public void setCob_fecha(Date cob_fecha) {
		this.cob_fecha = cob_fecha;
	}
 

	public double getCob_cobrado() {
		return cob_cobrado;
	}

	public void setCob_cobrado(double cob_cobrado) {
		this.cob_cobrado = cob_cobrado;
	}

	public Deuda getDeuda() {
		return deuda;
	}

	public void setDeuda(Deuda deuda) {
		this.deuda = deuda;
	}
	
	
	
}
