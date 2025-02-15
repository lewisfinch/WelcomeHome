package com.wh.aop;

import com.wh.mapper.OperateLogMapper;
import com.wh.pojo.OperateLog;
import com.wh.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.wh.annotation.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获取操作人ID
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        String operateUser = (String) claims.get("userName");

        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();

        // 操作方法
        String methodName = joinPoint.getSignature().getName();

        // 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        // 方法返回值
        String returnValue = result.toString();

        // 运行时间
        long costTime = end - begin;

        OperateLog oplog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(oplog);

        log.info("AOP record Log: {}", oplog.toString());

        return result;
    }
}
