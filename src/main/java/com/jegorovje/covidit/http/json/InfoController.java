package com.jegorovje.covidit.http.json;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class InfoController {

  @Get("/status")
  public String status(){
    return "OK";
  }

}
