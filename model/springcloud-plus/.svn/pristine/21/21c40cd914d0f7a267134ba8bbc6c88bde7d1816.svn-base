package net.getbang;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import net.getbang.entity.Product;
import net.getbang.utils.reptile.URLFecter;

public class ClientTest {

	
	 static final Log logger = LogFactory.getLog(ClientTest.class);
	public static void main(String[] args) throws Exception {
		
		//初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
     //   String url="http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
       
        
        String tianmao ="https://list.tmall.com/search_product.htm?spm=a222t.7794920.fsnav.1.333c667c0gAjBH&cat=50918004&acm=lb-zebra-24139-328537.1003.4.455785&scm=1003.4.lb-zebra-24139-328537.OTHER_14458832193540_455785";
        
        //抓取的数据
        List<Product> bookdatas=URLFecter.URLParser(client, tianmao);
        //循环输出抓取的数据
        for (Product jd:bookdatas) {
        	logger.info("bookPrice:"+jd.getPrice()+"\t"+"bookName:"+jd.getTitle());
        }
	}
	
}
