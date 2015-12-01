Prerequisites
-------------
Run in `TomEE 7.0.0-SNAPSHOT webprofile`.

Test
----
Build with maven and run.

Result
------
The [documentation for ManagedScheduledExecutorService](http://docs.oracle.com/javaee/7/api/javax/enterprise/concurrent/ManagedScheduledExecutorService.html) states that

> If a transaction is required, use a javax.transaction.UserTransaction instance. A UserTransaction instance is available (...) by requesting an injection of a UserTransaction object using the Resource annotation.

However, when doing that, we get the following exception when invoking the task:

```
SEVERE: Error calling bean
java.lang.NullPointerException
	at org.example.UserTransactionalBean.doTransactionalStuff(UserTransactionalBean.java:25)
	at org.example.Task.run(Task.java:27)
	at org.apache.openejb.threads.task.CURunnable$1.call(CURunnable.java:35)
	at org.apache.openejb.threads.task.CURunnable$1.call(CURunnable.java:32)
	at org.apache.openejb.threads.task.CUTask.invoke(CUTask.java:57)
	at org.apache.openejb.threads.task.CURunnable.run(CURunnable.java:32)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.runAndReset(FutureTask.java:308)
	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$301(ScheduledThreadPoolExecutor.java:180)
	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:294)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
```