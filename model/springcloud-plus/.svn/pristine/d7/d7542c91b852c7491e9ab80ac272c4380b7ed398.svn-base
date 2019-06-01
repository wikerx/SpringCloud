package solrtest;

import org.apache.solr.client.solrj.SolrClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.TermsOptions.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alibaba.fastjson.JSON;

import net.getbang.ServerSolrApplication;
import net.getbang.dao.solr.ProductSolrRepository;
import net.getbang.entity.solr.Product;
import net.getbang.service.solr.IProductSolrService;

import java.util.UUID;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration  
@AutoConfigureMockMvc
@SpringBootTest(classes=ServerSolrApplication.class)  
public class SolrTest {

	@Autowired
	private IProductSolrService iProductSolrService;
	
	
	@Autowired
	private ProductSolrRepository productSolrRepository;
	
	@Autowired
    private SolrClient client;
	@Test
	public void productPage() {
	Page<Product>	page = 
			productSolrRepository.findAll(new PageRequest(0, 5));
		
		System.out.println(JSON.toJSON(page));
	}
	
	

	@Test
	public void addProduct()  {
		
		

	    
		for (int i =10; i < 60; i++) {
			
			
			Product product =new Product();
			
			product.setId("110"+i);
			
			product.setTitle("张"+i);
			
			productSolrRepository.save(product);
			
		}
	     
		
	}
}
