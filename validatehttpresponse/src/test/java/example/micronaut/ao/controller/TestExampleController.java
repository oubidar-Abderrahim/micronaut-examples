package example.micronaut.ao.controller;

import example.micronaut.ao.dto.ExampleDto;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class TestExampleController {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void testExampleController() {
        ExampleDto dto = client.toBlocking().retrieve(HttpRequest.GET("/test"), ExampleDto.class);
        assertNotNull(dto);
        System.out.println(dto.getAccessToken());
    }

}
