package com.loki.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName("blog")
public class EditBlog {
    @NotBlank(message = "标题不能为空")
    private String blogTitle;
    @NotBlank(message = "内容不能为空")
    private String blogContent;
    @NotBlank(message = "文章描述不能为空")
    private String blogDesc;
    private String typeId;
    private long userId;
}
