package com.wesdm.springcache.service;

import com.wesdm.springcache.model.Product;

public interface ProductService {
	 
    Product getByName(String name);
    void refreshAllProducts();
    Product updateProduct(Product product);
     
}
