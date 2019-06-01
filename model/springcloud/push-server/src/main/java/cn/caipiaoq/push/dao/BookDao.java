package cn.caipiaoq.push.dao;

import cn.caipiaoq.push.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface BookDao extends ElasticsearchRepository<BookEntity, String> {

    public Optional<BookEntity> findById(String id);

    public List<BookEntity> getByMessage(String key);

    // >= spring boot 2.0
    public Page<BookEntity> getByMessage(String key, Pageable pageable);
    // < spring boot 2.0
    //public Page<BookEntity> getByMessage(String key, PageRequest pageRequest);
}
