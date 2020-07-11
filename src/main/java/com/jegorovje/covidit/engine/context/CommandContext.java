package com.jegorovje.covidit.engine.context;

import com.jegorovje.covidit.engine.EngineConfiguration;
import com.jegorovje.covidit.engine.context.cmd.Command;
import com.jegorovje.covidit.engine.factory.SqlSessionFactory;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import java.util.Stack;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.Getter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@TransactionalAdvice
public class CommandContext<T> {

  private static Stack<CommandContext> stack = new Stack<>();

  private static final Logger logger = LoggerFactory.getLogger(CommandContext.class);


  @Inject @Getter
  EngineConfiguration engineConfiguration;

  @Inject @Getter
  private SqlSessionFactory sessionFactory;

  public static CommandContext getCommandContext() {
    return stack.peek();
  }

  @EventListener
  void postConstruct(ServerStartupEvent event) {
    stack.push(this);
  }

  public void execute(Command<T> command) {
    try {
      Session session = sessionFactory.getSession();
      command.execute(this);
      session.flush();
    } catch (Exception e) {
      logger.error("Error during execute session", e);
    }
  }
}
