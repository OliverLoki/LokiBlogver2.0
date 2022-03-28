package com.loki.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
public class Comment {

    private long commId;
    private String commName;
    private String commContent;
    @TableField(value = "gmt_create")
    private Date gmt_create;
    private long blogId;


}
