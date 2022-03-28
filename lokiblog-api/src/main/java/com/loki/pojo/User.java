package com.loki.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @TableId("user_id")
  private long userId;
  private String role;
  private String username;
  private String password;
  @Email(message = "邮箱格式不正确")
  private String email;
  private String github;
  private String avatar;

}
