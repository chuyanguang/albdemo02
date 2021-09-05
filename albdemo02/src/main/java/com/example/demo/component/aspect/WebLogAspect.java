package com.example.demo.component.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Order;

@Slf4j
@Component
@Aspect
public class WebLogAspect {

    @Pointcut("execution(public * com.example.demo.controller..*(..))")
    private void controllerAspect() {
    }

    /**
     * 方法调用打印入参
     *
     * @param joinPoint
     */
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        for (Object arg : args) {
            params.append(arg).append(" ");
        }
        log.info("{}的{}入参为：{}", className, methodName, params);
    }

    /**
     * 返回打印出参
     *
     * @param joinPoint
     * @param returnVal
     */
    @AfterReturning(value = "controllerAspect()", returning = "returnVal")
    public void afterReturin(JoinPoint joinPoint, Object returnVal) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{}的{}结果为：{}", className, methodName, returnVal.toString());
    }

    /**
     * 过程中监测，catch到异常之后返回包装后的错误信息，并打印日志
     *
     * @param joinPoint
     * @return
     */
//    @Around(value = "execution(public * com.example.demo.service..*(..))")
//    public ResultData catchException(ProceedingJoinPoint joinPoint) {
//        try {
//            ResultData baseResponse = new ResultData();
//            baseResponse.setCode(HttpStatus.OK);
//            baseResponse.setData(joinPoint.proceed());
//            return baseResponse;
//        } catch (Throwable e) {
//            String className = joinPoint.getTarget().getClass().getName();
//            String methodName = joinPoint.getSignature().getName();
//            log.warn("在" + className + "的" + methodName + "中，发生了异常：" + e);
//            return ResultData.fail("程序异常", null);
//        }
//    }
}
