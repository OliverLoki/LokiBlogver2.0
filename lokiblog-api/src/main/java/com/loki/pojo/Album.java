package com.loki.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {

  private long picId;
  private String picAuth;
  private String picContent;
  private String picEmail;

}
