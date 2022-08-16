package example.oracle.micronaut;

import jakarta.inject.Singleton;

@Singleton
public class SheduledCron {
    private final String cronTime = "0 38 14 1/1 * ?";

    public String getCronTime() {
        return cronTime;
    }
}
