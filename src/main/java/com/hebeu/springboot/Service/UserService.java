package com.hebeu.springboot.Service;

import com.hebeu.springboot.Pojo.User;
import com.hebeu.springboot.Pojo.UserfindByMul;


import java.sql.SQLException;
import java.util.List;

public interface UserService {
    //业务----功能，登录，注册
    //中间层：上面连接控制层，下面是Dao

    //登录业务
    public User login(User user);

    //注册业务
    public int  save(User user);

    //全部查询业务
    public List findALL();

    //多条件查询业务
    public List findByMul(UserfindByMul userfindbymul);

    //删除业务
    public  void  deleteUser(String user) throws SQLException;

    //修改业务
    public  void  updateUser(User user) throws SQLException;

}
