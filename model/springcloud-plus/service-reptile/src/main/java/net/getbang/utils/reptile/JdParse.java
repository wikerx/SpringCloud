package net.getbang.utils.reptile;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.getbang.ClientTest;
import net.getbang.entity.Product;

public class JdParse {

	 static final Log logger = LogFactory.getLog(JdParse.class);
	
	 public static List<Product> getData (String html) throws Exception{
		 
		//获取的数据，存放在集合中
	        List<Product> data = new ArrayList<Product>();
	        //采用Jsoup解析
	        Document doc = Jsoup.parse(html); 
	        
	        //获取html标签中的内容
	        Elements elements=doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
	       
	        
	        
	     
	        //类型
	        Elements elementsbrand=doc.select("ul[class=J_valueList]").select("li");
	        
	        
	        
	        logger.info("elementsbrand="+elementsbrand);
	        for (Element element : elementsbrand) {
				
	        	String brandName= element.select("a").text();
	        	logger.info("brandName==="+brandName);
			}
	        
	        //类型
	        Elements elementsbrand2=doc.select("ul[class=av-collapse row-2]").select("li");
	       
	        
	        List<String>  strList =new ArrayList<String>();
	        
	        strList.add("ul[class=av-collapse row-2]");
	        strList.add("li");
	        
	       
	        
	       // Elements elementsbrand2 =doc;
	        for (Element element : elementsbrand2) {
	        	
	        	String brandName= element.select("a").text();
	        	logger.info("天猫冰箱品牌列表："+brandName);
	        }
	        
	        
	        Elements elementstype=doc.select("ul[class=J_valueList v-fixed]").select("li");
	        logger.info("elementstype="+elementstype);
	        for (Element element : elementstype) {
				
	        	String typename= element.select("a").text();
	        	logger.info("brandName="+typename);
			}
	        
	        
	        for (Element ele:elements) {
	            String bookID=ele.attr("data-sku");
	            String bookPrice=ele.select("div[class=p-price]").select("strong").select("i").text();
	            String bookName=ele.select("div[class=p-name]").select("em").text();
	            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
	            Product jdModel=new Product();
	            //对象的值
	            
	            jdModel.setTitle(bookName);
	            jdModel.setPrice(Double.valueOf(bookPrice));
	            //将每一个对象的值，保存到List集合中
	            data.add(jdModel);
	        }
			return data;
	        
	        
		 
	 }
}
