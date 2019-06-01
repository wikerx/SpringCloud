package cn.caipiaoq.push.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Baike {
    private String id;
    private String desc;
    private List<String> tag = new ArrayList<String>();

    private Comment comment = null;
    private Date createDate = null;
    private Date updateDate = null;
}
