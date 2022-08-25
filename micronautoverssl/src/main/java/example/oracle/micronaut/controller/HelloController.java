package example.oracle.micronaut.controller;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.x509.X509Authentication;

import static io.micronaut.http.MediaType.TEXT_PLAIN;
import static io.micronaut.security.rules.SecurityRule.IS_ANONYMOUS;

@Controller
public class HelloController {

    @Secured(IS_ANONYMOUS)
    @Get(produces = TEXT_PLAIN)
    String hello(@Nullable X509Authentication x509Authentication,
                 @Nullable Authentication authentication) {
        if (x509Authentication == null && authentication == null) {
            return "Hello unknown!";
        }
        if (x509Authentication == null) {
            return "ERROR: Authentication is present but not X509Authentication";
        }
        if (x509Authentication != authentication) {
            return "ERROR: Authentication and X509Authentication should be the same instance";
        }
        return "Hello " +
                x509Authentication.getName() +
                " (X.509 cert issued )";
    }
}
