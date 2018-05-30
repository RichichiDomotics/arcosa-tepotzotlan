package com.cbj.almacen.repository;

import com.cbj.almacen.domain.Inventario;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jolvera on 17/05/2014.
 */
public class TestInventario {

    private static final Logger logger = LoggerFactory
            .getLogger(TestInventario.class);

    private ApplicationContext context;

    private InventarioDao inventarioDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(
                "classpath:test-context.xml");
        inventarioDao = (InventarioDao) context.getBean("inventarioDao");
    }

    @Test
    public void testInsertInventario() {
        Inventario inventario = new Inventario();
        inventario.setConsecutivo(1);
        inventario.setIdCliente("1");
        inventario.setCantidadSalida("1");
        inventario.setPesou(new Double("1"));
        inventarioDao.insertInventario(inventario);
    }

    @Test
    public void testUpdateInventario() {
        Inventario inventario = new Inventario();
        inventario.setConsecutivo(1);
        inventario.setIdCliente("1");
        inventario.setRenglon(new Double("1"));
        inventario.setCantidadSalida("1");
        inventario.setPesou(new Double("1"));
        inventarioDao.updateInventario(inventario);
    }

    @Test
    public void testFindInventario() {
        int idInventario = 1;
        Inventario inventario = inventarioDao.findInventario(idInventario);
        assert(inventario.getConsecutivo()==idInventario);
    }

    @Test
    public void testDeleteInventario() {
        int idInventario = 1;
        assert(inventarioDao.deleteInventario(idInventario));
    }
}
