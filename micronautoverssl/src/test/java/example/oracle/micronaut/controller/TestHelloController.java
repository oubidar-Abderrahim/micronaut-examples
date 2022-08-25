package example.oracle.micronaut.controller;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class TestHelloController {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void testClientCert() {
        String response = httpClient.toBlocking().retrieve("/");
        String expected = "Hello aoubidar (X.509 cert issued )";
        assertEquals(expected, response);
    }
}
