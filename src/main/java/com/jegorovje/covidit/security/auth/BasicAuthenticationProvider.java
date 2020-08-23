package com.jegorovje.covidit.security.auth;

import static io.micronaut.security.authentication.AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH;
import static io.micronaut.security.authentication.AuthenticationFailureReason.USER_NOT_FOUND;

import com.jegorovje.covidit.security.data.UserDto;
import com.jegorovje.covidit.security.service.UserService;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.Flowable;
import java.util.Arrays;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.reactivestreams.Publisher;

@Singleton
public class BasicAuthenticationProvider implements AuthenticationProvider {

    @Inject UserService userService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(
        HttpRequest httpReq, AuthenticationRequest authReq) {

        final String login = authReq.getIdentity().toString();
        final String password = authReq.getSecret().toString();

        Optional<UserDto> existingUser =
            userService.findUser(login);

        return Flowable.just(
            existingUser.map(user -> {
                if (user.getPassword().equals(password)) {
                    return new UserDetails(login,
                        Arrays.asList(user.getRole()));
                }
                return new
                    AuthenticationFailed(CREDENTIALS_DO_NOT_MATCH);
            })
                .orElse(new AuthenticationFailed(USER_NOT_FOUND))
        );
    }
}
