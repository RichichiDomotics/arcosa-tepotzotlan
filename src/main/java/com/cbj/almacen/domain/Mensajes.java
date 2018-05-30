package com.cbj.almacen.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mensajes")
@NamedQueries({
		@NamedQuery(name="Mensajes.getMisMensajesXId",
				query="SELECT c FROM Mensajes c where c.idMensaje = :idMensaje"),
		@NamedQuery(name="Mensajes.getMiMensajesXUsuario",
				query="SELECT c FROM Mensajes c where c.usuarioRecibe = :usuarioRecibe GROUP BY c.usuarioEnvia"),
		@NamedQuery(name="Mensajes.getTodosMisMensajesT",
				query="SELECT c FROM Mensajes c where usuarioRecibe = :usuarioRecibe"),
		@NamedQuery(name="Mensajes.getMisMensajes",
				query="SELECT c FROM Mensajes c where usuarioRecibe = :usuarioRecibe AND c.fechaHoraVisto is null"),
		@NamedQuery(name="Mensajes.updateMensajesVisto",
				query="UPDATE Mensajes c SET c.fechaHoraVisto = NOW() WHERE c.idMensaje = :idMensaje")
})

public class Mensajes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "idMensaje")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMensaje;

	private String usuarioEnvia;
	private String usuarioRecibe;
	private String mensaje;
	private String fecHoraEnviado;
	private String fechaHoraVisto;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Mensajes [idMensaje=" + getIdMensaje() + ", usuarioEnvia=" + getUsuarioEnvia() + ", usuarioRecibe=" + getUsuarioRecibe()
				+ ", mensaje=" + getMensaje() + ", fecHoraEnviado=" + getFecHoraEnviado() + ", fechaHoraVisto=" + getFechaHoraVisto()
				+ "]";
	}


	public Integer getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(Integer idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getUsuarioEnvia() {
		return usuarioEnvia;
	}

	public void setUsuarioEnvia(String usuarioEnvia) {
		this.usuarioEnvia = usuarioEnvia;
	}

	public String getUsuarioRecibe() {
		return usuarioRecibe;
	}

	public void setUsuarioRecibe(String usuarioRecibe) {
		this.usuarioRecibe = usuarioRecibe;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFecHoraEnviado() {
		return fecHoraEnviado;
	}

	public void setFecHoraEnviado(String fecHoraEnviado) {
		this.fecHoraEnviado = fecHoraEnviado;
	}

	public String getFechaHoraVisto() {
		return fechaHoraVisto;
	}

	public void setFechaHoraVisto(String fechaHoraVisto) {
		this.fechaHoraVisto = fechaHoraVisto;
	}
}