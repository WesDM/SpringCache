package com.wesdm.springcache.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.wesdm.springcache.model.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService{
 
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
     
    private static List<Product> products;
     
    static{
        products = getDummyProducts();
    }
     
    @Override
    @Cacheable(value="products", key="#name", condition="#name!='HTC'" , unless="#result==null")
    public Product getByName(String name) {
        logger.info("<!----------Entering getByName()--------------------->");
        for(Product p : products){
            if(p.getName().equalsIgnoreCase(name))
                return p;
        }
        return null;
    }
 
 
    @CacheEvict(value = "products", allEntries = true)
    public void refreshAllProducts() {
        //This method will remove all 'products' from cache, say as a result of flush API.
    }
    
    @Caching(evict = {
            @CacheEvict(value = "products", key="#product.name"),
            @CacheEvict(value = "items"   , key = "#product.id")
    })
    public void refreshProduct(Product product) {
        //This method will remove only this specific product from 'products' & 'items' cache.
    } 
     
 
    @Override
    @CachePut(value = "products", key = "#product.name" , unless="#result==null")
    public Product updateProduct(Product product) {
        logger.info("<!----------Entering updateProduct ------------------->");
        for(Product p : products){
            if(p.getName().equalsIgnoreCase(product.getName()))
                p.setPrice(product.getPrice());
                return p;
        }
        return null;
    }
     
 
    private static List<Product> getDummyProducts(){
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("IPhone",500));
        products.add(new Product("Samsung",600));
        products.add(new Product("HTC",800));
        return products;
    }
 
}
