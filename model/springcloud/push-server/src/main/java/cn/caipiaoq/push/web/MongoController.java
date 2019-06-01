package cn.caipiaoq.push.web;

import cn.caipiaoq.push.entity.Baike;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RestController
@RequestMapping("baike")
public class MongoController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(MongoController.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/add")
    public Baike add(Baike baike){
        baike.setCreateDate(new Date());
        LOGGER.info("add baike :{}",baike);
        mongoTemplate.insert(baike);
        return baike;
    }

    @GetMapping("/delete")
    public Baike delete(String id){
        LOGGER.info("delete baike id:{}",id);
        Baike baike = new Baike();
        baike.setId(id);
        mongoTemplate.remove(baike);
        return baike;
    }

    @GetMapping("/update/tag/{tag}")
    public String addOne(@PathVariable String tag){
        Criteria criteria = where("tag").in(tag);
        Update update = new Update();
        update.inc("comment.good",1);
        WriteResult result = mongoTemplate.updateMulti(query(criteria), update, Baike.class);
        return "成功修改"+result.getN();
    }

    @GetMapping("/findUser/{name}")
    public Baike findUser(@PathVariable String name){
       Baike baike = mongoTemplate.findById(name, Baike.class);
       return baike;
    }

    @GetMapping("/querybad/{bad}")
    public List<Baike> queryBad(@PathVariable int bad){
        Criteria criteria = where("comment.bad").gt(bad);
        List<Baike> baikes = mongoTemplate.find(query(criteria), Baike.class);
        return baikes;
    }

    @GetMapping("/baike/tag/{tag}/{pageNum}")
    public List<Baike> findBaike(@PathVariable String tag, @PathVariable int pageNum){
        Criteria criteria = where("tag").in(tag);
        Query query = query(criteria);
        //查询总数
        long totalCount = mongoTemplate.count(query, Baike.class);
        int numOfPage = 10;
        //总页数
        long totalPage = totalCount%numOfPage==0?(totalCount/numOfPage):(totalCount/numOfPage+1);
        int skip = (pageNum-1) * numOfPage;
        query.skip(skip).limit(numOfPage);
        List<Baike> baikes = mongoTemplate.find(query, Baike.class);
        return baikes;
    }
}
