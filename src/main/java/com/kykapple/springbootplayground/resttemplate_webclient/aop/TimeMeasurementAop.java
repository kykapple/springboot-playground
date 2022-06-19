package com.kykapple.springbootplayground.resttemplate_webclient.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class TimeMeasurementAop {

    @Around("@annotation(TimeMeasurement)")
    public void measureTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        pjp.proceed();

        stopWatch.stop();
        System.out.println("elapsed time: " + stopWatch.getTotalTimeMillis());
    }

}
