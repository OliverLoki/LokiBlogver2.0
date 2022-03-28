package com.loki;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loki.dto.Result;
import com.loki.mapper.BlogMapper;
import com.loki.pojo.Blog;
import com.loki.service.BlogService;
import com.loki.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Arrays;


@SpringBootTest
class LokiblogSpringbootApplicationTests {
    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;
    @Autowired
    BlogMapper blogMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void getUserBlog() {
        //参数：当前页码，每页几条记录
        Page<Blog> bookPage = new Page<>(1, 5);
        Page<Blog> page = blogService.page(bookPage, new QueryWrapper<Blog>().eq("user_id",2));

        System.out.println(page.getRecords());
    }

    @Test
    public void testInsert(){
        Blog blog = new Blog();
        blog.setBlogCommentcount(1);
        blog.setBlogContent("## 这是博客");
        blog.setBlogDesc("hhh");
        blog.setBlogEmojicount(1);
        blog.setTypeId(1);
        blog.setUserId(2);
        blog.setBlogTitle("标题");
        int insert = blogMapper.insert(blog);
        System.out.println(insert);
        System.out.println(blog);
    }

    @Test
    public void testUpdate(){
        //通过id删除
        blogMapper.deleteById(1);
        //批量删除
        blogMapper.deleteBatchIds(Arrays.asList(1,2,3));
        //逻辑删除



    }
}
