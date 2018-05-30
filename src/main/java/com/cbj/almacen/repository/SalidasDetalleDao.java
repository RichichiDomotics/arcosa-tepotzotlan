package com.cbj.almacen.repository;


        import com.cbj.almacen.domain.SalidasDetalle;

        import java.util.Date;
        import java.util.List;

/**
 * Created by jolvera on 02/06/2014.
 */
public interface SalidasDetalleDao {

    public int insertSalidasDetalle(final SalidasDetalle salidasDetalle);
    //public SalidasDetalle findSalidaByClienteConsecutivo(final String cliente, final int consecutivo);
    public List<SalidasDetalle> findSalidaByClienteConsecutivo(final String cliente,final int folioSalida);
    public List<SalidasDetalle> findSalidaFolioSalida(final int folioSalida);
    public List<SalidasDetalle> getSalidaByClienteConsecutivo(final String cliente,final int folioSalida);
    public List<SalidasDetalle> findSalidaByConsecutivo(final int cliente);
    public List<SalidasDetalle> findSalidaByConsecutivoFolioSalida(final int cliente,final int folioSalida);
    public List<Object[]> getKilosByDay(final Date fechaSalida);
    public List<Object[]> getKilosByFechas(final Date fechaInicio, final Date fechaFin);
    public List<Object[]> getKilosByFechasConcentrado(Date fechaInicio, Date fechaFin);
    public List<Object[]> getKilosByFechasCliente(final Date fechaInicio, final Date fechaFin, final String idCliente);
    public List<Object[]> getKilosByFechasClienteDetalle(final Date fechaInicio, final Date fechaFin, final String idCliente);
    public Object getKilosByFechasClienteDetalleTotal(final Date fechaInicio, final Date fechaFin, final String idCliente);
    public List<Object[]> getReporteByCamara(final Date fechaInicio, final Date fechaFin, final String idCamara);
    public List<Object[]> getReporteByCamaraVacia(final Date fechaInicio, final Date fechaFin);
    public List<Object[]> getArrastreSaldosDetalle(final int consecutivo);
    public List<SalidasDetalle> getArrastreSaldosDetalleAgrupado(final int consecutivo);
    public List<SalidasDetalle> findSalidaByFolioSalida(final int consecutivo);
}
