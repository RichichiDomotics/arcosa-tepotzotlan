package com.cbj.almacen.service;

        import com.cbj.almacen.domain.SalidasDetalle;

        import java.io.Serializable;
        import java.util.Date;
        import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
public interface SalidasDetalleManager extends Serializable {

    public int registraSalidaDetalle(final SalidasDetalle salidaDetalle);
    public List<SalidasDetalle> getSalidaByClienteConsecutivo(final String consecutivo, final int idCliente, final int folioSalida);
    public List<SalidasDetalle> getSalidaByClienteConsecutivo(final String consecutivo, final int folioSalida);
    public List<SalidasDetalle> getSalidaByClienteConsecutivo(final int consecutivo, final int folioSalida);
    public List<SalidasDetalle> findSalidaFolioSalida(final int folioSalida);
    public List<SalidasDetalle> findSalidaByClienteConsecutivo(final String idCliente, final int folioSalida);
    public List<SalidasDetalle> getSalidaByConsecutivo(final int consecutivo);
    public List<SalidasDetalle> getSalidaByConsecutivoFolioSalida(final int consecutivo,final int folioSalida);
    public List<Object[]> getKilosByDay(Date fechaSalida);
    public List<Object[]> getKilosByFechas(Date fechaInicio,final Date fechaFin);
    public List<Object[]> getKilosByFechasConcentrado(Date fechaInicio, Date fechaFin);
    public List<Object[]> getKilosByFechasCliente(final Date fechaInicio,final Date fechaFin, final String idCliente);
    public List<Object[]> getKilosByFechasClienteDetalle(final Date fechaInicio,final Date fechaFin, final String idCliente);
    public Object getKilosByFechasClienteDetalleTotal(final Date fechaInicio,final Date fechaFin, final String idCliente);
    public List<Object[]> getReporteByCamara(final Date fechaInicio,final Date fechaFin, final String idCamara);
    public List<Object[]> getReporteByCamaraVacia(final Date fechaInicio,final Date fechaFin);
    public List<Object[]> getArrastreSaldosDetalle(final int consecutivo);
    public List<SalidasDetalle> getArrastreSaldosDetalleAgrupado(final int consecutivo);
    public List<SalidasDetalle> findSalidaByFolioSalida(final int consecutivo);
}
