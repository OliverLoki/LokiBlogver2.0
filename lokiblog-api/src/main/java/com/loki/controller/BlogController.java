package com.loki.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loki.dto.EditBlog;
import com.loki.dto.Result;
import com.loki.pojo.Blog;
import com.loki.service.BlogService;
import com.loki.service.EditBlogService;
import com.loki.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;
    @Autowired
    EditBlogService editBlogService;

    //获取所有博客(只有管理员可以操作)
    @PostMapping("/getAllBlog/{page}")
    public Result getAllBlog(@PathVariable(value = "page") Integer pn) {
        //参数：当前页码，每页几条记录
        Page<Blog> bookPage = new Page<>(pn, 6);
        Page<Blog> page = blogService.page(bookPage, null);
        return Result.succ(200,"请求成功",page);
    }
    //获取当前用户博客
    @PostMapping("/getUserBlog/{page}/{userId}")
    public Result getUserBlog(@PathVariable(value = "page") Integer pn,@PathVariable(value = "userId")Integer id) {
        //参数：当前页码，每页几条记录
        Page<Blog> bookPage = new Page<>(pn, 5);
        Page<Blog> page = blogService.page(bookPage, new QueryWrapper<Blog>().eq("user_id",id));
        return Result.succ(200,"请求成功",page);
    }

    //根据Id获取博客
    @PostMapping("/getBlogById/{blogId}")
    public Result getBlogById(@PathVariable(value = "blogId") Long id){
        Blog b = blogService.getOne(new QueryWrapper<Blog>().eq("blog_id", id));
        return Result.succ(200,"请求博客详情成功",b);
    }
    //根据Id删除博客
    @PostMapping("/deleteBlogById/{blogId}")
    public Result deleteBlogById(@PathVariable(value = "blogId") Long id){
        boolean b = blogService.removeById(id);
        return Result.succ(200,"删除博客成功",b);
    }

    //多选列表的删除
    //@PostMapping("/deleteBlogByIds/{blogIds}")
    public Result deleteBlogByIds(@PathVariable(value = "blogIds") Long id){
        boolean b = blogService.removeById(id);
        return Result.succ(200,"删除博客成功",b);
    }

    //获取要编辑博客的博客原信息
    @PostMapping("/editBlogById/{blogId}")
    public Result editBlogById(@PathVariable(value = "blogId")Integer id){
        EditBlog b = editBlogService.getOne(new QueryWrapper<EditBlog>().eq("blog_id", id));

        return Result.succ(200,"请求编辑成功",b);
    }

    //更新数据库数据
    @PostMapping("/updateBlog/{info}")
    public Result updateBlog(@PathVariable(value = "info") EditBlog e){


        return Result.succ(200,"更新成功","返回值为1");
    }








}
