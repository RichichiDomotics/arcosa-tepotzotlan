package com.cbj.almacen.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idproduct")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idproduct;

    private String name;
    private Double price;

    public Integer getIdProduct() {
	return idproduct;
    }

    public void setIdproduct(Integer idproduct) {
	this.idproduct = idproduct;
    }

    public String getDescription() {
	return name;
    }

    public void setDescription(String name) {
	this.name = name;
    }

    public Double getPrice() {
	return price;
    }

    public void setPrice(Double price) {
	this.price = price;
    }

    public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("Name: " + name + ";");
	buffer.append("Price: " + price);
	return buffer.toString();
    }
}