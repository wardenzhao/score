package cn.thinkjoy.hsll.service;

import cn.thinkjoy.hsll.bean.UserScore;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
public interface UserScoreService {


    List<UserScore> getByUserId(long userId);

    void insertData(UserScore userScore);

    UserScore findOneById(long scoreId);

    void updateData(UserScore userScore);

    void deleteById(long id);
}
