package com.cbj.almacen.domain;

import javax.persistence.*;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.io.Serializable;

/**
 * Created by colvera on 11/05/2014.
 */
@Entity
@Table(name = "servicios")
@NamedQueries({
    @NamedQuery(name="Servicios.getByActivo",
            query="SELECT c FROM Servicios c where c.activo = '1'"),
    @NamedQuery(name="Servicios.getByActivoByIdCliente",
            query="SELECT c FROM Servicios c where c.activo = '1' and c.idCliente like :idCliente order by descripcion"),
    @NamedQuery(name="Servicios.getByActivoByIdClienteClave",
            query="SELECT c FROM Servicios c where c.activo = '1' and c.idCliente like :idCliente AND c.clave like :clave"),
    @NamedQuery(name="Servicios.getCboServicios",
            query="SELECT DISTINCT(c.clave),c.descripcion FROM Servicios c where c.activo = '1'")
})
public class Servicios implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idServicio")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idServicio;
    private String idCliente;
    private String clave;
    private Double cuota;
    private String descripcion;
    private String activo;
    private String como;
    
    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }
    
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
    @Override
	public String toString() {
		return "Servicio [idServicio=" + getIdServicio()  
				+ ", clave=" + getClave() + ", cuota="
				+ getCuota() + ", descripcion="
				+ getDescripcion() + ", activo="
				+ getActivo() + "]";
	}

    public String getComo() {
        return como;
    }

    public void setComo(String como) {
        this.como = como;
    }
}
