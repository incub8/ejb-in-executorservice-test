package org.example;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Task implements Runnable
{
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    private final UserTransactionalBean userTransactionalBean;

    @Inject
    Task(UserTransactionalBean userTransactionalBean)
    {
        this.userTransactionalBean = userTransactionalBean;
    }

    @Override
    public void run()
    {
        log.info("Executing task");

        try
        {
            userTransactionalBean.doTransactionalStuff();
        }
        catch (Exception e)
        {
            log.error("Error performing the task", e);
        }

        log.info("Task executed");
    }
}