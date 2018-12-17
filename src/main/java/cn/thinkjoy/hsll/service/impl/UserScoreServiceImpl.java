package cn.thinkjoy.hsll.service.impl;

import cn.thinkjoy.hsll.bean.UserScore;
import cn.thinkjoy.hsll.dao.UserScoreDao;
import cn.thinkjoy.hsll.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/7/22.
 */
@Service("userScoreService")
public class UserScoreServiceImpl implements UserScoreService{


    @Autowired
    private UserScoreDao userScoreDao;


    @Override
    public List<UserScore> getByUserId(long userId) {
        return userScoreDao.getByUserId(userId);
    }

    @Override
    public void insertData(UserScore userScore) {
        userScoreDao.insertData(userScore);
    }

    @Override
    public UserScore findOneById(long scoreId) {
        return userScoreDao.findOneById(scoreId);
    }

    @Override
    public void updateData(UserScore userScore) {
        userScoreDao.updateData(userScore);
    }

    @Override
    public void deleteById(long id) {
        userScoreDao.deleteById(id);
    }
}
