package zgy.quartz2;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.HashMap;

public class HelloSchedulerDemo {
    public static void main(String[] args) throws SchedulerException {
        Date start = new Date(); start.setTime(start.getTime()+10000);
        Date end = new Date(); end.setTime(end.getTime()+10000);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("a","A");
        jobDataMap.put("b",11);
        jobDataMap.put("c",22l);
        // 1.调度器（Scheduler），从工程中获取调度实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 2.任务实例（JobDetail）
        JobDetail JobDetail1 = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "job1-group1")
//                .usingJobData("count", 0)
                .build();
        // 3.触发器
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "trigger1-group1")
//                .usingJobData("d", "trigger触发器")
//                .startNow() // 立即执行
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5)
                    .withRepeatCount(2)) // 每5秒执行一次，连续执行3次后停止，默认值是0
                .startAt(start)
                .endAt(end)
                .build();
        // 4.让调度器关联任务和触发器
        scheduler.scheduleJob(JobDetail1, trigger1);
        // 5.启动
        scheduler.start();
    }
}
