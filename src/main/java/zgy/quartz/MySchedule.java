package zgy.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class MySchedule implements Job {

    public void doSomething(){
        System.out.println(new Date() + ": job master Online...");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job doing something...");
    }
}
