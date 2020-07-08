package com.jegorovje.covidit.engine.context;

import com.jegorovje.covidit.engine.context.cmd.Command;
import com.jegorovje.covidit.engine.factory.SqlSessionFactory;
import com.jegorovje.covidit.engine.manager.CovidStatisticEntityManager;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.Getter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class CommandContext<T> {

  private Logger logger = LoggerFactory.getLogger(CommandContext.class);


  @Inject @Getter
  CovidStatisticEntityManager covidStatisticEntityManager;

  @Inject
  private SqlSessionFactory sessionFactory;

  public void execute(Command<T> command) {
    try (Session session = sessionFactory.openSession()) {
      sessionFactory.getCurrentSession();
      command.execute(this, session);
      session.flush();
    } catch (Exception e) {
      logger.error("Error during execute session", e);
    }
  }
}
