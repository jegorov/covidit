package com.jegorovje.covidit.engine;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jegorovje.covidit.engine.manager.CovidStatisticEntityManager;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
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

  @Getter
  ExecutorService executorService;

  //сделать нужно предконстракт, а это вот постконстракт
  @SneakyThrows
  @EventListener
  public void init(ServerStartupEvent event) {
    Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
    initThreadPool();
  }

  private void initThreadPool() {
    ThreadFactory namedThreadFactory =
        new ThreadFactoryBuilder().setNameFormat("covidit-thread-%d").build();
    this.executorService = Executors.newFixedThreadPool(20, namedThreadFactory);
  }


}
