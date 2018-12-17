package cn.thinkjoy.hsll.dao;

import cn.thinkjoy.hsll.bean.UserScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface UserScoreDao {

    List<UserScore> getByUserId(@Param("userId") long userId);

    void insertData(@Param("userScore") UserScore userScore);

    UserScore findOneById(@Param("scoreId") long scoreId);

    void updateData(@Param("userScore") UserScore userScore);

    void deleteById(@Param("id") long id);
}
