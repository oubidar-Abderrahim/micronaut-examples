package example.micronaut.ao.controller;

import example.micronaut.ao.dto.ExampleDto;
import example.micronaut.ao.service.ExampleService;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;

import javax.validation.Valid;

@Validated
@Controller
public class ExampleController {

    @Inject
    ExampleService exampleService;

    @Get("/test")
    @Valid
    public ExampleDto getTestDate() {
        return exampleService.getDto();
    }
}
