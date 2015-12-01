package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Task implements Runnable
{
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    @Override
    public void run()
    {
        log.info("Executing task");

        try
        {
            /* ...performing the task... */
        }
        catch (Exception e)
        {
            log.error("Error performing the task", e);
        }

        log.info("Task executed");
    }
}