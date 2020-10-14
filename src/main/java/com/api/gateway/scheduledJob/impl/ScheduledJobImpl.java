package com.api.gateway.scheduledJob.impl;

import com.api.gateway.scheduledJob.ScheduledJob;
import com.api.gateway.service.FixerIOService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScheduledJobImpl implements ScheduledJob {
    private final FixerIOService fixerIOService;
    private final String TIME_INTERVAL = "0 * * * *"; //every 1 hour

    @Override
    @Scheduled(cron = TIME_INTERVAL)
    public void getRatesFromFixer() {
            fixerIOService.persistNewestRate();
    }
  }
