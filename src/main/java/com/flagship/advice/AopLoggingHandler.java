package com.flagship.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Aspect
@Component
public class AopLoggingHandler {

  private final Logger logger = Logger.getLogger("Flagship");
  private ObjectMapper mapper;

  @PostConstruct
  private void postConstruct() {
    mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.registerModule(new Jdk8Module());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
  }

  @Around("execution(* org.example.*.*.*(..))")
  public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().toString();

    try {
      Object[] array = joinPoint.getArgs();
      logger.info(className + "." + methodName + "() :: " + mapper.writeValueAsString(array));
    } catch (Exception e) {
      logger.warning(e.getLocalizedMessage());
    }

    Object object = joinPoint.proceed();

    try {
      logger.info(className + "." + methodName + "() :: " + mapper.writeValueAsString(object));
    } catch (Exception e) {
      logger.warning(e.getLocalizedMessage());
    }

    return object;
  }
}
