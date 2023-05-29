package com.employee.management.customAnnotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditLogAspect {
    @AfterReturning("@annotation(auditLog)")
    public void logAction(JoinPoint joinPoint, AuditLog auditLog) {
        String action = auditLog.action();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println("Action: " + action);
        System.out.println("Method: " + methodName);
        System.out.println("Class: " + className);
    }
}
