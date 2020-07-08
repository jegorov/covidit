package com.jegorovje.covidit.engine;

import com.jegorovje.covidit.engine.manager.CovidStatisticEntityManager;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.Getter;
import lombok.SneakyThrows;
import org.h2.tools.Server;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@Singleton
public class EngineConfiguration {

  @Getter
  private static SessionFactory sessionFactory;

  @Inject
  CovidStatisticEntityManager covidStatisticEntityManager;

  @SneakyThrows
  @EventListener
  public void init(ServerStartupEvent event) {
    Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
  }

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      Configuration configuration = new Configuration();
      configuration.configure();
      sessionFactory = configuration.buildSessionFactory();
    }
    return sessionFactory;
  }


}
