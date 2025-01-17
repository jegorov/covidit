package com.jegorovje.covidit.engine.context;

import com.jegorovje.covidit.engine.EngineConfiguration;
import com.jegorovje.covidit.engine.context.cmd.Command;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@TransactionalAdvice
public class CommandContext {

  private static Deque<CommandContext> stack = new ArrayDeque<>();

  private static final Logger logger = LoggerFactory.getLogger(CommandContext.class);


  @Inject @Getter
  EngineConfiguration engineConfiguration;

  @Inject
  private SessionFactory sessionFactory;

  public static CommandContext getCommandContext() {
    return stack.peek();
  }

  @EventListener
  void postConstruct(ServerStartupEvent event) {
    stack.push(this);
  }

  public <T> void execute(Command<T> command) {
    try {
      Session session = sessionFactory.getCurrentSession();
      command.execute(this);
      session.flush();
    } catch (Exception e) {
      logger.error("Error during execute session", e);
    }
  }
}
