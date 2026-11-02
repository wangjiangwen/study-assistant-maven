package com.kuaishou.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjiangjiao <wangjiangjiao@kuaishou.com>
 * Created on 2026-10-28
 */
public class StudyPlanService {
    private final Map<Long, StudyPlan> plans = new HashMap<>();
    private long nextId = 1L;
    private long taskId = 1L;

    public StudyPlan createPlan(String title) {
        long id = this.nextId;
        StudyPlan studyPlan = new StudyPlan(id, title, System.currentTimeMillis());
        this.plans.put(id, studyPlan);
        this.nextId = id + 1;
        return studyPlan;
    }

    public StudyPlan getPlan(long id) {
        return this.plans.get(id);
    }

    public List<StudyPlan> listPlans() {
        return new ArrayList<>(this.plans.values());
    }

    public StudyTask addTask(long planId, String title) {
        StudyPlan plan = getPlan(planId);
        if (plan == null) {
            throw new IllegalArgumentException("学习计划不存在");
        }

        return plan.createTask(taskId++, title);
    }

    public List<StudyTask> getTasks(long planId) {
        StudyPlan plan = getPlan(planId);
        if (plan == null) {
            throw new IllegalArgumentException("学习计划不存在");
        }
        return plan.getTasks();
    }

    public void completeTask(long planId, long taskId) {
        StudyPlan plan = getPlan(planId);
        if (plan == null) {
            throw new IllegalArgumentException("学习计划不存在");
        }
        plan.completeTask(taskId);
    }

    public double calculateCompletionRate(long planId) {
        StudyPlan plan = getPlan(planId);
        if (plan == null) {
            throw new IllegalArgumentException("学习计划不存在");
        }
        return plan.calculateCompletionRate();
    }
}
