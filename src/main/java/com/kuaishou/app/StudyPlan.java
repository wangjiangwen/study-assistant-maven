package com.kuaishou.app;

/**
 * @author wangjiangjiao <wangjiangjiao@kuaishou.com>
 * Created on 2026-10-27
 */
public class StudyPlan {
    private long id;
    private String title;
    private  long createTime = 0;

    public StudyPlan(long id, String title, long createTime) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void printStudyPlan() {
        System.out.println("id:" + this.id);
        System.out.println("title:" + this.title);
        System.out.println("createTime:" + this.createTime);
    }
}
