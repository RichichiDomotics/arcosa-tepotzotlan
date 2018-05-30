package com.cbj.almacen.repository;

import java.util.List;

import com.cbj.almacen.domain.Product;

public interface ProductDao {

    public List<Product> getProductList();

    public void saveProduct(Product prod);

}
