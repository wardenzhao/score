package cn.thinkjoy.hsll.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by warden on 17/7/22.
 */
public class UserScore implements Serializable {

    private long id;
    private long userId;
    private String addCode;
    private String weekProgress;
    private String testMsg;
    private String homeworkMsg;
    private String teacherComment;
    private String parentComment;
    private Date createTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAddCode() {
        return addCode;
    }

    public void setAddCode(String addCode) {
        this.addCode = addCode;
    }

    public String getWeekProgress() {
        return weekProgress;
    }

    public void setWeekProgress(String weekProgress) {
        this.weekProgress = weekProgress;
    }

    public String getTestMsg() {
        return testMsg;
    }

    public void setTestMsg(String testMsg) {
        this.testMsg = testMsg;
    }

    public String getHomeworkMsg() {
        return homeworkMsg;
    }

    public void setHomeworkMsg(String homeworkMsg) {
        this.homeworkMsg = homeworkMsg;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    public String getParentComment() {
        return parentComment;
    }

    public void setParentComment(String parentComment) {
        this.parentComment = parentComment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
