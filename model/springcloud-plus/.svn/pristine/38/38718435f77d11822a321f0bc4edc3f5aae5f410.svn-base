package net.getbang.controller.solr;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.getbang.entity.solr.Product;
import net.getbang.service.solr.IProductSolrService;

@RestController
@RequestMapping("solr")
public class SolrController {
	@Autowired
    private SolrClient client;
	 @RequestMapping("add")
	    public String add() {
	        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	        try {
	            SolrInputDocument doc = new SolrInputDocument();
	            doc.setField("id", uuid);
	            doc.setField("content_ik", "我是中国人, 我爱中国");

	            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
	             * 下面都是一样的
	             */

	            client.add(doc);
	            client.commit();
	           // client.commit("collection1");
	            return uuid;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return "error";
	    }

	@Autowired
	private IProductSolrService iProductSolrService;
	@RequestMapping(value="addProduct",method = RequestMethod.GET)
	public String addProduct(HttpServletRequest request ) {
		HttpHeaders headers = new HttpHeaders();
		
		System.out.println(request.getContentType());
		request.getContentType();
		
	
			
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			Product product =new Product();
			
			product.setId(uuid);
			
			product.setTitle("张3");
			
			iProductSolrService.saveProduct(product);
			
		
		return "ok";
		
	}
}
