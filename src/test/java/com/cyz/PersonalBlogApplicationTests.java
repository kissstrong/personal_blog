package com.cyz;

import com.cyz.dao.BlogsDao;
import com.cyz.dao.TagsDao;
import com.cyz.po.Blog;
import com.cyz.po.Tag;
import com.cyz.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class PersonalBlogApplicationTests {
 @Autowired
    private TagsDao tagsDao;
 @Autowired
 private BlogsDao blogsDao;
    @Test
    void contextLoads() {
    }

}
