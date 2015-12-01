package org.example;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TaskScheduler
{
    private static final Logger log = LoggerFactory.getLogger(TaskScheduler.class);

    private final Instance<Task> taskInstance;

    @Resource
    private ManagedScheduledExecutorService managedScheduledExecutorService;

    @Inject
    TaskScheduler(Instance<Task> taskInstance)
    {
        this.taskInstance = taskInstance;
    }

    private void start(@Observes @Initialized(ApplicationScoped.class) Object ignored)
    {
        log.info("Starting task");
        Task task = taskInstance.get();
        managedScheduledExecutorService.scheduleAtFixedRate(task, 20, 10, TimeUnit.SECONDS);
        log.info("Task started");
    }
}