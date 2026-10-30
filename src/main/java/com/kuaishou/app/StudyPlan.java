package com.kuaishou.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.List;
/**
 * @author wangjiangjiao <wangjiangjiao@kuaishou.com>
 * Created on 2026-10-27
 */
public class StudyPlan {
    private final long id;
    private String title;
    private  long createTime = 0;
    private final Map<Long, StudyTask> tasks = new HashMap<>();

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public StudyTask createTask(long taskId, String taskTitle) {
        StudyTask task = new StudyTask(taskId, taskTitle, System.currentTimeMillis());
        tasks.put(taskId, task);
        return task;
    }

    public StudyTask getTask(long taskID) {
        return tasks.get(taskID);
    }

    public List<StudyTask> getTasks() {
        return new ArrayList<>(this.tasks.values());
    }

    public void completeTask(long taskId) {
        StudyTask task = tasks.get(taskId);
        if (task == null) {
            throw  new IllegalArgumentException(this.title + "计划不存在这个任务");
        }
        task.completeTask();
    }

    public void printStudyPlan() {
        System.out.println("id:" + this.id);
        System.out.println("title:" + this.title);
        System.out.println("createTime:" + this.createTime);
    }
}
