package com.cyz.po;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    /*关系维护*/
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs=new ArrayList<>();
}
