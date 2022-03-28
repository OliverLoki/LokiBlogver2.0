package com.loki.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

  @TableId(type = IdType.AUTO)
  private long blogId;
  private String blogTitle;
  private String blogContent;
  private long blogCommentcount;
  private String blogDesc;
  private long blogEmojicount;
  private long typeId;
  private long userId;
  //字段自动填充
  @TableField(fill = FieldFill.INSERT)//在插入时更新数据
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date gmtCreate;
  @TableField(fill = FieldFill.INSERT_UPDATE)//在插入和更新时更新数据
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date gmtModified;


}
