package com.jegorovje.covidit.http.security;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import java.security.Principal;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/security")
public class SecurityController {


  @Post("/test")
  public String testSecurity(Principal principal){
    return principal.getName();
  }
}
