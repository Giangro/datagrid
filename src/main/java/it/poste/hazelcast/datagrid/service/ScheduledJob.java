/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.poste.hazelcast.datagrid.service;

import java.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

/**
 *
 * @author alex
 */
// see https://github.com/Charlynux/task-scheduling/blob/master/src/main/java/demo/TaskSchedulingApplication.java
@Service
@Slf4j
public class ScheduledJob extends ThreadPoolTaskScheduler implements Runnable {

    private final String cronJobExpr;
    private ScheduledFuture<?> scheduledFuture;

    public ScheduledJob(String cronJobExpr) {
        super();
        this.cronJobExpr = cronJobExpr;
        scheduledFuture = null;
    }

    public void start() {
        if (scheduledFuture!=null) {
            scheduledFuture.cancel(false);
        }
        scheduledFuture = schedule(this, new CronTrigger(cronJobExpr));
        log.info("job has been started");
    }

    public void stop() {
        if (scheduledFuture!=null) {
            scheduledFuture.cancel(false);
            scheduledFuture = null;
            log.info("job has been stopped");
        }
    }

    @Override
    public void run() {
        log.debug("scheduled job running...");
    }

}
