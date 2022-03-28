package com.loki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loki.mapper.TypeMapper;
import com.loki.pojo.Type;
import com.loki.service.BlogService;
import com.loki.service.TypeService;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
