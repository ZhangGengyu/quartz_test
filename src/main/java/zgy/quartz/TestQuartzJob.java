package zgy.quartz;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestQuartzJob {
    public static void main(String[] args) throws BeansException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-quartz.xml");
        QuartzManager quartzManager = (QuartzManager) ctx.getBean("quartzManager");
        try {
            System.out.println("===============【系统启动】===============");

            Thread.sleep(5000);
            System.out.println("【增加job1启动】开始(每1秒输出一次)...");
            quartzManager.addJob("test", "test", "test", "test", MySchedule1.class, "0/1 * * * * ?");

            Thread.sleep(20000);
            System.out.println("【修改job1时间】开始(每3秒输出一次)...");
            quartzManager.modifyJobTime("test", "test", "test", "test", "0/3 * * * * ?");

            Thread.sleep(20000);
            System.out.println("【移除job1定时】开始...");
            quartzManager.removeJob("test", "test", "test", "test");

            Thread.sleep(10000);
            System.out.println("【增加job2启动】开始(每1秒输出一次)...");
            quartzManager.addJob("test", "test", "test", "test", MySchedule1.class, "0/1 * * * * ?");

            Thread.sleep(15000);

            // 关掉任务调度容器
            quartzManager.shutdownJobs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
