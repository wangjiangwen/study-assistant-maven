package com.kuaishou.app;
import com.kuaishou.app.StudyPlan;
import java.util.List;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//        StudyPlan studyPlan = new StudyPlan("1", "wang", System.currentTimeMillis());
//        studyPlan.printStudyPlan();
        StudyPlanService service = new StudyPlanService();
        StudyPlan plan = service.createPlan("Java 入门");
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
    }
}
