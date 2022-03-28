package com.loki.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loki.dto.Result;
import com.loki.pojo.Blog;
import com.loki.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class TypeController {
 /*   @Autowired
    TypeService typeService;
    //获取所有标签
    @PostMapping("/getAllTags")
    public Result getAllTags(Long id){
        Blog b = (Blog) typeService.list();
        return Result.succ(200,"请求博客详情成功",b);
    }
*/
}
