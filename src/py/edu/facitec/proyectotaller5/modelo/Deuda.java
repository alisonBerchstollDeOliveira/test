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
public class Deuda {
	
	
	@Id
	@GenericGenerator(name="emp_generator",strategy="increment")
	@GeneratedValue(generator="emp_generator")
	@Column(length=8)
	private int deu_id;
	
//	@Column(length=10)
//	private double deu_monto;
	
	@Column
	private Date deu_fecha;
	@Column
	private boolean deu_pagado;
	
	

	@ManyToOne
	@JoinColumn(name="rep_id", referencedColumnName="rep_id")
	private Reparacion reparacion;


	public int getDeu_id() {
		return deu_id;
	}


	public void setDeu_id(int deu_id) {
		this.deu_id = deu_id;
	}


	public Reparacion getReparacion() {
		return reparacion;
	}


	public void setReparacion(Reparacion reparacion) {
		this.reparacion = reparacion;
	}




	public Date getDeu_fecha() {
		return deu_fecha;
	}


	public void setDeu_fecha(Date deu_fecha) {
		this.deu_fecha = deu_fecha;
	}


	public boolean isDeu_pagado() {
		return deu_pagado;
	}


	public void setDeu_pagado(boolean deu_pagado) {
		this.deu_pagado = deu_pagado;
	}


	@Override
	public String toString() {
		return "Deuda [deu_id=" + deu_id + ", deu_fecha=" + deu_fecha + ", deu_pagado=" + deu_pagado + ", reparacion="
				+ reparacion + "]";
	}
	
	
	
	
	
}
