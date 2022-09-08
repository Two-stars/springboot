package com.hebeu.springboot.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用护实体类，使用lombok简化实体类
@Data //getter setter toString
@AllArgsConstructor  //所有属性构造
@NoArgsConstructor   //无参构造
public class Detail {
    private int id;
    private String user_id;
    private String detail;

    private User user;

    public Detail(int id, String user_id, String detail) {
        super();
        this.id = id;
        this.user_id = user_id;
        this.detail = detail;
    }
}
