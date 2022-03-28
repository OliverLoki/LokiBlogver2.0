package com.loki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loki.dto.EditBlog;
import com.loki.mapper.EditBlogMapper;
import com.loki.service.EditBlogService;
import org.springframework.stereotype.Service;

@Service
public class EditblogServiceImpl extends ServiceImpl<EditBlogMapper,EditBlog> implements EditBlogService {

}
