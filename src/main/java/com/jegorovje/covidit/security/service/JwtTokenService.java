package com.jegorovje.covidit.security.service;

import com.jegorovje.covidit.engine.data.entity.impl.RefreshedTokenEntity;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.Optional;
import javax.inject.Singleton;
import org.reactivestreams.Publisher;

@Singleton
@TransactionalAdvice
public class JwtTokenService implements RefreshTokenPersistence {

  private final UserService userService;

  public JwtTokenService(UserService userService) {
    this.userService = userService;
  }

  @Override
  @EventListener
  public void persistToken(RefreshTokenGeneratedEvent event) {
    if (event != null &&
        event.getRefreshToken() != null &&
        event.getUserDetails() != null &&
        event.getUserDetails().getUsername() != null) {
      userService.saveRefreshToken(event.getUserDetails().getUsername(), event.getRefreshToken());
//      String payload = event.getRefreshToken();
//      refreshTokenRepository.save(event.getUserDetails() .getUsername(), payload, Boolean.FALSE)
    }
  }

  @Override
  public Publisher<UserDetails> getUserDetails(String refreshToken) {
    return Flowable.create(emitter -> {
      Optional<RefreshedTokenEntity> tokenOpt = null;
//          refreshTokenRepository
//          .findByRefreshToken(refreshToken);
      if (tokenOpt.isPresent()) {
        RefreshedTokenEntity token = tokenOpt.get();
        if (token.getRevoked()) {
          emitter.onError(new RuntimeException());
        } else {
          emitter.onNext(new UserDetails(token.getUsername(), new ArrayList<>()));
          emitter.onComplete();
        }
      } else {
        emitter.onError(new RuntimeException());
      }
    }, BackpressureStrategy.ERROR);

  }
}
