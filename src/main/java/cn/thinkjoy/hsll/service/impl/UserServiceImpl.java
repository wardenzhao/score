package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.User;
import cn.thinkjoy.hsll.dao.UserDao;
import cn.thinkjoy.hsll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
@Service("userService")
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public List<User> getUserList(String username, String name, int pageSize, int page) {
        return userDao.getUserList(username, name, pageSize, page);
    }

    @Override
    public void insertData(User user) {
        userDao.insertData(user);
    }

    @Override
    public void updateData(User user) {
        userDao.updateData(user);
    }

    @Override
    public User findOneById(long id) {
        return userDao.findOneById(id);
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }
}
