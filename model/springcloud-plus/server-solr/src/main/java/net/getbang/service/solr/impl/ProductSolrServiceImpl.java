package net.getbang.service.solr.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.getbang.dao.solr.ProductSolrRepository;
import net.getbang.entity.solr.Product;
import net.getbang.service.solr.IProductSolrService;

@Service
public class ProductSolrServiceImpl implements IProductSolrService{

	private Logger logger = LoggerFactory.getLogger(ProductSolrServiceImpl.class);

	@Autowired
	private ProductSolrRepository productSolrRepository;
	@Override
	public Page<Product> productPage(String name) {
		// TODO Auto-generated method stub
		
		return productSolrRepository.findAll(new PageRequest(1, 20));
	}
	@Override
	public void saveProduct(Product product) {
		 productSolrRepository.save(product);
	}    
	
	
  
    
	
}
