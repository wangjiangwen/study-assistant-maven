package com.kuaishou.app;
import com.kuaishou.app.StudyPlan;
import java.util.List;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
// -------Lesson 1 类和对象，创建StudyPlan
//        StudyPlan studyPlan = new StudyPlan("1", "wang", System.currentTimeMillis());
//        studyPlan.printStudyPlan();
// -------Lesson 2 集合与泛型， 创建StudyPlanService
        StudyPlanService service = new StudyPlanService();
        StudyPlan plan = service.createPlan("Java 入门");
// -------Lesson 3  任务管理与枚举，StudyTask
        service.addTask(plan.getId(), "学习枚举");
        StudyPlan serverPlan = service.createPlan("Java 服务端");
        StudyTask serverTask = service.addTask(serverPlan.getId(), "学习Spring");
        service.completeTask(serverPlan.getId(), serverTask.getId());
//        long maxIdToCheck = 3;
//        for (int i = 1; i <= maxIdToCheck; i++) {
//            StudyPlan studyPlan = service.getPlan(i);
//            if (studyPlan == null) {
//                System.out.println("学习计划"+ i+"不存在");
//            } else {
//                studyPlan.printStudyPlan();
//            }
//        }

        List<StudyPlan> studyLists = service.listPlans();
        studyLists.forEach(studyPlan -> {
            studyPlan.printStudyPlan();

            List<StudyTask> tasks = studyPlan.getTasks();
            tasks.forEach(StudyTask::printTask);
        });
// -------Lesson 4  输入校验与异常处理 IllegalArgumentException
        try {
            service.createPlan("   ");
        } catch (IllegalArgumentException e) {
            System.out.println("创建失败：" + e.getMessage());
        }
    }
}
