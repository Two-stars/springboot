package com.hebeu.springboot.Mapper;

import com.hebeu.springboot.Pojo.Detail;
import com.hebeu.springboot.Pojo.User;
import com.hebeu.springboot.Pojo.UserfindByMul;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
@Mapper
public interface UserMapper {
    //注册
    public  void insertUser(User user) throws SQLException;
    //检查
    public User  check(String user);
    //登录
    public User findbyuserandpassword(User user);
    //按不同条件查询
    //全部查询
    public List<User> findAll();
    //条件查询，神奇的sql语句
    public List<User> findByMul(UserfindByMul us);
    //删除
    public void deleteUser(String user)  throws SQLException;
    //更新
    public void updateUser(User user) throws SQLException;
    //建表
    public void creat();
    //finddetailbyuser
    public List<Detail> finddetailbyuser();
}
