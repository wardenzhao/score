package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface UserDao {

    User getUserByUsername(@Param("username") String username);

    List<User> getUserList(@Param("username") String username,@Param("name") String name,@Param("grade") String grade,@Param("pageSize") int pageSize,@Param("page") int page);

    void insertData(@Param("user") User user);

    void updateData(@Param("user") User user);

    User findOneById(@Param("id") long id);

    void deleteById(@Param("id") long id);
}
