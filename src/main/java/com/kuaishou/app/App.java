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
        service.createPlan("学习1");
        service.createPlan("学习2");
        long maxIdToCheck = 3;
        for (int i = 1; i <= maxIdToCheck; i++) {
            StudyPlan studyPlan = service.getPlan(i);
            if (studyPlan == null) {
                System.out.println("学习计划"+ i+"不存在");
            } else {
                studyPlan.printStudyPlan();
            }
        }

        List<StudyPlan> studyLists = service.listPlans();
        studyLists.forEach(studyPlan -> {
            studyPlan.printStudyPlan();
        });
    }
}
