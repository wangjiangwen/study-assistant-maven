package com.kuaishou.app;
/**
 * 第一阶段综合演示。
 */
public class App {
    public static void main(String[] args) {
        System.out.println("服务端学习");

        demonstrateLearningProgress();
        demonstrateInvalidInput();
    }

    public static void demonstrateLearningProgress() {
        StudyPlanService service = new StudyPlanService();
        // 创建“Java 服务端入门”计划
        StudyPlan serverPlan = service.createPlan("Java 服务端入门");
        // 添加“学习集合”“学习异常”“编写测试”三个任务
        StudyTask serverCollectionTask = service.addTask(serverPlan.getId(), "学习集合");
        StudyTask serverExceptionTask = service.addTask(serverPlan.getId(), "学习异常");
        StudyTask serverTestTask = service.addTask(serverPlan.getId(), "编写测试");

        // 创建第二份计划，确认它的任务状态和完成率不受第一份计划影响
        StudyPlan androidPlan = service.createPlan("Android 入门");
        // 添加“Java基础”一个任务
        service.addTask(androidPlan.getId(), "Java 基础");

        // 输出初始完成率
        printServiceCompleteRate(service, serverPlan);

        // 完成“学习集合”
        service.completeTask(serverPlan.getId(), serverCollectionTask.getId());

        // 输出完成率
        printServiceCompleteRate(service, serverPlan);

        // 重复完成“学习集合”
        service.completeTask(serverPlan.getId(), serverCollectionTask.getId());

        // 输出完成率
        printServiceCompleteRate(service, serverPlan);

        // 完成另外两个任务
        service.completeTask(serverPlan.getId(), serverExceptionTask.getId());
        service.completeTask(serverPlan.getId(), serverTestTask.getId());

        // 输出完成率
        printServiceCompleteRate(service, serverPlan);

        // 输出android计划完成率
        printServiceCompleteRate(service, androidPlan);
    }

    public static void demonstrateInvalidInput() {
        StudyPlanService service = new StudyPlanService();
        StudyPlan androidPlan = service.createPlan("Android 入门");
        try {
            service.addTask( androidPlan.getId(), "   ");
        } catch (IllegalArgumentException e) {
            System.out.println("创建失败：" + e.getMessage());
        }
        StudyTask task = service.addTask(androidPlan.getId(), "Kotlin 基础");
        System.out.println("异常后继续创建成功：" + task.getTitle());
    }

    public static void printServiceCompleteRate(StudyPlanService service, StudyPlan serverPlan) {
        double rate = service.calculateCompletionRate(serverPlan.getId());
        System.out.printf("%s学习计划完成率：%.2f%%%n",
                serverPlan.getTitle(), rate);
    }
}
