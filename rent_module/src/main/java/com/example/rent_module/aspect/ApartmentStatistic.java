package com.example.rent_module.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ApartmentStatistic {

    public static final String POINT_CUT = "execution(* com.example.rent_module.service.impl.RentServiceImpl.findApartmentById(..))";

    @AfterReturning(pointcut = POINT_CUT, returning = "result")
    public void testAspectCatchResult(JoinPoint joinPoint, Object result) {

        log.info("-> метод testAspectCatchResult начал работу");
        String name = joinPoint.getSignature().getName();
        log.info("-> метод {} перехвачен", name);
        Class<?> aClass = result.getClass();
        log.info("-> класс {} перехвачен", aClass);
        log.info(result.toString());
    }


}
