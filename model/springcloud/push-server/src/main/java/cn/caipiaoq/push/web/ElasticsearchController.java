package cn.caipiaoq.push.web;

import cn.caipiaoq.push.dao.BookDao;
import cn.caipiaoq.push.entity.BookEntity;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/es")
public class ElasticsearchController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchController.class);

    @Autowired
    BookDao bookDao;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/book/add")
    public BookEntity add(BookEntity bookEntity){
        LOGGER.info("/book/add :{}",JSON.toJSONString(bookEntity));
        return bookDao.save(bookEntity);
    }

    @DeleteMapping("/book/delete/{id}")
    public BookEntity delete(@PathVariable String id){
        BookEntity bookEntity = bookDao.findOne(id);
        LOGGER.info("/book/delete/id :{}, bookEntity:{}",id, JSON.toJSONString(bookEntity));
        bookDao.delete(id);
        return bookEntity;
    }

    @PutMapping("/book/update")
    public BookEntity update(BookEntity bookEntity) {
        LOGGER.info("/book/update bookEntity:{}", JSON.toJSONString(bookEntity));
        return bookDao.save(bookEntity);
    }

    @GetMapping("/book/{id}")
    public BookEntity getBookById(@PathVariable String id) {
        LOGGER.info("/book/{}", id);
        Optional<BookEntity> opt = bookDao.findById(id);
        BookEntity book = opt.get();
        return book;
    }

    @GetMapping("/book/get/{id}")
    public BookEntity getBookById2(@PathVariable String id) {
        LOGGER.info("/book/{}", id);
        return bookDao.findOne(id);
    }

    @GetMapping("/book/search/{key}")
    public List<BookEntity> search(@PathVariable String key){
        LOGGER.info("/book/search/{}", key);
        List<BookEntity> list = bookDao.getByMessage(key);
        return list;
    }

    @GetMapping("/book/select/{q}")
    public List<BookEntity> searchQ(@PathVariable String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<BookEntity> search = bookDao.search(builder);
        Iterator<BookEntity> iterator = search.iterator();
        List<BookEntity> list = new ArrayList<BookEntity>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * 分页、分数、分域（结果一个也不少）
     */
    @GetMapping("/{page}/{size}/{q}")
    public List<BookEntity> searchPage(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String q) {
        Pageable pageable = new PageRequest(page, size);

        // 分数，并自动按分排序
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", q)),
                        ScoreFunctionBuilders.weightFactorFunction(1000)) // 权重：name 1000分
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("message", q)),
                        ScoreFunctionBuilders.weightFactorFunction(100)); // 权重：message 100分

        // 分数、分页
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();

        Page<BookEntity> searchPageResults = bookDao.search(searchQuery);
        return searchPageResults.getContent();

    }

    @GetMapping("/book/search/{key}/{page}")
    public List<BookEntity> searchMessagePage(@PathVariable String key, @PathVariable int page){
        int numberOfPage = 5;
        PageRequest request = new PageRequest(page,numberOfPage);
        Page<BookEntity> pages = bookDao.getByMessage(key,request);
        long totalElements = pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        List<BookEntity> list = pages.getContent();
        LOGGER.info("/book/search/{}/{} ,totalElements:{}, totalPages:{}",key, page,totalElements, totalPages);
        LOGGER.info("list:{}", JSON.toJSONString(list));
        return list;
    }

    @GetMapping("/book/all")
    public List<Map<String, Object>> searchAll() throws Exception {
        //这一步是最关键的
        Client client = elasticsearchTemplate.getClient();
        // @Document(indexName = "product", type = "book")
        SearchRequestBuilder srb = client.prepareSearch("product").setTypes("book");
        SearchResponse sr = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet(); // 查询所有
        SearchHits hits = sr.getHits();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSource();
            list.add(source);
            System.out.println(hit.getSourceAsString());
        }
        return list;
    }

}
