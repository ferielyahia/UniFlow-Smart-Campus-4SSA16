package com.example.timetableservice.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
public class MaintenanceHealthIndicator implements HealthIndicator {

    private static final Path FLAG = Path.of("maintenance.flag");

    @Override
    public Health health() {
        log.debug("Verification du fichier {}", FLAG.toAbsolutePath());

        if (Files.exists(FLAG)) {
            return Health.down()
                    .withDetail("raison", "Service en maintenance")
                    .build();
        }
        return Health.up().build();
    }
}