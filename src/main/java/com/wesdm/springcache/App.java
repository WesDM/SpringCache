package com.wesdm.springcache;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.wesdm.springcache.model.Product;
import com.wesdm.springcache.service.ProductService;


@SpringBootApplication
public class App {
    private static final Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);
 
		ProductService service = (ProductService) context.getBean("productService");

		logger.info("IPhone ->" + service.getByName("IPhone"));
		logger.info("IPhone ->" + service.getByName("IPhone"));
		logger.info("IPhone ->" + service.getByName("IPhone"));

		logger.info("HTC ->" + service.getByName("HTC"));
		logger.info("HTC ->" + service.getByName("HTC"));
		logger.info("HTC ->" + service.getByName("HTC"));

		Product product = new Product("IPhone", 550);
		service.updateProduct(product);

		logger.info("IPhone ->" + service.getByName("IPhone"));
		logger.info("IPhone ->" + service.getByName("IPhone"));
		logger.info("IPhone ->" + service.getByName("IPhone"));

		logger.info("Refreshing all products");

		service.refreshAllProducts();
		logger.info("IPhone [after refresh]->" + service.getByName("IPhone"));
		logger.info("IPhone [after refresh]->" + service.getByName("IPhone"));
		logger.info("IPhone [after refresh]->" + service.getByName("IPhone"));

		((AbstractApplicationContext) context).close();
	}
}