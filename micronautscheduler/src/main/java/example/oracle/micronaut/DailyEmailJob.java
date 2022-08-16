package example.oracle.micronaut;

import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Bean;
import io.micronaut.scheduling.annotation.Scheduled;

import jakarta.inject.Singleton;

@Singleton
public class DailyEmailJob {

    protected final EmailUseCase emailUseCase;

    public DailyEmailJob(EmailUseCase emailUseCase) {
        this.emailUseCase = emailUseCase;
    }

//    @Scheduled(cron = "0 30 14 1/1 * ?")
    @Scheduled(cron = "${my.cron}")
    void execute() {
        emailUseCase.send("john.doe@micronaut.example", "Test Message");
    }
}
