package com.jegorovje.covidit;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
    info = @Info(
        title = "covidit",
        version = "0.1"
    )
)
public class Application {

  public static void main(String[] args) {
    Micronaut.run(Application.class);
  }
}