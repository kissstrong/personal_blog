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
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String nickName;
    private String userName;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    /*关系维护*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /*关系维护*/
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    /*关系维护*/
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs=new ArrayList<>();

}
