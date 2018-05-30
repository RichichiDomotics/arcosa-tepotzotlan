package com.cbj.almacen.repository.impl;

import com.cbj.almacen.domain.DetallesRd;
import com.cbj.almacen.domain.Inventario;
import com.cbj.almacen.domain.Inventarioview;
import com.cbj.almacen.repository.InventarioDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jolvera on 17/05/2014.
 */
@Repository(value = "inventarioDao")
public class InventarioDaoImpl implements InventarioDao {

    private static final Logger logger = LoggerFactory
            .getLogger(InventarioDaoImpl.class);

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = false)
    public boolean insertInventario(Inventario inventario) {
        boolean respuesta=false;
        logger.trace(inventario.toString());
        try{
            logger.info(inventario.toString());
            em.persist(inventario);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;

    }

    @Transactional(readOnly = false)
    public boolean updateInventario(Inventario inventario) {
        boolean respuesta=false;
        logger.trace(inventario.toString());
        try{
            logger.info(inventario.toString());
            em.merge(inventario);
            respuesta=true;
        }catch(Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;
    }

    @Transactional(readOnly = false)
    public boolean deleteInventario(int id) {
        boolean respuesta=false;

        try{
            Inventario inventario = findInventario(id);
            em.remove(em.contains(inventario) ? inventario : em.merge(inventario));
            respuesta=true;
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            respuesta=false;
        }
        return respuesta;

    }

    @Override
    public Inventario findInventario(int id) {
        Inventario inventario = new Inventario();
        try{
            inventario= em.find(Inventario.class,id);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return inventario;

    }

    @Override
    public List<Inventario> findInventarioSalidas(String consecutivo, String idCliente, String descripcion, String caducidad) {
        List<Inventario> inventario = null;
        try {
            inventario = em.createQuery("select p from Inventario p WHERE p.cantidadInventario > 0 and p.idCliente="+idCliente+" and(p.consecutivo = '" + consecutivo + "' OR p.descripcion like '" + descripcion + "%' OR p.caducidad like '" + caducidad + "%')").getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        //List<DetallesRd> detalle = em.createQuery("select p from DetallesRd p WHERE consecutivo like '"+ consecutivo + "%' AND idCliente like '"+ idCliente +"%' AND descripcion like '"+ descripcion +"%' AND caducidad like '"+ caducidad +"%'").getResultList();
        return inventario;
    }

    @Override
    @Transactional (propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = true)
    public List<Inventarioview> findInventarioReporte(String consecutivo, String camara, String idCliente, String claveProducto, String tunel) {
        List<Inventarioview> inventario = null;
        try {
            String query = "select p from Inventarioview p WHERE 1 = 1 ";
                    if(!consecutivo.trim().equalsIgnoreCase("")) query+="AND p.consecutivo = '" + consecutivo + "' ";
            if(!idCliente.trim().equalsIgnoreCase("")) query+="AND p.idCliente = '" + idCliente + "' ";
            if(!camara.trim().equalsIgnoreCase("")) query+="AND p.camara = '" + camara+ "' ";
            if(!claveProducto.trim().equalsIgnoreCase("")) query+="AND p.claveProducto like '" + claveProducto + "%'";
            if(!tunel.trim().equalsIgnoreCase("")) query+="AND p.camara = '" + tunel + "' ";
            query+="AND p.cantidadInventario>0";
            inventario = em.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        //List<DetallesRd> detalle = em.createQuery("select p from DetallesRd p WHERE consecutivo like '"+ consecutivo + "%' AND idCliente like '"+ idCliente +"%' AND descripcion like '"+ descripcion +"%' AND caducidad like '"+ caducidad +"%'").getResultList();
        return inventario;
    }

    @Override
    public List<Inventario> findInventarioReporte2(String consecutivo, String camara, String idCliente, String claveProducto, String tunel) {
        List<Inventario> inventario = null;
        try {
            String query = "select p from Inventario p WHERE 1 = 1 ";
            if(!consecutivo.trim().equalsIgnoreCase("")) query+="AND p.consecutivo = '" + consecutivo + "' ";
            if(!idCliente.trim().equalsIgnoreCase("")) query+="AND p.idCliente = '" + idCliente + "' ";
            if(!camara.trim().equalsIgnoreCase("")) query+="AND p.camara = '" + camara+ "' ";
            if(!claveProducto.trim().equalsIgnoreCase("")) query+="AND p.claveProducto like '" + claveProducto + "%'";
            if(!tunel.trim().equalsIgnoreCase("")) query+="AND p.camara = '" + tunel + "' ";
            query+="AND p.cantidadInventario>0 ORDER BY p.consecutivo,p.renglon";
            inventario = em.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        //List<DetallesRd> detalle = em.createQuery("select p from DetallesRd p WHERE consecutivo like '"+ consecutivo + "%' AND idCliente like '"+ idCliente +"%' AND descripcion like '"+ descripcion +"%' AND caducidad like '"+ caducidad +"%'").getResultList();
        return inventario;
    }

    @Override
    public List<Integer> findInventarioReporteRD(String consecutivo, String camara, String idCliente, String claveProducto, String tunel) {
        List<Integer> inventario = null;
        try {
            String query = "select sum((p.pesou*p.cantidadInventario)1000) from Inventarioview p WHERE 1 = 1 ";
            if(!consecutivo.trim().equalsIgnoreCase("")) query+="AND p.consecutivo = '" + consecutivo + "' ";
            if(!idCliente.trim().equalsIgnoreCase("")) query+="AND p.idCliente = '" + idCliente + "' ";
            if(!camara.trim().equalsIgnoreCase("")) query+="AND p.camara = '" + camara+ "' ";
            if(!claveProducto.trim().equalsIgnoreCase("")) query+="AND p.claveProducto like '" + claveProducto + "%'";
            if(!tunel.trim().equalsIgnoreCase("")) query+="AND p.camara = '" + tunel + "' ";
            query+="AND p.cantidadInventario>0";
            inventario = em.createQuery(query).getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getCause());
        }
        //List<DetallesRd> detalle = em.createQuery("select p from DetallesRd p WHERE consecutivo like '"+ consecutivo + "%' AND idCliente like '"+ idCliente +"%' AND descripcion like '"+ descripcion +"%' AND caducidad like '"+ caducidad +"%'").getResultList();
        return inventario;
    }

    @Override
    public Inventario findByClienteConsecutivo(String idCliente, int consecutivo) {
        Inventario inventario = new Inventario();
        try {
        Query query = em.createNamedQuery("Inventario.getByClienteConsecutivo");
        query.setParameter("idCliente", idCliente);
        query.setParameter("consecutivo", consecutivo);
        query.setFirstResult(0);
        query.setMaxResults(1);
        inventario = (Inventario) query.getSingleResult();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
        }
        return inventario;
    }

    @Override
    public Inventario findByClienteFolioSalida(String idCliente, int folioSalida) {
        return null;
    }

    @Override
    public List<Inventario> findByConsecutivo(int consecutivo) {
        List<Inventario> inventarios = new ArrayList<Inventario>();
        try {
            Query query = em.createNamedQuery("Inventario.getByConsecutivo");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage() + e.getCause());
            return inventarios;
        }
    }
    
    @Override
    public List<Inventario> getArrastreSaldosInventarioAgrupado(int consecutivo) {
        try {
            final Query query = em.createNamedQuery("Inventario.getArrastreSaldosInventarioAgrupado");
            query.setParameter("consecutivo", consecutivo);
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<Inventario>();
        }
    }

    @Override
    public List<Object[]> getSaldoXCamara() {
        try {
            final Query query = em.createNamedQuery("Inventario.getSaldoXCamara");
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getSaldoXTunel() {
        try {
            final Query query = em.createNamedQuery("Inventario.getSaldoXTunel");
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getSaldoXCliente() {
        try {
            final Query query = em.createNamedQuery("Inventario.getSaldoXCliente");
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }

    @Override
    public List<Object[]> getTodasCamaras() {
        try {
            final Query query = em.createNamedQuery("Inventario.getTodasCamaras");
            List<Object[]> res = query.getResultList();
            return res;
        }catch (NoResultException e){
            return new ArrayList<Object[]>();
        }
    }
    @Override
    public List<Inventario> getCitroFrut(String[] consecutivo){
        try{
            String query = "SELECT i FROM Inventario i WHERE consecutivo IN(";
            int i=0;
            int aux = consecutivo.length;
            while(i<consecutivo.length){
                if(i==(aux-1)){
                    query += consecutivo[i];
                }else {
                    query += consecutivo[i] + ",";
                }
                i++;
            }
            query+=") ORDER BY consecutivo";
            return em.createQuery(query).getResultList();
        }catch (Exception e){
            logger.error("Error al traer los inventarios de Citrofrut"+e);
            return new ArrayList<Inventario>();
        }
    }

    @Override
    public List<Object> getSaldosTotales(){
        try{
           Query query = em.createNamedQuery("Inventario.getSaldosTotales");
            return query.getResultList();
        }catch (Exception e){
            logger.error("Erro al traer los saldos totales del inventario");
            return new ArrayList<Object>();
        }
    }

    @Override
    public List<Object> getRDContinuidades(String idcliente, Date fechaini, Date fechafin){
        try{

            Query query = em.createNativeQuery("SELECT d.idCliente as cliente, d.consecutivo as rd , date_format(det.FECHAFINPER, '%Y-%m-%d') + INTERVAL 1 DAY as iniper," +
                    "CASE WHEN c.periodo=30 THEN date_format(det.FECHAFINPER, '%Y-%m-%d') + INTERVAL 1 MONTH " +
                    "WHEN DATE_FORMAT(LAST_DAY(curdate()),'%d') = '31' and c.periodo = 15 and det.FECHAFINPER >16 THEN DATE_ADD(date_format(det.FECHAFINPER, '%Y-%m-%d'),INTERVAL c.periodo DAY)" +
                    "WHEN DATE_FORMAT(LAST_DAY(curdate()),'%d') = '31' and c.periodo = 20 and det.FECHAFINPER >11 THEN DATE_ADD(date_format(det.FECHAFINPER, '%Y-%m-%d'),INTERVAL c.periodo DAY) " +
                    "ELSE DATE_ADD(date_format(det.FECHAFINPER, '%Y-%m-%d'), INTERVAL c.periodo DAY) END as finper, d.camara as camara, round(sum(d.cantidadInventario * d.pesou),2)as kilos" +
                    ",'ALMC' as servicio,c.cuotaAlmacenaje as cuota, round(sum(d.cantidadInventario * d.pesou)*c.cuotaAlmacenaje,2) as subtotal," +
                    "round(sum(d.cantidadInventario * d.pesou)*c.cuotaAlmacenaje,2) as total, '',curdate() FROM inventarios d, convenios c, detalle_facturacion det " +
                    "WHERE d.consecutivo=det.RD and c.claveProducto=d.claveProducto and d.cantidadInventario>0 and det.TIPOSERVICIO in ('ALM','ALMC')  and date(det.FECHAFINPER) >= :fechaini and date(det.FECHAFINPER) <= :fechafin  " +
                    "and d.idCliente in ( "+idcliente+" ) and d.idCliente not in (341,428,536,109,116,194,196,394,395,187,314,333,258,387,396,393) GROUP BY d.consecutivo");
            //query.setParameter("idcliente", idcliente);
            query.setParameter("fechaini", fechaini , TemporalType.DATE);
            query.setParameter("fechafin", fechafin, TemporalType.DATE);

            return query.getResultList();
            //return em.createNativeQuery(query).getResultList();
        }catch (Exception e){
            logger.error("Error al traer las continuidades"+e.getStackTrace()+e.getMessage()+e.getCause());
            return new ArrayList<Object>();
        }
    }

    @Override
    public List<Object> getInventarioTif(int idcliente){
        try{
            Query query = em.createNativeQuery("SELECT " +
                    "r.consecutivo, " +
                    "v.idCliente, " +
                    "i.fechaCaptura, " +
                    "SUM(i.cantidadInventario) AS piezas, " +
                    "ROUND(" +
                    "SUM(i.cantidadInventario * pesou) " +
                    ") AS kilos, " +
                    "CASE " +
                    "WHEN v.idFleje = 2002 THEN " +
                    "'TIF' " +
                    "ELSE " +
                    "'NO TIF' " +
                    "END AS entroComo, " +
                    " i.camara " +
                    "FROM " +
                    "inventarios i, " +
                    "regentradas r, " +
                    "vehiculo v, " +
                    "cat_general c " +
                    "WHERE " +
                    "i.consecutivo = r.consecutivo " +
                    "AND r.idIngresoVehiculo = v.idIngresoVehiculo " +
                    "AND v.idFleje = c.idCatalogo " +
                    "AND i.idCliente ="+idcliente+" "+
                    "AND i.cantidadInventario > 0 " +
                    "GROUP BY " +
                    "i.consecutivo; ");
            return query.getResultList();
        }catch (Exception e){
            logger.error("Error al traer el inventario por estatus tif");
            e.printStackTrace();
            return new ArrayList<Object>();
        }
    }
}
