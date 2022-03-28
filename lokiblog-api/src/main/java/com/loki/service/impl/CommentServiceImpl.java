package com.loki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loki.mapper.CommentMapper;
import com.loki.pojo.Comment;
import com.loki.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService  {

}
