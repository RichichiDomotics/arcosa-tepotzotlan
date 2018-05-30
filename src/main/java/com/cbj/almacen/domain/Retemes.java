package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "retemes")
@NamedQueries({
    @NamedQuery(name="Retemes.getByConsecutivo",
            query="SELECT c FROM Retemes c where c.CONSECUTIVO = :consecutivo and c.KILOS > 0"),
    @NamedQuery(name="Retemes.getByIdCliente",
            //query="SELECT c.FOLIORETEME, c.FECHA, c.CONSECUTIVO, c.KILOS, c.ID_CLIENTE, c.TIPOSERVICIO, c.OBSERV FROM Retemes c where c.ID_CLIENTE like :idCliente AND c.FECHA >= :fecini AND c.FECHA <= :fecfin AND c.FOLIORETEME like :folioReteme"),
			query="SELECT c.FOLIORETEME, c.FECHA, c.CONSECUTIVO, c.KILOS, c.ID_CLIENTE, c.TIPOSERVICIO, c.OBSERV FROM Retemes c where (c.FECHA >= :fecini AND c.FECHA <= :fecfin) OR ((c.ID_CLIENTE = :idCliente) OR (c.FOLIORETEME = :folioReteme) OR (c.CONSECUTIVO = :consecutivo)) and c.KILOS > 0 and c.CONSECUTIVO <> '' ORDER BY c.FECHA"),
    @NamedQuery(name="Retemes.getMaxId",
            query="SELECT MAX(c.FOLIORETEME) FROM Retemes c where c.TIPO='O'"),
		@NamedQuery(name="Retemes.getMaxIdTIF",
				query="SELECT MAX(c.FOLIORETEME) FROM Retemes c where c.TIPO='T'")
})
public class Retemes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idFolioReteme")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idFolioReteme;
    private String FOLIORETEME;
	private String FECHA;
    private String CONSECUTIVO;
    private double KILOS;
    private String CAUSAS_CIASA;
    private String FIRMA_RETEME;
    private String ID_CLIENTE;
    private String FREAL;
    private String CAMARA;
    private double TCARGO;
    private double CARGOA;
    private String ALTURA;
    private String HDESDE;
    private String HHASTA;
    private String TIPOSERVICIO;
    private String REFEREN;
    private String OBSERV;
	private String TIPO;
    
    public int getIdFolioReteme() {
		return idFolioReteme;
	}

	public void setIdFolioReteme(int idFolioReteme) {
		this.idFolioReteme = idFolioReteme;
	}

	public String getFolioReteme() {
		return FOLIORETEME;
	}

	public void setFolioRemete(String FOLIORETEME) {
		this.FOLIORETEME = FOLIORETEME;
	}

	public String getFecha() {
		return FECHA;
	}

	public void setFecha(String FECHA) {
		this.FECHA = FECHA;
	}

	public String getConsecutivo() {
		return CONSECUTIVO;
	}

	public void setConsecutivo(String CONSECUTIVO) {
		this.CONSECUTIVO = CONSECUTIVO;
	}

	public double getKilos() {
		return KILOS;
	}

	public void setKilos(double KILOS) {
		this.KILOS = KILOS;
	}

	public String getCausasCiasa() {
		return CAUSAS_CIASA;
	}

	public void setCausasCiasa(String CAUSAS_CIASA) {
		this.CAUSAS_CIASA = CAUSAS_CIASA;
	}

	public String getFirmaReteme() {
		return FIRMA_RETEME;
	}

	public void setFirmaReteme(String FIRMA_RETEME) {
		this.FIRMA_RETEME = FIRMA_RETEME;
	}

	public String getIdCliente() {
		return ID_CLIENTE;
	}

	public void setIdCliente(String ID_CLIENTE) {
		this.ID_CLIENTE = ID_CLIENTE;
	}

	public String getFReal() {
		return FREAL;
	}

	public void setFReal(String FREAL) {
		this.FREAL = FREAL;
	}

	public String getCamara() {
		return CAMARA;
	}

	public void setCamara(String CAMARA) {
		this.CAMARA = CAMARA;
	}

	public double getTCargo() {
		return TCARGO;
	}

	public void setTCargo(double TCARGO) {
		this.TCARGO = TCARGO;
	}
	
	public double getCargoA() {
		return CARGOA;
	}

	public void setCargoA(double CARGOA) {
		this.CARGOA = CARGOA;
	}

	public String getAltura() {
		return ALTURA;
	}

	public void setAltura(String ALTURA) {
		this.ALTURA = ALTURA;
	}

	public String getHDesde() {
		return HDESDE;
	}

	public void setHDesde(String HDESDE) {
		this.HDESDE = HDESDE;
	}

	public String getHHasta() {
		return HHASTA;
	}

	public void setHHasta(String HHASTA) {
		this.HHASTA = HHASTA;
	}

	public String getTipoServicio() {
		return TIPOSERVICIO;
	}

	public void setTipoServicio(String TIPOSERVICIO) {
		this.TIPOSERVICIO = TIPOSERVICIO;
	}
	
	public String getReferen() {
		return REFEREN;
	}

	public void setReferen(String REFEREN) {
		this.REFEREN = REFEREN;
	}
	
	public String getObserv() {
		return OBSERV;
	}

	public void setObserv(String OBSERV) {
		this.OBSERV = OBSERV;
	}

	@Override
	public String toString() {
		return "Reteme [idFolioReteme=" + getIdFolioReteme()  
				+ ", FOLIORETEME=" + getFolioReteme() + ", FECHA="
				+ getFecha() + ", CONSECUTIVO="
				+ getConsecutivo() + ", KILOS="
				+ getKilos() + ", CAUSAS_CIASA=" + getCausasCiasa()
				+ ", FIRMA_RETEME=" + getFirmaReteme() + ", ID_CLIENTE=" + getIdCliente()
				+ ", FREAL=" + getFReal() + ", CAMARA="
				+ getCamara() + ", TCARGO=" + getTCargo()
				+ ", CARGOA=" + getCargoA()
				+ ", ALTURA=" + getAltura()
				+ ", HDESDE=" + getHDesde()
				+ ", HHASTA=" + getHHasta()
				+ ", TIPOSERVICIO=" + getTipoServicio()
				+ ", REFEREN=" + getReferen()
				+ ", OBSERV=" + getObserv() + "]";
	}

	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String TIPO) {
		this.TIPO = TIPO;
	}
}