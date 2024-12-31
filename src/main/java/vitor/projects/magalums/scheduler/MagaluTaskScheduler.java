package vitor.projects.magalums.scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MagaluTaskScheduler {
    
    private static final Logger logger = LoggerFactory.getLogger(MagaluTaskScheduler.class); // logger instance

    @Scheduled(fixedDelay = 1L , timeUnit = TimeUnit.MINUTES)
    public void runTask(){
        var dateTime = LocalDateTime.now();
        logger.info("Task executed at: {}", dateTime);
    }
}
