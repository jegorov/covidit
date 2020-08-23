package com.jegorovje.covidit.security.service;

import com.jegorovje.covidit.security.data.entity.RefreshedTokenEntity;
import com.jegorovje.covidit.security.manager.RefreshTokenRepository;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.Optional;
import javax.inject.Singleton;
import org.reactivestreams.Publisher;

@Singleton
public class JwtTokenService implements RefreshTokenPersistence {

  private final RefreshTokenRepository refreshTokenRepository;

  public JwtTokenService(RefreshTokenRepository refreshTokenRepository) {
    this.refreshTokenRepository = refreshTokenRepository;
  }

  @Override
  @EventListener
  public void persistToken(RefreshTokenGeneratedEvent event) {
    if (event != null &&
        event.getRefreshToken() != null &&
        event.getUserDetails() != null &&
        event.getUserDetails().getUsername() != null) {
      String payload = event.getRefreshToken();
      refreshTokenRepository.save(event.getUserDetails() .getUsername(), payload, Boolean.FALSE);
    }
  }

  @Override
  public Publisher<UserDetails> getUserDetails(String refreshToken) {
    return Flowable.create(emitter -> {
      Optional<RefreshedTokenEntity> tokenOpt = refreshTokenRepository.findByRefreshToken(refreshToken);
      if (tokenOpt.isPresent()) {
        RefreshedTokenEntity token = tokenOpt.get();
        if (token.getRevoked()) {
          emitter.onError(new RuntimeException());
//          emitter.onError(new OauthErrorResponseException(IssuingAnAccessTokenErrorCode.INVALID_GRANT, "refresh token revoked", null));
        } else {
          emitter.onNext(new UserDetails(token.getUserId(), new ArrayList<>()));
          emitter.onComplete();
        }
      } else {
        emitter.onError(new RuntimeException());
//        emitter.onError(new OauthErrorResponseException(IssuingAnAccessTokenErrorCode.INVALID_GRANT, "refresh token not found", null));
      }
    }, BackpressureStrategy.ERROR);

  }
}
