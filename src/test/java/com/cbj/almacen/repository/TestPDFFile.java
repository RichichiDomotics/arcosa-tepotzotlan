package com.cbj.almacen.repository;

import com.cbj.almacen.domain.*;
import com.cbj.almacen.service.PdfEntradasManager;
import com.cbj.almacen.service.impl.PdfEntradasManagerImpl;
import com.cbj.almacen.web.EntradasController;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jolvera on 18/06/2014.
 */
public class TestPDFFile {

    private static final Logger logger = LoggerFactory
            .getLogger(TestInventario.class);

    private ApplicationContext context;

    private InventarioDao inventarioDao;
    private VehiculoDao vehiculoDao;
    private ClienteDao clienteDao;
    private RegEntradasDao regEntradasDao;
    private DetalleRdDao detalleRdDao;
    private PdfEntradasManager pdfEntradasManager;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(
                "classpath:test-context.xml");
        inventarioDao = (InventarioDao) context.getBean("inventarioDao");
        vehiculoDao = (VehiculoDao) context.getBean("vehiculoDao");
        clienteDao = (ClienteDao) context.getBean("clienteDao");
        regEntradasDao = (RegEntradasDao) context.getBean("regEntradasDao");
        detalleRdDao = (DetalleRdDao) context.getBean("detalleRdDao");
        pdfEntradasManager = (PdfEntradasManager) context.getBean("pdfEntradasManager");
    }

    @Test
    public void testCreaPdf() {
        pdfEntradasManager.generaPdfEntradas(33115);
    }

}
