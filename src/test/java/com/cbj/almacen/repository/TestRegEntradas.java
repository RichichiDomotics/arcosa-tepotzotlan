package com.cbj.almacen.repository;

import com.cbj.almacen.domain.RegEntradas;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by bvelasco on 17/05/2014.
 */
public class TestRegEntradas {

    private static final Logger logger = LoggerFactory
            .getLogger(TestRegEntradas.class);

    private ApplicationContext context;
    private RegEntradasDao regEntradasDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(
                "classpath:test-context.xml");
        regEntradasDao = (RegEntradasDao) context.getBean("regEntradasDao");
    }

    @Test
    public void testInsertDetalleRd() {
        RegEntradas entradas = new RegEntradas();
        entradas.setIdCliente("1");
        entradas.setClienteCantidad(new Float("7"));
        entradas.setAnden("4");
        regEntradasDao.insertRegistroRegEntradas(entradas);
    }

    @Test
    public void testFindDetalleRd() {
    	RegEntradas entradas = regEntradasDao.findRegistroRegEntradas(8744);
        logger.info(entradas.getConsecutivo().toString());
        assert(entradas.getConsecutivo()>0);
    }

    @Test
    public void testUpdateDetalleRd() {
    	RegEntradas entradas = regEntradasDao.findRegistroRegEntradas(8744);
    	entradas.setAnden("9");
        regEntradasDao.updateRegistroRegEntradas(entradas);
        logger.info(entradas.getAnden().toString());
        assert(!entradas.getAnden().equals(""));
    }

    @Test
    public void testDeleteDetalleRd() {
        boolean exito = regEntradasDao.deleteRegistroRegEntradas(33104);
        assert(exito);
    }

    @Test
    public void testgetAllByActualDate() {
        List<RegEntradas> entradas = regEntradasDao.getAllByActualDate();
        logger.info("entradas "+entradas.size());
    }

}
