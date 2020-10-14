package com.api.gateway.scheduledJob;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public interface ScheduledJob {
    @Async
    void getRatesFromFixer();

}
