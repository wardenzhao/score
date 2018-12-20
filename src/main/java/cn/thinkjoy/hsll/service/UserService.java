package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.User;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface UserService {


    User getUserByUsername(String username);

    List<User> getUserList(String username, String name,String grade, int pageSize, int page);

    void insertData(User user);

    void updateData(User user);

    User findOneById(long id);

    void deleteById(long id);
}
