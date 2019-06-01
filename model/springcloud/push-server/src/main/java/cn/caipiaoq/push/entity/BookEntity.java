package cn.caipiaoq.push.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;


@Document(indexName = "product", type = "book")
@Getter
@Setter
public class BookEntity {

    @Id
    private String id;
    private String name;
    private String message;
    private Date postDate;
    private String type;
}
