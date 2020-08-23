package com.jegorovje.covidit.engine.factory;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Requires;
import java.sql.SQLException;
import javax.inject.Singleton;
import org.h2.tools.Server;

//todo не работает
@Singleton
public class BootstrapFactory {

  @Context
  @Bean(preDestroy = "stop")
  //todo вероятно чтобы работало необхоимо в пропертях объявить h2.console.enabled=true
  @Requires(property = "h2.console.enabled", value = "true")
  public Server h2WebServer() throws SQLException {
    return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
  }
}