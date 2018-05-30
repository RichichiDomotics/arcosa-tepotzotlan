package com.cbj.almacen.repository;

import com.cbj.almacen.domain.DetallesRd;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jolvera on 17/05/2014.
 */
public class TestDetalleRd {

    private static final Logger logger = LoggerFactory
            .getLogger(TestDetalleRd.class);

    private ApplicationContext context;
    private DetalleRdDao detalleRdDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(
                "classpath:test-context.xml");
        detalleRdDao = (DetalleRdDao) context.getBean("detalleRdDao");
    }

    @Test
    public void testInsertDetalleRd() {
        DetallesRd detalle = new DetallesRd();
        detalle.setConsecutivo(1);
        detalle.setIdCliente("1");
        detalle.setCantidad(new Double(1.0));
        detalle.setPesou(new Double("1"));
        detalleRdDao.insertRegistroDetallesRd(detalle);
    }

    @Test
    public void testFindDetalleRd() {
        DetallesRd detallesRd = detalleRdDao.findRegistroDetallesRd(8744);
        logger.info(detallesRd.getIdIngresoVehiculo().toString());
        assert(detallesRd.getIdIngresoVehiculo()>0);
    }

    @Test
    public void testUpdateDetalleRd() {
        DetallesRd detallesRd = detalleRdDao.findRegistroDetallesRd(8744);
        detallesRd.setPesoTotal(new Double(1.1));
        detalleRdDao.updateRegistroDetallesRd(detallesRd);
        logger.info(detallesRd.getPesoTotal().toString());
        assert(detallesRd.getIdIngresoVehiculo()>0);
    }

    @Test
    public void testDeleteDetalleRd() {
        boolean exito = detalleRdDao.deleteRegistroDetallesRd(8744);
        assert(exito);
    }
}
