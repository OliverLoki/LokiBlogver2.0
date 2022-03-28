package com.loki.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loki.dto.Result;
import com.loki.pojo.Blog;
import com.loki.pojo.Comment;
import com.loki.service.BlogService;
import com.loki.service.CommentService;
import com.loki.service.EditBlogService;
import com.loki.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CommentController {
    @Autowired
    BlogService blogService;

    @Autowired
    CommentService commentService;
    //根据博客id获取所有评论
    @PostMapping("/getCommentByBlogId/{id}")
    public Result getCommentByBlogId(@PathVariable(value = "id") Integer id) {
        List<Comment> list = commentService.list(new QueryWrapper<Comment>().eq("blog_id",id));
        return Result.succ(200,"请求成功",list);
    }


}
