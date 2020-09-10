package zgy.quartz2;

import org.quartz.*;

import java.util.Date;

//@PersistJobDataAfterExecution
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("----正在执行-->>");
    }
}
