package com.jegorovje.covidit.http.security;

import com.jegorovje.covidit.security.auth.BasicAuthenticationProvider;
import com.jegorovje.covidit.security.data.UserDto;
import com.jegorovje.covidit.security.service.UserService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.reactivex.Single;
import java.util.Optional;
import javax.inject.Inject;
import javax.naming.AuthenticationException;

@Controller("/login")
@Secured(SecurityRule.IS_ANONYMOUS)
public class LoginController {

    @Inject
    UserService userService;


    @Post
    public String login(UserDto user) {

        Optional<UserDto> existingUser =
            userService.findUser(user.getLogin());

        if (existingUser.isEmpty()) {
            Single.error(new AuthenticationException("user doesn't exist"));
        }

      return "login ok";
    }

    @Get("/check")
    public String check() {
        return "OK";
    }
}
