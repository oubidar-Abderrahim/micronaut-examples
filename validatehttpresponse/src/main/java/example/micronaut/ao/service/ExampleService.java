package example.micronaut.ao.service;

import example.micronaut.ao.dto.ExampleDto;
import jakarta.inject.Singleton;

@Singleton
public class ExampleService {

    public ExampleDto getDto() {
        ExampleDto dto = new ExampleDto();
        dto.setAccessToken("token");
        dto.setExpiresIn(null);
        dto.setTokenType(null); // this should fail
        return dto;
    }
}
