package com.jegorovje.covidit.engine;

import com.jegorovje.covidit.engine.manager.CovidStatisticEntityManager;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import lombok.Getter;
import lombok.SneakyThrows;
import org.h2.tools.Server;


@Singleton
public class EngineConfiguration {

  @Inject @Getter
  CovidStatisticEntityManager covidStatisticEntityManager;

  @Inject @Getter
  EntityManager entityManager;

  @SneakyThrows
  @EventListener
  public void init(ServerStartupEvent event) {
    Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
  }


}
