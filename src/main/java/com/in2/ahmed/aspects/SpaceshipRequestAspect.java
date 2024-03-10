package com.in2.ahmed.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class SpaceshipRequestAspect {
    private static final Logger logger = LoggerFactory.getLogger(SpaceshipRequestAspect.class);

    @Before("execution(* com.in2.ahmed.services.SpaceshipService.getSpaceshipById(String)) && args(id)")
    public void logNegativeIdRequest(String id) {
        if (id != null && id.startsWith("-")) {
            logger.warn("Negative ID requested: " + id);
        }
    }
}
