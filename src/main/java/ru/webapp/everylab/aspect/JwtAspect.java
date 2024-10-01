package ru.webapp.everylab.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ru.webapp.everylab.service.JwtService;

@Aspect
@Component
@RequiredArgsConstructor
public class JwtAspect {

    private final JwtService jwtService;
    private final HttpServletRequest request;

    @Around("@annotation(ru.webapp.everylab.aspect.annotation.RequiresUserId)")
    public Object injectUserId(ProceedingJoinPoint joinPoint) throws Throwable {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }

        if(token != null) {
            String userId = jwtService.extractUserIdFromToken(token);

            //Проходим по аргументам метода и заменяем параметр с userId
            Object[] args = joinPoint.getArgs();
            for(int i = 0; i < args.length; i++) {
                if(args[i] instanceof String && "userId".equals(args[i])) {
                    args[i] = userId;   // заменяем userId на реальное значение
                }
            }
            // Продолжаем выполнение метода с обновленными аргументами
            return joinPoint.proceed(args);
        } else {
            throw new RuntimeException("JWT token is missing");
        }
    }
}
