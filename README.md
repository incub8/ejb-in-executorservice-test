Prerequisites
-------------
Run in `TomEE 7.0.0-SNAPSHOT webprofile`.

Test
----
Build with maven and run.

Solution
------
The `TaskScheduler` runs to early in the containers lifecycle when using `@Observes @Initialized(ApplicationScoped.class) Object ignored`. When using `@PostConstruct` inside a `@Startup @Singleton` bean, the container is in the correct state for the injection of the stateless EJB to succeed.

Quirk
-----
When using this method, the EJB itself has to be `public` although it resides in the same package as the task. Otherwise, the following exception is thrown on task execution.

```
SEVERE: Error calling EJB
java.lang.IllegalAccessException: Class org.apache.openejb.core.interceptor.ReflectionInvocationContext$Invocation can not access a member of class org.example.StatelessEjb with modifiers "public"
	at sun.reflect.Reflection.ensureMemberAccess(Reflection.java:102)
	at java.lang.reflect.AccessibleObject.slowCheckMemberAccess(AccessibleObject.java:296)
	at java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:288)
	at java.lang.reflect.Method.invoke(Method.java:490)
	at org.apache.openejb.core.interceptor.ReflectionInvocationContext$Invocation.invoke(ReflectionInvocationContext.java:205)
	at org.apache.openejb.core.interceptor.ReflectionInvocationContext.proceed(ReflectionInvocationContext.java:186)
	at org.apache.openejb.monitoring.StatsInterceptor.record(StatsInterceptor.java:181)
	at org.apache.openejb.monitoring.StatsInterceptor.invoke(StatsInterceptor.java:100)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at org.apache.openejb.core.interceptor.ReflectionInvocationContext$Invocation.invoke(ReflectionInvocationContext.java:205)
	at org.apache.openejb.core.interceptor.ReflectionInvocationContext.proceed(ReflectionInvocationContext.java:186)
	at org.apache.openejb.core.interceptor.InterceptorStack.invoke(InterceptorStack.java:85)
	at org.apache.openejb.core.stateless.StatelessContainer._invoke(StatelessContainer.java:227)
	at org.apache.openejb.core.stateless.StatelessContainer.invoke(StatelessContainer.java:194)
	at org.apache.openejb.core.ivm.EjbObjectProxyHandler.synchronizedBusinessMethod(EjbObjectProxyHandler.java:265)
	at org.apache.openejb.core.ivm.EjbObjectProxyHandler.businessMethod(EjbObjectProxyHandler.java:260)
	at org.apache.openejb.core.ivm.EjbObjectProxyHandler._invoke(EjbObjectProxyHandler.java:89)
	at org.apache.openejb.core.ivm.BaseEjbProxyHandler.invoke(BaseEjbProxyHandler.java:319)
	at org.example.StatelessEjb$$LocalBeanProxy.doTransactionalStuff(org/example/StatelessEjb.java)
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