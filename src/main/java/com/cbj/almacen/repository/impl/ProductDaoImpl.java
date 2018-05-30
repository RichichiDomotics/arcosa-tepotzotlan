package com.cbj.almacen.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cbj.almacen.domain.Product;
import com.cbj.almacen.repository.ProductDao;

@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
	this.em = em;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Product> getProductList() {
	return em.createQuery("select p from Product p order by p.idproduct")
		.getResultList();
    }

    @Transactional(readOnly = false)
    public void saveProduct(Product prod) {
	em.merge(prod);
    }

}
