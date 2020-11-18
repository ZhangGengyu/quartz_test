package zgy.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class MySchedule1 implements Job {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-quartz.xml");
    QuartzManager quartzManager = (QuartzManager) ctx.getBean("quartzManager");
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() + ": ========执行设备离线的逻辑处理，第一次先4h后发mq...");
        System.out.println("【移除job1定时】开始...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        quartzManager.removeJob("test", "test", "test", "test");
        System.out.println("【修改job1时间-第二次设置8h（发送之前先查看缓存中是否有该设备信息）】开始(每3秒输出一次)...");
        quartzManager.modifyJobTime("test",  "test", "test", "test", "0/3 * * * * ?");
    }
}
