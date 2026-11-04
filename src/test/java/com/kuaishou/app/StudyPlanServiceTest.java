package com.kuaishou.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author wangjiangjiao <wangjiangjiao@kuaishou.com>
 * Created on 2026-11-02
 */
public class StudyPlanServiceTest {
    @Test
    void shouldCreatePlanWithTrimmedTitle() {
        // 准备
        StudyPlanService service = new StudyPlanService();

        // 执行
        StudyPlan plan = service.createPlan("  Java 入门  ");

        // 断言
        assertEquals("Java 入门", plan.getTitle());
        assertEquals(1, service.listPlans().size());
        assertSame(plan, service.getPlan(plan.getId()));
    }
    @Test
    void shouldRejectBlankPlanTitleWithoutSaving() {
        StudyPlanService service = new StudyPlanService();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.createPlan("   ")
        );

        assertEquals(0, service.listPlans().size());
    }

    @Test
    void shouldCompletionRate() {
        StudyPlanService service = new StudyPlanService();

        // 执行
        StudyPlan serverPlan = service.createPlan("Java 服务端");
        StudyTask serverIocTask = service.addTask(serverPlan.getId(), "容器Ioc");
        service.completeTask(serverPlan.getId(), serverIocTask.getId());
        StudyTask serverTask = service.addTask(serverPlan.getId(), "学习Spring");
        StudyTask serverHttpTask = service.addTask(serverPlan.getId(), "Http");
        assertEquals(100.0/ 3, service.calculateCompletionRate(serverPlan.getId()), 0.000001);
    }
}
