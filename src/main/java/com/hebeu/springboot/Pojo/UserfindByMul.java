package com.hebeu.springboot.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用护实体类，使用lombok简化实体类
@Data //getter setter toString
@AllArgsConstructor  //所有属性构造
@NoArgsConstructor   //无参构造
public class UserfindByMul extends User{
    private String minage;
    private String maxage;

    public UserfindByMul(String username,String sex,String minage, String maxage,String email) {
        super();
        this.minage = minage;
        this.maxage = maxage;
        setUser(username);
        setSex(sex);
        setEmail(email);

    }
}

