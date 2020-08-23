package com.jegorovje.covidit.http.json;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller
@Secured(SecurityRule.IS_ANONYMOUS)
public class InfoController {

  @Get("/status")
  public String status(){
    return "OK";
  }

}
