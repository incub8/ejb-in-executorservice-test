package org.example;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class StatelessEjb
{
    private static final Logger log = LoggerFactory.getLogger(StatelessEjb.class);

    public void doTransactionalStuff()
    {
        log.info("Doing transactional stuff");

        /* ...transactional stuff is happening here... */

        log.info("Did transactional stuff");
    }
}