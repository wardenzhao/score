package cn.thinkjoy.hsll.resp;

import java.util.Date;
import java.util.List;

/**
 * Created by warden on 18/12/6.
 */
public class ScoreResp {

    private long id;
    private long userId;
    private String addCode;
    private String weekProgress;
    private TestMsg testMsg;
    private List<HomeworkMsg> homeworkMsg;
    private List<String> homeworkImage;
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

    public TestMsg getTestMsg() {
        return testMsg;
    }

    public void setTestMsg(TestMsg testMsg) {
        this.testMsg = testMsg;
    }

    public List<HomeworkMsg> getHomeworkMsg() {
        return homeworkMsg;
    }

    public void setHomeworkMsg(List<HomeworkMsg> homeworkMsg) {
        this.homeworkMsg = homeworkMsg;
    }

    public List<String> getHomeworkImage() {
        return homeworkImage;
    }

    public void setHomeworkImage(List<String> homeworkImage) {
        this.homeworkImage = homeworkImage;
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
