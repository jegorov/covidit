package com.jegorovje.covidit.http.security;

import com.jegorovje.covidit.security.data.UserDto;
import com.jegorovje.covidit.security.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;
import java.util.Optional;
import javax.inject.Inject;
import javax.naming.AuthenticationException;

@Controller
@Secured(SecurityRule.IS_ANONYMOUS)
public class SignupController {

    @Inject
    UserService userService;

    @Post("/signup")
    public Single<HttpResponse> register(UserDto userDto) {
        userDto.setRole("VIEW");
        Optional<UserDto> existingUser =
            userService.findUser(userDto.getLogin());
        if (existingUser.isPresent()) {
            return Single.error(new AuthenticationException("user already exists"));
        }
        return Single.just(HttpResponse.ok(userService.createUser(userDto)));
    }
}
