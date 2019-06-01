package net.getbang.service.solr;

import org.springframework.data.domain.Page;

import net.getbang.entity.solr.Product;

public interface IProductSolrService {

	
	Page<Product> productPage(String name);
	
	
	void saveProduct(Product product);
}
