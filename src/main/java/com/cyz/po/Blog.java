package com.cyz.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    /*标题*/
    private String title;
    /*内容*/
    private String content;
    /*首图*/
    private String firstPicture;
    /*描述*/
    private String description;
      /*类型转载*/
    private String flag;
    /*浏览次数*/
    private Integer views;
    /*赞赏开启*/
    private boolean appreciation;
    /*版权开启*/
    private boolean shareStatement;
    /*评论开启*/
    private boolean commentabled;
    /*发布*/
    private boolean published;
    /*是否推荐*/
    private boolean recommend;
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;
    /*关系维护*/
    @ManyToOne
    private Type type;
    /*关系维护*/
    @ManyToOne/*级联新增*/
    private Tag tag;
    /*关系维护*/
    @ManyToOne
    private User user;
    /*关系维护*/
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments=new ArrayList<>();
}
