package com.poscoict.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	@Around("execution(* *..*.repository.*.*(..))")
	public Object aroundStopWatchAdvice(ProceedingJoinPoint pjp) throws Throwable{
		/* before*/
		StopWatch sw = new StopWatch();
		sw.start();
	
 		Object result=pjp.proceed();
		
		/* After */
 		sw.stop();
 		String className="";
 		String methodName="";
 		String taskName = className+"."+methodName;
 		
 		Long totalTime = sw.getTotalTimeMillis();
 		System.out.println("[Execution Time]["+taskName+"]"+totalTime+"millis");
		System.out.println("total time : "+totalTime.toString());
		return result;
	}
}