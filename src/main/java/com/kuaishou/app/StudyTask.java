package com.kuaishou.app;

enum TaskStatus {
    TODO,
    DONE
}

/**
 * @author wangjiangjiao <wangjiangjiao@kuaishou.com>
 * Created on 2026-10-30
 */
public class StudyTask {
    private final long id;
    private String title;
    private TaskStatus taskStatus;
    private long createTime;

    public StudyTask(long id, String title, long createTime) {
        this.id = id;
        this.taskStatus = TaskStatus.TODO;
        this.createTime = createTime;
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("学习任务标题不能为空");
        }
        this.title = title.strip();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("学习任务标题不能为空");
        }
        this.title = title.strip();
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void completeTask() {
        this.taskStatus = TaskStatus.DONE;
    }

    public void printTask() {
        System.out.println("Task id:" + this.id + "   title:" + this.title + "    createTime:" + this.createTime
                + " TaskStatus:" + this.taskStatus);
    }

}
