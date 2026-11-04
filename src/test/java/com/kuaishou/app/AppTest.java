package com.kuaishou.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void completingOneTaskShouldLeaveOtherTaskUnchanged() {
        StudyPlanService service = new StudyPlanService();
        StudyPlan plan = service.createPlan("Java 入门");
        StudyTask first = service.addTask(plan.getId(), "学习集合");
        StudyTask second = service.addTask(plan.getId(), "学习异常");

        service.completeTask(plan.getId(), first.getId());

        assertEquals(TaskStatus.DONE, first.getTaskStatus());
        assertEquals(TaskStatus.TODO, second.getTaskStatus());
    }

    @Test
    void invalidPlanTitleChangeShouldPreserveOriginalTitle() {
        StudyPlan plan = new StudyPlan(1L, "Java 入门", 0L);
        for (String invalid : new String[]{null, "", "   "}) {
            assertThrows(IllegalArgumentException.class, () -> plan.setTitle(invalid));
            assertEquals("Java 入门", plan.getTitle());
        }
    }

    @Test
    void invalidTaskTitleChangeShouldPreserveOriginalTitle() {
        StudyTask task = new StudyTask(1L, "学习集合", 0L);
        for (String invalid : new String[]{null, "", "   "}) {
            assertThrows(IllegalArgumentException.class, () -> task.setTitle(invalid));
            assertEquals("学习集合", task.getTitle());
        }
    }
}
