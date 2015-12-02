package org.example;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class UserTransactionalBean
{
    private static final Logger log = LoggerFactory.getLogger(UserTransactionalBean.class);

    @Inject
    private UserTransaction userTransaction;

    public void doTransactionalStuff()
    {
        try
        {
            userTransaction.begin();
            log.info("Doing transactional stuff");

            /* ...transactional stuff is happening here... */

            log.info("Did transactional stuff");
            userTransaction.commit();
        }
        catch (HeuristicRollbackException | RollbackException | HeuristicMixedException | SystemException | NotSupportedException e1)
        {
            log.error("Error occurred", e1);
            if (userTransaction != null)
            {
                try
                {
                    userTransaction.rollback();
                }
                catch (SystemException e2)
                {
                    log.error("Error rolling back", e2);
                }
            }
        }
    }
}