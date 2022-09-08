package com.hebeu.springboot.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

//用护实体类，使用lombok简化实体类
@Data //getter setter toString
@AllArgsConstructor  //所有属性构造
@NoArgsConstructor   //无参构造
public class User {
    private String user;
    private String password;
    private String sex;
    private int    age;
    private String email;
    private String hobby;
    private Date time;
    private Detail detail;

    public User(String user, String password, String sex, int age, String email, String hobby, Date time) {
        super();
        this.user = user;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.hobby = hobby;
        this.time = time;
    }
    
}
