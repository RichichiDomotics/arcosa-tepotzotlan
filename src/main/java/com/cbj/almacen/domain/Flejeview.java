package com.cbj.almacen.domain;

/**
 * Created by Richard on 28/08/2015.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "flejeview")
public class Flejeview implements  Serializable{
    @Id
    @Column(name = "idIngresoVehiculo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idIngresoVehiculo;
    private Integer idCliente;
    private String NOM_CLIENTE;
    private String consecutivo;
    private String fecha;

}
