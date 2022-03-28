package com.loki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loki.mapper.BlogMapper;
import com.loki.pojo.Blog;
import com.loki.service.BlogService;
import org.springframework.stereotype.Service;

@Service
public class BolgServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
