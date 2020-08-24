package com.jegorovje.covidit.http.security;

import com.jegorovje.covidit.security.dto.UserDto;
import com.jegorovje.covidit.security.service.UserService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
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
            userService.findUser(user.getUsername());

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
