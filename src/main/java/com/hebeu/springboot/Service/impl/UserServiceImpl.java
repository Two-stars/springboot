package com.hebeu.springboot.Service.impl;

import com.hebeu.springboot.Mapper.UserMapper;
import com.hebeu.springboot.Pojo.Detail;
import com.hebeu.springboot.Pojo.User;
import com.hebeu.springboot.Pojo.UserfindByMul;
import com.hebeu.springboot.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource(name="UserMapper")
    private UserMapper userDao;


    @Override
    public User login(User user) {//登录业务
        User user_sql=userDao.findbyuserandpassword(user);
        return user_sql;
    }

    @Override
    public int save(User user) {//注册业务
        //检查注册信息是否重复
        User user_qul1=userDao.check(user.getUser());
        //没有问题的输入
        int n=0;
        if(user_qul1==null) {
            try {
                userDao.insertUser(user);
                //	sqlsession.commit();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            n=1;
        }
        return n;
    }

    @Override
    public List findALL() {//全部查询
        List user=userDao.findAll();
        return user;
    }
    
    @Override
    public List findByMul(UserfindByMul userfindbymul) {//条件查询
        List user=userDao.findByMul(userfindbymul);
        return user;
    }

    @Override
    public void deleteUser(String user) throws SQLException {//删除
        userDao.deleteUser(user);
        //sqlsession.commit();
    }

    @Override
    public void updateUser(User user) throws SQLException {//更新
        userDao.updateUser(user);
    }
}
