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
    private long nextId = 0L;

    public StudyPlan createPlan(String title) {
        long id = this.nextId + 1;
        StudyPlan studyPlan = new StudyPlan(id, title, System.currentTimeMillis());
        this.plans.put(id, studyPlan);
        this.nextId = id;
        return studyPlan;
    }
    public StudyPlan getPlan(long id) {
        return this.plans.get(id);
    }

    public List<StudyPlan> listPlans() {
        List<StudyPlan> lists = new ArrayList<>(this.plans.values());
        return lists;
    }
}
