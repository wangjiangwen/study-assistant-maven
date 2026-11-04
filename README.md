# 学习助手

Java 服务端学习项目，当前实现学习计划、任务管理和计划完成率统计，使用内存保存数据。

## 环境要求

- JDK 11（项目的 `maven.compiler.release` 为 `11`）
- Maven

使用 `java -version` 和 `mvn -version` 检查实际使用的 Java 环境。

## 编译和运行

在项目根目录执行：

```bash
mvn compile
java -cp target/classes com.kuaishou.app.App
```

也可以在 IDE 中打开 Maven 项目，选择 JDK 11，运行 `com.kuaishou.app.App.main()`。

当前综合演示展示“Java 服务端入门”计划的完成率 `0.00% → 33.33% → 33.33%（重复完成）→ 100.00%`，并确认第二份计划保持 `0.00%`。非法标题异常被捕获后，程序继续创建合法任务。

## 自动化测试

```bash
mvn test
```

从干净编译结果开始执行：

```bash
mvn clean test
```

仅运行业务测试类：

```bash
mvn -Dtest=StudyPlanServiceTest test
```

第六课阶段测试覆盖计划标题去空白及保存、空白标题拒绝且不保存、三分之一完成率。第七课补充完成任务不影响其他任务、计划及任务非法修改标题后保持原值的测试。其余边界与隔离测试待后续补充。运行时应确认实际执行测试数量大于零，且 Failures 和 Errors 均为零。

## 核心结构

```text
src/main/java/com/kuaishou/app/
├── App.java                  控制台演示入口
├── StudyPlanService.java     管理计划、分配编号、协调业务操作
├── StudyPlan.java            保存计划及所属任务，计算完成率
└── StudyTask.java            保存任务信息并管理 TODO / DONE 状态

src/test/java/com/kuaishou/app/
├── StudyPlanServiceTest.java 第六课业务测试
└── AppTest.java              第七课业务规则测试
```

`TaskStatus` 枚举目前定义在 `StudyTask.java` 中。

完成任务的调用关系：`App → StudyPlanService.completeTask → StudyPlan.completeTask → StudyTask.completeTask`。

## 业务规则

- 计划和任务的 ID 使用 `long`，通过 Service 创建时分别从 1 递增。
- 计划和任务标题不能为 `null`、空字符串或纯空白；保存时去掉首尾空白，保留内部空格。修改标题同样执行校验。
- 新任务默认状态为 `TODO`，完成后为 `DONE`，重复完成保持 `DONE`。
- 完成任务时只查找指定计划内部的任务，不能通过另一个计划完成该任务。
- `getPlan(id)` 查不到时返回 `null`；添加任务、查询任务列表、完成任务和查询完成率时，计划不存在则抛出 `IllegalArgumentException`。
- 完成率以单份计划为单位：已完成任务数 / 总任务数 × 100，返回 `double`，范围为 0～100；没有任务时返回 0。
- 完成率根据当前状态实时计算，展示格式由 App 处理。
- 查询列表返回新建的列表，清空返回列表不会清空内部存储；列表中的对象仍是原对象，并非深拷贝。

## 当前限制

- 数据仅保存在内存，程序退出后丢失，编号重新开始。
- 当前用于单线程学习演示，未提供并发访问保证。
- 尚未提供 HTTP 接口、数据库存储或用户认证。

## 课程进度

1. 类与对象。
2. 集合、泛型与计划管理。
3. 枚举与任务管理。
4. 输入校验与异常。
5. 完成率与浮点数运算。
6. JUnit 自动化测试（阶段验收）。
7. 综合演示与项目文档。

## 历史演示备份

原始 App.java 完整内容保存在 `docs/archive/App-lessons-1-to-5.java.txt`，用于回顾前五课。采用 `.txt` 扩展名，避免与当前 `App` 类重复参与编译。

## 后续方向

完善核心业务测试和综合演示，再逐步接入 HTTP、数据库与用户认证。
